package com.rsmaxwell.tobbogan;

import java.io.IOException;

public class Part2 extends Part {

	public Part2(String[] args) throws AppException, IOException {
		super(args);
	}

	public static void main(String[] args) throws IOException {

		try {
			Part2 toboggan = new Part2(args);
			int n1 = toboggan.checkRoute(1, 1);
			int n2 = toboggan.checkRoute(3, 1);
			int n3 = toboggan.checkRoute(5, 1);
			int n4 = toboggan.checkRoute(7, 1);
			int n5 = toboggan.checkRoute(1, 2);

			int N = n1 * n2 * n3 * n4 * n5;
			System.out.println("Product = " + N);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
