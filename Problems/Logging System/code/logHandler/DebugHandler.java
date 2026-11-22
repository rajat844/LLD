package code.logHandler;

import code.entities.LogType;

public class DebugHandler extends LogHandler {
    public DebugHandler() {
        super(LogType.DEBUG);
    }
}