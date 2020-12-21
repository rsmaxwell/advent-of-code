package com.rsmaxwell.operation.part1;

import java.util.List;

public class Part1 {

	public static void main(String[] args) throws Exception {

		try {

			long total = 0;

			List<String> lines = Utils.readData(args);
			for (String line : lines) {

				Expression1 expression = new Expression1(line);

				System.out.println("");
				System.out.println(expression);

				long result = expression.evaluate();
				System.out.println(String.format("result = %d", result));

				total += result;
			}

			System.out.println("");
			System.out.println(String.format("total = %d", total));

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
