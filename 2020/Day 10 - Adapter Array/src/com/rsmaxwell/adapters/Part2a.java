package com.rsmaxwell.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2a extends Part {

	private int total;

	public static void main(String[] args) throws Exception {

		try {
			Part2a part = new Part2a(args);

			List<Integer> breadcrumbs = new ArrayList<>();
			part.process(CHARGING_OUTLET_KEY, breadcrumbs);
			System.out.println("Total: " + part.total);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private Part2a(String[] args) throws AppException, IOException {
		super(args);
	}

	protected void process(int previousRating, List<Integer> breadcrumbs) {

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
			breadcrumbs.add(rating);

			if (rating + 3 == deviceRating) {
				total++;
				// printBreadCrumbs(breadcrumbs);
			} else {
				process(rating, breadcrumbs);
			}

			// Make this adapter available again
			adapters[index] = rating;
			breadcrumbs.remove(breadcrumbs.size() - 1);
		}

		if (count == 0) {
			return;
		}
	}

	private void printBreadCrumbs(List<Integer> breadcrumbs) {

		String prefix = String.format("(%d), ", CHARGING_OUTLET_KEY);
		String suffix = String.format("(%d)", deviceRating);

		StringBuffer sb = new StringBuffer(prefix);
		for (Integer crumb : breadcrumbs) {
			sb.append(crumb);
			sb.append(", ");
		}
		sb.append(suffix);
		System.out.println(sb.toString());
	}
}
