package com.rsmaxwell.cubes;

import com.rsmaxwell.cubes.cube.Cube;
import com.rsmaxwell.cubes.cube.Cube4D;

public class Part2 {

	public static void main(String[] args) throws Exception {

		try {
			Cube cube = Cube4D.load(args);
			cube.print();
			System.out.println(String.format("Number of active cubes: %d", cube.size()));

			for (int i = 0; i < 6; i++) {
				cube.cycle();
				cube.print();
				System.out.println(String.format("Number of active cubes: %d", cube.size()));
			}

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
