package com.rsmaxwell.cubes.cube;

import java.io.IOException;
import java.util.List;

import com.rsmaxwell.cubes.AppException;
import com.rsmaxwell.cubes.Utils;

public class Cube4D extends AbstractCube {

	private String key(int x, int y, int z, int w) {
		return Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z) + ":" + Integer.toString(w);
	}

	public Integer put(int x, int y, int z, int w, Integer value) {
		return data.put(key(x, y, z, w), value);
	}

	public Integer get(int x, int y, int z, int w) {
		return data.get(key(x, y, z, w));
	}

	public Integer remove(int x, int y, int z, int w) {
		return data.remove(key(x, y, z, w));
	}

	public static Cube load(String[] args) throws IOException, AppException {
		Cube4D cube = new Cube4D();

		List<String> lines = Utils.readData(args);

		int y = 0;
		for (String line : lines) {
			for (int x = 0; x < line.length(); x++) {

				char ch = line.charAt(x);
				if (ch == '#') {
					cube.put(x, y, 0, 0, 123456);
				}
			}
			y++;
		}

		return cube;
	}

	@Override
	public void cycle() {

		counter++;
		Cube4D working = new Cube4D();
		Limits4D limits = new Limits4D(data);

		for (int z = limits.z_min - 1; z <= limits.z_max + 1; z++) {
			for (int y = limits.y_min - 1; y <= limits.y_max + 1; y++) {
				for (int x = limits.x_min - 1; x <= limits.x_max + 1; x++) {
					for (int w = limits.w_min - 1; w <= limits.w_max + 1; w++) {
						step(x, y, z, w, working);
					}
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

	private void step(int x, int y, int z, int w, Cube4D working) {

		Integer value = get(x, y, z, w);
		int count = 0;

		for (int ww = w - 1; ww <= w + 1; ww++) {
			for (int zz = z - 1; zz <= z + 1; zz++) {
				for (int yy = y - 1; yy <= y + 1; yy++) {
					for (int xx = x - 1; xx <= x + 1; xx++) {

						if ((x != xx) || (y != yy) || (z != zz) || (w != ww)) {
							Integer vv = get(xx, yy, zz, ww);
							if (vv != null) {
								count++;
							}
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
		working.put(x, y, z, w, newValue);
	}

	@Override
	public void print() {

		printTitle();

		Limits4D limits = new Limits4D(data);

		for (int w = limits.w_min; w <= limits.w_max; w++) {
			for (int z = limits.z_min; z <= limits.z_max; z++) {
				System.out.println("");
				System.out.println(String.format("z = %d, w = %d", z, w));

				for (int y = limits.y_min; y <= limits.y_max; y++) {
					StringBuffer sb = new StringBuffer();

					for (int x = limits.x_min; x <= limits.x_max; x++) {
						Integer value = get(x, y, z, w);
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
}
