package com.rsmaxwell.passport;

import java.io.IOException;
import java.util.List;

abstract public class Part {

	private List<String> lines;
	private int index;

	public String[] loadFirst(String[] args) throws IOException {

		lines = Utils.readData(args);

		index = 0;
		return loadNext();
	}

	public String[] loadNext() {

		if (index >= lines.size()) {
			return null;
		}

		String separator = "";
		StringBuffer sb = new StringBuffer();
		while (index < lines.size()) {
			String line = lines.get(index++).trim();

			if (line.length() <= 0) {
				break;
			}

			sb.append(separator);
			sb.append(line);
			separator = " ";
		}

		return sb.toString().split("\\s+");
	}
}
