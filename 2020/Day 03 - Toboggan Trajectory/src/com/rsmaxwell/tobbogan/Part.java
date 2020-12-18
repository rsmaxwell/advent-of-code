package com.rsmaxwell.tobbogan;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part {

	public List<String> list;
	private int width;

	private static Pattern p = Pattern.compile("[\\.#]+");

	public Part(String[] args) throws AppException, IOException {

		list = Utils.readData(args);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (String row : list) {
			min = Math.min(min, row.length());
			max = Math.max(max, row.length());

			Matcher m = p.matcher(row);
			if (!m.matches()) {
				throw new AppException(String.format("Inconsistent map. row: %s", row));
			}
		}

		if (min != max) {
			throw new AppException(String.format("Inconsistent map. min: %d, max: %d", min, max));
		}

		width = min;
	}

	public boolean isTree(int row, int column) {
		int col = column % width;
		return list.get(row).charAt(col) == '#';
	}

	public int checkRoute(int right, int down) throws AppException {

		StringBuffer sb = new StringBuffer();
		int numberOfTrees = 0;
		int j = 0;
		for (int i = 0; i < list.size(); i += down, j += right) {
			boolean b = isTree(i, j);
			if (b) {
				numberOfTrees++;
				sb.append("X");
			} else {
				sb.append("O");
			}
		}

		System.out.println(String.format("right: %d, down: %d, number of trees : %d, pattern: %s", right, down, numberOfTrees, sb.toString()));
		return numberOfTrees;
	}
}
