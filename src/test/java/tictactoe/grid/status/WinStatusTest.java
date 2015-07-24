package tictactoe.grid.status;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.X;
import static tictactoe.grid.status.WinStatus.noWin;
import static tictactoe.grid.status.WinStatus.winFor;

public class WinStatusTest {

    @Test
    public void returnsWinningDetails() {
        WinStatus winStatus = winFor(X);

        assertThat(winStatus.winningSymbol(), is(X));
        assertThat(winStatus.hasWinner(), is(true));
    }

    @Test
    public void indicatesNoWinner() {
        WinStatus winStatus = noWin();

        assertThat(winStatus.hasWinner(), is(false));
    }
}