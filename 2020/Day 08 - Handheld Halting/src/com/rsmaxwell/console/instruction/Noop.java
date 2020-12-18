package com.rsmaxwell.console.instruction;

import com.rsmaxwell.console.Computer;

public class Noop extends AbstractInstruction {

	public Noop(int value) {
		super(value);
	}

	@Override
	public void execute(Computer computer) {
		computer.incrementInstructionPointer(1);
	}

	@Override
	public String toString() {
		return format("nop");
	}

}
