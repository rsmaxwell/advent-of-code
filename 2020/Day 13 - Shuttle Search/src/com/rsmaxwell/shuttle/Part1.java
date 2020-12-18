package com.rsmaxwell.shuttle;

import java.util.List;

public class Part1 {

	public static void main(String[] args) throws Exception {

		try {
			List<String> lines = Utils.readData(args);
			if (lines.size() != 2) {
				throw new AppException("Unexpected number of lines in the data: " + lines.size());
			}

			int timestamp = Integer.parseInt(lines.get(0));
			String[] words = lines.get(1).split(",");

			System.out.println(String.format("My arrival time:               %3d", timestamp));

			int minimumWaitTime = Integer.MAX_VALUE;
			int busWithShortestWaitTime = 0;
			for (String word : words) {
				try {
					int bus = Integer.parseInt(word);

					int number = timestamp / bus;

					int busTimestampBefore = number * bus;
					int busTimestampAfter = (number + 1) * bus;
					int waitTime = busTimestampAfter - timestamp;

					if (minimumWaitTime > waitTime) {
						minimumWaitTime = waitTime;
						busWithShortestWaitTime = bus;
					}

					System.out.println(String.format("bus: %3d, busTimestampBefore: %4d, busTimestampAfter: %4d, waitTime: %4d", bus, busTimestampBefore, busTimestampAfter, waitTime));
				} catch (NumberFormatException e) {
				}
			}

			System.out.println(String.format("busWithShortestWaitTime: %3d, minimumWaitTime: %4d", busWithShortestWaitTime, minimumWaitTime));
			System.out.println(String.format("Answer: %3d", busWithShortestWaitTime * minimumWaitTime));

		} catch (

		AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
