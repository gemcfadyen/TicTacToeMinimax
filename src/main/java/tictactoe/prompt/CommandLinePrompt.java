package tictactoe.prompt;

import tictactoe.Symbol;
import tictactoe.grid.Cell;
import tictactoe.grid.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static tictactoe.Symbol.VACANT;
import static java.lang.String.format;

public class CommandLinePrompt implements Prompt {
    private static final String NEW_LINE_CHARACTER = "\n";

    private final BufferedReader reader;
    private final Writer writer;

    protected CommandLinePrompt(BufferedReader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public String readsInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error in reading input", e);
        }
    }

    @Override
    public void promptPlayerForNextMove() {
        writeToConsole("Please enter the number of the cell where you wish to put your next move");
    }

    @Override
    public void promptForOrderOfPlay() {
        writeToConsole("Please enter which player you wish to start the game - Automated Player or Human Player (A/H)");
    }

    @Override
    public void displayWinningMessageFor(Symbol symbol) {
        writeToConsole(format("Player%s wins", symbol));
    }

    @Override
    public void displayGameOver() {
        writeToConsole("Game Over");
    }

    @Override
    public void promptPlayerToStartNewGame() {
        writeToConsole("Play again? (Y/N)");
    }

    @Override
    public void display(List<Row> rows) {
        StringBuffer gridDisplay = new StringBuffer();

        for (Row row : rows) {
            gridDisplay.append(prints(row));
        }

        writeToConsole(gridDisplay.toString());
    }

    @Override
    public void display(Symbol symbol, int move) {
        writeToConsole(format("Placing symbol %s at %s", symbol, move));
    }

    private StringBuffer prints(Row row) {
        StringBuffer gridDisplay = new StringBuffer();
        gridDisplay.append(" | ");
        for (Cell cell : row.getCells()) {
            if (cell.getSymbol() == VACANT) {
                gridDisplay.append("(" + cell.getOffset() + ")");
            } else {
                gridDisplay.append(" " + cell.getSymbol() + " ");
            }
            gridDisplay.append(" | ");
        }

        return gridDisplay.append("\n");
    }

    private void writeToConsole(String message) {
        try {
            writer.write(NEW_LINE_CHARACTER);
            writer.write(message);
            writer.write(NEW_LINE_CHARACTER);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error in writing to the command line", e);
        }
    }
}
