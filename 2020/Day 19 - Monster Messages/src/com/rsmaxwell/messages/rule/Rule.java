package com.rsmaxwell.messages.rule;

import java.util.List;

import com.rsmaxwell.messages.Context;
import com.rsmaxwell.messages.Utils;

public class Rule extends AbstractRule {

	private char ch;

	public Rule(char ch) {
		this.ch = ch;
	}

	@Override
	public boolean increment(int depth) {
		Utils.debug(String.format("%s%b", Utils.pad(depth), true));
		return true;
	}

	@Override
	public boolean matches(int depth, Context context, String message) {

		context.index++;
		if (context.index >= message.length()) {
			Utils.debug(String.format("%scontext.message[%d]: --> %b", Utils.pad(depth), context.index, false));
			return false;
		}

		if (message.charAt(context.index) == ch) {
			Utils.debug(String.format("%scontext.message[%d]: %c --> %b", Utils.pad(depth), context.index, message.charAt(context.index), true));
			return true;
		} else {
			Utils.debug(String.format("%scontext.message[%d]: %c --> %b", Utils.pad(depth), context.index, message.charAt(context.index), false));
		}

		return false;
	}

	@Override
	public void collectState(List<Integer> list) {
	}

	@Override
	public String toString() {
		return String.format("Rule: \"%c\"", ch);
	}

}
