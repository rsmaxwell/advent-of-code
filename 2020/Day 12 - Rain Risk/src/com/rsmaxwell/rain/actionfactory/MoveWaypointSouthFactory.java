package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWaypointSouth;

public class MoveWaypointSouthFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWaypointSouth(value);
	}
}
