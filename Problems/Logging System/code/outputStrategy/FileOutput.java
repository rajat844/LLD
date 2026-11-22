package code.outputStrategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import code.entities.LogMessage;
import code.formatStrategy.FormatStrategy;

public class FileOutput implements OutputStrategy {
    private FormatStrategy formatter;
    private final PrintWriter writer;

    public FileOutput(String path, FormatStrategy strategy) throws IOException {
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        this.formatter = strategy;
    }

    @Override
    public void append(LogMessage msg) {
        String line = formatter.getLogLine(msg);
        synchronized (System.out) {
            writer.println(line);
            writer.flush();
        }
    }

}
