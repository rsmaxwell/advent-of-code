package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveEast extends Action {

	public MoveEast(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.position.horiz += value;
	}
}
