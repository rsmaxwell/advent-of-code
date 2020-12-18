package com.rsmaxwell.shuttle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

	public static void main(String[] args) throws Exception {

		try {
			Part2 part = new Part2();
			part.process(args);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private void process(String[] args) throws Exception {

		List<Bus> busses = readData(args);

		while (busses.size() > 1) {

			Collections.sort(busses);

			Bus one = busses.remove(0);
			Bus two = busses.remove(0);

			System.out.println("number of busses: " + busses.size());
			System.out.println("time: " + one.departureTime);

			busses.add(merge(one, two));
		}

		long time = busses.get(0).departureTime;

		System.out.println(String.format("Time: %d", time));

		busses = readData(args);
		for (Bus bus : busses) {
			check(time, bus);
		}
	}

	private void check(long time, Bus bus) throws Exception {
		long remainder = (time + bus.offset) % bus.id;
		if (remainder != 0) {
			throw new Exception(String.format("Bus: %s, remainder: %d", bus, remainder));
		}
	}

	private Bus merge(Bus one, Bus two) throws Exception {

		long i = 0;
		while (true) {
			long time = one.departureTime + i * one.id;
			long number = two.id - (time % two.id);

			if (number == two.offset) {
				long id = Utils.lcm(one.id, two.id);
				return new Bus(id, 0, time);
			} else {
				i++;
			}
		}
	}

	protected long roundedUp(long number, long id) {
		long N = (number + id - 1) / id;
		return N * id;
	}

	protected List<Bus> readData(String[] args) throws IOException, AppException {
		List<String> lines = Utils.readData(args);
		if (lines.size() != 2) {
			throw new AppException("Unexpected number of lines in the data: " + lines.size());
		}

		String[] words = lines.get(1).split(",");

		int offset = 0;
		List<Bus> busses = new ArrayList<>();
		for (String word : words) {
			try {
				int id = Integer.parseInt(word);
				Bus bus = new Bus(id, offset, 0);
				busses.add(bus);

			} catch (NumberFormatException e) {
			}

			offset++;
		}

		Collections.sort(busses);

		return busses;
	}
}
