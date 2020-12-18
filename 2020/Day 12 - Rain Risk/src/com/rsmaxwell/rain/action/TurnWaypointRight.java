package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.AppException;
import com.rsmaxwell.rain.Ship;

public class TurnWaypointRight extends Action {

	public TurnWaypointRight(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) throws AppException {

		int horiz = ship.waypoint.horiz;
		int vert = ship.waypoint.vert;

		if (value == 0) {

		} else if (value == 90) {
			ship.waypoint.horiz = vert;
			ship.waypoint.vert = -horiz;
		} else if (value == 180) {
			ship.waypoint.horiz = -horiz;
			ship.waypoint.vert = -vert;
		} else if (value == 270) {
			ship.waypoint.horiz = -vert;
			ship.waypoint.vert = horiz;
		} else {
			throw new AppException(String.format("Unexpected value: %s", value));
		}
	}

}
