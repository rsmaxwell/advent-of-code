package com.rsmaxwell.operation;

import java.util.ArrayList;
import java.util.List;

import com.rsmaxwell.operation.item.Item;

public class Expression {

	List<Item> items = new ArrayList<>();

	public Expression(String line) {

		String[] words = line.split("\\s+");
		for (String word : words) {

		}

	}

	@Override
	public String toString() {
		String separator = "";
		StringBuffer sb = new StringBuffer();
		for (Item item : items) {
			sb.append(separator);
			sb.append(item);
			separator = " ";
		}
		return sb.toString();
	}

	public Integer evaluate() {
		return null;
	}
}
