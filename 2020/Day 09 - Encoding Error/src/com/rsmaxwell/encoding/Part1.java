package com.rsmaxwell.encoding;

import java.io.IOException;

public class Part1 extends Part {

	public static void main(String[] args) throws Exception {

		try {
			Part1 part = new Part1(args);
			long invalidNumber = part.findInvalidNumber();

			System.out.println("Invalid number = " + invalidNumber);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private Part1(String[] args) throws AppException, IOException {
		super(args);
	}
}
