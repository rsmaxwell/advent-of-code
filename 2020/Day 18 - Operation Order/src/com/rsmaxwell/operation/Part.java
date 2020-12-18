package com.rsmaxwell.operation;

public class Part {

	public static void main(String[] args) throws Exception {

		try {
			Calculator calculator = Calculator.load(args);
			calculator.execute();

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
