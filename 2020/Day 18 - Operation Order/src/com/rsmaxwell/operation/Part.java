package com.rsmaxwell.operation;

import java.util.List;

// 3885397323131 wrong: too high

public class Part {

	public static void main(String[] args) throws Exception {

		try {

			long total = 0;

			List<String> lines = Utils.readData(args);
			for (String line : lines) {

				Expression expression = new Expression(line);

				System.out.println("");
				System.out.println(expression);

				long result = expression.evaluate();
				System.out.println(String.format("result = %d", result));

				total += result;

				System.out.println("");
				System.out.println(String.format("total = %d", total));
			}

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
