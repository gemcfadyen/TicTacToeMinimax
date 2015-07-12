package tictactoe.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tictactoe.Symbol;

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
public class GridContainsAPotentiallyWinningRowTest {
    private static final int NO_SUGGESTED_MOVE = -1;
    private static final int NO_OFFSET = 0;

    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final Symbol symbol;
    private final int winningIndex;

    public GridContainsAPotentiallyWinningRowTest(Row top, Row middle, Row bottom, Symbol symbol, int winningIndex) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.symbol = symbol;
        this.winningIndex = winningIndex;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(O, O, X, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(X, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, O, VACANT, BOTTOM_ROW_OFFSET).build(),
                        X,
                        5
                },
                {
                        aRowBuilder().withHorizontalRow(X, O, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        O,
                        8
                },
                {
                        aRowBuilder().withHorizontalRow(X, O, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        X,
                        8

                },
                {
                        aRowBuilder().withHorizontalRow(X, O, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, O, BOTTOM_ROW_OFFSET).build(),
                        X,
                        NO_SUGGESTED_MOVE

                }
        });
    }

    @Test
    public void identifiesWhenAWinningRowExistsInTheGrid() {
        Grid grid = new Grid(
                topRow,
                middleRow,
                bottomRow);

        assertThat(grid.evaluateWinningMoveFor(symbol).getIndexOfMove(), is(winningIndex));
    }
}
