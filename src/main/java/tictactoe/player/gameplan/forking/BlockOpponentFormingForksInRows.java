package tictactoe.player.gameplan.forking;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.CornerMove;
import tictactoe.player.gameplan.GamePlan;

import java.util.Map;

import static tictactoe.grid.Grid.CENTRE;

public class BlockOpponentFormingForksInRows extends CornerMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        Map<Integer, Integer> vacantCorners = applyToCorners(getVacantCorners(grid));
        if (has(vacantCorners) && grid.getSymbolAtCellWithOffset(CENTRE).equals(symbol)) {
            return grid.getVacantNonCornerCellOnEdge().getIndexOfMove();
        }
        return NO_SUGGESTED_MOVE;
    }


    private Predicate<Map.Entry<Integer, Integer>> getVacantCorners(final Grid grid) {
        return corner -> grid.isEmptyAt(corner.getKey());
    }
}
