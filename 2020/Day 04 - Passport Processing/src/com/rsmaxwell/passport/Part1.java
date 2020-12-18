package com.rsmaxwell.passport;

import java.io.IOException;

import com.rsmaxwell.passport.exception.AppException;

public class Part1 extends Part {

	public static void main(String[] args) throws IOException {

		Part1 part = new Part1();
		part.process(args);
	}

	private void process(String[] args) throws IOException {

		int numberOfValidPassports = 0;

		String[] group = loadFirst(args);
		while (group != null) {

			try {
				Passport passport = new Passport(group);
				numberOfValidPassports++;
			} catch (AppException e) {
				System.out.println(e.getMessage());
				for (String item : group) {
					System.out.println("    " + item);
				}
				System.out.println("");
			}

			group = loadNext();
		}

		System.out.println(String.format("Number Of Valid Passports: %d", numberOfValidPassports));
	}
}
