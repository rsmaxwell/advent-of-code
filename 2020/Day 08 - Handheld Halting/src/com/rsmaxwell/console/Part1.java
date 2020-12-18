package com.rsmaxwell.console;

import java.util.List;

import com.rsmaxwell.console.instruction.Instruction;

public class Part1 extends Part {

	public static void main(String[] args) throws Exception {

		try {
			Computer computer = new Computer();
			List<Instruction> code = computer.load(args);
			computer.execute(code);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}
}
