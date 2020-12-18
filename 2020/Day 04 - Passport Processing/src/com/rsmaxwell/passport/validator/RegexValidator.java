package com.rsmaxwell.passport.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator extends AbstractValidator {

	private Pattern pattern;

	public RegexValidator(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	@Override
	public boolean validate(String key, String value) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
