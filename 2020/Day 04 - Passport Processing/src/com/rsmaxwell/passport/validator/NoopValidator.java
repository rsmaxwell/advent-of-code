package com.rsmaxwell.passport.validator;

public class NoopValidator extends AbstractValidator {

	@Override
	public boolean validate(String key, String value) {
		return true;
	}

}
