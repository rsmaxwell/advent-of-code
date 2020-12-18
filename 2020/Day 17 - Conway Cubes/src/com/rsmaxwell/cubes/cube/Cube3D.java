package com.rsmaxwell.cubes.cube;

import java.io.IOException;
import java.util.List;

import com.rsmaxwell.cubes.AppException;
import com.rsmaxwell.cubes.Utils;

public class Cube3D extends AbstractCube {

	private String key(int x, int y, int z) {
		return Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z);
	}

	public Integer put(int x, int y, int z, Integer value) {
		return data.put(key(x, y, z), value);
	}

	public Integer get(int x, int y, int z) {
		return data.get(key(x, y, z));
	}

	public Integer remove(int x, int y, int z) {
		return data.remove(key(x, y, z));
	}

	public static Cube3D load(String[] args) throws IOException, AppException {
		Cube3D cube = new Cube3D();

		List<String> lines = Utils.readData(args);

		int y = 0;
		for (String line : lines) {
			for (int x = 0; x < line.length(); x++) {

				char ch = line.charAt(x);
				if (ch == '#') {
					cube.put(x, y, 0, 123456);
				}
			}
			y++;
		}

		return cube;
	}

	@Override
	public void cycle() {

		counter++;
		Cube3D working = new Cube3D();
		Limits3D limits = new Limits3D(data);

		for (int z = limits.z_min - 1; z <= limits.z_max + 1; z++) {
			for (int y = limits.y_min - 1; y <= limits.y_max + 1; y++) {
				for (int x = limits.x_min - 1; x <= limits.x_max + 1; x++) {
					step(x, y, z, working);
				}
			}
		}

		// Merge the working set with the cube data
		for (String key : working.data.keySet()) {
			Integer value = working.data.get(key);

			if (value > 0) {
				data.put(key, value);
			} else {
				data.remove(key);
			}
		}
	}

	private void step(int x, int y, int z, Cube3D working) {

		Integer value = get(x, y, z);
		int count = 0;

		for (int zz = z - 1; zz <= z + 1; zz++) {
			for (int yy = y - 1; yy <= y + 1; yy++) {
				for (int xx = x - 1; xx <= x + 1; xx++) {

					if ((x != xx) || (y != yy) || (z != zz)) {
						Integer vv = get(xx, yy, zz);
						if (vv != null) {
							count++;
						}
					}
				}
			}
		}

		Integer newValue = 0;
		if (value == null) {
			if (count == 3) {
				newValue = 123456;
			}
		} else if ((count == 2) || (count == 3)) {
			newValue = 123456;
		}
		working.put(x, y, z, newValue);
	}

	@Override
	public void print() {

		printTitle();

		Limits3D limits = new Limits3D(data);

		for (int z = limits.z_min; z <= limits.z_max; z++) {
			System.out.println("");
			System.out.println("z = " + z);

			for (int y = limits.y_min; y <= limits.y_max; y++) {
				StringBuffer sb = new StringBuffer();

				for (int x = limits.x_min; x <= limits.x_max; x++) {
					Integer value = get(x, y, z);
					if (value == null) {
						sb.append('.');
					} else {
						sb.append('#');
					}
				}
				System.out.println(sb.toString());
			}
		}
	}
}
