package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.GridFactory.createEmptyGrid;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private static final String DONT_REPLAY_GAME = "N";
    private static final String REPLAY_GAME = "Y";
    private static final String INVALID = "invalid";
    private static final String VALID_CHOICE = "A";

    @Mock private Player playerO;
    @Mock private Player playerX;
    @Mock private Prompt prompt;

    private Game game;
    private Grid grid;

    @Before
    public void setup() {
        grid = createEmptyGrid();
        game = new Game(grid, prompt, new Player[] {playerX, playerO});

        when(playerO.getSymbol()).thenReturn(O);
        when(playerX.getSymbol()).thenReturn(X);
    }

    @Test
    public void gameEndsWhenNineMovesHaveBeenMade() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 3, 6, 7, 8);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 2, 5, 9);
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(playerX, times(5)).nextMoveOn(grid);
        verify(playerO, times(4)).nextMoveOn(grid);
        verify(prompt).displayGameOver();
    }

    @Test
    public void gameEndsWhenGridContainsThreeXsInARow() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 2, 3);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 5);
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(1)).displayWinningMessageFor(X);
    }

    @Test
    public void gameEndsWhenGridContainsThreeOsInARow() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 7, 3);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 5, 6);
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(1)).displayWinningMessageFor(O);
    }

    @Test
    public void gridIsUpdatedOnceAPlayerHasMadeTheirMove() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 2, 3);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 5);
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        assertThat(grid.getCellWithOffset(1).getSymbol(), equalTo(X));
        assertThat(grid.getCellWithOffset(2).getSymbol(), equalTo(X));
        assertThat(grid.getCellWithOffset(3).getSymbol(), equalTo(X));
        assertThat(grid.getCellWithOffset(4).getSymbol(), equalTo(O));
        assertThat(grid.getCellWithOffset(5).getSymbol(), equalTo(O));
    }

    @Test
    public void repromptPlayerToStartANewGameWhenInvalidInputProvided() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 2, 3, 1, 2, 3);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 5, 4, 5);

        when(prompt.readsInput()).thenReturn(VALID_CHOICE)
                .thenReturn(INVALID)
                .thenReturn(REPLAY_GAME)
                .thenReturn(VALID_CHOICE)
                .thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(2)).displayGameOver();
        verify(prompt, times(2)).promptForOrderOfPlay();
        verify(prompt, times(3)).promptPlayerToStartNewGame();
    }


    @Test
    public void repromptPlayerForOrderOfPlayWhenInvalidInputProvided() {
        when(playerX.nextMoveOn(grid)).thenReturn(1, 2, 3);
        when(playerO.nextMoveOn(grid)).thenReturn(4, 5);
        when(prompt.readsInput())
                .thenReturn(INVALID)
                .thenReturn(VALID_CHOICE)
                .thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt).displayGameOver();
        verify(prompt, times(2)).promptForOrderOfPlay();
        verify(prompt).promptPlayerToStartNewGame();
    }
}