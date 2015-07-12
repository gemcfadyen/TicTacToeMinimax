package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

public interface Player {
    int nextMoveOn(Grid grid);

    Symbol getSymbol();
}
