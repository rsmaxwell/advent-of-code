package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveWaypointSouth extends Action {

	public MoveWaypointSouth(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.waypoint.vert -= value;
	}

}
