package tictactoe.player;


import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCell;
import tictactoe.player.gameplan.cornermoves.TakeEmptyCorner;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerToOpponent;
import tictactoe.player.gameplan.cornermoves.TopLeftCorner;
import tictactoe.player.gameplan.forking.BlockOpponentFormingCornerTrapFork;
import tictactoe.player.gameplan.forking.BlockOpponentFormingForksInRows;
import tictactoe.player.gameplan.forking.ForkFormationFromTopRowWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInDiagonalsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInVerticalRowsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationWhenCentreCellIsOccupied;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;
import tictactoe.prompt.Prompt;


public class AutomatedPlayer implements Player {
    private static final int NO_POTENTIAL_MOVE = -1;

    private final Symbol symbol;
    private final GamePlan[] orderedGamePlan;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
        this.orderedGamePlan = orderedGamePlan();
    }

    @Override
    public int nextMoveOn(Grid grid) {
        for (GamePlan gamePlan : orderedGamePlan) {
            int move = gamePlan.execute(grid, symbol);
            if (move != NO_POTENTIAL_MOVE) {
                prompt.display(symbol, move);
                return move;
            }
        }

        throw new NoMovesAvailableException("No moves available for player");
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }


    private GamePlan[] orderedGamePlan() {
        return new GamePlan[]{
                new TakeWinningMove(),
                new Block(new TakeWinningMove()),

                new TopLeftCorner(),

                new ForkFormationWhenCentreCellIsOccupied(),
                new ForkFormationFromTopRowWhenCentreIsVacant(),
                new ForkFormationInVerticalRowsWhenCentreIsVacant(),
                new ForkFormationInDiagonalsWhenCentreIsVacant(),

                new TakeCentreMove(),
                new TakeOppositeCornerToOpponent(),

                new BlockOpponentFormingCornerTrapFork(),
                new BlockOpponentFormingForksInRows(),

                new TakeEmptyCorner(),
                new TakeVacantCell()
        };
    }
}

class NoMovesAvailableException extends RuntimeException {

    public NoMovesAvailableException(String message) {
        super(message);
    }
}
