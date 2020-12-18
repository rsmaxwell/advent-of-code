package com.rsmaxwell.ticket;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

	public String name;
	public List<Field> fields = new ArrayList<>();
	public boolean valid;

	public int check(Templates rules) {

		// System.out.println(String.format("---[ Ticket name: %s
		// ]----------------------------", name));

		int ticketScanningErrorRate = 0;
		valid = true;

		for (Field field : fields) {
			boolean fieldOK = false;

			for (Template rule : rules.list) {
				boolean ruleOK = rule.check(field.value);
				if (ruleOK) {
					fieldOK = true;
				}
			}

			if (!fieldOK) {
				ticketScanningErrorRate += field.value;
				valid = false;
				// System.out.println(String.format("Field value: %d is not valid for ANY rule",
				// field.value));
			} else {
				// System.out.println(String.format("Field value: %d is OK", field.value));
			}
		}

		return ticketScanningErrorRate;
	}

	@Override
	public String toString() {
		return String.format("name: '%s'", name);
	}
}
