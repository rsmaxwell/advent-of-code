package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWaypointWest;

public class MoveWaypointWestFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWaypointWest(value);
	}
}
