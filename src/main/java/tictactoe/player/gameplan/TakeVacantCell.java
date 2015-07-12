package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;

public class TakeVacantCell implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        GameStatus gameStatus = grid.getFirstVacantCell();

        return gameStatus.hasPotentialMove()
                ? gameStatus.getIndexOfMove()
                : NO_SUGGESTED_MOVE;
    }
}
