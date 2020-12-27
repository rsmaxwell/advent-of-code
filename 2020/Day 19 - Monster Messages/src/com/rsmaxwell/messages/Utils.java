package com.rsmaxwell.messages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	private static List<String> lines;
	private static int index;

	public static List<String> loadFirst(String[] args) throws IOException {

		lines = Utils.readData(args);

		index = 0;
		return loadNext();
	}

	public static List<String> loadNext() {

		if (index >= lines.size()) {
			return null;
		}

		List<String> group = new ArrayList<>();

		while (index < lines.size()) {
			String line = lines.get(index++).trim();

			if (line.length() <= 0) {
				break;
			}

			group.add(line);
		}

		return group;
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

	public static String pad(int n) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append("    ");
		}

		return sb.toString();
	}

	public static void debug(String string) {
		// System.out.println(string);
	}
}
