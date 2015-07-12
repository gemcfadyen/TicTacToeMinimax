package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.player.Player;
import tictactoe.prompt.Prompt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;
import static tictactoe.grid.status.GameStatus.noWin;
import static tictactoe.grid.status.GameStatus.winFor;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private static final String DONT_REPLAY_GAME = "N";
    private static final String REPLAY_GAME = "Y";
    private static final String INVALID = "invalid";
    private static final String VALID_CHOICE = "A";

    @Mock private Player playerO;
    @Mock private Player playerX;
    @Mock private Prompt prompt;
    @Mock private Grid grid;

    private Game game;

    @Before
    public void setup() {
        game = new Game(grid, prompt) {
            protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
                return new Player[] {
                        playerO, playerX
                };
            }
        };
    }

    @Test
    public void gameEndsWhenNineMovesHaveBeenMade() {
        when(grid.evaluateWinningStatus()).thenReturn(noWin());
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(playerX, times(4)).nextMoveOn(grid);
        verify(playerO, times(5)).nextMoveOn(grid);
        verify(prompt).displayGameOver();
    }

    @Test
    public void gameEndsWhenGridContainsThreeXsInARow() {
        when(grid.evaluateWinningStatus()).thenReturn(winFor(X));
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(2)).display(grid.rows());
        verify(prompt, times(1)).displayWinningMessageFor(X);
    }

    @Test
    public void gameEndsWhenGridContainsThreeOsInARow() {
        when(grid.evaluateWinningStatus()).thenReturn(winFor(O));
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(2)).display(grid.rows());
        verify(prompt, times(1)).displayWinningMessageFor(O);
    }

    @Test
    public void gridIsUpdatedOnceAPlayerHasMadeTheirMove() {
        when(playerO.nextMoveOn(grid)).thenReturn(3);
        when(playerO.getSymbol()).thenReturn(O);
        when(grid.evaluateWinningStatus()).thenReturn(winFor(O));
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(grid, times(1)).update(3, O);
    }

    @Test
    public void repromptPlayerToStartANewGameWhenInvalidInputProvided() {
        when(grid.evaluateWinningStatus()).thenReturn(noWin());
        when(prompt.readsInput()).thenReturn(VALID_CHOICE).thenReturn(INVALID).thenReturn(REPLAY_GAME).thenReturn("A").thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt, times(2)).displayGameOver();
        verify(prompt, times(2)).promptForOrderOfPlay();
        verify(grid, times(2)).reset();
        verify(prompt, times(3)).promptPlayerToStartNewGame();
    }


    @Test
    public void repromptPlayerForOrderOfPlayWhenInvalidInputProvided() {
        when(grid.evaluateWinningStatus()).thenReturn(noWin());
        when(prompt.readsInput()).thenReturn(INVALID).thenReturn(VALID_CHOICE).thenReturn(DONT_REPLAY_GAME);

        game.play();

        verify(prompt).displayGameOver();
        verify(prompt, times(2)).promptForOrderOfPlay();
        verify(prompt).promptPlayerToStartNewGame();
    }
}
