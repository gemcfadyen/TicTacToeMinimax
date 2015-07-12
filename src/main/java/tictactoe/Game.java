package tictactoe;

import com.google.common.collect.ImmutableList;
import tictactoe.grid.Grid;
import tictactoe.grid.status.GameStatus;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import java.util.List;
import java.util.function.Function;

import static tictactoe.grid.GridFactory.createEmptyGrid;
import static tictactoe.player.OrderedPlayerFactory.createPlayersInOrder;
import static tictactoe.prompt.PromptFactory.createCommandLinePrompt;

public class Game {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final Void VOID = null;
    private List<String> validOpponentChoices = ImmutableList.of("H", "A");
    private List<String> validReplayOptions = ImmutableList.of("Y", "N");

    private final Grid grid;
    private Player[] players;
    private final Prompt prompt;

    Game() {
        grid = createEmptyGrid();
        prompt = createCommandLinePrompt();
        players = new Player[NUMBER_OF_PLAYERS];
    }

    protected Game(Grid grid, Prompt prompt) {
        this.grid = grid;
        this.players = new Player[NUMBER_OF_PLAYERS];
        this.prompt = prompt;
    }

    protected Game(Grid grid, Prompt prompt, Player[] players) {
        this.grid = grid;
        this.prompt = prompt;
        this.players = players;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    public void play() {
        boolean isGameInProgress = true;
        while (isGameInProgress) {
            grid.reset();
            players = configureOrderOfPlay();
            playGame();
            isGameInProgress = replay();
        }
    }

    protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
        return createPlayersInOrder(typeOfPlayerToGoFirst, prompt);
    }

    private void playGame() {
        prompt.display(grid.rows());
        int currentPlayerIndex = FIRST_PLAYER;
        for (int i = 0; i < Grid.TOTAL_CELLS; i++) {

            grid.update(playersMove(currentPlayerIndex), playersSymbol(currentPlayerIndex));
            prompt.display(grid.rows());

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
        return playersTurn == FIRST_PLAYER
                ? SECOND_PLAYER
                : FIRST_PLAYER;
    }

    private boolean replay() {
        prompt.promptPlayerToStartNewGame();
        String playAgainOption = prompt.readsInput();
        playAgainOption = repromptUntilValid(playAgainOption, validReplayOptions, repromptReplayOption());
        return playAgainOption.equalsIgnoreCase("Y");
    }

    private Function<Prompt, Void> repromptReplayOption() {
        return prompt -> {
            prompt.promptPlayerToStartNewGame();
            return VOID;
        };
    }

    private Player[] configureOrderOfPlay() {
        prompt.promptForOrderOfPlay();
        String playerToGoFirst = prompt.readsInput();
        playerToGoFirst = repromptUntilValid(playerToGoFirst, validOpponentChoices, repromptOrderOfPlay());
        return initialiseOrderedPlayers(playerToGoFirst);
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
