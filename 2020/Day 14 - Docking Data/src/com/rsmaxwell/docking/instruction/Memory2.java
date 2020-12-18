package com.rsmaxwell.docking.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.docking.AppException;
import com.rsmaxwell.docking.Utils;
import com.rsmaxwell.docking.computer.Computer;
import com.rsmaxwell.docking.computer.Computer2;

public class Memory2 extends AbstractInstruction {

	private static Pattern pattern = Pattern.compile("[a-z]+\\[([\\d]+)\\] = ([\\d]+)");

	public long address;
	public long value;

	public Memory2(String line) throws AppException {

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
		Computer2 computer = (Computer2) Icomputer;

		System.out.println(String.format("---[ mem[%d] = %d ]-----------------------------------", address, value));

		System.out.println(String.format("    address  = %36s (decimal %d)", Long.toBinaryString(address), address));
		System.out.println(String.format("    mask     = %36s", computer.mask));

		String original = Utils.leftPad(Long.toBinaryString(address), 36, '0');
		char[] array = new char[computer.WORD_SIZE];
		List<Integer> floating = new ArrayList<>();

		for (int i = 0; i < computer.WORD_SIZE; i++) {
			char ch = original.charAt(i);
			char m = computer.mask.charAt(i);

			switch (m) {

			case '0':
				array[i] = ch;
				break;

			case '1':
				array[i] = '1';
				break;

			default:
				array[i] = 'X';
				floating.add(i);
				break;
			}
		}

		if (floating.size() == 0) {
			String str = new String(array);
			address = Long.parseLong(str, 2);
			System.out.println(String.format("    result   = %36s (decimal %d)", Long.toBinaryString(address), address));
			computer.addressSpace.put(address, value);

		} else {

			for (int i = 0; i < Math.pow(2, floating.size()); i++) { // for each combination ...
				char[] number = Utils.leftPad(Long.toBinaryString(i), floating.size(), '0').toCharArray();

				for (int j = 0; j < floating.size(); j++) { // for each binary digit in this combination ...
					int index = floating.get(j); // set the binary digit in the array
					char ch = number[j];
					array[index] = ch;
				}

				String str = new String(array); // convert the char array into a number address
				address = Long.parseLong(str, 2);
				System.out.println(String.format("    result   = %36s (decimal %d)", Long.toBinaryString(address), address));
				computer.addressSpace.put(address, value); // finally write the value into that address
			}
		}
	}
}
