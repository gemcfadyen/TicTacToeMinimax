package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;
import tictactoe.prompt.PromptFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.RowBuilder.aRowBuilder;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    private Prompt prompt = PromptFactory.createCommandLinePrompt();
    private Player automatedPlayer;

    @Before
    public void setup() {
        automatedPlayer = new AutomatedPlayer(X, prompt);
    }

    @Test
    public void playerHasCorrectSymbol() {
        assertThat(automatedPlayer.getSymbol(), is(X));
    }

    @Test
    public void takesWinningMoveInTopRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, X, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void takesWinningMoveInMiddleRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }


    @Test
    public void takesWinningMoveInBottomRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }


    @Test
    public void takesWinningMoveInLeftColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, O, O, 0).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(6));
    }

    @Test
    public void takesWinningMoveInMiddleColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void takesWinningMoveInRightColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(5));
    }

    @Test
    public void takesWinningMoveInForwardDiagonal() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, O, X, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));
    }

    @Test
    public void takesWinningMoveInBackwardsDiagonal() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void blocksOpponentsWinInLeftVerticalColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, X, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blocksOpponentsWinInMiddleVerticalColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void blocksOpponentsWinInRightVerticalColumn() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, X, O, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(8));
    }

    @Test
    public void blocksOpponentsWinInTopRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(O, VACANT, O, 0).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, X, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void blocksOpponentsWinInMiddleRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(O, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(5));
    }

    @Test
    public void blocksOpponentsMoveInBottomRow() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, X, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

    @Test
    public void blocksOpponentsWinInBackwardsDiagonal() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(O, X, X, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));
    }

    @Test
    public void blocksOpponentsWinInForwardDiagonal() {
        Grid grid = new Grid(
                aRowBuilder().withHorizontalRow(X, VACANT, VACANT, 0).build(),
                aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NUMBER_OF_CELLS_IN_ROW).build(),
                aRowBuilder().withHorizontalRow(O, X, VACANT, BOTTOM_ROW_OFFSET).build()
        );

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }
}
