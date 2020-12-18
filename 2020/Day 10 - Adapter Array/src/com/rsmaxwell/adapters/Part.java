package com.rsmaxwell.adapters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Part {

	public static int CHARGING_OUTLET_KEY = 0;
	public static int CHARGING_OUTLET_RATING = 0;

	protected int[] adapters;
	protected int deviceRating;

	protected Part(String[] args) throws AppException, IOException {
		List<Integer> data = Utils.readIntegers(args);

		adapters = new int[data.size()];

		for (int key = 0; key < data.size(); key++) {
			adapters[key] = data.get(key);
		}

		Arrays.sort(adapters);

		int largest = Integer.MIN_VALUE;
		for (int rating : adapters) {
			largest = Math.max(largest, rating);
		}

		deviceRating = largest + 3;
		System.out.println(String.format("Device Rating: %d", deviceRating));
	}
}
