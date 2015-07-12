package tictactoe.grid;

import org.junit.Test;
import tictactoe.grid.status.GameStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

public class GridCreateForkFormationTest {
    private static final int NO_SUGGESTED_MOVE = -1;

    @Test
    public void takeOppositeCornerOfTopLeftToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForForksWhenCentreIsOccupied(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(8));
    }

    @Test
    public void takeOppositeCornerOfTopRightToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForForksWhenCentreIsOccupied(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(6));
    }

    @Test
    public void takeOppositeCornerOfBottomLeftToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForForksWhenCentreIsOccupied(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(2));
    }

    @Test
    public void noForksFoundWhenCentreIsOccupied() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, O, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForForksWhenCentreIsOccupied(X);

        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }


    @Test
    public void takeOppositeCornerOfBottomRightToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromBottomRow(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(6));
    }

    @Test
    public void noForksFoundAroundBottomRow() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(O, VACANT, X, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromBottomRow(X);

        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }

    @Test
    public void takeRemainingCornerOnTopRowToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromTopRow(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(2));
    }

    @Test
    public void noForksFoundAroundTopRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, O, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromTopRow(X);

        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }

    @Test
    public void takeTopLeftCornerToStartFork() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromVerticalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(0));
    }

    @Test
    public void noForksFoundAroundVerticalRows() {
        Row topRow = aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build();
        Grid grid = new Grid(topRow, middleRow, bottomRow);

        GameStatus gameStatus = grid.evaluateForksFromVerticalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }


    @Test
    public void forkFormationAroundLeftDiagonalOnBottomRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, O, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(8));
    }

    @Test
    public void forkFormationAroundLeftDiagonalOnTopRow() {
        Row topRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(O, VACANT, X, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(0));
    }

    @Test
    public void forkFormationAroundRightDiagonalOnBottomRow() {
        Row topRow = aRowBuilder().withHorizontalRow(O, VACANT, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(6));
    }

    @Test
    public void forkFormationAroundRightDiagonalOnTopRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(2));
    }

    @Test
    public void forkFormationAroundLeftDiagonalWithVerticalRow() {
        Row topRow = aRowBuilder().withHorizontalRow(X, O, X, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(8));
    }

    @Test
    public void forkFormationAroundRightDiagonalWithVerticalRow() {
        Row topRow = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, O, X, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(true));
        assertThat(gameStatus.getIndexOfMove(), is(2));
    }

    @Test
    public void noForksFoundAroundDiagonal() {
        Row topRow = aRowBuilder().withHorizontalRow(X, O, VACANT, 0).build();
        Row middleRow = aRowBuilder().withHorizontalRow(O, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build();
        Row bottomRow = aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build();

        Grid grid = new Grid(topRow, middleRow, bottomRow);
        GameStatus gameStatus = grid.evaluateForksFromDiagonalRows(X);

        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }

}
