package com.rsmaxwell.boardingpass;

import java.io.IOException;
import java.util.List;

public class Part1 {

	public String key;

	public Part1(String string) {
		key = string;
	}

	// Find the largest seatID from a list of BoardingPassIDs
	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		int largestID = 0;
		for (String string : list) {

			Part1 boardingpass = new Part1(string);
			try {
				int seatID = boardingpass.seatID();
				largestID = Math.max(largestID, seatID);

			} catch (AppException e) {
				e.printStackTrace();
			}
		}
		System.out.println("largestID: " + largestID);
	}

	// Calculate the SeatID for a BoardingPass
	private int seatID() throws AppException {

		if (key.length() != 10) {
			throw new AppException(String.format("Unexpected BoardingPass length: was: %d, expected %d", key.length(), 10));
		}

		int row = calculate('B', 'F', 0, 7);
		int column = calculate('R', 'L', 7, 10);

		int seatID = row * 8 + column;
		System.out.println("BoardingPass ID: " + key + " --> seat ID: " + seatID);

		return seatID;
	}

	// Interpret a string as containing a binary number, and return the number
	private int calculate(char one, char zero, int start, int end) throws AppException {

		int number = 0;

		for (int i = start; i < end; i++) {
			char ch = key.charAt(i);

			if (ch == one) {
				int j = end - 1 - i;
				int digit = 1 << j;
				number += digit;
			} else if (ch == zero) {

			} else {
				throw new AppException(String.format("Unexpected BoardingPass ID[%d]: was: %s, expected one of: %s", i, ch, one + zero));
			}
		}

		return number;
	}
}
