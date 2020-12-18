package com.rsmaxwell.haversacks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

	private String name;
	private List<Record> records = new ArrayList<>();

	private static Pattern pattern1 = Pattern.compile("(.*) bags contain (.*)\\.");
	private static Pattern pattern2 = Pattern.compile("([\\d]+) (.*) bag[s]?");
	private static Pattern pattern3 = Pattern.compile("no other bags");

	public Rule(String line) throws com.rsmaxwell.haversacks.AppException {

		Matcher m = pattern1.matcher(line);
		if (!m.matches()) {
			throw new AppException(String.format("Syntax error: %s", line));
		}

		name = m.group(1);
		String list = m.group(2);

		String[] items = list.split("\\s*,\\s*");
		for (String item : items) {

			m = pattern2.matcher(item);
			if (!m.matches()) {
				m = pattern3.matcher(item);
				if (!m.matches()) {
					throw new AppException(String.format("Syntax error: %s", line));
				}
				break;
			}

			int number = Integer.parseInt(m.group(1));
			String containsName = m.group(2);
			records.add(new Record(containsName, number));
		}
	}

	public String getName() {
		return name;
	}

	public List<Record> getRecords() {
		return records;
	}
}
