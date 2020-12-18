package com.rsmaxwell.seating;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class SeatingSystem {

	public static final char FLOOR = '.';
	public static final char EMPTY = 'L';
	public static final char OCCUPIED = '#';

	public int numberOfCols;
	public int numberOfRows;
	protected char[][] buffer;
	protected char[] current;
	protected char[] previous;
	protected int counter;
	public int debug = 0;

	protected SeatingSystem(String[] args) throws AppException, IOException {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		List<String> data = Utils.readData(args);
		for (String line : data) {
			min = Math.min(min, line.length());
			max = Math.max(max, line.length());
		}

		if (min != max) {
			throw new AppException(String.format("Data has different line lengths: %d to %d", min, max));
		}

		numberOfCols = min;
		numberOfRows = data.size();

		buffer = new char[2][];
		buffer[0] = new char[numberOfCols * data.size()];
		buffer[1] = new char[numberOfCols * data.size()];

		current = buffer[counter % 2];
		previous = buffer[(counter + 1) % 2];

		for (int row = 0; row < numberOfRows; row++) {
			String line = data.get(row);
			for (int col = 0; col < numberOfCols; col++) {
				char value = line.charAt(col);
				setCurrent(row, col, value);
			}
		}
	}

	public int process() {

		toggleBuffers();

		int changes = 0;

		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {

				changes = processOneSeat(changes, row, col);
			}
		}

		return changes;
	}

	public int processOneSeat(int changes, int row, int col) {
		int occupiedCount = countOccupied(row, col);

		Character ch = getPrevious(row, col);
		setCurrent(row, col, ch);

		applyRulesToCurrent(row, col, occupiedCount, ch);

		char ch2 = getCurrent(row, col);
		if (ch != ch2) {
			changes++;
		}
		return changes;
	}

	public void toggleBuffers() {
		counter++;
		current = buffer[counter % 2];
		previous = buffer[(counter + 1) % 2];
	}

	public char getPrevious(int row, int col) {
		int index = row * numberOfCols + col;
		return previous[index];
	}

	public char getCurrent(int row, int col) {
		int index = row * numberOfCols + col;
		return current[index];
	}

	public void setCurrent(int row, int col, char value) {
		int index = row * numberOfCols + col;
		current[index] = value;
	}

	public static String subArray(char[] array, int beg, int length) {
		return new String(Arrays.copyOfRange(array, beg, beg + length));
	}

	protected void output() {

		System.out.println("------------------------------");
		for (int row = 0; row < numberOfRows; row++) {
			String line = subArray(current, row * numberOfCols, numberOfCols);
			System.out.println(line);
		}
	}

	public int numberOfOccupiedSeats() {

		int occupied = 0;
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				char ch = getCurrent(row, col);
				if (ch == OCCUPIED) {
					occupied++;
				}
			}
		}
		return occupied;
	}

	abstract public void applyRulesToCurrent(int row, int col, int occupiedCount, Character ch);

	abstract public int countOccupied(int row, int col);
}
