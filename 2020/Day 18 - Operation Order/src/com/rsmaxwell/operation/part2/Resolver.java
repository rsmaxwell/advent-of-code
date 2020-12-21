package com.rsmaxwell.operation.part2;

import com.rsmaxwell.operation.part2.Expr.Unary;

public class Resolver implements Expr.Visitor<Void> {

	public Resolver(Interpreter interpreter) {
	}

	public void resolve(Expr left) {

	}

	@Override
	public Void visitLiteralExpr(Expr.Literal expr) {
		return null;
	}

	@Override
	public Void visitUnaryExpr(Unary expr) {
		return null;
	}

	@Override
	public Void visitBinaryExpr(Expr.Binary expr) {
		resolve(expr.left);
		resolve(expr.right);
		return null;
	}

	@Override
	public Void visitGroupingExpr(Expr.Grouping expr) {
		resolve(expr.expression);
		return null;
	}

}
