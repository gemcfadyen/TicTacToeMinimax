package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.prompt.Prompt;
import tictactoe.prompt.PromptFactory;

import static tictactoe.Symbol.X;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    private Prompt prompt = PromptFactory.createCommandLinePrompt();
    private Player automatedPlayer;

    @Before
    public void setup() {
        automatedPlayer = new AutomatedPlayer(X, prompt);
    }

    @Test
    public void test() {
        automatedPlayer.getSymbol();
        assert true;
    }

}
