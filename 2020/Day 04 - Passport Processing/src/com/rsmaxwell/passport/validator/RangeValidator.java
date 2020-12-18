package com.rsmaxwell.passport.validator;

public class RangeValidator extends IntegerValidator {

	private int min;
	private int max;

	public RangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean validate(String key, String value) {

		if (!super.validate(key, value)) {
			return false;
		}

		if (integerValue < min) {
			return false;
		}

		if (integerValue > max) {
			return false;
		}

		return true;
	}

}
