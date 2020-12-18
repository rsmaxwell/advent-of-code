package com.rsmaxwell.console.factory;

import com.rsmaxwell.console.instruction.Instruction;

public interface InstructionFactory {

	public Instruction make(int value);

}
