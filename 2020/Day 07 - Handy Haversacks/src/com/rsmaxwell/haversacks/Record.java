package com.rsmaxwell.haversacks;

public class Record {

	public String name;
	public int number;

	public Record(String name, int number) {
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return name + "(" + number + ")";
	}
}
