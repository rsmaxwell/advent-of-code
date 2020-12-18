package com.rsmaxwell.cubes.cube;

import java.util.Map;

public class Limits3D {

	public int x_min, y_min, z_min, x_max, y_max, z_max;

	public Limits3D(Map<String, Integer> data) {

		x_min = y_min = z_min = Integer.MAX_VALUE;
		x_max = y_max = z_max = Integer.MIN_VALUE;

		for (String key : data.keySet()) {

			String[] keys = key.split(":");
			int x = Integer.parseInt(keys[0]);
			int y = Integer.parseInt(keys[1]);
			int z = Integer.parseInt(keys[2]);

			x_min = Math.min(x_min, x);
			y_min = Math.min(y_min, y);
			z_min = Math.min(z_min, z);

			x_max = Math.max(x_max, x);
			y_max = Math.max(y_max, y);
			z_max = Math.max(z_max, z);
		}
	}
}
