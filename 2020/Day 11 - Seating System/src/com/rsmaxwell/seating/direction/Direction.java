package com.rsmaxwell.seating.direction;

import com.rsmaxwell.seating.SeatingSystem;

public interface Direction {

	Character look(SeatingSystem part, int row, int col);
}
