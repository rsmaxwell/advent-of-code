package com.rsmaxwell.operation;

import com.rsmaxwell.operation.stack.ListStack;
import com.rsmaxwell.operation.stack.Stack;

public class Expression {

	private String line;
	private int index;

	private enum State {
		START, QUIT, PARSE, NUMBER, GET_NEXT, ADD, MULTIPLY
	};

	public Expression(String line) {
		this.line = line;
	}

	public long evaluate() throws Exception {
		index = -1;
		return eval();
	}

	private long eval() throws Exception {

		debug("----------------------------------------------------------------------------");

		Stack<Long> valueStack = new ListStack<>();
		Stack<State> operationStack = new ListStack<>();
		Stack<State> stateStack = new ListStack<>();

		State next = State.START;
		State state;

		boolean end_of_file = false;
		StringBuffer sb = new StringBuffer();
		long value = 0;
		char ch = 0;

		while (next != State.QUIT) {
			state = next;

			debug("");
			debug(String.format("%s %s", state, stateStack));
			debug(String.format("%d %s", value, valueStack));
			debug(String.format("operationStack: %s", operationStack));
			debug(String.format("index:%d, ch:%c", index, ch));

			switch (state) {
			case START:
				if (operationStack.isEmpty()) {
					stateStack.push(State.PARSE);
					next = State.GET_NEXT;
				} else {
					next = operationStack.pop();
				}
				break;

			case PARSE:
				if (Character.isDigit(ch)) {
					debug(" --> digit");
					sb.setLength(0);
					stateStack.push(State.START);
					next = State.NUMBER;

				} else if (Character.isWhitespace(ch)) {
					debug(" --> whitespace");
					stateStack.push(State.PARSE);
					next = State.GET_NEXT;

				} else if (ch == '+') {
					debug(" --> plus");
					valueStack.push(value);
					operationStack.push(State.ADD);
					stateStack.push(State.PARSE);
					next = State.GET_NEXT;

				} else if (ch == '*') {
					debug(" --> multiply");
					valueStack.push(value);
					operationStack.push(State.MULTIPLY);
					stateStack.push(State.PARSE);
					next = State.GET_NEXT;

				} else if (ch == '(') {
					debug(" --> open-bracket");
					value = eval();
					next = State.START;

				} else if (ch == ')') {
					debug(" --> close-bracket");
					end_of_file = true;
					next = State.QUIT;

				} else if (ch == (char) -1) {
					debug(" --> end-of-file");
					next = State.QUIT;
				}
				break;

			case NUMBER:
				if (Character.isDigit(ch)) {
					sb.append(ch);
					stateStack.push(State.NUMBER);
					next = State.GET_NEXT;
				} else {
					value = Integer.parseInt(sb.toString());
					next = stateStack.pop();
				}
				break;

			case GET_NEXT:
				index++;
				if (end_of_file || (index >= line.length())) {
					ch = (char) -1;
					next = stateStack.pop();
				} else {
					ch = line.charAt(index);
					next = stateStack.pop();
				}

				if (ch == ')') {
					end_of_file = true;
					ch = (char) -1;
				}
				break;

			case ADD:
				value = value + valueStack.pop();
				next = State.START;
				break;

			case MULTIPLY:
				value = value * valueStack.pop();
				next = State.START;
				break;

			default:
				throw new Exception("Unexpected state: " + state);
			}
		}

		debug("----------------------------------------------------------------------------");
		return value;
	}

	@Override
	public String toString() {
		return line;
	}

	private void debug(String string) {
		// System.out.println(string);
	}
}
