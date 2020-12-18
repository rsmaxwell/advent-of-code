package com.rsmaxwell.cubes;

import com.rsmaxwell.cubes.cube.Cube;
import com.rsmaxwell.cubes.cube.Cube3D;

public class Part1 {

	public static void main(String[] args) throws Exception {

		try {
			Cube cube = Cube3D.load(args);
			cube.print();

			for (int i = 0; i < 6; i++) {
				cube.cycle();
				cube.print();
			}

			System.out.println(String.format("Number of active cubes: %d", cube.size()));

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
