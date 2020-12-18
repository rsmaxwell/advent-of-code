package com.rsmaxwell.encoding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part {

	protected int LIMIT;

	protected List<Long> data;
	protected List<Long> ring = new ArrayList<>();

	protected Part(String[] args) throws AppException, IOException {

		LIMIT = 25;
		if (args.length >= 2) {
			LIMIT = Integer.parseInt(args[1]);
		}

		data = Utils.readLongs(args);
		if (data.size() <= LIMIT) {
			throw new AppException("Not enough data: count = " + data.size());
		}

		for (int i = 0; i < LIMIT; i++) {
			long number = data.get(i);
			ring.add(number);
		}
	}

	protected long findInvalidNumber() throws AppException, IOException {

		for (int i = LIMIT; i < data.size(); i++) {
			long number = data.get(i);

			boolean found = check(number);
			if (!found) {
				// @formatter:off
				// System.out.println(String.format("The item at index: %d, value: %d is NOT the sum of two of the previous %d numbers", i, number, LIMIT));
				// @formatter:on
				return number;
			}

			int index = i % LIMIT;
			ring.set(index, number);
		}

		throw new AppException("Did not find invalid number");
	}

	private boolean check(long number) throws AppException, IOException {

		for (int i = 0; i < LIMIT; i++) {
			long one = ring.get(i);

			for (int j = 0; j < LIMIT; j++) {
				long two = ring.get(j);

				if (one != two) {
					if ((one + two) == number) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
