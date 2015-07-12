package tictactoe.player.gameplan;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.CENTRE;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public class TakeCentreMoveTest {
    private TakeCentreMove takeCentreMove = new TakeCentreMove();

    @Test
    public void takesCentreMove() {
        Grid grid = GridFactory.createEmptyGrid();
        assertThat(takeCentreMove.execute(grid, X), is(CENTRE));
    }

    @Test
    public void noSuggestedMoveWhenCentreCellIsOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        grid.update(CENTRE, O);
        assertThat(takeCentreMove.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }
}
