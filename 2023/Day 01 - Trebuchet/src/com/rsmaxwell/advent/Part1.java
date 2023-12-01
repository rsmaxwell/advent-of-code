package com.rsmaxwell.advent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Part1 {

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			System.out.println("Missing data file argument");
			System.exit(1);
		}
		String filename = args[0];

		Part1 part = new Part1();

		int result = part.processData(filename);

		System.out.println(String.format("Result = %d", result));
	}

	public int processData(String filename) throws Exception {

		int result = 0;
		List<String> list = Utils.readData(filename);

		for (int i = 0; i < list.size(); i++) {
			result += processLine(list.get(i));
		}

		return result;
	}

	public int processLine(String line) throws Exception {

		int count = 0;
		char first = '0';
		char last = '0';

		for (char ch : line.toCharArray()) {
			if (!Character.isDigit(ch)) {
				continue;
			}

			if (count == 0) {
				first = ch;
			}

			last = ch;
			count++;
		}

		if (count == 0) {
			throw new Exception("No digits could be found");
		}

		List<Character> str = Arrays.asList(first, last);

		StringBuilder sb = new StringBuilder();
		for (Character ch : str) {
			sb.append(ch);
		}
		String string = sb.toString();

		return Integer.valueOf(string);
	}
}
