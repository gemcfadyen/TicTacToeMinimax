package tictactoe.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

@RunWith(Parameterized.class)
public class GridContainsWinningRowTest {
    private static final int NO_OFFSET = 0;
    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final boolean hasWinningRow;

    public GridContainsWinningRowTest(Row top, Row middle, Row bottom, boolean hasWinningRow) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.hasWinningRow = hasWinningRow;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        false
                },
                {
                        aRowBuilder().withHorizontalRow(X, X, X, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(O, O, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(X, X, X, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                }
        });
    }

    @Test
    public void identifiesWhenAWinningRowExistsInTheGrid() {
        Grid grid = new Grid(
                topRow,
                middleRow,
                bottomRow);

        assertThat(grid.evaluateWinningStatus().hasWinner(), is(hasWinningRow));
    }
}