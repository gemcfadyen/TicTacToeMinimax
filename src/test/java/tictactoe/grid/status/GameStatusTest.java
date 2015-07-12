package tictactoe.grid.status;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.X;
import static tictactoe.grid.status.GameStatus.noPotentialMove;
import static tictactoe.grid.status.GameStatus.noWin;
import static tictactoe.grid.status.GameStatus.potentialMoveAt;
import static tictactoe.grid.status.GameStatus.winFor;
import static tictactoe.player.gameplan.GamePlan.NO_SUGGESTED_MOVE;

public class GameStatusTest {

    @Test
    public void returnsIndexOfPotentialMove() {
        GameStatus gameStatus = potentialMoveAt(2);

        assertThat(gameStatus.getIndexOfMove(), is(2));
        assertThat(gameStatus.hasPotentialMove(), is(true));
    }

    @Test
    public void returnsWinningDetails() {
        GameStatus gameStatus = winFor(X);

        assertThat(gameStatus.winningSymbol(), is(X));
        assertThat(gameStatus.hasWinner(), is(true));
    }

    @Test
    public void indicatesNoWinner() {
        GameStatus gameStatus = noWin();

        assertThat(gameStatus.hasWinner(), is(false));
        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }

    @Test
    public void indicatesNoPotentialMove() {
        GameStatus gameStatus = noPotentialMove();

        assertThat(gameStatus.hasWinner(), is(false));
        assertThat(gameStatus.hasPotentialMove(), is(false));
        assertThat(gameStatus.getIndexOfMove(), is(NO_SUGGESTED_MOVE));
    }
}