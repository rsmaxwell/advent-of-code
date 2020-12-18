package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWaypointEast;

public class MoveWaypointEastFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWaypointEast(value);
	}
}
