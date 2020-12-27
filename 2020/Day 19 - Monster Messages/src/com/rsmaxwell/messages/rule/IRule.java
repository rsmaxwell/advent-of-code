package com.rsmaxwell.messages.rule;

import java.util.List;

import com.rsmaxwell.messages.Context;

public interface IRule {

	boolean matches(int depth, Context context, String message) throws Exception;

	boolean increment(int depth);

	void collectState(List<Integer> list);

}
