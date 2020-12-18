package com.rsmaxwell.seating.direction;

import com.rsmaxwell.seating.SeatingSystem;

public class NorthWest implements Direction {

	@Override
	public Character look(SeatingSystem part, int row, int col) {

		int index = 1;
		int i = row - index;
		int j = col - index;

		while ((i >= 0) && (j >= 0)) {

			char ch = part.getPrevious(i, j);
			if (ch == SeatingSystem.EMPTY) {
				return ch;
			} else if (ch == SeatingSystem.OCCUPIED) {
				return ch;
			}

			index++;
			i = row - index;
			j = col - index;
		}

		return null;
	}

}
