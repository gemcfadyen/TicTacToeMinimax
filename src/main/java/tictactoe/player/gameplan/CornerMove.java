package tictactoe.player.gameplan;

import com.google.common.base.Predicate;

import java.util.Map;

import static com.google.common.collect.Maps.filterEntries;
import static tictactoe.grid.Grid.DIAGONAL_OPPOSITE_CORNERS;

public class CornerMove {

    protected Map<Integer, Integer> applyToCorners(Predicate<Map.Entry<Integer, Integer>> function) {
        return filterEntries(DIAGONAL_OPPOSITE_CORNERS, function);
    }

    protected boolean has(Map<Integer, Integer> vacantCorners) {
        return vacantCorners != null && vacantCorners.size() > 0;
    }

    protected Integer first(Map<Integer, Integer> vacantCorners) {
        return vacantCorners.keySet().iterator().next();
    }
}
