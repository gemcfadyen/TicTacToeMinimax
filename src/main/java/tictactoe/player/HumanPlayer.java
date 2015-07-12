package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.inputvalidation.InputValidator;
import tictactoe.player.inputvalidation.IsDigit;
import tictactoe.player.inputvalidation.IsInGridBoundary;
import tictactoe.player.inputvalidation.IsVacantCell;
import tictactoe.prompt.Prompt;

public class HumanPlayer implements Player {
    private final Prompt prompt;
    private final Symbol symbol;

    public HumanPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        prompt.promptPlayerForNextMove();
        String nextMove = prompt.readsInput();

        return repromptUntilValid(nextMove, grid);
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    private int repromptUntilValid(String nextMove, Grid grid) {
        InputValidator[] validators = getInputValidators(grid);
        String move = nextMove;
        while (!valid(move, validators)) {
            prompt.promptPlayerForNextMove();
            move = prompt.readsInput();
        }
        return Integer.valueOf(move);
    }

    private boolean valid(String specifiedMove, InputValidator[] validators) {
        for (InputValidator validator : validators) {
            if (!validator.isValid(specifiedMove)) {
                return false;
            }
        }

        return true;
    }

    private InputValidator[] getInputValidators(Grid grid) {
        return new InputValidator[] {
                    new IsDigit(),
                    new IsInGridBoundary(),
                    new IsVacantCell(grid)};
    }
}