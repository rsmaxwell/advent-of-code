package com.rsmaxwell.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.console.factory.AccumulatorFactory;
import com.rsmaxwell.console.factory.InstructionFactory;
import com.rsmaxwell.console.factory.JumpFactory;
import com.rsmaxwell.console.factory.NoopFactory;
import com.rsmaxwell.console.instruction.Instruction;
import com.rsmaxwell.console.instruction.Jump;
import com.rsmaxwell.console.instruction.Noop;

public class Computer {

	private int accumulator;
	private int instructionPointer;

	private static Map<String, InstructionFactory> factories = new HashMap<>();
	private static Pattern p = Pattern.compile("([a-z]{3}) (\\+|\\-)([\\d]+)");

	static {
		factories.put("nop", new NoopFactory());
		factories.put("acc", new AccumulatorFactory());
		factories.put("jmp", new JumpFactory());
	}

	public List<Instruction> load(String[] args) throws IOException, AppException {

		List<Instruction> code = new ArrayList<>();

		List<String> lines = Utils.readData(args);
		for (String line : lines) {

			Matcher m = p.matcher(line);
			if (!m.matches()) {
				throw new AppException("Syntax error: line: " + line);
			}

			String opcode = m.group(1);
			String sign = m.group(2);
			Integer number = Integer.parseInt(m.group(3));

			int intSign = (sign.equals("+")) ? 1 : -1;
			int value = intSign * number;

			InstructionFactory factory = factories.get(opcode);
			Instruction instruction = factory.make(value);
			code.add(instruction);
		}

		return code;
	}

	public void execute(List<Instruction> code) throws AppException {

		accumulator = 0;
		instructionPointer = 0;

		if (code.size() <= 0) {
			throw new AppException("No Code to run!");
		}

		while ((instructionPointer >= 0) && (instructionPointer < code.size())) {
			Instruction instruction = code.get(instructionPointer);
			System.out.println(String.format("%3d %6d : %s", instructionPointer, accumulator, instruction.toString()));
			instruction.beenHere();
			instruction.execute(this);
		}

		System.err.println("Program terminated normally");
	}

	public void execute2(List<Instruction> code) throws AppException {

		if (code.size() <= 0) {
			throw new AppException("No Code to run!");
		}

		for (int index = 0; index < code.size(); index++) {
			Instruction original = code.get(index);

			Instruction replacement;
			if (original instanceof Jump) {
				replacement = new Noop(original.getValue());
			} else if (original instanceof Noop) {
				replacement = new Jump(original.getValue());
			} else {
				continue;
			}
			code.set(index, replacement);

			for (Instruction instruction : code) {
				instruction.setBeenHere(0);
			}

			boolean ok = true;
			accumulator = 0;
			instructionPointer = 0;
			try {
				while ((instructionPointer >= 0) && (instructionPointer < code.size())) {
					Instruction instruction = code.get(instructionPointer);
					instruction.beenHere();
					instruction.execute(this);
				}
			} catch (InfiniteLoopException e) {
				ok = false;
			}

			if (ok) {
				System.err.println("Program terminated normally");
				System.out.println(String.format("accumulator: %d", accumulator));
			}

			code.set(index, original);
		}
	}

	public void incrementInstructionPointer(int value) {
		instructionPointer += value;
	}

	public void incrementAccumulator(int value) {
		accumulator += value;
	}
}
