package tictactoe.player.gameplan.forking;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.gameplan.GamePlan;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class BlockOpponentFormingCornerTrapFork implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        GameStatus gameStatus = grid.evaluateTopRowCornerTraps(opponent(symbol));

        return gameStatus.hasPotentialMove()
                ? gameStatus.getIndexOfMove()
                : grid.evaluateBottomRowCornerTraps(opponent(symbol)).getIndexOfMove();
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}
