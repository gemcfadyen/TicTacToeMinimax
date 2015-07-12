package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

import static tictactoe.grid.Grid.CENTRE;

public class TakeCentreMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        return grid.centreCellTaken()
                ? NO_SUGGESTED_MOVE
                : CENTRE;
    }
}
