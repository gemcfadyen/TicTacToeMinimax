package tictactoe.grid;

import static tictactoe.Symbol.VACANT;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

public class GridFactory {
    private static final int STARTING_INDEX = 0;

    public static Grid createEmptyGrid() {
        return new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, STARTING_INDEX).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );
    }
}
