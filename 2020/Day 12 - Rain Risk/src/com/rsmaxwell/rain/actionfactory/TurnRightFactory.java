package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.TurnRight;

public class TurnRightFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new TurnRight(value);
	}
}
