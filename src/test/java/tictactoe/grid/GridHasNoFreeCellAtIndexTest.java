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
public class GridHasNoFreeCellAtIndexTest {

    private static final Row EMPTY_TOP_ROW = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
    private static final Row EMPTY_MIDDLE_ROW = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
    private static final Row EMPTY_BOTTOM_ROW = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
    private static final int NO_OFFSET = 0;

    private final Row topRow;
    private final Row bottomRow;
    private final Row middleRow;
    private final int occupiedCellIndex;

    public GridHasNoFreeCellAtIndexTest(Row top, Row middle, Row bottom, int index) {
        this.topRow = top;
        this.middleRow = middle;
        this.bottomRow = bottom;
        this.occupiedCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        aRowBuilder().withHorizontalRow(X, O, X, NO_OFFSET).build(),
                        EMPTY_MIDDLE_ROW,
                        EMPTY_BOTTOM_ROW,
                        2
                },
                {
                        EMPTY_TOP_ROW,
                        aRowBuilder().withHorizontalRow(X, O, X, NUMBER_OF_CELLS_IN_ROW).build(),
                        EMPTY_BOTTOM_ROW,
                        4
                },
                {
                        EMPTY_TOP_ROW,
                        EMPTY_MIDDLE_ROW,
                        aRowBuilder().withHorizontalRow(X, O, X, BOTTOM_ROW_OFFSET).build(),
                        8
                }

        });
    }

    @Test
    public void indicatesAGridHasNoFreeCellAtSpecifiedIndex() {
        Grid gridWithOccupiedCell = new Grid(topRow, middleRow, bottomRow);
        assertThat(gridWithOccupiedCell.isEmptyAt(occupiedCellIndex), is(false));
    }
}

