package tictactoe.player.gameplan.forking;

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
public class BlockOpponentFormingForksInRowsTest {
    private static final int NO_SUGGESTED_MOVE = -1;
    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final int freeCellIndex;

    private BlockOpponentFormingForksInRows blockOpponentFormingForksInRows = new BlockOpponentFormingForksInRows();

    public BlockOpponentFormingForksInRowsTest(Row top, Row middle, Row bottom, int index) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.freeCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(X, VACANT, X, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(X, VACANT, X, BOTTOM_ROW_OFFSET).build(),
                        NO_SUGGESTED_MOVE
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        1
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        NO_SUGGESTED_MOVE
                },

        });
    }

    @Test
    public void blockPotentialFork() {
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        assertThat(blockOpponentFormingForksInRows.execute(grid, X), is(freeCellIndex));
    }

}