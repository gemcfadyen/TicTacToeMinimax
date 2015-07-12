package tictactoe.grid;

import tictactoe.Symbol;
import tictactoe.grid.status.GameStatus;

import java.util.List;

import static tictactoe.grid.Row.FIRST_CELL_INDEX;
import static tictactoe.grid.RowGenerator.generateRowsForAllDirections;
import static tictactoe.grid.RowGenerator.horizontalRows;
import static tictactoe.grid.status.GameStatus.noWin;
import static tictactoe.grid.status.GameStatus.winFor;

public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    public static final int BOTTOM_ROW_OFFSET = NUMBER_OF_CELLS_IN_ROW * 2;

    protected static final int LEFT_CELL_INDEX = 0;

    private Row topRow;
    private Row middleRow;
    private Row bottomRow;

    public Grid(Row topRow, Row middleRow, Row bottomRow) {
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

    public boolean isEmptyAt(int offset) {
        Row row = determineRowFrom(offset);
        return row.isVacantAt(offset);
    }

    public GameStatus evaluateWinningStatus() {
        for (Row row : generateRowsForAllDirections(topRow, middleRow, bottomRow)) {
            if (row.isWinningRow()) {
                return winFor(row.getSymbolAt(FIRST_CELL_INDEX));
            }
        }
        return noWin();
    }

    public void update(int offset, Symbol symbol) {
        Row row = determineRowFrom(offset);
        row.putSymbolAt(offset, symbol);
    }


    public List<Row> rows() {
        return horizontalRows(topRow, middleRow, bottomRow);
    }

    public void reset() {
        topRow.reset();
        middleRow.reset();
        bottomRow.reset();
    }

    private Row determineRowFrom(int index) {
        if (index < NUMBER_OF_CELLS_IN_ROW) {
            return topRow;
        }
        if (index <= BOTTOM_ROW_OFFSET - 1) {
            return middleRow;
        }
        return bottomRow;
    }
}

