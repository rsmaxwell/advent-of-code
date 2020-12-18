package com.rsmaxwell.repair;

import java.io.IOException;
import java.util.List;

public class Part2 {

	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		for (int i = 0; i < list.size(); i++) {
			int one = Integer.parseInt(list.get(i));
			for (int j = i + 1; j < list.size(); j++) {
				int two = Integer.parseInt(list.get(j));
				for (int k = j + 1; k < list.size(); k++) {
					int three = Integer.parseInt(list.get(k));
					if (one + two + three == 2020) {
						System.out.println(String.format("one: %d, two: %d , three: %d --> %d", one, two, three, one * two * three));
					}
				}
			}
		}
	}
}
