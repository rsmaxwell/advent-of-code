package com.rsmaxwell.operation.stack;

public interface Stack<T> {

	void push(T number);

	T pop();

	T peek();

	boolean isEmpty();
}
