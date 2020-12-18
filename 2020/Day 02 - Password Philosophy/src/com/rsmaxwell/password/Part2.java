package com.rsmaxwell.password;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

	private char letter;
	private int first;
	private int second;
	private String string;

	static private Pattern p = Pattern.compile("(\\d+)-(\\d+)\\s+(.):\\s(.+)");

	public Part2(String text) throws AppException {

		Matcher m = p.matcher(text);
		if (!m.matches()) {
			throw new AppException(String.format("Syntax error. string: %s", text));
		}

		first = Integer.parseInt(m.group(1)) - 1;
		second = Integer.parseInt(m.group(2)) - 1;
		letter = m.group(3).charAt(0);
		string = m.group(4);
	}

	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		int numberOfValidPasswords = 0;
		for (String text : list) {
			Part2 password = null;
			try {
				password = new Part2(text);
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

		boolean test1 = (string.charAt(first) == letter);
		boolean test2 = (string.charAt(second) == letter);

		if (test1) {
			if (!test2) {
				return true;
			}
		} else if (test2) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("%d-%d %c: %s", first, second, letter, string);
	}

}
