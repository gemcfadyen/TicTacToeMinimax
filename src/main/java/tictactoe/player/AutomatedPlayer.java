package tictactoe.player;


import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;


public class AutomatedPlayer implements Player {
    private final Symbol symbol;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        prompt.display(grid.rows());

        return -1;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}

