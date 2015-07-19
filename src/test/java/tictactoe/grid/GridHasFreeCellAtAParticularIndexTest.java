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
import static tictactoe.grid.GridFactory.createEmptyGrid;
import static tictactoe.grid.GridFactory.createGridWith;

@RunWith(Parameterized.class)
public class GridHasFreeCellAtAParticularIndexTest {
    private Grid gridWithFreeCell;
    private final int freeCellIndex;

    public GridHasFreeCellAtAParticularIndexTest(Grid gridWithFreeCell, int index) {
        this.gridWithFreeCell = gridWithFreeCell;
        this.freeCellIndex = index;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        createEmptyGrid(),
                        1
                },
                {
                        createGridWith(
                                VACANT, X, O,
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT),
                        1
                },
                {

                        createGridWith(
                                VACANT, VACANT, VACANT,
                                O, O, VACANT,
                                VACANT, VACANT, VACANT),
                        6
                },
                {

                        createGridWith(
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT,
                                X, VACANT, X),
                        8
                }

        });
    }

    @Test
    public void indicatesAGridHasFreeCellAtGivenIndex() {
        assertThat(gridWithFreeCell.isEmptyAt(freeCellIndex), is(true));
    }
}