package tictactoe.grid;

import tictactoe.Symbol;

public class Cell {
    private Symbol symbol;
    private int offset;

    public Cell(Symbol symbol, int offset) {
        this.symbol = symbol;
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}