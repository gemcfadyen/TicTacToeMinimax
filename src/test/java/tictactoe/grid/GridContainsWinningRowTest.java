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
public class GridContainsWinningRowTest {
    private final Grid gridWithPotentialWinningRow;
    private final boolean hasWinningRow;

    public GridContainsWinningRowTest(Grid gridWithPotentialWinningRow, boolean hasWinningRow) {
        this.gridWithPotentialWinningRow = gridWithPotentialWinningRow;
        this.hasWinningRow = hasWinningRow;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        createGridWith(
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT),
                        false
                },
                {
                        createGridWith(
                                X, X, X,
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT),
                        true
                },
                {
                        createGridWith(
                                VACANT, VACANT, VACANT,
                                O, O, O,
                                VACANT, VACANT, VACANT),
                        true
                },
                {
                        createGridWith(
                                VACANT, VACANT, VACANT,
                                VACANT, VACANT, VACANT,
                                X, X, X),
                        true
                },
                {
                        createGridWith(
                                X, VACANT, VACANT,
                                X, VACANT, VACANT,
                                X, VACANT, VACANT),
                        true
                },
                {
                        createGridWith(
                                VACANT, X, VACANT,
                                VACANT, X, VACANT,
                                VACANT, X, VACANT),
                        true
                },
                {
                        createGridWith(
                                VACANT, VACANT, X,
                                VACANT, VACANT, X,
                                VACANT, VACANT, X),
                        true
                },
                {
                        createGridWith(
                                X, VACANT, VACANT,
                                VACANT, X, VACANT,
                                VACANT, VACANT, X),
                        true
                },
                {
                        createGridWith(
                                VACANT, VACANT, X,
                                VACANT, X, VACANT,
                                X, VACANT, VACANT),
                        true
                }
        });
    }


    @Test
    public void identifiesWhenAWinningRowExistsInTheGrid() {
        assertThat(gridWithPotentialWinningRow.evaluateWinningStatus().hasWinner(), is(hasWinningRow));
    }
}