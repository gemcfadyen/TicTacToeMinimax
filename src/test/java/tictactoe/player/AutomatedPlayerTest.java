package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.grid.GridFactory;
import tictactoe.prompt.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.CENTRE;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    @Mock private Prompt prompt;
    private Player automatedPlayer;

    @Before
    public void setup() {
        automatedPlayer = new AutomatedPlayer(X, prompt);
    }

    @Test
    public void returnsPlayerSymbol() {
        assertThat(automatedPlayer.getSymbol(), is(X));
    }

    @Test
    public void takesWinningMove() {
        Grid gridWithPotentialWin = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, X, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPotentialWin), is(1));
    }

    @Test
    public void displaysMoveThatWasTaken() {
        Grid gridWithPotentialWin = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, X, O, BOTTOM_ROW_OFFSET).build()
        );

        automatedPlayer.nextMoveOn(gridWithPotentialWin);

        verify(prompt).display(X, 1);
    }

    @Test(expected = NoMovesAvailableException.class)
    public void noStrategicMoveAvailable() {
        Grid gridWithNoMoves = new Grid(
                aRowBuilder().withHorizontalRow(O, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(O, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, X, O, BOTTOM_ROW_OFFSET).build()
        );

        automatedPlayer.nextMoveOn(gridWithNoMoves);
    }

    @Test
    public void takeOpponentsWinningMove() {
        Grid gridWithOpponentPotentialWin = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, X, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithOpponentPotentialWin), is(1));
    }

    @Test
    public void takeOpeningMoveInTopLeftCell() {
        Grid emptyGrid = GridFactory.createEmptyGrid();

        assertThat(automatedPlayer.nextMoveOn(emptyGrid), is(0));
    }

    @Test
    public void startForkWhenCentreIsOccupied() {
        Grid gridWithPossibleFork = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPossibleFork), is(8));
    }

    @Test
    public void startForkFromTopRowWhenCentreIsVacant() {
        Grid gridWithPossibleFork = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPossibleFork), is(2));
    }

    @Test
    public void startForkFromVerticalRowWhenCentreIsVacant() {
        Grid gridWithPossibleFork = new Grid(
                aRowBuilder().withHorizontalRow(X, O, X, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPossibleFork), is(6));
    }

    @Test
    public void startForkFromBottomRowWhenCentreIsVacant() {
        Grid gridWithPossibleFork = new Grid(
                aRowBuilder().withHorizontalRow(X, O, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPossibleFork), is(8));
    }

    @Test
    public void startForkAroundDiagonalsWhenCentreIsVacant() {
        Grid gridWithPossibleFork = new Grid(
                aRowBuilder().withHorizontalRow(X, O, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithPossibleFork), is(8));
    }

    @Test
    public void takeCentreMove() {
        Grid gridWithEmptyCentreAndNoForkFormations = new Grid(
                aRowBuilder().withHorizontalRow(X, O, X, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithEmptyCentreAndNoForkFormations), is(CENTRE));

    }

    @Test
    public void takeOppositeCornerMove() {
        Grid gridWithOppositeCornerFree = new Grid(
                aRowBuilder().withHorizontalRow(O, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, O, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithOppositeCornerFree), is(8));
    }

    @Test
    public void blockOpponentsForkFromCentre() {
        Grid gridToBlockOpponentsFork = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridToBlockOpponentsFork), is(1));
    }

    @Test
    public void blockOpponentsForkAroundCorner() {
        Grid gridToBlockOpponentsFork = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridToBlockOpponentsFork), is(1));
    }

    @Test
    public void blockCornerForks() {
        Grid gridToBlockOpponentsFork = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridToBlockOpponentsFork), is(2));
    }

    @Test
    public void takeVacantCornerMove() {
        Grid gridWithFreeCorners = new Grid(
                aRowBuilder().withHorizontalRow(O, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithFreeCorners), is(6));
    }

    @Test
    public void takeAnyVacantCellMove() {
        Grid gridWithEmptyEdge = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(O, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(gridWithEmptyEdge), is(1));
    }
}
