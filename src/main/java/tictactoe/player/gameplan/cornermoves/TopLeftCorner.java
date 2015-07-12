package tictactoe.player.gameplan.cornermoves;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.GamePlan;

public class TopLeftCorner implements GamePlan {
    private static final int TOP_LEFT_CORNER = 0;

    @Override
    public int execute(Grid grid, Symbol symbol) {
        if (grid.isEmpty()) {
            return TOP_LEFT_CORNER;
        }
        return NO_SUGGESTED_MOVE;
    }
}
