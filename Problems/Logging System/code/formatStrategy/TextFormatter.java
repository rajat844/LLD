package code.formatStrategy;

import code.entities.LogMessage;

public class TextFormatter implements FormatStrategy {

    @Override
    public String getLogLine(LogMessage mssg) {
        return String.format("%s %s %s", mssg.getType().toString(), mssg.getTimestamp(), mssg.getMessage());
    }

}
