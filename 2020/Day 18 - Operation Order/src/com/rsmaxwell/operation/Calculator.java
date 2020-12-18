package com.rsmaxwell.operation;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static Calculator load(String[] args) {

		List<String> lines = new ArrayList<>();
		for (String line : lines) {
			Expression expression = new Expression(line);
			expression.print();
			Integer result = expression.evaluate();
			System.out.println(String.format("result = %d", result));
		}

		return null;
	}

	public void execute() throws AppException {
	}

}
