package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveForward;

public class MoveForwardFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveForward(value);
	}
}
