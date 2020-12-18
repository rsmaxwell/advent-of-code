package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWaypointForward;

public class MoveWaypointForwardFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWaypointForward(value);
	}
}
