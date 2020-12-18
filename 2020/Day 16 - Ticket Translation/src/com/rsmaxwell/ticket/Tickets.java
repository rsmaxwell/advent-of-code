package com.rsmaxwell.ticket;

import java.util.ArrayList;
import java.util.List;

public class Tickets {

	public String name;
	public List<Ticket> list = new ArrayList<>();

	public Tickets(List<String> group) throws AppException {

		if (group.size() < 2) {
			throw new AppException("Unexpected ticket section: number of lines: " + group.size());
		}

		name = group.get(0).trim();

		for (int i = 1; i < group.size(); i++) {

			String data = group.get(i);
			Ticket ticket = new Ticket();
			ticket.name = data;
			list.add(ticket);

			String[] values = data.split(",");

			for (int j = 0; j < values.length; j++) {
				String value = values[j];
				Field field = new Field();
				field.index = j;
				field.value = Integer.parseInt(value);
				ticket.fields.add(field);
			}
		}
	}

}
