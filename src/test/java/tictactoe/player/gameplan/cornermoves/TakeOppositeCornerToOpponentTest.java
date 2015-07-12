package tictactoe.player.gameplan.cornermoves;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public class TakeOppositeCornerToOpponentTest {
    private TakeOppositeCornerToOpponent oppositeCornerMove = new TakeOppositeCornerToOpponent();

    @Test
    public void takeCornerOppositeOpponent() {
        Grid grid = GridFactory.createEmptyGrid();
        grid.update(0, O);
        assertThat(oppositeCornerMove.execute(grid, X), is(8));
    }

    @Test
    public void noSuggestedMoveIfAllCornersAreOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        updateGridCornersWithSymbols(grid);
        assertThat(oppositeCornerMove.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }

    @Test
    public void noSuggestedMoveIfOppositeCornerIsNotOccupiedByOpponent() {
        Grid grid = GridFactory.createEmptyGrid();
        grid.update(0, X);
        grid.update(2, X);
        assertThat(oppositeCornerMove.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }

    private void updateGridCornersWithSymbols(Grid grid) {
        int cornerIndex = 0;
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex += 2, O);
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex, O);
    }

}
