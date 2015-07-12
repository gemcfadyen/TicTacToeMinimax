package tictactoe.grid.status;

import tictactoe.Symbol;
import tictactoe.grid.State;

import static tictactoe.grid.State.NO_POTENTIAL_MOVE;
import static tictactoe.grid.State.NO_WIN;
import static tictactoe.grid.State.POTENTIAL_MOVE;
import static tictactoe.grid.State.WIN;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public final class GameStatus {
    private Symbol winningSymbol;
    private int indexOfMove = NO_SUGGESTED_MOVE;
    private final State state;

    private GameStatus(State state) {
        this.state = state;
    }

    private GameStatus(State state, Symbol winningSymbol) {
        this.state = state;
        this.winningSymbol = winningSymbol;
    }

    private GameStatus(State state, int indexOfMove) {
        this.state = state;
        this.indexOfMove = indexOfMove;
    }

    public static GameStatus noWin() {
        return new GameStatus(NO_WIN);
    }

    public static GameStatus winFor(Symbol symbol) {
        return new GameStatus(WIN, symbol);
    }

    public static GameStatus potentialMoveAt(int index) {
        return new GameStatus(POTENTIAL_MOVE, index);
    }

    public static GameStatus noPotentialMove() {
        return new GameStatus(NO_POTENTIAL_MOVE);
    }

    public Symbol winningSymbol() {
        return winningSymbol;
    }

    public boolean hasWinner() {
        return state == WIN;
    }

    public boolean hasPotentialMove() {
        return state == POTENTIAL_MOVE;
    }

    public int getIndexOfMove() {
        return indexOfMove;
    }

}



