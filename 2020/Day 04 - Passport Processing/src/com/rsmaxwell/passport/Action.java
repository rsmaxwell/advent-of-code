package com.rsmaxwell.passport;

import com.rsmaxwell.passport.exception.AppException;

public interface Action {

	void perform(Template template, Field field) throws AppException;

}
