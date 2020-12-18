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
import com.rsmaxwell.rain.actionfactory.MoveWaypointEastFactory;
import com.rsmaxwell.rain.actionfactory.MoveWaypointForwardFactory;
import com.rsmaxwell.rain.actionfactory.MoveWaypointNorthFactory;
import com.rsmaxwell.rain.actionfactory.MoveWaypointSouthFactory;
import com.rsmaxwell.rain.actionfactory.MoveWaypointWestFactory;
import com.rsmaxwell.rain.actionfactory.TurnWaypointLeftFactory;
import com.rsmaxwell.rain.actionfactory.TurnWaypointRightFactory;

public class Part2 {

	private static final Pattern pattern = Pattern.compile("([A-Z])([\\d]+)");
	private static Map<String, ActionFactory> actionFactories = new HashMap<>();

	static {
		actionFactories.put("N", new MoveWaypointNorthFactory());
		actionFactories.put("S", new MoveWaypointSouthFactory());
		actionFactories.put("E", new MoveWaypointEastFactory());
		actionFactories.put("W", new MoveWaypointWestFactory());
		actionFactories.put("L", new TurnWaypointLeftFactory());
		actionFactories.put("R", new TurnWaypointRightFactory());
		actionFactories.put("F", new MoveWaypointForwardFactory());
	}

	public static void main(String[] args) throws Exception {

		try {
			Ship ship = new Ship(0, 0, 90);
			ship.waypoint = new Position(10, 1);
			List<Action> actions = readActions(args);

			for (Action action : actions) {
				System.out.println(ship.toStringFull() + "  :  " + action);
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
