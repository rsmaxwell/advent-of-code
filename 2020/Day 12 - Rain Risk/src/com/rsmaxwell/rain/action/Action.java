package com.rsmaxwell.rain.action;

import com.rsmaxwell.rain.AppException;
import com.rsmaxwell.rain.Ship;

public abstract class Action {

	protected int value;

	public Action(int value) {
		this.value = value;
	}

	public abstract void move(Ship ship) throws AppException;

	@Override
	public String toString() {
		return String.format("%20s: %d", this.getClass().getSimpleName(), value);
	}

}
