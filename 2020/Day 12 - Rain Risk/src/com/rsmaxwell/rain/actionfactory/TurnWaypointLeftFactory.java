package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.TurnWaypointLeft;

public class TurnWaypointLeftFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new TurnWaypointLeft(value);
	}
}
