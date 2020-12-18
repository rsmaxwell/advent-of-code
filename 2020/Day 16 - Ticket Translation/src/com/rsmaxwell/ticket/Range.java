package com.rsmaxwell.ticket;

public class Range {

	public int minimum;
	public int maximum;

	public Range(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public boolean check(int value) {
		if (value > maximum) {
			return false;
		}

		if (value < minimum) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return String.format("%d-%d", minimum, maximum);
	}
}
