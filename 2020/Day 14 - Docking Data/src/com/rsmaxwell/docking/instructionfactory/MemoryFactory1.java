package com.rsmaxwell.docking.instructionfactory;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.instruction.Instruction;
import com.rsmaxwell.docking.instruction.Memory1;

public class MemoryFactory1 extends AbstractInstructionFactory {

	@Override
	public Instruction make(String line) throws AppException {
		return new Memory1(line);
	}

}
