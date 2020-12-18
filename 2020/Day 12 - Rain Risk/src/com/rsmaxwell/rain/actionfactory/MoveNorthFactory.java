package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveNorth;

public class MoveNorthFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveNorth(value);
	}
}
