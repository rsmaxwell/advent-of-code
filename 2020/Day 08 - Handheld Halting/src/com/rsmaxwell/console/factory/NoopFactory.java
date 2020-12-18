package com.rsmaxwell.console.factory;

import com.rsmaxwell.console.instruction.Instruction;
import com.rsmaxwell.console.instruction.Noop;

public class NoopFactory implements InstructionFactory {

	@Override
	public Instruction make(int value) {
		return new Noop(value);
	}

}
