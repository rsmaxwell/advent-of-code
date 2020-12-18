package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveWest extends Action {

	public MoveWest(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.position.horiz -= value;
	}

}
