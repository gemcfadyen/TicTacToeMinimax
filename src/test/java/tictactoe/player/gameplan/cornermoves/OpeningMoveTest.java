package tictactoe.player.gameplan.cornermoves;

import org.junit.Test;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public class OpeningMoveTest {
    private TopLeftCorner topLeftCorner = new TopLeftCorner();

    @Test
    public void takeCornerOnEmptyGrid() {
        Grid grid = GridFactory.createEmptyGrid();
        assertThat(topLeftCorner.execute(grid, X), is(0));
    }

    @Test
    public void noSuggestedMoveIfCornerCellsAreOccupied() {
        Grid grid = GridFactory.createEmptyGrid();
        updateGridCornersWithSymbols(grid);
        assertThat(topLeftCorner.execute(grid, X), is(NO_SUGGESTED_MOVE));
    }

    private void updateGridCornersWithSymbols(Grid grid) {
        int cornerIndex = 0;
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex += 2, O);
        grid.update(cornerIndex += 2, X);
        grid.update(cornerIndex, O);
    }


}
