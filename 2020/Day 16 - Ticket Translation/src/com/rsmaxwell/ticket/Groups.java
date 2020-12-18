package com.rsmaxwell.ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Groups {

	private List<String> allLines;
	private int index;

	public List<String> loadFirst(String[] args) throws IOException {

		allLines = Utils.readData(args);

		index = 0;
		return loadNext();
	}

	public List<String> loadNext() {

		List<String> lines = new ArrayList<>();

		if (index >= allLines.size()) {
			return null;
		}

		while (index < allLines.size()) {
			String line = allLines.get(index++).trim();

			if (line.length() <= 0) {
				break;
			}

			lines.add(line);
		}

		return lines;
	}
}
