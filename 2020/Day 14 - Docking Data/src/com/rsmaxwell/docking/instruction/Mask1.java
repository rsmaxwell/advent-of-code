package com.rsmaxwell.docking.instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.computer.Computer;
import com.rsmaxwell.docking.computer.Computer1;

public class Mask1 extends AbstractInstruction {

	private static Pattern pattern = Pattern.compile("[^=]+ = ([X10]{36})");

	public long OR_mask;
	public long AND_mask;

	public Mask1(String line) throws AppException {

		Matcher m = pattern.matcher(line);
		if (!m.matches()) {
			throw new AppException("Unexpected Input: " + line);
		}

		String string = m.group(1);

		StringBuffer OR = new StringBuffer();
		StringBuffer AND = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);

			if (ch == '0') {
				OR.append('0');
				AND.append('0');
			} else if (ch == '1') {
				OR.append('1');
				AND.append('1');
			} else {
				OR.append('0');
				AND.append('1');
			}
		}

		this.OR_mask = Long.parseLong(OR.toString(), 2);
		this.AND_mask = Long.parseLong(AND.toString(), 2);
	}

	@Override
	public void execute(Computer Icomputer) {
		Computer1 computer = (Computer1) Icomputer;

		computer.OR_mask = OR_mask;
		computer.AND_mask = AND_mask;
	}
}
