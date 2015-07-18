package tictactoe.grid;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.status.GameStatus;

import java.util.List;

import static com.google.common.collect.Iterables.all;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.status.GameStatus.noWin;
import static tictactoe.grid.status.GameStatus.winFor;

public class Grid {
    public static final int NUMBER_OF_CELLS_IN_ROW = 3;
    public static final int TOTAL_CELLS = NUMBER_OF_CELLS_IN_ROW * NUMBER_OF_CELLS_IN_ROW;

    protected static final int FIRST_CELL_INDEX = 0;
    private List<Cell> cells;

    public Grid(List<Cell> cells) {
        this.cells = cells;
    }

    public boolean isEmptyAt(int offset) {
        Cell cellWithOffset = getCellWithOffset(offset);
        return cellWithOffset.getSymbol() == VACANT;
    }

    public void update(int offset, Symbol symbol) {
        Cell cellToUpdate = getCellWithOffset(offset);
        cellToUpdate.setSymbol(symbol);
    }

    public Cell getCellWithOffset(final int offset) {
        Iterable<Cell> cellWithOffset = filter(cells, cell -> cell.getOffset() == offset);
        return getOnlyElement(cellWithOffset);
    }

    public boolean isWinningRow(List<Cell> cells) {
        Symbol symbol = cells.get(FIRST_CELL_INDEX).getSymbol();
        return symbol != VACANT
                && all(cells, checkAllCellsHaveTheSame(symbol));
    }

    public GameStatus evaluateWinningStatus() {
        List<List<Cell>> allRows = generateRowsForAllDirection();

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

    private List<List<Cell>> generateRowsForAllDirection() {
        List<List<Cell>> horizontalRows = generateHorizontalRows();

        List<List<Cell>> rows = newArrayList();
        rows.addAll(generateDiagonals(horizontalRows));
        rows.addAll(generateVertical(horizontalRows));
        rows.addAll(horizontalRows);

        return rows;
    }

    private List<List<Cell>> generateHorizontalRows() {
        List<List<Cell>> horizontalRows = newArrayList();
        int startingIndex = 0;
        while (startingIndex < TOTAL_CELLS) {
            horizontalRows.add(cells.subList(startingIndex, startingIndex + NUMBER_OF_CELLS_IN_ROW));
            startingIndex += NUMBER_OF_CELLS_IN_ROW;
        }
        return horizontalRows;
    }

    private List<List<Cell>> generateVertical(List<List<Cell>> horizontalRows) {
        List<List<Cell>> verticalRows = newArrayList();
        int cellIndex = 0;

        while (cellIndex < NUMBER_OF_CELLS_IN_ROW) {
            List<Cell> verticalRow = newArrayList();
            for (List<Cell> horizontalRow : horizontalRows) {
                verticalRow.add(horizontalRow.get(cellIndex));
            }
            verticalRows.add(verticalRow);
            cellIndex++;
        }

        return verticalRows;
    }

    private List<List<Cell>> generateDiagonals(List<List<Cell>> horizontalRows) {
        List<List<Cell>> diagonalRows = newArrayList();
        List<Cell> backwardsDiagonal = generateBackwardsDiagonal(horizontalRows);
        List<Cell> forwardDiagonal = generateForwardDiagonal(horizontalRows);

        diagonalRows.add(forwardDiagonal);

        diagonalRows.add(backwardsDiagonal);
        return diagonalRows;
    }

    private List<Cell> generateForwardDiagonal(List<List<Cell>> horizontalRows) {
        List<Cell> forwardsDiagonal = newArrayList();
        int rowIndex = horizontalRows.size() - 1;
        int cellIndex = 0;
        while (rowIndex >= 0) {
            forwardsDiagonal.add(horizontalRows.get(rowIndex).get(cellIndex));
            cellIndex++;
            rowIndex--;
        }
        return forwardsDiagonal;
    }

    private List<Cell> generateBackwardsDiagonal(List<List<Cell>> horizontalRows) {
        List<Cell> backwardsDiagonal = newArrayList();
        int startingIndex = NUMBER_OF_CELLS_IN_ROW - 1;
        while (startingIndex >= 0) {
            backwardsDiagonal.add(horizontalRows.get(startingIndex).get(startingIndex));
            startingIndex--;
        }
        return backwardsDiagonal;
    }
}
