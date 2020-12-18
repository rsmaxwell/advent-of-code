package com.rsmaxwell.passport.validator;

public class IntegerValidator extends AbstractValidator {

	protected int integerValue;

	@Override
	public boolean validate(String key, String value) {

		try {
			integerValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

}
