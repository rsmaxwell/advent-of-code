package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveNorth extends Action {

	public MoveNorth(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.position.vert += value;
	}

}
