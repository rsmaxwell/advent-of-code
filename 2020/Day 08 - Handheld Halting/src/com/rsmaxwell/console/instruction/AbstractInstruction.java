package com.rsmaxwell.console.instruction;

import com.rsmaxwell.console.InfiniteLoopException;

public abstract class AbstractInstruction implements Instruction {

	protected int value;
	protected int beenHereCount;

	public AbstractInstruction(int value) {
		this.value = value;
	}

	public String format(String code) {

		String sign = (value < 0) ? "-" : "+";
		int number = Math.abs(value);

		return String.format("%s %s%d", code, sign, number);
	}

	@Override
	public void beenHere() throws InfiniteLoopException {

		if (beenHereCount > 0) {
			throw new InfiniteLoopException("Infinite loop detected");
		}

		beenHereCount++;
	}

	@Override
	public void setBeenHere(int value) {
		beenHereCount = 0;
	}

	@Override
	public int getValue() {
		return value;
	}
}
