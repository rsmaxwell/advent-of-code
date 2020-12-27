package com.rsmaxwell.messages.rule;

import com.rsmaxwell.messages.Context;

public abstract class AbstractRule implements IRule {

	@Override
	public boolean matches(int depth, Context context, String message) throws Exception {
		throw new Exception("Not Supported");
	}
}
