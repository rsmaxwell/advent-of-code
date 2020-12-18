package com.rsmaxwell.seating;

// 2676

public class Part2a {

	public static void main(String[] args) throws Exception {

		try {
			String[] args2 = { "inputData/test2a.txt" };
			DirectionSeatingSystem seatingSystem = new DirectionSeatingSystem(args2);

			int row = 4;
			int col = 3;

			seatingSystem.output();

			seatingSystem.toggleBuffers();

			int occupiedCount = seatingSystem.countOccupied(row, col);

			if (occupiedCount == 8) {
				System.out.println("Success");
			} else {
				throw new AppException(String.format("Unexpected occupiedCount: %d", occupiedCount));
			}

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
