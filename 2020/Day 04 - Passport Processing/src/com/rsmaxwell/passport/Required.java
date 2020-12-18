package com.rsmaxwell.passport;

import com.rsmaxwell.passport.exception.AppException;
import com.rsmaxwell.passport.exception.MissingRequiredFieldException;

public class Required implements Action {

	@Override
	public void perform(Template template, Field field) throws AppException {
		if (field == null) {
			throw new MissingRequiredFieldException(String.format("Missing required field: '%s': %s", template.key, template.description));
		}
	}
}
