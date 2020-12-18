package com.rsmaxwell.ticket;

import java.util.ArrayList;
import java.util.List;

public class Template {

	public String name;
	public List<Range> ranges = new ArrayList<>();
	public Integer fieldIndex;

	public Template(String name) {
		this.name = name;
	}

	public boolean check(int value) {

		for (Range range : ranges) {
			boolean ok = range.check(value);
			if (ok) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("name: '%s', fieldIndex: %s, ranges: %s", name, (fieldIndex == null) ? "(null)" : fieldIndex, Utils.listOfRangesToString(ranges));
	}
}
