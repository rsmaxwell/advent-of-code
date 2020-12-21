package com.rsmaxwell.operation.part2;

import static com.rsmaxwell.operation.part2.TokenType.EOF;
import static com.rsmaxwell.operation.part2.TokenType.LEFT_PAREN;
import static com.rsmaxwell.operation.part2.TokenType.NUMBER;
import static com.rsmaxwell.operation.part2.TokenType.PLUS;
import static com.rsmaxwell.operation.part2.TokenType.RIGHT_PAREN;
import static com.rsmaxwell.operation.part2.TokenType.STAR;

import java.util.ArrayList;
import java.util.List;

// Original Grammar
// @formatter:off
// term           --> factor ( "+" factor )* ;
// factor         --> unary ( "*" unary )* ;
// unary          --> primary ;
// primary        --> NUMBER
//                    | "(" term ")" ;
// @formatter:on

// Modified grammar
//@formatter:off
//term           --> factor ( "*" factor )* ;
//factor         --> unary ( "+" unary )* ;
//unary          --> primary ;
//primary        --> NUMBER
//                 | "(" term ")" ;
//@formatter:on

class Parser {
	private static class ParseError extends RuntimeException {
	}

	private final List<Token> tokens;
	private int current = 0;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Expr> parse() {
		List<Expr> statements = new ArrayList<>();
		while (!isAtEnd()) {
			statements.add(term());
		}

		return statements;
	}

	// term --> factor ( "*" factor )* ;
	private Expr term() {
		Expr expr = factor();

		while (match(STAR)) {
			Token operator = previous();
			Expr right = factor();
			expr = new Expr.Binary(expr, operator, right);
		}

		return expr;
	}

	// factor --> unary ( "+" unary )* ;
	private Expr factor() {
		Expr expr = unary();

		while (match(PLUS)) {
			Token operator = previous();
			Expr right = unary();
			expr = new Expr.Binary(expr, operator, right);
		}

		return expr;
	}

	// unary --> primary ;
	private Expr unary() {
		return primary();
	}

	// primary --> NUMBER
	// | "(" term ")" ;
	private Expr primary() {

		if (match(NUMBER)) {
			return new Expr.Literal(previous().literal);
		}

		if (match(LEFT_PAREN)) {
			Expr expr = term();
			consume(RIGHT_PAREN, "Expect ')' after expression.");
			return new Expr.Grouping(expr);
		}

		throw error(peek(), "Expect expression.");
	}

	private boolean match(TokenType... types) {
		for (TokenType type : types) {
			if (check(type)) {
				advance();
				return true;
			}
		}

		return false;
	}

	private Token consume(TokenType type, String message) {
		if (check(type))
			return advance();

		throw error(peek(), message);
	}

	private boolean check(TokenType type) {
		if (isAtEnd())
			return false;
		return peek().type == type;
	}

	private Token advance() {
		if (!isAtEnd())
			current++;
		return previous();
	}

	private boolean isAtEnd() {
		return peek().type == EOF;
	}

	private Token peek() {
		return tokens.get(current);
	}

	private Token previous() {
		return tokens.get(current - 1);
	}

	private ParseError error(Token token, String message) {
		Part2.error(token, message);
		return new ParseError();
	}
}
