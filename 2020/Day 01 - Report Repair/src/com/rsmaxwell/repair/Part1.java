package com.rsmaxwell.repair;

import java.io.IOException;
import java.util.List;

public class Part1 {

	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		for (int i = 0; i < list.size(); i++) {
			int one = Integer.parseInt(list.get(i));
			for (int j = i + 1; j < list.size(); j++) {
				int two = Integer.parseInt(list.get(j));
				if (one + two == 2020) {
					System.out.println(String.format("one: %d, two: %d --> %d", one, two, one * two));
				}
			}
		}
	}
}
