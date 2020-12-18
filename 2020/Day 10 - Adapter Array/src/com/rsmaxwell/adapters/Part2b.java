package com.rsmaxwell.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2b {

	private Adapter start;
	private Adapter finish;
	private List<Adapter> adapters;

	public static void main(String[] args) throws Exception {

		try {
			Part2b part = new Part2b(args);
			part.buildNetwork();
			part.dump();
			part.solve();

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private Part2b(String[] args) throws Exception {

		List<String> data = Utils.readData(args);

		adapters = new ArrayList<>();

		int maxRating = Integer.MIN_VALUE;
		for (String string : data) {
			int rating = Integer.parseInt(string);
			maxRating = Math.max(rating, maxRating);
			adapters.add(new Adapter(rating));
		}
		int deviceRating = maxRating + 3;

		start = new Adapter(0);
		finish = new Adapter(deviceRating);

		adapters.add(start);
		adapters.add(finish);

		Collections.sort(adapters);
	}

	private void buildNetwork() {

		for (int i = 0; i < adapters.size(); i++) {
			Adapter adapter = adapters.get(i);

			for (int j = i + 1; j < adapters.size(); j++) {
				Adapter other = adapters.get(j);

				int difference = other.rating - adapter.rating;

				if (difference > 3) {
					break;
				}

				if (difference < 1) {
					continue;
				}

				adapter.after.add(other);
				other.before.add(adapter);
			}
		}
	}

	private void dump() {
		for (Adapter adapter : adapters) {
			System.out.println(adapter.toStringFull());
		}
	}

	private void solve() {
		start.value = 1L;
		finish.calculateValue();
		System.out.println(String.format("Answer: %d", finish.value));
	}
}
