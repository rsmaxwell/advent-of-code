package com.rsmaxwell.docking.computer;

import java.util.List;

import com.rsmaxwell.docking.instruction.Instruction;

public interface Computer {

	void execute(List<Instruction> program);

	long sumAddressSpace();
}
