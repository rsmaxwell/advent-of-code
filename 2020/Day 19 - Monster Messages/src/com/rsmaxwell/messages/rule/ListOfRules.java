package com.rsmaxwell.messages.rule;

import java.util.List;

import com.rsmaxwell.messages.Context;
import com.rsmaxwell.messages.Utils;

public class ListOfRules extends AbstractRule {

	private List<Integer> ids;

	public ListOfRules(List<Integer> listOfIDs) {
		this.ids = listOfIDs;
	}

	@Override
	public boolean increment(int depth) {

		for (int id : ids) {
			IRule rule = RuleFactory.Rules.get(id);
			Utils.debug(String.format("%s(%d): %s", Utils.pad(depth), id, rule));

			boolean carry = rule.increment(depth + 1);
			if (!carry) {
				Utils.debug(String.format("%s%b", Utils.pad(depth), false));
				return false;
			}
		}

		Utils.debug(String.format("%s%b", Utils.pad(depth), true));
		return true;
	}

	@Override
	public boolean matches(int depth, Context context, String message) throws Exception {

		for (Integer id : ids) {
			IRule rule = RuleFactory.Rules.get(id);
			Utils.debug(String.format("%s(%d): %s", Utils.pad(depth), id, rule));

			boolean ok = rule.matches(depth + 1, context, message);

			Utils.debug(String.format("%s%b", Utils.pad(depth), ok));
			if (!ok) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void collectState(List<Integer> list) {
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer("ListOfRules: ");

		String seperator = "";
		for (Integer id : ids) {
			sb.append(String.format("%s%d", seperator, id));
			seperator = " ";
		}

		return sb.toString();
	}
}
