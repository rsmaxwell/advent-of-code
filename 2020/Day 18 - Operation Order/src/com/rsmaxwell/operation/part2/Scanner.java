//> Scanning scanner-class
package com.rsmaxwell.operation.part2;

import static com.rsmaxwell.operation.part2.TokenType.EOF;
import static com.rsmaxwell.operation.part2.TokenType.LEFT_PAREN;
import static com.rsmaxwell.operation.part2.TokenType.NUMBER;
import static com.rsmaxwell.operation.part2.TokenType.PLUS;
import static com.rsmaxwell.operation.part2.TokenType.RIGHT_PAREN;
import static com.rsmaxwell.operation.part2.TokenType.STAR;

import java.util.ArrayList;
import java.util.List;

class Scanner {

	private final String source;
	private final List<Token> tokens = new ArrayList<>();

	private int start = 0;
	private int current = 0;

	Scanner(String source) {
		this.source = source;
	}

	List<Token> scanTokens() {
		while (!isAtEnd()) {
			start = current;
			scanToken();
		}

		tokens.add(new Token(EOF, "", null));
		return tokens;
	}

	private void scanToken() {
		char c = advance();
		switch (c) {
		case '(':
			addToken(LEFT_PAREN);
			break;
		case ')':
			addToken(RIGHT_PAREN);
			break;
		case '+':
			addToken(PLUS);
			break;
		case '*':
			addToken(STAR);
			break;

		case ' ':
		case '\t':
			// Ignore whitespace.
			break;

		default:
			if (isDigit(c)) {
				number();
			} else {
				Part2.error("Unexpected character.");
			}

			break;
		}
	}

	private void number() {
		while (isDigit(peek())) {
			advance();
		}

		addToken(NUMBER, Long.parseLong(source.substring(start, current)));
	}

	private char peek() {
		if (isAtEnd()) {
			return '\0';
		}
		return source.charAt(current);
	}

	private boolean isDigit(char c) {
		return ((c >= '0') && (c <= '9'));
	}

	private boolean isAtEnd() {
		return current >= source.length();
	}

	private char advance() {
		current++;
		return source.charAt(current - 1);
	}

	private void addToken(TokenType type) {
		addToken(type, null);
	}

	private void addToken(TokenType type, Object literal) {
		String text = source.substring(start, current);
		tokens.add(new Token(type, text, literal));
	}

}
