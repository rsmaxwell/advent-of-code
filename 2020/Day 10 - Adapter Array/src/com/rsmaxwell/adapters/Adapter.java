package com.rsmaxwell.adapters;

import java.util.ArrayList;
import java.util.List;

public class Adapter implements Comparable<Adapter> {

	public int rating;
	public List<Adapter> before = new ArrayList<>();
	public List<Adapter> after = new ArrayList<>();
	public Long value;

	private static final String LS = System.getProperty("line.separator");

	public Adapter(int rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(Adapter other) {
		return Integer.compare(rating, other.rating);
	}

	@Override
	public String toString() {
		return String.format("%s", rating);
	}

	public String toStringFull() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("rating: %d", rating) + LS);
		sb.append("before: " + printArray(before) + LS);
		sb.append("after:  " + printArray(after) + LS);
		return sb.toString();
	}

	private static String printArray(List<Adapter> list) {
		String seperator = "";
		StringBuffer sb = new StringBuffer("[");
		for (Adapter adapter : list) {
			sb.append(seperator);
			sb.append(adapter);
			seperator = " ";
		}
		sb.append("]");
		return sb.toString();
	}

	public void calculateValue() {

		long total = 0;
		for (Adapter previous : before) {
			if (previous.value == null) {
				previous.calculateValue();
			}
			total += previous.value;
		}
		value = total;
	}
}
