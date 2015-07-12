package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.prompt.Prompt;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class OrderedPlayerFactory {
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int SECOND_PLAYER_INDEX = 1;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final String AUTOMATED_PLAYER = "A";

    private static Player createHumanPlayer(Symbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }

    private static Player createAutomatedPlayer(Symbol symbol, Prompt prompt) {
        return new AutomatedPlayer(symbol, prompt);
    }

    public static Player[] createPlayersInOrder(String option, Prompt prompt) {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        players[FIRST_PLAYER_INDEX] = firstPlayer(option, prompt);
        players[SECOND_PLAYER_INDEX] = secondPlayer(option, prompt);

        return players;
    }

    private static Player firstPlayer(String option, Prompt prompt) {
        return option.equalsIgnoreCase(AUTOMATED_PLAYER)
                ? createAutomatedPlayer(X, prompt)
                : createHumanPlayer(O, prompt);
    }

    private static Player secondPlayer(String option, Prompt prompt) {
        return option.equalsIgnoreCase(AUTOMATED_PLAYER)
                ? createHumanPlayer(O, prompt)
                : createAutomatedPlayer(X, prompt);
    }

}
