package com.rsmaxwell.passport;

import com.rsmaxwell.passport.exception.AppException;
import com.rsmaxwell.passport.validator.Validator;

public class Template {

	public String key;
	public String description;
	public Action action;
	public Validator validator;
	public boolean present;

	public Template(String key, String description, Action action, Validator validator) {
		this.key = key;
		this.description = description;
		this.action = action;
		this.validator = validator;
	}

	public boolean validate(String value) throws AppException {
		return validator.validate(key, value);
	}
}
