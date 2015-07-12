package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;
import tictactoe.prompt.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.X;

@RunWith(MockitoJUnitRunner.class)
public class HumanPlayerTest {
    private static final Integer VACANT_CELL = 3;
    private static final Integer OCCUPIED_CELL = 2;

    @Mock private Prompt prompt;
    @Mock private Grid grid;

    private Player human;

    @Before
    public void setup() {
        human = new HumanPlayer(X, prompt);
    }

    @Test
    public void playerIsPromptedForNextMove() {
        when(prompt.readsInput()).thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt).promptPlayerForNextMove();
    }

    @Test
    public void playerSpecifiesNextMoveThroughThePrompt() {
        when(prompt.readsInput()).thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        int indexOfNextMove = human.nextMoveOn(grid);

        verify(prompt).readsInput();
        assertThat(indexOfNextMove, is(equalTo(VACANT_CELL)));
    }

    @Test
    public void repromptPlayerWhenTheySpecifyANonNumericInputForNextMove() {
        when(prompt.readsInput()).thenReturn("hello").thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(2)).promptPlayerForNextMove();
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellLargerThanGrid() {
        when(prompt.readsInput()).thenReturn("100").thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(2)).promptPlayerForNextMove();
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellSmallerThanTheGrid() {
        when(prompt.readsInput()).thenReturn("-50").thenReturn("-1").thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(3)).promptPlayerForNextMove();
    }

    @Test
    public void repromptPlayerWhenTheySpecifyACellThatIsAlreadyTaken() {
        when(prompt.readsInput()).thenReturn(OCCUPIED_CELL.toString()).thenReturn(VACANT_CELL.toString());
        when(grid.isEmptyAt(OCCUPIED_CELL)).thenReturn(false);
        when(grid.isEmptyAt(VACANT_CELL)).thenReturn(true);

        human.nextMoveOn(grid);

        verify(prompt, times(2)).promptPlayerForNextMove();
    }

    @Test
    public void returnPlayersSymbol() {
        assertThat(human.getSymbol(), is(equalTo(X)));
    }
}
