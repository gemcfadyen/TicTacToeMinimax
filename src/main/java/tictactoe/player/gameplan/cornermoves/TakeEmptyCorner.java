package tictactoe.player.gameplan.cornermoves;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.CornerMove;
import tictactoe.player.gameplan.GamePlan;

import java.util.Map;

public class TakeEmptyCorner extends CornerMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        Map<Integer, Integer> vacantCorners = applyToCorners(getVacantCorners(grid));
        return has(vacantCorners)
                ? first(vacantCorners)
                : NO_SUGGESTED_MOVE;
    }

    private Predicate<Map.Entry<Integer, Integer>> getVacantCorners(final Grid grid) {
        return corner -> grid.isEmptyAt(corner.getKey());
    }
}

