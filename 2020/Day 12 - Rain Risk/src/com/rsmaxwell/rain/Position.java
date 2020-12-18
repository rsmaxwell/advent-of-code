package com.rsmaxwell.rain;

public class Position {

	public int horiz;
	public int vert;

	public Position(int horiz, int vert) {
		this.horiz = horiz;
		this.vert = vert;
	}

	public int manhattenDistance() {
		return Math.abs(horiz) + Math.abs(vert);
	}

	@Override
	public String toString() {
		return String.format("( %4d, %4d )", horiz, vert);
	}
}
