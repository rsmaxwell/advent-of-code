package com.rsmaxwell.seating;

// 2676

public class Part2 {

	public static void main(String[] args) throws Exception {

		try {
			SeatingSystem seatingSystem = new DirectionSeatingSystem(args);

			int changes = 1;
			while (changes > 0) {
				seatingSystem.output();
				changes = seatingSystem.process();
			}

			seatingSystem.output();

			int numberOfOccupiedSeats = seatingSystem.numberOfOccupiedSeats();
			System.out.println("number of occupied seats: " + numberOfOccupiedSeats);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
