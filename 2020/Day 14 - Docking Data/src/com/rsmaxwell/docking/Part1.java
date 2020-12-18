package com.rsmaxwell.docking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.docking.computer.Computer1;
import com.rsmaxwell.docking.instruction.Instruction;
import com.rsmaxwell.docking.instructionfactory.InstructionFactory;
import com.rsmaxwell.docking.instructionfactory.MaskFactory1;
import com.rsmaxwell.docking.instructionfactory.MemoryFactory1;

public class Part1 {

	private static Pattern pattern = Pattern.compile("([a-z]+).+");

	private static Map<String, InstructionFactory> factories = new HashMap<>();

	static {
		factories.put("mask", new MaskFactory1());
		factories.put("mem", new MemoryFactory1());
	}

	private List<Instruction> program = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		try {
			Part1 part = new Part1(args);

			Computer1 computer = new Computer1();

			computer.execute(part.program);

			long total = computer.sumAddressSpace();
			System.out.println("------------------------------------------------");
			System.out.println("Total: " + total);

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	public Part1(String[] args) throws AppException, IOException {
		List<String> lines = Utils.readData(args);

		for (String line : lines) {
			Matcher m = pattern.matcher(line);

			if (!m.matches()) {
				throw new AppException("Unexpected input: " + line);
			}

			String code = m.group(1);
			InstructionFactory factory = factories.get(code);
			Instruction instruction = factory.make(line);
			program.add(instruction);
		}
	}

	protected boolean process(int previousRating) {

		return false;
	}
}
