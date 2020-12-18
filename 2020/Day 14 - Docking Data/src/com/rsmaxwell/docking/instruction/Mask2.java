package com.rsmaxwell.docking.instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.Utils;
import com.rsmaxwell.docking.computer.Computer;
import com.rsmaxwell.docking.computer.Computer2;

public class Mask2 extends AbstractInstruction {

	private static Pattern pattern = Pattern.compile("[^=]+ = ([X10]{36})");

	public String mask;

	public Mask2(String line) throws AppException {

		Matcher m = pattern.matcher(line);
		if (!m.matches()) {
			throw new AppException("Unexpected Input: " + line);
		}

		mask = Utils.leftPad(m.group(1), 36, '0');
	}

	@Override
	public void execute(Computer Icomputer) {
		Computer2 computer = (Computer2) Icomputer;
		computer.mask = mask;
	}
}
