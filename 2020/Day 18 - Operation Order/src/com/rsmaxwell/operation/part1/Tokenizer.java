package com.rsmaxwell.operation.part1;

import com.rsmaxwell.operation.part1.stack.ListStack;
import com.rsmaxwell.operation.part1.stack.Stack;
import com.rsmaxwell.operation.part1.token.Addition;
import com.rsmaxwell.operation.part1.token.CloseBracket;
import com.rsmaxwell.operation.part1.token.Multiplication;
import com.rsmaxwell.operation.part1.token.NumberToken;
import com.rsmaxwell.operation.part1.token.OpenBracket;
import com.rsmaxwell.operation.part1.token.Token;

public class Tokenizer {

	private String line;
	private int index;
	private Token lookahead;

	private enum State {
		START, QUIT, PARSE, NUMBER, GET_NEXT
	};

	public void load(String line) {
		this.line = line;
		index = -1;
	}

	public Token peek() throws Exception {
		if (lookahead == null) {
			lookahead = next();
		}
		return lookahead;
	}

	public void advance() throws Exception {
		lookahead = null;
	}

	private Token next() throws Exception {

		debug("----------------------------------------------------------------------------");

		Stack<State> stateStack = new ListStack<>();
		State next = State.START;
		State state;
		Token token = null;
		boolean end_of_file = false;
		StringBuffer sb = new StringBuffer();
		char ch = 0;

		while (next != State.QUIT) {
			state = next;

			debug("");
			debug(String.format("stack: %s %s", state, stateStack));
			debug(String.format("index: %d, ch:%c", index, ch));

			switch (state) {
			case START:
				stateStack.push(State.PARSE);
				next = State.GET_NEXT;
				break;

			case PARSE:
				if (Character.isDigit(ch)) {
					debug(" --> digit");
					sb.setLength(0);
					next = State.NUMBER;

				} else if (Character.isWhitespace(ch)) {
					debug(" --> whitespace");
					stateStack.push(State.PARSE);
					next = State.GET_NEXT;

				} else if (ch == '+') {
					debug(" --> plus");
					token = new Addition();
					next = State.QUIT;

				} else if (ch == '*') {
					debug(" --> multiply");
					token = new Multiplication();
					next = State.QUIT;

				} else if (ch == '(') {
					debug(" --> open-bracket");
					token = new OpenBracket();
					next = State.QUIT;

				} else if (ch == '(') {
					debug(" --> close-bracket");
					token = new CloseBracket();
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
					int value = Integer.parseInt(sb.toString());
					token = new NumberToken(value);
					next = State.QUIT;
				}
				break;

			case GET_NEXT:

				if (end_of_file || (index + 1 >= line.length())) {
					ch = (char) -1;
				} else {
					index++;
					ch = line.charAt(index);
				}

				if (ch == ')') {
					end_of_file = true;
					ch = (char) -1;
				}

				next = stateStack.pop();

				break;

			default:
				throw new Exception("Unexpected state: " + state);
			}
		}

		debug("----------------------------------------------------------------------------");
		return token;
	}

	@Override
	public String toString() {
		return line;
	}

	private void debug(String string) {
		// System.out.println(string);
	}
}
