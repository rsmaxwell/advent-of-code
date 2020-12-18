package com.rsmaxwell.recitation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private Map<Long, Long> numbers = new HashMap<>();
	private int index;

	public void play(String[] args, long limit) throws IOException {

		long index = 0;
		long number = 0;
		long mostRecentNumber = 0;
		List<String> lines = Utils.readData(args);

		for (String line : lines) {
			System.out.println(String.format("%s", line));

			String[] words = line.split(",");

			for (String word : words) {
				number = Integer.parseInt(word);

				index++;
				mostRecentNumber = initial(index, mostRecentNumber, number);
			}
		}

		while (index < limit) {
			index++;
			number = turn(index, number);
		}

		// System.out.println("--------------------------------");
		System.out.println(String.format("%d %d", index, number));
	}

	private long initial(long index, long mostRecentNumber, long spoken) {

		if (index > 1) {
			numbers.put(mostRecentNumber, index);
		}

		// System.out.println(String.format("%d %d", index, spoken));

		return spoken;
	}

	private long turn(long index, long mostRecentNumber) {

		long spoken = 0;
		Long previousIndex = numbers.put(mostRecentNumber, index);

		if (previousIndex == null) {
			spoken = 0;
		} else {
			spoken = index - previousIndex;
		}

		// System.out.println(String.format("%d %d", index, spoken));
		return spoken;
	}
}
