package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.TurnLeft;

public class TurnLeftFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new TurnLeft(value);
	}
}
