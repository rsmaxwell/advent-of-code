package com.rsmaxwell.docking.instructionfactory;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.instruction.Instruction;

public interface InstructionFactory {

	Instruction make(String line) throws AppException;

}
