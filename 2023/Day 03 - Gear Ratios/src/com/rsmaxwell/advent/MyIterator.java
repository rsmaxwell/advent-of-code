package com.rsmaxwell.advent;

import java.util.List;

public class MyIterator {

    Schematic schematic;
    public int row, col;
    public MyNumber value;
    
    public MyIterator(Schematic schematic) {
        this.schematic = schematic;
    }
    
    public boolean hasNext() {
        if (row >= schematic.lines.size()) {
            return false;
        }
        
        String line = schematic.lines.get(row);
        
        if (col < line.length()) {
            return true;
        }
        
        return false;
    }
    
    public MyNumber next() {

        // Skip over non-digit chars till we find the next digit
        String line = schematic.lines.get(row);
        while (true) {

            char ch = line.charAt(col);
            if (Character.isDigit(ch)) {
                break;
            }
            
            col++;
            if (col >= line.length()) {
                col = 0;
                row++;
            }
            if (row >= schematic.lines.size()) {
                return null;
            }

            line = schematic.lines.get(row);
        } 
        int startRow = row;
        int startCol = col;
        
        // Accumulate the next number
        StringBuilder sb = new StringBuilder();
        char ch = line.charAt(col);
        while (Character.isDigit(ch)) {
            sb.append(ch);
            
            col++;
            if (col >= line.length()) {
                col = 0;
                row++;
                break;
            }
            
            ch = line.charAt(col);
        }
        
        
        return new MyNumber(sb.toString(), startRow, startCol);
    }
}
