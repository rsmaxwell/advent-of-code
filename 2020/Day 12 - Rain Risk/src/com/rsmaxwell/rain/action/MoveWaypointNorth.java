package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveWaypointNorth extends Action {

	public MoveWaypointNorth(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.waypoint.vert += value;
	}

}
