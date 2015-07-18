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
import static tictactoe.grid.GridFactory.createGridWith;

@RunWith(Parameterized.class)
public class GridHasNoFreeCellAtIndexTest {
    private Grid gridWithOccupiedCell;
    private final int occupiedCellIndex;

    public GridHasNoFreeCellAtIndexTest(Grid grid, int index) {
        this.gridWithOccupiedCell = grid;
        this.occupiedCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        createGridWith(
                                X, O, X,
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT),
                        2
                },
                {
                        createGridWith(
                                VACANT, VACANT, VACANT,
                                X, O, X,
                                VACANT, VACANT, VACANT),
                        4
                },
                {
                        createGridWith(
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT,
                                X, O, X),
                        8
                }

        });
    }

    @Test
    public void indicatesAGridHasNoFreeCellAtSpecifiedIndex() {
        assertThat(gridWithOccupiedCell.isEmptyAt(occupiedCellIndex), is(false));
    }
}