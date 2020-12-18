package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.TurnWaypointRight;

public class TurnWaypointRightFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new TurnWaypointRight(value);
	}
}
