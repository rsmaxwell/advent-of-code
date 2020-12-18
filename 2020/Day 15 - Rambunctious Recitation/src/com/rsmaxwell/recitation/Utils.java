package com.rsmaxwell.recitation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<String> readData(String[] args) throws IOException {

		if (args.length < 1) {
			System.out.println("Missing data file argument");
			System.exit(1);
		}
		String filename = args[0];

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
