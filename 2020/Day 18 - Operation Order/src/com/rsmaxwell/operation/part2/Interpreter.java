package com.rsmaxwell.operation.part2;

import com.rsmaxwell.operation.part2.Expr.Unary;

public class Interpreter implements Expr.Visitor<Object> {

	@Override
	public Object visitLiteralExpr(Expr.Literal expr) {
		return expr.value;
	}

	@Override
	public Object visitUnaryExpr(Unary expr) {
		return (long) evaluate(expr.right);
	}

	@Override
	public Object visitBinaryExpr(Expr.Binary expr) {
		Object left = evaluate(expr.left);
		Object right = evaluate(expr.right);

		switch (expr.operator.type) {

		case PLUS:
			if (left instanceof Long && right instanceof Long) {
				return (long) left + (long) right;
			}

			throw new RuntimeError(expr.operator, "Operands must be two numbers.");

		case STAR:
			checkNumberOperands(expr.operator, left, right);
			return (long) left * (long) right;
		}

		// Unreachable.
		return null;
	}

	@Override
	public Object visitGroupingExpr(Expr.Grouping expr) {
		return evaluate(expr.expression);
	}

	long interpret(Expr expression) {
		long result = 0;
		try {
			Object value = evaluate(expression);
			result = (long) value;

		} catch (RuntimeError error) {
			Part2.runtimeError(error);
		}

		return result;
	}

	private String stringify(Object object) {
		if (object == null)
			return "nil";

		if (object instanceof Double) {
			String text = object.toString();
			if (text.endsWith(".0")) {
				text = text.substring(0, text.length() - 2);
			}
			return text;
		}

		return object.toString();
	}

	private Object evaluate(Expr expr) {
		return expr.accept(this);
	}

	private void execute(Expr stmt) {
		stmt.accept(this);
	}

	private void checkNumberOperands(Token operator, Object left, Object right) {
		if (left instanceof Long && right instanceof Long) {
			return;
		}
		throw new RuntimeError(operator, "Operands must be numbers.");
	}
}
