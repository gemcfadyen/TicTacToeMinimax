package tictactoe.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.prompt.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class OrderedPlayerFactoryTest {
    private static final int FIRST_PLAYER_INDEX = 0;
    @Mock private Prompt prompt;

    @Test
    public void automatedPlayerConfiguredToTakeFirstGo() {
        Player[] orderedPlayers = OrderedPlayerFactory.createPlayersInOrder("A", prompt);

        assertThat(orderedPlayers[FIRST_PLAYER_INDEX], is(AutomatedPlayer.class));
    }

    @Test
    public void humanPlayerConfiguredToTakeFirstGo() {
        Player[] orderedPlayers = OrderedPlayerFactory.createPlayersInOrder("H", prompt);

        assertThat(orderedPlayers[FIRST_PLAYER_INDEX], is(HumanPlayer.class));
    }

}