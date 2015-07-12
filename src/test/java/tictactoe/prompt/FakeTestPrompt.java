package tictactoe.prompt;

import org.apache.log4j.Logger;
import tictactoe.Symbol;
import tictactoe.grid.Row;

import java.util.List;

public class FakeTestPrompt implements Prompt {
    private static final Logger LOGGER = Logger.getLogger(FakeTestPrompt.class);
    private static final String GAME_OVER = "N";
    private static final String REPLAY_GAME = "Y";

    private int numberOfGamesLeftToPlay;
    private Symbol symbolForNonAutomatedPlayer;
    private String[] inputs;
    private int indexOfInput = 0;
    private int numberOfWinsForO = 0;
    private int numberOfWinsForX = 0;

    public FakeTestPrompt(int numberOfGamesLeftToPlay, String startingPlayerType, Symbol symbolForNonAutomatedPlayer) {
        this.numberOfGamesLeftToPlay = numberOfGamesLeftToPlay;
        inputs = new String[] {startingPlayerType, REPLAY_GAME};
        this.symbolForNonAutomatedPlayer = symbolForNonAutomatedPlayer;
    }

    @Override
    public String readsInput() {
        String valueToReturn = GAME_OVER;
        if (numberOfGamesLeftToPlay >= 0) {
            valueToReturn = inputs[indexOfInput];
            togglePlayersResponseIndex();
        }
        numberOfGamesLeftToPlay--;
        return valueToReturn;
    }

    @Override
    public void displayWinningMessageFor(Symbol symbol) {
        LOGGER.debug("Win for [" + symbol + "]");
        if (symbol.equals(symbolForNonAutomatedPlayer)) {
            numberOfWinsForO++;
        } else {
            numberOfWinsForX++;
        }
    }

    public int totalWinsForNonAutomatedPlayer() {
        return numberOfWinsForO;
    }
    public int totalWinsForAutomatedPlayer() {
        return numberOfWinsForX;
    }

    private void togglePlayersResponseIndex() {
        if (indexOfInput == 1) {
            indexOfInput = 0;
        } else {
            indexOfInput = 1;
        }
    }

    @Override
    public void display(Symbol symbol, int move) {
        LOGGER.debug("Random Test Player Generated Cell Offset: [" + move + "]");
    }

    @Override
    public void promptPlayerToStartNewGame() {
    }

    @Override
    public void promptPlayerForNextMove() {
    }

    @Override
    public void promptForOrderOfPlay() {
    }

    @Override
    public void displayGameOver() {
    }

    @Override
    public void display(List<Row> rows) {
    }
}
