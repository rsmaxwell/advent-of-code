package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveWest;

public class MoveWestFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveWest(value);
	}
}
