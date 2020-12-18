package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveWaypointWest extends Action {

	public MoveWaypointWest(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.waypoint.horiz -= value;
	}

}
