package tictactoe.grid;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.Grid.TOTAL_CELLS;

final class RowGenerator {
    public static List<List<Cell>> generateRowsForAllDirectionsFrom(List<Cell> cells) {
        List<List<Cell>> horizontals = generateHorizontalRows(cells);

        List<List<Cell>> rows = newArrayList();
        rows.addAll(generateDiagonalsFrom(horizontals));
        rows.addAll(generateVerticalsFrom(horizontals));
        rows.addAll(horizontals);

        return rows;
    }

    private static List<List<Cell>> generateHorizontalRows(List<Cell> cells) {
        List<List<Cell>> horizontals = newArrayList();
        int startingIndex = 0;
        while (startingIndex < TOTAL_CELLS) {
            horizontals.add(cells.subList(startingIndex, startingIndex + NUMBER_OF_CELLS_IN_ROW));
            startingIndex += NUMBER_OF_CELLS_IN_ROW;
        }
        return horizontals;
    }

    private static List<List<Cell>> generateDiagonalsFrom(List<List<Cell>> horizontalRows) {
        List<List<Cell>> diagonalRows = newArrayList();
        List<Cell> backwardsDiagonal = generateBackwardsDiagonal(horizontalRows);
        List<Cell> forwardDiagonal = generateForwardDiagonal(horizontalRows);

        diagonalRows.add(forwardDiagonal);
        diagonalRows.add(backwardsDiagonal);

        return diagonalRows;
    }

    private static List<List<Cell>> generateVerticalsFrom(List<List<Cell>> horizontalRows) {
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

    private static List<Cell> generateBackwardsDiagonal(List<List<Cell>> horizontalRows) {
        List<Cell> backwardsDiagonal = newArrayList();
        int startingIndex = NUMBER_OF_CELLS_IN_ROW - 1;
        while (startingIndex >= 0) {
            backwardsDiagonal.add(horizontalRows.get(startingIndex).get(startingIndex));
            startingIndex--;
        }
        return backwardsDiagonal;
    }

    private static List<Cell> generateForwardDiagonal(List<List<Cell>> horizontalRows) {
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
}
