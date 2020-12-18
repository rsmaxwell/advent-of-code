package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class TurnRight extends Action {

	public TurnRight(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.bearing = (ship.bearing + value) % 360;
	}

}
