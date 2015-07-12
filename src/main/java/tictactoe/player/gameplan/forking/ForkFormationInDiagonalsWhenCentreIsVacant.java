package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

public class ForkFormationInDiagonalsWhenCentreIsVacant implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(symbol);

        return gameStatus.hasPotentialMove()
                ? gameStatus.getIndexOfMove()
                : NO_SUGGESTED_MOVE;
    }
}
