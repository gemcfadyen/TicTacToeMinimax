package tictactoe.grid;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.status.WinStatus;

import java.util.List;

import static com.google.common.collect.Iterables.all;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getOnlyElement;
import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.RowGenerator.generateRowsForAllDirectionsFrom;
import static tictactoe.grid.status.WinStatus.noWin;
import static tictactoe.grid.status.WinStatus.winFor;

public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;
    protected static final int FIRST_CELL_INDEX = 0;

    private List<Cell> cells;

    public Grid(final List<Cell> cells) {
        this.cells = cells;
    }

    public Cell getCellWithOffset(final int offset) {
        Iterable<Cell> cellWithOffset = filter(cells, cell -> cell.getOffset() == offset);
        return getOnlyElement(cellWithOffset);
    }

    public boolean isEmptyAt(final int offset) {
        Cell cellWithOffset = getCellWithOffset(offset);
        return cellWithOffset.getSymbol() == VACANT;
    }

    public void update(final int offset, final Symbol symbol) {
        Cell cellToUpdate = getCellWithOffset(offset);
        cellToUpdate.setSymbol(symbol);
    }

    public boolean isWinningRow(final List<Cell> cells) {
        Symbol symbol = cells.get(FIRST_CELL_INDEX).getSymbol();
        return symbol != VACANT
                && all(cells, checkAllCellsHaveTheSame(symbol));
    }

    public WinStatus winStatus() {
        List<List<Cell>> allRows = generateRowsForAllDirectionsFrom(cells);

        for (List<Cell> row : allRows) {
            if (isWinningRow(row)) {
                return winFor(row.get(FIRST_CELL_INDEX).getSymbol());
            }
        }
        return noWin();
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void reset() {
        for (Cell cell : cells) {
            cell.setSymbol(VACANT);
        }
    }

    private Predicate<Cell> checkAllCellsHaveTheSame(final Symbol firstSymbol) {
        return new Predicate<Cell>() {
            @Override
            public boolean apply(Cell cell) {
                return symbolsMatch(cell);
            }

            private boolean symbolsMatch(Cell cell) {
                return cell.getSymbol() == firstSymbol;
            }
        };
    }
}
