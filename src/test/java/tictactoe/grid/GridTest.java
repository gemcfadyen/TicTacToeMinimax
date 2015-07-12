package tictactoe.grid;

import org.junit.Before;
import org.junit.Test;
import tictactoe.grid.status.GameStatus;

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
    private static final int MIDDLE_ROW_INDEX = 1;
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
    public void identifiesAnEmptyGrid() {
        Grid emptyGrid = GridFactory.createEmptyGrid();
        assertThat(emptyGrid.isEmpty(), is(true));
    }

    @Test
    public void identifiesThatGridIsNotEmpty() {
        assertThat(grid.isEmpty(), is(false));
    }

    @Test
    public void updateGridAtGivenIndex() {
        grid.update(6, X);

        Row bottomRow = getSpecifiedRow(grid.rows(), BOTTOM_ROW_INDEX);
        Cell updatedCell = getCellWithOffset(bottomRow, 6);

        assertThat(grid.isEmptyAt(6), is(false));
        assertThat(updatedCell.getSymbol(), is(equalTo(X)));
    }

    @Test
    public void noUpdateToGridIsMadeWhenCellIsNotVacant() {
        grid.update(3, O);

        Cell updatedCell = getCellWithOffset(getSpecifiedRow(grid.rows(), MIDDLE_ROW_INDEX), 3);
        assertThat(updatedCell.getSymbol(), is(equalTo(X)));
    }

    @Test
    public void resetGridToEmpty() {
        grid.reset();
        assertThat(grid.isEmpty(), is(true));
    }

    @Test
    public void getFirstVacantCellOnGrid() {
        GameStatus gameStatus = grid.getFirstVacantCell();
        assertThat(gameStatus.getIndexOfMove(), is(1));
    }

    @Test
    public void getVacantNonCornerCellOnEdge() {
        GameStatus gameStatus = grid.getVacantNonCornerCellOnEdge();
        assertThat(gameStatus.getIndexOfMove(), is(1));
    }

    @Test
    public void noVacantCellOnEdgeIfOnlyCornersAreFree() {
        GameStatus gameStatus = gridWithNoEdgeCellsVacant().getVacantNonCornerCellOnEdge();
        assertThat(gameStatus.hasPotentialMove(), is(false));
    }

    @Test
    public void noVacantCellsOnFullyPopulatedGrid() {
        Grid fullyPopulatedGrid = fullyPopulatedGrid();
        GameStatus gameStatus = fullyPopulatedGrid.getFirstVacantCell();
        assertThat(gameStatus.hasPotentialMove(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionThrownIfOffsetIsOutOfRange() {
        grid.update(10, X);
    }

    private Grid fullyPopulatedGrid() {
        Row top = aRowBuilder().withHorizontalRow(X, O, X, NO_OFFSET).build();
        Row middle = aRowBuilder().withHorizontalRow(X, X, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottom = aRowBuilder().withHorizontalRow(O, O, O, BOTTOM_ROW_OFFSET).build();

        return new Grid(top, middle, bottom);
    }

    private Grid gridWithNoEdgeCellsVacant() {
        Row top = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NO_OFFSET).build();
        Row middle = aRowBuilder().withHorizontalRow(X, X, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottom = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, BOTTOM_ROW_OFFSET).build();

        return new Grid(top, middle, bottom);
    }

    private Cell getCellWithOffset(Row row, int offset) {
        return row.getCellWithOffset(offset);
    }

    private Row getSpecifiedRow(List<Row> rows, int rowIndex) {
        return rows.get(rowIndex);
    }
}
