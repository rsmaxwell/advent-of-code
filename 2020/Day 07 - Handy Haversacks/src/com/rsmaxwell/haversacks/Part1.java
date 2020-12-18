package com.rsmaxwell.haversacks;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

	private Map<String, Rule> rules = new HashMap<>();

	public static void main(String[] args) throws IOException {

		try {
			Part1 part = new Part1();
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

		int numberOfRules = 0;

		String target = "shiny gold";
		for (String name : rules.keySet()) {
			if (!name.equals(target)) {
				Rule rule = rules.get(name);
				int count = count(rule, target);

				if (count > 0) {
					numberOfRules++;
					System.out.println(String.format("    %-15s     count: %d", rule.getName(), count));
				} else {
					System.out.println(String.format("    %-15s", rule.getName()));
				}
			}
		}

		System.out.println("numberOfRules: " + numberOfRules);
	}

	public int count(Rule rule, String target) throws AppException {

		int total = 0;

		for (Record record : rule.getRecords()) {
			if (record.name.equals(target)) {
				total += record.number;
			} else {
				Rule rule2 = rules.get(record.name);

				if (rule2 == null) {
					throw new AppException(String.format("Could not find the rule: '%s'"));
				}

				total += count(rule2, target);
			}
		}

		return total;
	}
}
