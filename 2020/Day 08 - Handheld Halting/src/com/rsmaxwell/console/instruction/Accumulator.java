package com.rsmaxwell.console.instruction;

import com.rsmaxwell.console.Computer;

public class Accumulator extends AbstractInstruction {

	public Accumulator(int value) {
		super(value);
	}

	@Override
	public void execute(Computer computer) {
		computer.incrementAccumulator(value);
		computer.incrementInstructionPointer(1);
	}

	@Override
	public String toString() {
		return format("acc");
	}

}
