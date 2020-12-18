package com.rsmaxwell.console.factory;

import com.rsmaxwell.console.instruction.Accumulator;
import com.rsmaxwell.console.instruction.Instruction;

public class AccumulatorFactory implements InstructionFactory {

	@Override
	public Instruction make(int value) {
		return new Accumulator(value);
	}

}
