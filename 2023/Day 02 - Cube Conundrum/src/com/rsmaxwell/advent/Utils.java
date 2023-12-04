package com.rsmaxwell.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<String> readData(String filename) throws IOException {

		List<String> list = null;
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String str;
			list = new ArrayList<String>();
			while ((str = in.readLine()) != null) {
				list.add(str);
			}
		}

		return list;
	}
}
