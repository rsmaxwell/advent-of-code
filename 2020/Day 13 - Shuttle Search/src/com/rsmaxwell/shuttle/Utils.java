package com.rsmaxwell.shuttle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	public static String formatInterval(final long l) {
		final long hr = TimeUnit.MILLISECONDS.toHours(l);
		final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
		final long ms = TimeUnit.MILLISECONDS.toMillis(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
		return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
	}

	public static long lcm(long number1, long number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		long absNumber1 = Math.abs(number1);
		long absNumber2 = Math.abs(number2);
		long absHigherNumber = Math.max(absNumber1, absNumber2);
		long absLowerNumber = Math.min(absNumber1, absNumber2);
		long lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}
}
