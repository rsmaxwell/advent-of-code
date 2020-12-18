package com.rsmaxwell.console.instruction;

import com.rsmaxwell.console.Computer;

public class Jump extends AbstractInstruction {

	public Jump(int value) {
		super(value);
	}

	@Override
	public void execute(Computer computer) {
		computer.incrementInstructionPointer(value);
	}

	@Override
	public String toString() {
		return format("jmp");
	}

}
