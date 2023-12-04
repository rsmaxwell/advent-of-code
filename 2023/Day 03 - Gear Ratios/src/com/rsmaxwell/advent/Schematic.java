package com.rsmaxwell.advent;

import java.io.IOException;
import java.util.List;

public class Schematic {

    public List<String> lines;
    
    public Schematic(String filename) throws IOException {
        lines = Utils.readData(filename);
    }
    
    
    public MyIterator iterator() {
        return new MyIterator(this);
    }


    boolean isAdjacentToSymbol(MyNumber number) {
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= number.text.length(); j++) {
                if ((i == 0) && (j == 0)) {
                    continue;
                }
                int y = number.row + i;
                if (y < 0) {
                    continue;
                }
                if (y >= lines.size()) {
                    continue;
                }
                String line = lines.get(y);
                
                int x = number.col + j;
                if (x < 0) {
                    continue;
                }
                if (x >= line.length()) {
                    continue;
                }
                
                char ch = line.charAt(x);  
                if (isSymbol(ch)) {
                    return true;
                }
            }
        }
        
        return false;
    }   

    boolean isSymbol(char ch) {
        
        if (Character.isDigit(ch)) {
            return false;
        }
        if (ch == '.') {
            return false;
        }
        if (Character.isAlphabetic(ch)) {
            return false;
        }
        
        return true;
    }
}
