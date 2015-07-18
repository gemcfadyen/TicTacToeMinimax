package tictactoe;

import com.google.common.collect.ImmutableList;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import java.util.List;
import java.util.function.Function;

import static tictactoe.grid.GridFactory.createEmptyGrid;
import static tictactoe.player.PlayerFactory.createPlayers;
import static tictactoe.prompt.PromptFactory.createCommandLinePrompt;

public class Game {
    private static final Void VOID = null;
    private static final int AUTOMATED_PLAYER_INDEX = 0;
    private static final int HUMAN_PLAYER_INDEX = 1;
    private static final String HUMAN_PLAYER = "H";
    private static final String AUTOMATED_PLAYER = "A";
    private static final String PLAY_AGAIN = "Y";
    private static final String DONT_PLAY_AGAIN = "N";

    private List<String> validOpponentChoices = ImmutableList.of(HUMAN_PLAYER, AUTOMATED_PLAYER);
    private List<String> validReplayOptions = ImmutableList.of(PLAY_AGAIN, DONT_PLAY_AGAIN);

    private final Grid grid;
    private Player[] players;
    private final Prompt prompt;

    protected Game(Grid grid, Prompt prompt, Player[] players) {
        this.grid = grid;
        this.prompt = prompt;
        this.players = players;
    }

    public static void main(String[] args) {
        Prompt prompt = createCommandLinePrompt();
        Game game = new Game(createEmptyGrid(), prompt, createPlayers(prompt));
        game.play();
    }

    public void play() {
        boolean isGameInProgress = true;
        while (isGameInProgress) {
            grid.reset();
            playGame();
            isGameInProgress = replay();
        }
    }

    private void playGame() {
        int currentPlayerIndex = determineOpeningPlayer();
        prompt.display(grid.getCells());
        for (int i = 0; i < Grid.TOTAL_CELLS; i++) {

            grid.update(playersMove(currentPlayerIndex), playersSymbol(currentPlayerIndex));
            prompt.display(grid.getCells());

            if (isWinningMove()) {
                break;
            }
            currentPlayerIndex = opponent(currentPlayerIndex);
        }
        prompt.displayGameOver();
    }

    private Symbol playersSymbol(int currentPlayerIndex) {
        return players[currentPlayerIndex].getSymbol();
    }

    private int playersMove(int currentPlayerIndex) {
        return players[currentPlayerIndex].nextMoveOn(grid);
    }

    private boolean isWinningMove() {
        GameStatus gameStatus = grid.evaluateWinningStatus();
        if (gameStatus.hasWinner()) {
            prompt.displayWinningMessageFor(gameStatus.winningSymbol());
            return true;
        }
        return false;
    }

    private int opponent(int playersTurn) {
        return playersTurn == HUMAN_PLAYER_INDEX
                ? AUTOMATED_PLAYER_INDEX
                : HUMAN_PLAYER_INDEX;
    }

    private boolean replay() {
        prompt.promptPlayerToStartNewGame();
        String playAgainOption = prompt.readsInput();
        playAgainOption = repromptUntilValid(playAgainOption, validReplayOptions, repromptReplayOption());
        return playAgainOption.equalsIgnoreCase(PLAY_AGAIN);
    }

    private Function<Prompt, Void> repromptReplayOption() {
        return prompt -> {
            prompt.promptPlayerToStartNewGame();
            return VOID;
        };
    }

    private int determineOpeningPlayer() {
        prompt.promptForOrderOfPlay();
        String playerToGoFirst = prompt.readsInput();
        playerToGoFirst = repromptUntilValid(playerToGoFirst, validOpponentChoices, repromptOrderOfPlay());

        return playerToGoFirst.equalsIgnoreCase(HUMAN_PLAYER)
                ? HUMAN_PLAYER_INDEX
                : AUTOMATED_PLAYER_INDEX;
    }

    private Function<Prompt, Void> repromptOrderOfPlay() {
        return prompt -> {
            prompt.promptForOrderOfPlay();
            return VOID;
        };
    }

    private String repromptUntilValid(String playersInput, List<String> validOptions, Function<Prompt, Void> reprompt) {
        String opponentOption = playersInput;
        while (!valid(opponentOption, validOptions)) {
            reprompt.apply(prompt);
            opponentOption = prompt.readsInput();
        }
        return opponentOption;
    }


    private boolean valid(String playersInput, List<String> validOptions) {
        return validOptions.contains(playersInput.toUpperCase());
    }
}
