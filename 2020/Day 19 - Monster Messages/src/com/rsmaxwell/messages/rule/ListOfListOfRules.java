package com.rsmaxwell.messages.rule;

import java.util.List;

import com.rsmaxwell.messages.Context;
import com.rsmaxwell.messages.Utils;

public class ListOfListOfRules extends AbstractRule {

	public int index;
	private List<ListOfRules> group;

	public ListOfListOfRules(List<ListOfRules> group) {
		this.group = group;
	}

	@Override
	public boolean increment(int depth) {

		ListOfRules rule = group.get(index);
		Utils.debug(String.format("%sindex:%d", Utils.pad(depth), index));

		boolean carry = rule.increment(depth + 1);
		if (!carry) {
			Utils.debug(String.format("%s%b", Utils.pad(depth), false));
			return false;
		}

		index++;
		if (index < group.size()) {
			Utils.debug(String.format("%sindex: %d", Utils.pad(depth), index));
			Utils.debug(String.format("%s%b", Utils.pad(depth), false));
			return false;
		}

		index = 0;
		Utils.debug(String.format("%s%b", Utils.pad(depth), true));
		return true;
	}

	@Override
	public boolean matches(int depth, Context context, String message) throws Exception {

		ListOfRules rule = group.get(index);
		Utils.debug(String.format("%s%s: index:%d", Utils.pad(depth), rule, index));

		boolean ok = rule.matches(depth + 1, context, message);

		Utils.debug(String.format("%s%b", Utils.pad(depth), ok));
		if (ok) {
			return true;
		}

		return false;
	}

	@Override
	public void collectState(List<Integer> list) {
		list.add(index);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer(String.format("ListOfListOfRules[%d]: ", index));

		String seperator = "";
		for (ListOfRules rule : group) {
			sb.append(String.format("%s%s", seperator, rule.toString()));
			seperator = " | ";
		}

		return sb.toString();
	}
}
