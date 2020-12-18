package com.rsmaxwell.customs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

	private Map<Character, String> answers = new HashMap<>();
	private List<Person> people = new ArrayList<>();

	public Group(String[] lines) {
		for (String line : lines) {
			Person person = new Person(line);
			people.add(person);
			answers.putAll(person.answers);
		}
	}

	public int count() {

		int total = 0;

		for (char ch : answers.keySet()) {

			boolean unanimous = true;

			for (Person person : people) {
				if (!person.answers.containsKey(ch)) {
					unanimous = false;
				}
			}

			if (unanimous) {
				total++;
			}
		}

		return total;
	}
}
