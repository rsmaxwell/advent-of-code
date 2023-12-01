package com.rsmaxwell.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    static List<Word> words = new ArrayList<Word>();

    static {
        words.add(new Word("one", '1', 1));
        words.add(new Word("two", '2', 2));
        words.add(new Word("three", '3', 3));
        words.add(new Word("four", '4', 4));
        words.add(new Word("five", '5', 5));
        words.add(new Word("six", '6', 6));
        words.add(new Word("seven", '7', 7));
        words.add(new Word("eight", '8', 8));
        words.add(new Word("nine", '9', 9));

        for (int i = 0; i < 10; i++) {
            String str = Integer.toString(i);
            char ch = str.charAt(0);

            words.add(new Word(str, ch, i));
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Missing filename argument");
            System.exit(1);
        }
        String filename = args[0];

        Part2 part = new Part2();

        int result = part.processFile(filename);

        System.out.println(String.format("Result = %d", result));
    }

    public int processFile(String filename) throws Exception {

        int result = 0;
        List<String> list = Utils.readData(filename);

        for (int i = 0; i < list.size(); i++) {
            result += processLine(list.get(i));
        }

        return result;
    }

    public int processLine(String line) throws Exception {

        int count = 0;
        char first = '0';
        char last = '0';

        for (int index = 0; index < line.length(); index++) {

            Word found = null;
            for (Word word : words) {
                if (line.startsWith(word.text, index)) {
                    found = word;
                    break;
                }
            }

            if (found != null) {
                if (count == 0) {
                    first = found.ch;
                }
                last = found.ch;
                count++;
                continue;
            }
        }

        if (count == 0) {
            throw new Exception("No digits could be found");
        }

        List<Character> str = Arrays.asList(first, last);

        StringBuilder sb = new StringBuilder();
        for (Character ch : str) {
            sb.append(ch);
        }
        String string = sb.toString();

        System.out.println(String.format("%2s    %s", string, line));

        return Integer.valueOf(string);
    }
}
