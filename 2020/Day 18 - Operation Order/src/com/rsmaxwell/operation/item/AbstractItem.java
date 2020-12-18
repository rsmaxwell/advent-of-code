package com.rsmaxwell.operation.item;

public abstract class AbstractItem {

	public String string;

	@Override
	public String toString() {
		return String.format("%s", string);
	}

}
