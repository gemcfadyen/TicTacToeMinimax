package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;

import java.util.Random;

import static tictactoe.grid.Grid.TOTAL_CELLS;

public class RandomCellTestPlayer implements Player {
    private static final int MIN_INDEX = 1;

    private final Random randomNumberGenerator;
    private final Symbol symbol;
    private Prompt prompt;

    public RandomCellTestPlayer(Symbol symbol, Prompt prompt) {
        randomNumberGenerator = new Random();
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        int randomCellOffset = generateRandomCellOffsetWithinRange();
        randomCellOffset = regenerateUntilVacantCellFound(grid, randomCellOffset);

        prompt.display(symbol, randomCellOffset);
        return randomCellOffset;
    }

    private int regenerateUntilVacantCellFound(Grid grid, int randomCellOffset) {
        while (!grid.isEmptyAt(randomCellOffset)) {
            randomCellOffset = generateRandomCellOffsetWithinRange();
        }
        return randomCellOffset;
    }
    private int generateRandomCellOffsetWithinRange() {
        return randomNumberGenerator.nextInt(TOTAL_CELLS - MIN_INDEX + 1) + 1;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }
}
