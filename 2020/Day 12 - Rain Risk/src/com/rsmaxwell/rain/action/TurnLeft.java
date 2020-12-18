package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class TurnLeft extends Action {

	public TurnLeft(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.bearing = (ship.bearing + 360 - value) % 360;
	}

}
