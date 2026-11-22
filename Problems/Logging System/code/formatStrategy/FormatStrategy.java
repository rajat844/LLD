package code.formatStrategy;

import code.entities.LogMessage;

public interface FormatStrategy {
    public String getLogLine(LogMessage mssg);
}
