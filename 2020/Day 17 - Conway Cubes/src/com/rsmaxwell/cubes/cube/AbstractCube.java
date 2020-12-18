package com.rsmaxwell.cubes.cube;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractCube implements Cube {

	public int counter = 0;
	public Map<String, Integer> data = new HashMap<>();

	public void printTitle() {
		System.out.println("");
		System.out.println("-------------------------------------------");
		if (counter == 0) {
			System.out.println(String.format("Before any cycles:"));
		} else if (counter == 1) {
			System.out.println(String.format("After %d cycle", counter));
		} else {
			System.out.println(String.format("After %d cycles", counter));
		}
	}

	@Override
	public int size() {
		return data.size();
	}
}
