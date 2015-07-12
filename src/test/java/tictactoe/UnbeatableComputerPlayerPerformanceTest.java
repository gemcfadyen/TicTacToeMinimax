package tictactoe;

import org.apache.log4j.Logger;
import org.junit.Test;
import tictactoe.grid.GridFactory;
import tictactoe.player.AutomatedPlayer;
import tictactoe.player.Player;
import tictactoe.player.RandomCellTestPlayer;
import tictactoe.prompt.FakeTestPrompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.X;

public class UnbeatableComputerPlayerPerformanceTest {
    private static final Logger LOGGER = Logger.getLogger(UnbeatableComputerPlayerPerformanceTest.class);
    private static final int TIMES = 2000;

    @Test
    public void automatedPlayerOpensTheGameAndOpponentNeverWins() {
        FakeTestPrompt fakePrompt = new FakeTestPrompt(TIMES, "A", O);
        Player automatedPlayer = new AutomatedPlayer(X, fakePrompt);
        Player randomPlayer = new RandomCellTestPlayer(O, fakePrompt);

        Game game = new Game(GridFactory.createEmptyGrid(), fakePrompt, new Player[] {automatedPlayer, randomPlayer}) {
            protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
                return new Player[] {
                        automatedPlayer, randomPlayer
                };
            }
        };

        game.play();

        LOGGER.info("Automated player won [" + fakePrompt.totalWinsForAutomatedPlayer() + "/" + TIMES + "] games");
        assertThat(fakePrompt.totalWinsForNonAutomatedPlayer(), is(0));
    }

    @Test
    public void randomPlayerOpensTheGameAndNeverWins() {
        FakeTestPrompt fakePrompt = new FakeTestPrompt(TIMES, "H", O);
        Player automatedPlayer = new AutomatedPlayer(X, fakePrompt);
        Player randomPlayer = new RandomCellTestPlayer(O, fakePrompt);

        Game game = new Game(GridFactory.createEmptyGrid(), fakePrompt, new Player[] {automatedPlayer, randomPlayer}) {
            protected Player[] initialiseOrderedPlayers(String typeOfPlayerToGoFirst) {
                return new Player[] {
                        randomPlayer, automatedPlayer
                };
            }
        };

        game.play();

        LOGGER.info("Automated player won [" + fakePrompt.totalWinsForAutomatedPlayer() + "/" + TIMES + "]");
        assertThat(fakePrompt.totalWinsForNonAutomatedPlayer(), is(0));
    }
}

