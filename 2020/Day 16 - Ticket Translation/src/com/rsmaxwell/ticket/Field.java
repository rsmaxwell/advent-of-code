package com.rsmaxwell.ticket;

public class Field {

	public int index;
	public int value;

	@Override
	public String toString() {
		return String.format("index: %d, value: %d", index, value);
	}

}
