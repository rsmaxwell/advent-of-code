package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveSouth extends Action {

	public MoveSouth(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.position.vert -= value;
	}

}
