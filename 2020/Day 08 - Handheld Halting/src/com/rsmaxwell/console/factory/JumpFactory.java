package com.rsmaxwell.console.factory;

import com.rsmaxwell.console.instruction.Instruction;
import com.rsmaxwell.console.instruction.Jump;

public class JumpFactory implements InstructionFactory {

	@Override
	public Instruction make(int value) {
		return new Jump(value);
	}

}
