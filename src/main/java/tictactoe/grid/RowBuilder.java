package tictactoe.grid;

import tictactoe.Symbol;

import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.Grid.TOTAL_CELLS;

public final class RowBuilder {
    private Cell[] cells;

    public static RowBuilder aRowBuilder() {
        return new RowBuilder();
    }

    public RowBuilder withHorizontalRow(Symbol zero, Symbol one, Symbol two, int offset) {
        this.cells = new Cell[]{
                new Cell(zero, offset),
                new Cell(one, offset + 1),
                new Cell(two, offset + 2)
        };
        return this;
    }

    public RowBuilder withVerticalRow(Symbol top, Symbol middle, Symbol bottom, int startingOffset) {
        this.cells = new Cell[]{
                new Cell(top, startingOffset),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + startingOffset),
                new Cell(bottom, 2 * NUMBER_OF_CELLS_IN_ROW + startingOffset)
        };
        return this;
    }

    public RowBuilder withLeftDiagonal(Symbol top, Symbol middle, Symbol bottom) {
        this.cells = new Cell[]{
                new Cell(top, 0),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + 1),
                new Cell(bottom, TOTAL_CELLS - 1)
        };
        return this;
    }

    public RowBuilder withRightDiagonal(Symbol top, Symbol middle, Symbol bottom) {
        this.cells = new Cell[]{
                new Cell(top, NUMBER_OF_CELLS_IN_ROW - 1),
                new Cell(middle, NUMBER_OF_CELLS_IN_ROW + 1),
                new Cell(bottom, 2 * NUMBER_OF_CELLS_IN_ROW)
        };
        return this;
    }

    public Row build() {
        return new Row(cells);
    }
}
