package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWaypointNorth;

public class MoveWaypointNorthFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWaypointNorth(value);
	}
}
