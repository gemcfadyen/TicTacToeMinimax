package tictactoe.player;

import tictactoe.prompt.Prompt;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class PlayerFactory {

    public static Player[] createPlayers(Prompt prompt) {
        Player automatedPlayer = new AutomatedPlayer(X, prompt);
        Player humanPlayer = new HumanPlayer(O, prompt);

        return new Player[] { automatedPlayer, humanPlayer };
    }
}
