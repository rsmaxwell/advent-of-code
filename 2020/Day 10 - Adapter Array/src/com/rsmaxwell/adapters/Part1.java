package com.rsmaxwell.adapters;

import java.io.IOException;

public class Part1 extends Part {

	protected int differencesOfOne;
	protected int differencesOfThree;
	protected String breadcrumbs;

	public static void main(String[] args) throws Exception {

		try {
			Part1 part = new Part1(args);

			part.clear();
			part.process(CHARGING_OUTLET_KEY);

			part.differencesOfThree++;

			System.out.println("number of differences of one   = " + part.differencesOfOne);
			System.out.println("number of differences of three = " + part.differencesOfThree);
			System.out.println("answer = " + part.differencesOfOne * part.differencesOfThree);

			System.out.println(part.breadcrumbs);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	protected void clear() {
		differencesOfOne = 0;
		differencesOfThree = 0;
	}

	private Part1(String[] args) throws AppException, IOException {
		super(args);
	}

	protected boolean process(int previousRating) {

		int count = 0;
		for (int index = 0; index < adapters.length; index++) {
			int rating = adapters[index];
			if (rating < 0) {
				continue;
			}
			count++;

			int difference = rating - previousRating;

			if (difference > 3) {
				break;
			}

			if (difference < 1) {
				continue;
			}

			// We have used this adapter, so we had better hide it
			adapters[index] = -1;

			boolean result = process(rating);
			if (result) {
				System.out.println(String.format("    index:%3d, rating:%3d --> difference: %d", index, rating, difference));

				if (difference == 1) {
					differencesOfOne++;
				} else if (difference == 3) {
					differencesOfThree++;
				}

				breadcrumbs = Integer.toString(rating) + " " + breadcrumbs;

				return true;
			}

			// Make this adapter available again
			adapters[index] = rating;
		}

		if (count == 0) {
			breadcrumbs = "";
			return true;
		}

		return false;
	}
}
