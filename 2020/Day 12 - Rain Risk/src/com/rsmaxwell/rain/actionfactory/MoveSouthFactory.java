package com.rsmaxwell.rain.actionfactory;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.action.MoveSouth;

public class MoveSouthFactory extends ActionFactory {

	@Override
	public Action create(int value) {
		return new MoveSouth(value);
	}
}
