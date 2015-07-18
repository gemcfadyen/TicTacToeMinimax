package tictactoe.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.GridFactory.createGridWith;

public class GridTest {
    private Grid grid;

    @Before
    public void setup() {
        grid = createGridWith(
                X, VACANT, X,
                X, VACANT, VACANT,
                VACANT, O, X);
    }

    @Test
    public void updateGridAtGivenIndex() {
        grid.update(6, X);

        assertThat(grid.isEmptyAt(6), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionThrownIfOffsetIsOutOfRange() {
        grid.update(10, X);
    }

    @Test
    public void resetGridToEmpty() {
        Grid gridWithOneCellOccupied =
                createGridWith(
                        VACANT, VACANT, VACANT,
                        X, VACANT, VACANT,
                        VACANT, VACANT, VACANT);

        gridWithOneCellOccupied.reset();

        assertThat(gridWithOneCellOccupied.isEmptyAt(3), is(true));
    }
}
