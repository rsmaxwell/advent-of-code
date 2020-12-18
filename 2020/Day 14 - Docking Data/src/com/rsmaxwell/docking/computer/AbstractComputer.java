package com.rsmaxwell.docking.computer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rsmaxwell.docking.instruction.Instruction;

abstract class AbstractComputer implements Computer {

	public static final int WORD_SIZE = 36;

	public Map<Long, Long> addressSpace = new HashMap<>();

	@Override
	public void execute(List<Instruction> program) {

		for (Instruction instruction : program) {
			instruction.execute(this);
		}
	}

	@Override
	public long sumAddressSpace() {

		long total = 0L;
		for (Long address : addressSpace.keySet()) {
			Long value = addressSpace.get(address);
			total += value;
		}

		return total;
	}
}
