package com.rsmaxwell.haversacks;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

	private Map<String, Rule> rules = new HashMap<>();

	public static void main(String[] args) throws IOException {

		try {
			Part2 part = new Part2();
			part.process(args);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private void process(String[] args) throws IOException, AppException {

		List<String> lines = Utils.readData(args);
		for (String line : lines) {
			Rule rule = new Rule(line);
			rules.put(rule.getName(), rule);
		}

		String target = "shiny gold";
		Rule rule = rules.get(target);
		int count = countContents(rule, target);

		System.out.println("");
		System.out.println(String.format("Overall result: %-15s     count: %d", rule.getName(), count));
	}

	public int countContents(Rule rule, String trace) throws AppException {

		int count = 0;

		for (Record record : rule.getRecords()) {

			Rule rule2 = rules.get(record.name);
			if (rule2 == null) {
				throw new AppException(String.format("Could not find the rule: '%s'"));
			}

			String trace2 = String.format("%s -> %s", trace, record);
			count += record.number * (1 + countContents(rule2, trace2));
		}

		System.out.println(String.format("%-60s    count: %2d", trace, count));
		return count;
	}

	private String pad(int n) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
}
