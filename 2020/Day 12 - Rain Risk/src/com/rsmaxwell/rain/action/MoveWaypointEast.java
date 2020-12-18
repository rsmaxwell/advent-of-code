package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.Ship;

public class MoveWaypointEast extends Action {

	public MoveWaypointEast(int distance) {
		super(distance);
	}

	@Override
	public void move(Ship ship) {
		ship.waypoint.horiz += value;
	}
}
