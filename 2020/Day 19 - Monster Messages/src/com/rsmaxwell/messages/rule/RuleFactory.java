package com.rsmaxwell.messages.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.messages.AppException;

public class RuleFactory {

	private static Pattern pattern = Pattern.compile("\\\"([^\\\"])\\\"");

	public static Map<Integer, IRule> Rules = new HashMap<>();

	public static void make(String text) throws AppException {

		String[] words1 = text.split(":");
		if (words1.length != 2) {
			throw new AppException("Unexpected input: " + text);
		}

		int id = Integer.parseInt(words1[0]);
		String list = words1[1];

		Matcher m = pattern.matcher(list.trim());
		if (m.matches()) {

			char ch = m.group(1).charAt(0);
			IRule rule = new Rule(ch);
			Rules.put(id, rule);

		} else {

			List<ListOfRules> listOfListOfRules = new ArrayList<>();

			String[] words2 = list.trim().split("\\|");
			for (int i = 0; i < words2.length; i++) {
				String list2 = words2[i];

				List<Integer> listOfIDs = new ArrayList<>();

				String[] words3 = list2.trim().split("\\s+");
				for (int j = 0; j < words3.length; j++) {
					int id2 = Integer.parseInt(words3[j]);
					listOfIDs.add(id2);
				}

				ListOfRules subrule = new ListOfRules(listOfIDs);
				listOfListOfRules.add(subrule);
			}

			IRule rule = new ListOfListOfRules(listOfListOfRules);
			if (listOfListOfRules.size() == 1) {
				rule = listOfListOfRules.get(0);
			}
			Rules.put(id, rule);
		}
	}
}
