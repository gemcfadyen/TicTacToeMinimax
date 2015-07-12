package tictactoe.grid;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.grid.Grid.LEFT_CELL_INDEX;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

public class RowGenerator {
    private static final int MIDDLE_CELL_INDEX = 1;
    private static final int RIGHT_CELL_INDEX = NUMBER_OF_CELLS_IN_ROW - 1;

    protected static List<Row> generateRowsForAllDirections(Row topRow, Row middleRow, Row bottomRow) {
        List<Row> allRows = horizontalRows(topRow, middleRow, bottomRow);
        allRows.addAll(verticalRows(topRow, middleRow, bottomRow));
        allRows.addAll(diagonalRows(topRow, middleRow, bottomRow));

        return allRows;
    }

    protected static List<Row> horizontalRows(Row topRow, Row middleRow, Row bottomRow) {
        List<Row> rows = newArrayList();
        rows.add(topRow);
        rows.add(middleRow);
        rows.add(bottomRow);
        return rows;
    }

    protected static Row generateVerticalRow(int startingOffset, Row topRow, Row middleRow, Row bottomRow) {
        return aRowBuilder().withVerticalRow(
                topRow.getSymbolAt(startingOffset),
                middleRow.getSymbolAt(startingOffset),
                bottomRow.getSymbolAt(startingOffset),
                startingOffset).build();
    }

    protected static Row generateRightDiagonal(Row topRow, Row middleRow, Row bottomRow) {
        return aRowBuilder().withRightDiagonal(
                topRow.getSymbolAt(RIGHT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(LEFT_CELL_INDEX)).build();
    }

    protected static Row generateLeftDiagonal(Row topRow, Row middleRow, Row bottomRow) {
        return aRowBuilder().withLeftDiagonal(
                topRow.getSymbolAt(LEFT_CELL_INDEX),
                middleRow.getSymbolAt(MIDDLE_CELL_INDEX),
                bottomRow.getSymbolAt(RIGHT_CELL_INDEX)).build();
    }

    private static List<Row> verticalRows(Row topRow, Row middleRow, Row bottomRow) {
        List<Row> rows = newArrayList();
        rows.add(generateVerticalRow(LEFT_CELL_INDEX, topRow, middleRow, bottomRow));
        rows.add(generateVerticalRow(MIDDLE_CELL_INDEX, topRow, middleRow, bottomRow));
        rows.add(generateVerticalRow(RIGHT_CELL_INDEX, topRow, middleRow, bottomRow));
        return rows;
    }

    private static List<Row> diagonalRows(Row topRow, Row middleRow, Row bottomRow) {
        List<Row> rows = newArrayList();
        rows.add(generateRightDiagonal(topRow, middleRow, bottomRow));
        rows.add(generateLeftDiagonal(topRow, middleRow, bottomRow));
        return rows;
    }
}
