package com.rsmaxwell.boardingpass;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

	public String key;

	public Part2(String string) {
		key = string;
	}

	// Find the largest seatID from a list of BoardingPassIDs
	public static void main(String[] args) throws IOException {

		List<String> list = Utils.readData(args);

		Map<Integer, Integer> seats = new HashMap<>();
		int smallestID = Integer.MAX_VALUE;
		int largestID = Integer.MIN_VALUE;
		for (String string : list) {

			Part2 boardingpass = new Part2(string);
			try {
				int seatID = boardingpass.seatID();

				smallestID = Math.min(smallestID, seatID);
				largestID = Math.max(largestID, seatID);

				seats.put(seatID, seatID);

			} catch (AppException e) {
				e.printStackTrace();
			}
		}
		System.out.println("smallestID: " + smallestID);
		System.out.println("largestID: " + largestID);

		System.out.println("Consecutive SeatIDs:");
		for (int i = smallestID; i < largestID; i++) {

			Integer x = seats.get(i - 1);
			Integer y = seats.get(i);
			Integer z = seats.get(i + 1);

			if (x != null) {
				if (y == null) {
					if (z != null) {
						System.out.println(String.format("    %d", i));
					}
				}
			}
		}
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
