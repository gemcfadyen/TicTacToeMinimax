package tictactoe.prompt;

import tictactoe.Symbol;
import tictactoe.grid.Cell;

import java.util.List;

public interface Prompt {
    String readsInput();

    void promptPlayerToStartNewGame();
    void promptPlayerForNextMove();
    void promptForOrderOfPlay();

    void displayWinningMessageFor(Symbol symbol);
    void displayGameOver();
    void display(List<Cell> cells);
    void display(Symbol symbol, int move);
}
