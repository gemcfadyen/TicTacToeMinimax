package tictactoe.player.gameplan;

import tictactoe.Symbol;
import tictactoe.grid.Grid;

import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class Block implements GamePlan {

    private final GamePlan gamePlan;

    public Block(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    @Override
    public int execute(Grid grid, Symbol symbol) {
        return gamePlan.execute(grid, opponent(symbol));
    }

    private Symbol opponent(Symbol symbol) {
        return (symbol == X) ? O : X;
    }
}
