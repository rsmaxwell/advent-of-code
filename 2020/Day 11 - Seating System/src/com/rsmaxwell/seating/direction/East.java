package com.rsmaxwell.seating.direction;

import com.rsmaxwell.seating.SeatingSystem;

public class East implements Direction {

	@Override
	public Character look(SeatingSystem part, int row, int col) {

		int index = 1;
		int j = col + index;

		while (j < part.numberOfCols) {

			char ch = part.getPrevious(row, j);
			if (ch == SeatingSystem.EMPTY) {
				return ch;
			} else if (ch == SeatingSystem.OCCUPIED) {
				return ch;
			}

			index++;
			j = col + index;
		}

		return null;
	}

}
