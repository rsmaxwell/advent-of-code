package com.rsmaxwell.password;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	private char letter;
	private int min;
	private int max;
	private String string;

	static private Pattern p = Pattern.compile("(\\d+)-(\\d+)\\s+(.):\\s(.+)");

	public Part1(String text) throws AppException {

		Matcher m = p.matcher(text);
		if (!m.matches()) {
			throw new AppException(String.format("Syntax error. string: %s", text));
		}

		min = Integer.parseInt(m.group(1));
		max = Integer.parseInt(m.group(2));
		letter = m.group(3).charAt(0);
		string = m.group(4);
	}

	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		int numberOfValidPasswords = 0;
		for (String text : list) {
			Part1 password = null;
			try {
				password = new Part1(text);
			} catch (AppException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}

			boolean valid = password.check();
			System.out.println(String.format("%s --> %s", password, valid));
			if (valid) {
				numberOfValidPasswords++;
			}
		}

		System.out.println(String.format("Number of valid passwords = %d", numberOfValidPasswords));
	}

	private boolean check() {
		long count = string.chars().filter(ch -> ch == letter).count();

		if (count < min) {
			return false;
		} else if (count > max) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return String.format("%d-%d %c: %s", min, max, letter, string);
	}

}
