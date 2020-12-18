package com.rsmaxwell.customs;

import java.util.HashMap;
import java.util.Map;

public class Person {

	protected Map<Character, String> answers = new HashMap<>();

	public Person(String item) {
		for (char ch : item.toCharArray()) {
			answers.put(ch, item);
		}
	}
}
