package code.logHandler;

import code.entities.LogType;

public class FatalHandler extends LogHandler {
    public FatalHandler() {
        super(LogType.FATAL);
    }
}
