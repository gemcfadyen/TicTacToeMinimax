package tictactoe.player;

import tictactoe.prompt.Prompt;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class PlayerFactory {

    public static Player[] createPlayers(Prompt prompt) {
        Player humanPlayer = new HumanPlayer(O, prompt);
        Player automatedPlayer = new AutomatedPlayer(X, prompt);

        return new Player[] { humanPlayer, automatedPlayer };
    }
}
