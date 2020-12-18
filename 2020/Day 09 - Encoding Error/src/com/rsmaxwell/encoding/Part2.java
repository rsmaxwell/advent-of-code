package com.rsmaxwell.encoding;

import java.io.IOException;

public class Part2 extends Part {

	public static void main(String[] args) throws Exception {

		try {
			Part2 part = new Part2(args);

			long invalidNumber = part.findInvalidNumber();
			System.out.println("Invalid number = " + invalidNumber);

			long weakness = part.findWeakness(invalidNumber);
			System.out.println("Weakness = " + weakness);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private Part2(String[] args) throws AppException, IOException {
		super(args);
	}

	private long findWeakness(long invalidNumber) throws AppException {

		for (int i = 0; i < data.size(); i++) {
			long smallest = Long.MAX_VALUE;
			long largest = Long.MIN_VALUE;
			long total = 0;

			for (int j = i; j < data.size(); j++) {
				long number = data.get(j);

				smallest = Math.min(smallest, number);
				largest = Math.max(largest, number);

				total += number;
				if (total == invalidNumber) {
					long weakness = smallest + largest;
					// @formatter:off
					// System.out.println(String.format("Found weakness: smallest: %d largest: %s --> %d", smallest, largest, weakness));
					// @formatter:on
					return weakness;
				} else if (total > invalidNumber) {
					break;
				}
			}
		}

		throw new AppException("Could not find the encryption weakness");
	}
}
