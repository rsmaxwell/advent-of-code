package com.rsmaxwell.docking.instructionfactory;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.instruction.Instruction;
import com.rsmaxwell.docking.instruction.Mask1;

public class MaskFactory1 extends AbstractInstructionFactory {

	@Override
	public Instruction make(String line) throws AppException {
		return new Mask1(line);
	}

}
