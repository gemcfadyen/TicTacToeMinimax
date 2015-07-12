package tictactoe.player.gameplan.winningmoves;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

public class TakeWinningMove implements GamePlan {

    public int execute(Grid grid, Symbol symbol) {
        GameStatus status = grid.evaluateWinningMoveFor(symbol);

        return status.hasPotentialMove()
                ? status.getIndexOfMove()
                : NO_SUGGESTED_MOVE;
    }
}
