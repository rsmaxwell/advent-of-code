package com.rsmaxwell.advent;

public class MyNumber {

    public int row, col;
    public String text;
    
    public MyNumber(String text, int row, int col) {
        this.text = text;
        this.row = row;
        this.col = col;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{ ");
        sb.append(text);
        sb.append(", ");
        sb.append(row);
        sb.append(", ");
        sb.append(col);
        sb.append(" }");        
        
        return sb.toString();
    }
}
