package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.AppException;
import com.rsmaxwell.rain.Ship;

public class MoveForward extends Action {

	public MoveForward(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) throws AppException {

		if (ship.bearing == 0) {
			ship.position.vert += value;
		} else if (ship.bearing == 90) {
			ship.position.horiz += value;
		} else if (ship.bearing == 180) {
			ship.position.vert -= value;
		} else if (ship.bearing == 270) {
			ship.position.horiz -= value;
		} else {
			throw new AppException(String.format("Unexpected bearing: %s", ship));
		}
	}

}
