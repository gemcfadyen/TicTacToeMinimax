package tictactoe.prompt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class PromptFactory {

    public static Prompt createCommandLinePrompt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Writer writer = new BufferedWriter(new OutputStreamWriter(System.out));
        return new CommandLinePrompt(reader, writer);
    }
}
