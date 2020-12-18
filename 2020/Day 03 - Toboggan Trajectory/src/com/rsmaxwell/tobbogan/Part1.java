package com.rsmaxwell.tobbogan;

import java.io.IOException;

public class Part1 extends Part {

	public Part1(String[] args) throws AppException, IOException {
		super(args);
	}

	public static void main(String[] args) throws IOException {

		try {
			Part1 toboggan = new Part1(args);
			toboggan.checkRoute(3, 1);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
