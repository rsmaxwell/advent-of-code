package com.rsmaxwell.seating.direction;

import com.rsmaxwell.seating.SeatingSystem;

public class West implements Direction {

	@Override
	public Character look(SeatingSystem part, int row, int col) {

		int index = 1;
		int j = col - index;

		while (j >= 0) {

			char ch = part.getPrevious(row, j);
			if (ch == SeatingSystem.EMPTY) {
				return ch;
			} else if (ch == SeatingSystem.OCCUPIED) {
				return ch;
			}

			index++;
			j = col - index;
		}

		return null;
	}

}
