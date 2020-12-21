package com.rsmaxwell.operation.part1.token;

public abstract class AbstractToken implements Token {

	public long value;
	public int priority;

	public AbstractToken() {
		this.value = 0;
	}

	public AbstractToken(long value) {
		this.value = value;
	}

	public AbstractToken(long value, int priority) {
		this.value = value;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return String.format("value: %d, priority:%d", value, priority);
	}

}
