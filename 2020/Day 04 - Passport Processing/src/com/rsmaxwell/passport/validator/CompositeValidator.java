package com.rsmaxwell.passport.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeValidator extends AbstractValidator {

	private Map<String, Validator> validators = new HashMap<>();

	private static Pattern pattern = Pattern.compile("([\\d]+)([a-zA-Z]{2})");

	public CompositeValidator put(String key, Validator validator) {
		validators.put(key, validator);
		return this;
	}

	@Override
	public boolean validate(String key, String value) {

		Matcher m = pattern.matcher(value);

		if (!m.matches()) {
			return false;
		}

		String key2 = m.group(2);
		String value2 = m.group(1);

		Validator validator = validators.get(key2);
		return (validator == null) ? false : validator.validate(key2, value2);
	}
}
