//> Scanning token-class
package com.rsmaxwell.operation.part2;

class Token {
	final TokenType type;
	final String lexeme;
	final Object literal;

	Token(TokenType type, String lexeme, Object literal) {
		this.type = type;
		this.lexeme = lexeme;
		this.literal = literal;
	}

	@Override
	public String toString() {
		return String.format("%12s    %s", type, lexeme);
	}
}
