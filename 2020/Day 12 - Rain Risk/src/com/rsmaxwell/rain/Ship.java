package com.rsmaxwell.rain;

import com.rsmaxwell.rain.action.Action;

public class Ship {

	public Position position;
	public Position waypoint;
	public int bearing;

	public Ship(int horiz, int vert, int bearing) {
		this.bearing = bearing;
		this.position = new Position(horiz, vert);
	}

	public void move(Action action) throws AppException {
		action.move(this);
	}

	@Override
	public String toString() {
		return String.format("%4d deg %s", bearing, position);
	}

	public String toStringFull() {
		return String.format("%4d deg %s  %s", bearing, position, waypoint);
	}

	public int manhattenDistance() {
		return position.manhattenDistance();
	}
}
