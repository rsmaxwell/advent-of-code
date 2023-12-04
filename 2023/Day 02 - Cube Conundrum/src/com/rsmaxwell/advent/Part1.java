package com.rsmaxwell.advent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    static Map<String, Integer> possibles = new HashMap<String, Integer>(); 
    
    static {
        possibles.put("red", 12);
        possibles.put("green", 13);
        possibles.put("blue", 14);
    }
    
    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Missing data file argument");
            System.exit(1);
        }
        String filename = args[0];

        Part1 part = new Part1();

        int result = part.processData(filename);

        System.out.println(String.format("Result = %d", result));
    }

    public int processData(String filename) throws Exception {

        int result = 0;
        List<String> list = Utils.readData(filename);

        for (int i = 0; i < list.size(); i++) {
            result += processGame(list.get(i));
        }

        return result;
    }

    public int processGame(String line) throws Exception {

        Map<String, Integer> largestCounts = new HashMap<String, Integer>(); 
        
        Pattern gamePattern = Pattern.compile("Game\\s+(\\d+):(.*)");
        Pattern turnPattern = Pattern.compile("\\s*(\\d+?)\\s+(.*)\\s*");
        
        Matcher m = gamePattern.matcher(line);
        if (!m.matches()) {
            throw new Exception("NO MATCH");
        }

        int game = Integer.valueOf(m.group(1));
        String data = m.group(2);
        System.out.println("game: " + game);
        
        String turns[] = data.split(";");
        for (String turn: turns) {  
            
            String colours[] = turn.split(",");
            for (String colour: colours) {
                m = turnPattern.matcher(colour);
                if (!m.matches()) {
                    throw new Exception("NO MATCH");
                }

                int count = Integer.valueOf(m.group(1));
                String name = m.group(2);
                
                System.out.printf("    %s: %d\n", name, count);
                
                Integer previousLargestCount = largestCounts.get(name);
                if (previousLargestCount == null) {
                    largestCounts.put(name, count);
                }
                else {
                    if (previousLargestCount < count) {
                        largestCounts.put(name, count);
                    }
                }
            }
            System.out.println("");
        }

        
        
        for (String colour : largestCounts.keySet()) {
            int largestCount = largestCounts.get(colour);
            int possible = possibles.get(colour);

            System.out.printf("%8s: %5d  %5d\n", colour, largestCount, possible);
            
            if (largestCount > possible) {
                System.out.println("Not possible");
                return 0; 
            }
        }
        

        System.out.println("ok");
        return game;
    }
}
