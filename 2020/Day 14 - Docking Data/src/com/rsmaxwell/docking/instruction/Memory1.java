package com.rsmaxwell.docking.instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.computer.Computer;
import com.rsmaxwell.docking.computer.Computer1;

public class Memory1 extends AbstractInstruction {

	private static Pattern pattern = Pattern.compile("[a-z]+\\[([\\d]+)\\] = ([\\d]+)");

	public long address;
	public long value;

	public Memory1(String line) throws AppException {

		Matcher m = pattern.matcher(line);
		if (!m.matches()) {
			throw new AppException("Unexpected Input: " + line);
		}

		String one = m.group(1);
		String two = m.group(2);

		this.address = Long.parseLong(one);
		this.value = Long.parseLong(two);
	}

	@Override
	public void execute(Computer Icomputer) {
		Computer1 computer = (Computer1) Icomputer;

		System.out.println(String.format("---[ %s ]---------------------------------------------", address));

		System.out.println(String.format("(1) value    = %36s (decimal %d)", Long.toBinaryString(value), value));
		System.out.println(String.format("    OR_mask  = %36s", Long.toBinaryString(computer.OR_mask)));

		long number = value | computer.OR_mask;

		System.out.println(String.format("(2) number   = %36s (decimal %d)", Long.toBinaryString(number), number));
		System.out.println(String.format("    AND_mask = %36s", Long.toBinaryString(computer.AND_mask)));

		number &= computer.AND_mask;

		System.out.println(String.format("(3) number   = %36s (decimal %d)", Long.toBinaryString(number), number));

		computer.addressSpace.put(address, number);
	}
}
