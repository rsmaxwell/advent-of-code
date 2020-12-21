package com.rsmaxwell.operation.part1.stack;

import java.util.ArrayList;
import java.util.List;

public class ListStack<T> implements Stack<T> {

	private List<Object> list = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		return (T) list.remove(0);
	}

	@Override
	public void push(T value) {
		list.add(0, value);

	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		return (T) list.get(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		String seperator = "";
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			sb.append(seperator);
			sb.append(o);
			seperator = " ";
		}

		return sb.toString();
	}

}
