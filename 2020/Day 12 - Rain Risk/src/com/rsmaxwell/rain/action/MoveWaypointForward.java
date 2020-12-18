package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.AppException;
import com.rsmaxwell.rain.Ship;

public class MoveWaypointForward extends Action {

	public MoveWaypointForward(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) throws AppException {
		ship.position.vert += ship.waypoint.vert * value;
		ship.position.horiz += ship.waypoint.horiz * value;
	}
}
