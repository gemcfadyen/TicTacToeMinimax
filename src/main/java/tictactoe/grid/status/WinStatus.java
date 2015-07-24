package tictactoe.grid.status;

import tictactoe.Symbol;
import tictactoe.grid.State;

import static tictactoe.grid.State.NO_WIN;
import static tictactoe.grid.State.WIN;

public final class WinStatus {
    private Symbol winningSymbol;
    private final State state;

    private WinStatus(State state) {
        this.state = state;
    }

    private WinStatus(State state, Symbol winningSymbol) {
        this.state = state;
        this.winningSymbol = winningSymbol;
    }

    public static WinStatus noWin() {
        return new WinStatus(NO_WIN);
    }

    public static WinStatus winFor(Symbol symbol) {
        return new WinStatus(WIN, symbol);
    }

    public Symbol winningSymbol() {
        return winningSymbol;
    }

    public boolean hasWinner() {
        return state == WIN;
    }
}



