package code.outputStrategy;

import code.entities.LogMessage;
import code.formatStrategy.FormatStrategy;

public class ConsoleOutput implements OutputStrategy {
    private FormatStrategy formatter;

    public ConsoleOutput(FormatStrategy strategy) {
        this.formatter = strategy;
    }

    @Override
    public void append(LogMessage msg) {
        String line = formatter.getLogLine(msg);
        synchronized (System.out) {
            System.out.println(line);
        }
    }

}
