package com.rsmaxwell.seating;

import java.io.IOException;

public class AdjacentSeatingSystem extends SeatingSystem {

	protected AdjacentSeatingSystem(String[] args) throws AppException, IOException {
		super(args);
	}

	@Override
	public void applyRulesToCurrent(int row, int col, int occupiedCount, Character ch) {
		if (ch == EMPTY) {
			if (occupiedCount == 0) {
				setCurrent(row, col, OCCUPIED);
			}
		} else if (ch == OCCUPIED) {
			if (occupiedCount >= 4) {
				setCurrent(row, col, EMPTY);
			}
		}
	}

	@Override
	public int countOccupied(int row, int col) {
		int occupiedCount = 0;
		for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, numberOfRows); i++) {
			for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, numberOfCols); j++) {
				if ((i == row) && (j == col)) {
					continue;
				}

				char ch = getPrevious(i, j);
				if (ch == OCCUPIED) {
					occupiedCount++;
				}
			}
		}
		return occupiedCount;
	}
}
