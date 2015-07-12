package tictactoe.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

public class GridTest {
    private static final int NO_OFFSET = 0;
    private static final int BOTTOM_ROW_INDEX = 2;

    private Grid grid;

    @Before
    public void setup() {
        Row top = aRowBuilder().withHorizontalRow(X, VACANT, X, NO_OFFSET).build();
        Row middle = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottom = aRowBuilder().withHorizontalRow(VACANT, O, X, BOTTOM_ROW_OFFSET).build();

        grid = new Grid(top, middle, bottom);
    }

    @Test
    public void updateGridAtGivenIndex() {
        grid.update(6, X);

        Row bottomRow = getSpecifiedRow(grid.rows(), BOTTOM_ROW_INDEX);
        Cell updatedCell = getCellWithOffset(bottomRow, 6);

        assertThat(grid.isEmptyAt(6), is(false));
        assertThat(updatedCell.getSymbol(), is(equalTo(X)));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionThrownIfOffsetIsOutOfRange() {
        grid.update(10, X);
    }

    @Test
    public void resetGridToEmpty() {
        Row top = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build();
        Row middle = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottom = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid gridWithOneCellOccupied = new Grid(top, middle, bottom);
        gridWithOneCellOccupied.reset();

        assertThat(gridWithOneCellOccupied.isEmptyAt(3), is(true));
    }

    private Cell getCellWithOffset(Row row, int offset) {
        return row.getCellWithOffset(offset);
    }

    private Row getSpecifiedRow(List<Row> rows, int rowIndex) {
        return rows.get(rowIndex);
    }
}
