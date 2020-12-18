package com.rsmaxwell.console.instruction;

import com.rsmaxwell.console.Computer;
import com.rsmaxwell.console.InfiniteLoopException;

public interface Instruction {

	public void execute(Computer computer);

	public void beenHere() throws InfiniteLoopException;

	public void setBeenHere(int value);

	public int getValue();

}
