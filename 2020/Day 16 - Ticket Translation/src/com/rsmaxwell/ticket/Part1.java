package com.rsmaxwell.ticket;

import java.io.IOException;
import java.util.List;

public class Part1 {

	public Templates templates;
	public Ticket myTicket;
	public List<Ticket> nearbyTickets;

	public static void main(String[] args) throws Exception {

		try {
			Part1 part = new Part1(args);
			part.check();
			part.findFieldTypes();
			part.departureFields();

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	public Part1(String[] args) throws AppException, IOException {

		Groups groups = new Groups();

		List<String> group = groups.loadFirst(args);
		templates = new Templates(group);

		group = groups.loadNext();
		Tickets myTicketsSection = new Tickets(group);
		if (myTicketsSection.list.size() != 1) {
			throw new AppException("Unexpected number of 'my ticket' section: " + myTicketsSection.list.size());
		}
		myTicket = myTicketsSection.list.get(0);

		group = groups.loadNext();
		Tickets nearbyTicketsSection = new Tickets(group);
		nearbyTickets = nearbyTicketsSection.list;
	}

	private void check() {

		int ticketScanningErrorRate = 0;

		for (Ticket ticket : nearbyTickets) {
			ticketScanningErrorRate += ticket.check(templates);
		}

		System.out.println(String.format("ticket scanning error rate: %d", ticketScanningErrorRate));
	}

	private void findFieldTypes() throws AppException {

		boolean updated = true;
		boolean more = true;
		while (more) {

			updated = false;

			for (int fieldIndex = 0; fieldIndex < templates.list.size(); fieldIndex++) {
				// System.out.println(String.format("fieldIndex: %d", fieldIndex));

				int numberOfMatchingTemplates = 0;
				Template lastMatchingTemplate = null;

				for (int templateIndex = 0; templateIndex < templates.list.size(); templateIndex++) {
					Template template = templates.list.get(templateIndex);
					if (template.fieldIndex == null) {

						// System.out.println(String.format(" template: %s", template));

						int numberOfFailedChecks = 0;

						for (Ticket ticket : nearbyTickets) {
							// System.out.println(String.format(" ticket: %s", ticket));

							if (ticket.valid) {
								Field field = ticket.fields.get(fieldIndex);
								// System.out.println(String.format(" field: %s", field));

								boolean ok = template.check(field.value);
								// System.out.println(String.format(" check: %b", ok));
								if (!ok) {
									numberOfFailedChecks++;
								}
							}
						}

						if (numberOfFailedChecks == 0) {
							numberOfMatchingTemplates++;
							lastMatchingTemplate = template;
						}
					}
				}

				// If there are no other possible templates for this field
				// System.out.println(String.format(" numberOfMatchingTemplates: %d",
				// numberOfMatchingTemplates));
				if (numberOfMatchingTemplates == 1) {
					lastMatchingTemplate.fieldIndex = fieldIndex;
					System.out.println(String.format("    **** fieldIndex: %d, template: %s", fieldIndex, lastMatchingTemplate));
					updated = true;
				}
			}

			int unresolved = 0;
			for (Template template : templates.list) {
				if (template.fieldIndex == null) {
					unresolved++;
				}
			}

			if (unresolved == 0) {
				more = false;
			}

			if (updated == false) {
				throw new AppException("Did not update any Templates");
			}
		}
	}

	private void departureFields() {

		long result = 1;

		for (Template template : templates.list) {
			if (template.name.contains("departure")) {
				System.out.println(String.format("Template: name: %s, index: %d", template.name, template.fieldIndex));

				Field field = myTicket.fields.get(template.fieldIndex);
				System.out.println(String.format("field: value: %d", field.value));

				result = result * field.value;
			}
		}

		System.out.println(String.format("result = %d", result));
	}
}
