package com.rsmaxwell.rain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rsmaxwell.rain.action.Action;
import com.rsmaxwell.rain.actionfactory.ActionFactory;
import com.rsmaxwell.rain.actionfactory.MoveEastFactory;
import com.rsmaxwell.rain.actionfactory.MoveForwardFactory;
import com.rsmaxwell.rain.actionfactory.MoveNorthFactory;
import com.rsmaxwell.rain.actionfactory.MoveSouthFactory;
import com.rsmaxwell.rain.actionfactory.MoveWestFactory;
import com.rsmaxwell.rain.actionfactory.TurnLeftFactory;
import com.rsmaxwell.rain.actionfactory.TurnRightFactory;

public class Part1 {

	private static final Pattern pattern = Pattern.compile("([A-Z])([\\d]+)");
	private static Map<String, ActionFactory> actionFactories = new HashMap<>();

	static {
		actionFactories.put("N", new MoveNorthFactory());
		actionFactories.put("S", new MoveSouthFactory());
		actionFactories.put("E", new MoveEastFactory());
		actionFactories.put("W", new MoveWestFactory());
		actionFactories.put("L", new TurnLeftFactory());
		actionFactories.put("R", new TurnRightFactory());
		actionFactories.put("F", new MoveForwardFactory());
	}

	public static void main(String[] args) throws Exception {

		try {
			Ship ship = new Ship(0, 0, 90);
			List<Action> actions = readActions(args);

			for (Action action : actions) {
				System.out.println(ship + "  :  " + action);
				ship.move(action);
			}

			System.out.println(String.format("ship's position: %s", ship));
			System.out.println(String.format("ship's Manhatten Distance: %s", ship.manhattenDistance()));

		} catch (AppException e) {
			System.err.println(e.getMessage());
		}
	}

	private static List<Action> readActions(String[] args) throws IOException, AppException {
		List<String> lines = Utils.readData(args);

		List<Action> actions = new ArrayList<>();
		for (String line : lines) {
			Matcher m = pattern.matcher(line);

			if (!m.matches()) {
				throw new AppException(String.format("Unexpected data: %s", line));
			}

			String letter = m.group(1);
			int value = Integer.parseInt(m.group(2));

			ActionFactory factory = actionFactories.get(letter);
			Action action = factory.create(value);
			actions.add(action);
		}

		return actions;
	}
}
