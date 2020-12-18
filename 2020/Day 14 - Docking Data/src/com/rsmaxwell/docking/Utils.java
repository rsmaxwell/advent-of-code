package com.rsmaxwell.docking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	private static List<String> lines;
	private static int index;

	public static String[] loadFirst(String[] args) throws IOException {

		lines = Utils.readData(args);

		index = 0;
		return loadNext();
	}

	public static String[] loadNext() {

		if (index >= lines.size()) {
			return null;
		}

		String separator = "";
		StringBuffer sb = new StringBuffer();
		while (index < lines.size()) {
			String line = lines.get(index++).trim();

			if (line.length() <= 0) {
				break;
			}

			sb.append(separator);
			sb.append(line);
			separator = " ";
		}

		return sb.toString().split("\\s+");
	}

	public static List<String> readData(String[] args) throws IOException {

		if (args.length < 1) {
			System.out.println("Missing data file argument");
			System.exit(1);
		}
		String filename = args[0];

		List<String> list = null;
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String str;
			list = new ArrayList<String>();
			while ((str = in.readLine()) != null) {
				list.add(str);
			}
		}

		return list;
	}

	public static List<Integer> readIntegers(String[] args) throws IOException {

		if (args.length < 1) {
			System.out.println("Missing data file argument");
			System.exit(1);
		}
		String filename = args[0];

		List<Integer> list = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String str;
			while ((str = in.readLine()) != null) {
				int value = Integer.parseInt(str);
				list.add(value);
			}
		}

		return list;
	}

	public static String leftPad(String text, int length, char ch) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() + text.length() < length) {
			sb.append(ch);
		}
		sb.append(text);
		return sb.toString();
	}

	public static String listToString(List<Character> list) {
		StringBuffer sb = new StringBuffer();
		for (Character ch : list) {
			sb.append(ch);
		}
		return sb.toString();
	}
}
