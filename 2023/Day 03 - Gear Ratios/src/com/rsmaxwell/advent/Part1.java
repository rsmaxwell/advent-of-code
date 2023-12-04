package com.rsmaxwell.advent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Missing data file argument");
            System.exit(1);
        }
        String filename = args[0];

        Part1 part = new Part1();

        int result = part.readSchematic(filename);

        System.out.println(String.format("Result = %d", result));
    }

    int readSchematic(String filename) throws IOException {

        int total = 0;
        
        Schematic schematic = new Schematic(filename);
        MyIterator it = schematic.iterator();
        MyNumber number = it.next();
        while (number != null) {
            if (schematic.isAdjacentToSymbol(number)) {
                System.out.printf("%s    iS adjacent to symbol\n", number.toString());
                int value = Integer.valueOf(number.text);
                total += value;
            } else {
                System.out.printf("%s   is NOT adjacent to symbol\n", number.toString());
            }

            number = it.next();
        }

        return total;
    }

}
