package com.rsmaxwell.operation.part1.stack;

public interface Stack<T> {

	void push(T number);

	T pop();

	T peek();

	boolean isEmpty();
}
