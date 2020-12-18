package com.rsmaxwell.passport;

import java.io.IOException;

import com.rsmaxwell.passport.exception.AppException;
import com.rsmaxwell.passport.exception.InvalidFieldException;
import com.rsmaxwell.passport.exception.MissingRequiredFieldException;

public class Part2 extends Part {

	private static final String LS = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {

		Part2 part = new Part2();
		part.process(args);
	}

	private void process(String[] args) throws IOException {

		int numberOfValidPassports = 0;
		int numberOfPassportsWithMissingFields = 0;
		int numberOfPassportsWithInvalidData = 0;
		int numberOfOtherErrors = 0;

		int x = 0;
		String[] group = loadFirst(args);
		while (group != null) {

			x = x + 1;
			if (x > 68) {
				System.out.println(String.format("x = %d", x));
				printGroup(group);
			}

			try {
				Passport passport = new Passport(group);
				numberOfValidPassports++;
			} catch (InvalidFieldException e) {
				numberOfPassportsWithInvalidData++;
				System.out.println(e.getMessage() + LS + printGroup(group));
			} catch (MissingRequiredFieldException e) {
				numberOfPassportsWithMissingFields++;
				System.out.println(e.getMessage() + LS + printGroup(group));
			} catch (AppException e) {
				numberOfOtherErrors++;
				System.out.println(e.getMessage() + LS + printGroup(group));
			}

			group = loadNext();
		}

		System.out.println(String.format("Number Of Valid Passports: %d", numberOfValidPassports));
		System.out.println(String.format("Number Of Passports with missing required fields: %d", numberOfPassportsWithMissingFields));
		System.out.println(String.format("Number Of Passports with invalid data: %d", numberOfPassportsWithInvalidData));
		System.out.println(String.format("Number Of other errors: %d", numberOfOtherErrors));
	}

	private String printGroup(String[] group) {
		StringBuffer sb = new StringBuffer();
		for (String item : group) {
			sb.append("    " + item + LS);
		}
		sb.append(LS);
		return sb.toString();
	}
}
