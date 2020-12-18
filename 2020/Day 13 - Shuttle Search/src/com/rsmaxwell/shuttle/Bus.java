package com.rsmaxwell.shuttle;

public class Bus implements Comparable<Bus> {

	public long id;
	public long offset;
	public long departureTime;

	public Bus(long id, long offset, long departureTime) {
		this.id = id;
		this.offset = offset % id;
		this.departureTime = departureTime;
	}

	@Override
	public int compareTo(Bus other) {
		return Long.compare(this.offset, other.offset);
	}

	@Override
	public String toString() {
		return String.format("id: %d, offset:%d, departureTime:%d", id, offset, departureTime);
	}

}
