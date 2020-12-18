package com.rsmaxwell.seating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rsmaxwell.seating.direction.Direction;
import com.rsmaxwell.seating.direction.East;
import com.rsmaxwell.seating.direction.North;
import com.rsmaxwell.seating.direction.NorthEast;
import com.rsmaxwell.seating.direction.NorthWest;
import com.rsmaxwell.seating.direction.South;
import com.rsmaxwell.seating.direction.SouthEast;
import com.rsmaxwell.seating.direction.SouthWest;
import com.rsmaxwell.seating.direction.West;

public class DirectionSeatingSystem extends SeatingSystem {

	private static List<Direction> directions = new ArrayList<>();

	static {
		directions.add(new North());
		directions.add(new NorthEast());
		directions.add(new East());
		directions.add(new SouthEast());
		directions.add(new South());
		directions.add(new SouthWest());
		directions.add(new West());
		directions.add(new NorthWest());
	}

	protected DirectionSeatingSystem(String[] args) throws AppException, IOException {
		super(args);
	}

	@Override
	public void applyRulesToCurrent(int row, int col, int occupiedCount, Character ch) {
		if (ch == EMPTY) {
			if (occupiedCount == 0) {
				setCurrent(row, col, OCCUPIED);
			}
		} else if (ch == OCCUPIED) {
			if (occupiedCount >= 5) {
				setCurrent(row, col, EMPTY);
			}
		}
	}

	@Override
	public int countOccupied(int row, int col) {
		int occupiedCount = 0;
		for (Direction direction : directions) {
			Character ch = direction.look(this, row, col);
			if ((ch != null) && (ch == OCCUPIED)) {
				occupiedCount++;
			}
		}
		return occupiedCount;
	}
}
