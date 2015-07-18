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
import static tictactoe.grid.GridFactory.createGridWith;

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
        Grid grid = createGridWith(
                X, X, VACANT,
                O, VACANT, VACANT,
                VACANT, VACANT, O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }

    @Test
    public void takesWinningMoveInMiddleRow() {
        Grid grid = createGridWith(
                VACANT, VACANT, O,
                VACANT, X, X,
                VACANT, O, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void takesWinningMoveInBottomRow() {
        Grid grid = createGridWith(
                VACANT, VACANT, O,
                VACANT, O, VACANT,
                X, VACANT, X);

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

    @Test
    public void takesWinningMoveInLeftColumn() {

        Grid grid = createGridWith(
                X, O, O,
                X, VACANT, VACANT,
                VACANT, VACANT, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(6));
    }

    @Test
    public void takesWinningMoveInMiddleColumn() {

        Grid grid = createGridWith(
                O, VACANT, VACANT,
                VACANT, X, O,
                VACANT, X, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void takesWinningMoveInRightColumn() {

        Grid grid = createGridWith(
                VACANT, VACANT, X,
                VACANT, O, VACANT,
                O, VACANT, X);

        assertThat(automatedPlayer.nextMoveOn(grid), is(5));
    }

    @Test
    public void takesWinningMoveInForwardDiagonal() {

        Grid grid = createGridWith(
                VACANT, O, X,
                VACANT, VACANT, VACANT,
                X, VACANT, O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));
    }

    @Test
    public void takesWinningMoveInBackwardsDiagonal() {

        Grid grid = createGridWith(
                VACANT, O, VACANT,
                VACANT, X, O,
                VACANT, VACANT, X);

        assertThat(automatedPlayer.nextMoveOn(grid), is(0));
    }

    @Test
    public void blocksOpponentsWinInLeftVerticalColumn() {

        Grid grid = createGridWith(
                O, VACANT, VACANT,
                VACANT, VACANT, X,
                O, X, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(3));
    }

    @Test
    public void blocksOpponentsWinInMiddleVerticalColumn() {


        Grid grid = createGridWith(
                X, VACANT, VACANT,
                VACANT, O, X,
                VACANT, O, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void blocksOpponentsWinInRightVerticalColumn() {
        Grid grid = createGridWith(
                X, X, O,
                VACANT, VACANT, O,
                VACANT, VACANT, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(8));
    }

    @Test
    public void blocksOpponentsWinInTopRow() {

        Grid grid = createGridWith(
                O, VACANT, O,
                X, VACANT, VACANT,
                VACANT, X, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(1));
    }

    @Test
    public void blocksOpponentsWinInMiddleRow() {


        Grid grid = createGridWith(
                X, VACANT, VACANT,
                O, O, VACANT,
                X, VACANT, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(5));
    }

    @Test
    public void blocksOpponentsMoveInBottomRow() {

        Grid grid = createGridWith(
                VACANT, VACANT, X,
                VACANT, VACANT, X,
                O, VACANT, O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(7));
    }

    @Test
    public void blocksOpponentsWinInBackwardsDiagonal() {

        Grid grid = createGridWith(
                O, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, O);

        assertThat(automatedPlayer.nextMoveOn(grid), is(4));
    }

    @Test
    public void blocksOpponentsWinInForwardDiagonal() {
        Grid grid = createGridWith(
                X, VACANT, VACANT,
                VACANT, O, VACANT,
                O, X, VACANT);

        assertThat(automatedPlayer.nextMoveOn(grid), is(2));
    }
}