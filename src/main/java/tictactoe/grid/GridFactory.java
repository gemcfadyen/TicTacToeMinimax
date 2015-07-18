package tictactoe.grid;

import tictactoe.Symbol;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.Symbol.VACANT;

public class GridFactory {

    public static Grid createEmptyGrid() {
        return new Grid(
                withCells(
                        VACANT, VACANT, VACANT,
                        VACANT, VACANT, VACANT,
                        VACANT, VACANT, VACANT));
    }

    public static Grid createGridWith(Symbol... symbols) {
        return new Grid(withCells(symbols));
    }

    private static List<Cell> withCells(Symbol... symbols) {
        List<Cell> cells = newArrayList();
        int offset = 0;
        for (Symbol symbol : symbols) {
            cells.add(new Cell(symbol, offset));
            offset++;
        }

        return cells;
    }
}
