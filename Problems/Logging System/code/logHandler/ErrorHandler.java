package code.logHandler;

import code.entities.LogType;

public class ErrorHandler extends LogHandler {
    public ErrorHandler() {
        super(LogType.ERROR);
    }
}