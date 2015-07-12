package tictactoe.grid;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tictactoe.grid.status.GameStatus;

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
public class GridEvaluatesCornerTrapForksInTopRowTest {
    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final boolean hasPotentialFork;

    public GridEvaluatesCornerTrapForksInTopRowTest(Row top, Row middle, Row bottom, boolean hasPotentialFork) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.hasPotentialFork = hasPotentialFork;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(VACANT, O, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, O, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(O, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        true
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        false
                },
                {
                        aRowBuilder().withHorizontalRow(VACANT, X, VACANT, 0).build(),
                        aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                        aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build(),
                        false
                }
        });
    }

    @Test
    public void identifiesCornerTrapFork() {
        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateTopRowCornerTraps(O);
        assertThat(gameStatus.hasPotentialMove(), is(hasPotentialFork));
    }
}
