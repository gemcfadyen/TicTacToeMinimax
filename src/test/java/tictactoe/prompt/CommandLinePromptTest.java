package tictactoe.prompt;

import org.junit.Test;
import tictactoe.grid.Grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.GridFactory.createGridWith;

public class CommandLinePromptTest {
    private Writer stringWriter;

    @Test
    public void promptsUserForInput() {
        Prompt commandLinePrompt = createCommandLinePrompt();
        commandLinePrompt.promptPlayerForNextMove();

        assertThat(stringWriter.toString(),
                is("\nPlease enter the number of the cell where you wish to put your next move\n"));
    }

    @Test
    public void promptsUserToPlayNewGame() {
        Prompt commandLinePrompt = createCommandLinePrompt();
        commandLinePrompt.promptPlayerToStartNewGame();

        assertThat(stringWriter.toString(),
                is("\nPlay again? (Y/N)\n"));
    }

    @Test
    public void promptsUserToChooseOrderOfPlay() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        commandLinePrompt.promptForOrderOfPlay();

        assertThat(stringWriter.toString(),
                is("\nPlease enter which player you wish to start the game - Automated Player or Human Player (A/H)\n"));
    }

    @Test
    public void readsInputFromTheCommandLine() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        assertThat(commandLinePrompt.readsInput(), is("hello"));
    }

    @Test
    public void displaysMessage() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        commandLinePrompt.displayWinningMessageFor(X);

        assertThat(stringWriter.toString(), is("\nPlayerX wins\n"));
    }

    @Test
    public void displaysGameOverMessage() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        commandLinePrompt.displayGameOver();

        assertThat(stringWriter.toString(), is("\nGame Over\n"));
    }

    @Test
    public void displaysGrid() {
        Grid gridForDisplay = createGridWith(
                X, VACANT, X,
                X, VACANT, VACANT,
                VACANT, O, X);

        Prompt commandLinePrompt = createCommandLinePrompt();
        commandLinePrompt.display(gridForDisplay.getCells());

        StringBuffer expectedGridDisplay = new StringBuffer("\n |  X  | (2) |  X  | \n ");
        expectedGridDisplay.append("|  X  | (5) | (6) | \n ");
        expectedGridDisplay.append("| (7) |  O  |  X  | \n\n");

        assertThat(stringWriter.toString(), is(equalTo(expectedGridDisplay.toString())));
    }

    @Test
    public void displaysIndexOfMoveTaken() {
        Prompt commandLinePrompt = createCommandLinePrompt();

        commandLinePrompt.display(X, 2);

        assertThat(stringWriter.toString(), is("\nPlacing symbol X at 2\n"));
    }


    @Test(expected = RuntimeException.class)
    public void exceptionIsThrownWhenThereIsAnErrorInReading() throws IOException {
        Prompt commandLinePrompt = createPromptWithMockedReaderAndWriterWhichThrowExceptions();
        commandLinePrompt.readsInput();
    }

    @Test(expected = RuntimeException.class)
    public void exceptionIsThrownWhenThereIsAnErrorInWriting() throws IOException {
        Prompt commandLinePrompt = createPromptWithMockedReaderAndWriterWhichThrowExceptions();
        commandLinePrompt.readsInput();
    }

    private CommandLinePrompt createPromptWithMockedReaderAndWriterWhichThrowExceptions() throws IOException {
        Writer writer = mock(Writer.class);
        BufferedReader reader = mock(BufferedReader.class);

        when(reader.readLine()).thenThrow(new IOException("issue with reading"));
        doThrow(new IOException("issue with writing")).when(writer).write("\n");

        return new CommandLinePrompt(reader, writer);
    }

    private CommandLinePrompt createCommandLinePrompt() {
        BufferedReader stringReader = new BufferedReader(new StringReader("hello"));
        stringWriter = new StringWriter();
        return new CommandLinePrompt(stringReader, stringWriter);
    }
}
