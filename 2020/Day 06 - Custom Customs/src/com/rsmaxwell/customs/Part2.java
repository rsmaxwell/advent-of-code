package com.rsmaxwell.customs;

import java.io.IOException;

public class Part2 {

	public static void main(String[] args) throws IOException {

		Part2 part = new Part2();
		part.process(args);
	}

	private void process(String[] args) throws IOException {

		int total = 0;

		String[] lines = Utils.loadFirst(args);
		while (lines != null) {

			Group group = new Group(lines);
			int count = group.count();
			System.out.println("count: " + count);

			total += count;

			lines = Utils.loadNext();
		}

		System.out.println("Total: " + total);
	}
}
