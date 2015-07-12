package tictactoe.player.gameplan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tictactoe.grid.Grid;
import tictactoe.grid.Row;

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
public class TakeVacantCellTest {
    private static final int NO_SUGGESTED_MOVE = -1;
    private TakeVacantCell takeVacantCell = new TakeVacantCell();

    private static final int NO_OFFSET = 0;

    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final int indexOfMove;

    public TakeVacantCellTest(Row top, Row middle, Row bottom, int suggestedMove) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.indexOfMove = suggestedMove;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, O, BOTTOM_ROW_OFFSET).build(),
                        1
                },
                {
                        aRowBuilder().withHorizontalRow(O, X, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build(),
                        4
                },
                {
                        aRowBuilder().withHorizontalRow(O, X, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(X, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, O, BOTTOM_ROW_OFFSET).build(),
                        7

                },
                {
                        aRowBuilder().withHorizontalRow(O, X, O, NO_OFFSET).build(),
                        aRowBuilder().withHorizontalRow(X, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(O, X, O, BOTTOM_ROW_OFFSET).build(),
                        NO_SUGGESTED_MOVE
                }
        });
    }


    @Test
    public void returnsVacantCell() {
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(takeVacantCell.execute(grid, X), is(indexOfMove));
    }
}
