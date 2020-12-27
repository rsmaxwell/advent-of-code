//> Scanning lox-class
package com.rsmaxwell.operation.part2;

import java.util.List;

import com.rsmaxwell.operation.part1.Utils;

public class Part2 {

	public static void main(String[] args) throws Exception {

		long total = 0;

		List<String> lines = Utils.readData(args);
		for (String line : lines) {

			System.out.println("");
			System.out.println(line);

			Scanner scanner = new Scanner(line);
			List<Token> tokens = scanner.scanTokens();

			// for (Token token : tokens) {
			// System.out.println(token);
			// }

			Parser parser = new Parser(tokens);
			List<Expr> expressions = parser.parse();

			for (Expr expression : expressions) {
				// AstPrinter astPrinter = new AstPrinter();
				// String string = astPrinter.print(expression);
				// System.out.println(string);

				Interpreter interpreter = new Interpreter();
				Resolver resolver = new Resolver(interpreter);
				resolver.resolve(expression);

				long result = interpreter.interpret(expression);
				System.out.println("result = " + result);

				total += result;
			}
		}

		System.out.println("");
		System.out.println("total  = " + total);
	}

	public static void error(String message) {
		System.out.println(String.format("%s", message));
	}

	public static void error(Token token, String message) {
		System.out.println(String.format("%s: %s", token, message));
	}

	public static void runtimeError(RuntimeError error) {
		throw error;
	}
}
