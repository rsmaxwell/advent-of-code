package com.rsmaxwell.messages;

import java.util.ArrayList;
import java.util.List;

import com.rsmaxwell.messages.rule.IRule;
import com.rsmaxwell.messages.rule.RuleFactory;

public class Part1 {

	public static void main(String[] args) throws Exception {

		try {
			List<String> messages = new ArrayList<>();

			List<String> lines = Utils.loadFirst(args);
			for (String line : lines) {
				RuleFactory.make(line);
			}

			// for (Integer id : RuleFactory.Rules.keySet()) {
			// IRule rule = RuleFactory.Rules.get(id);
			// System.out.println(String.format("%d: %s", id, rule));
			// }

			// System.out.println("");

			messages = Utils.loadNext();
			if (messages == null) {
				throw new AppException("Missing 'messages' section in data");
			}

			// for (String line : messages) {
			// System.out.println(line);
			// }

			// System.out.println("");

			IRule rule = RuleFactory.Rules.get(0);

			int total = 0;
			int depth = 0;

			for (int index = 0; index < messages.size(); index++) {
				String message = messages.get(index);

				Utils.debug(String.format("message(%d) = %s", index, message));

				Utils.debug(String.format("%s%d: %s", Utils.pad(depth), 0, rule));

				boolean match = false;
				boolean more = true;
				while (more) {
					Utils.debug("---[ match ]------------------------------------------------------------------------");
					Context context = new Context(-1);
					match = rule.matches(depth + 1, context, message);
					System.out.println(String.format("%s%s --> %b", Utils.pad(depth), progress(), match));

					if (match) {
						more = false;
					} else {
						Utils.debug("---[ increment ] ------------------------------------------------------------------------");
						Utils.debug(String.format("%s(%d): %s", Utils.pad(depth), depth, rule));
						boolean carry = rule.increment(depth + 1);
						Utils.debug(String.format("%b", carry));

						if (carry) {
							Utils.debug(String.format("overflow"));
							more = false;
						}
					}
				}

				if (match) {
					total++;
				}
			}
			Utils.debug("---------------------------------------------------------------------------");

			Utils.debug("");
			System.out.println(String.format("total: %d", total));

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	public static String progress() {
		List<Integer> list = new ArrayList<>();
		for (Integer id : RuleFactory.Rules.keySet()) {
			IRule rule = RuleFactory.Rules.get(id);
			rule.collectState(list);
		}
		return list.toString();
	}

}
