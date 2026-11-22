package code.logHandler;

import code.entities.LogType;

public class WarnHandler extends LogHandler {
    public WarnHandler() {
        super(LogType.WARN);
    }
}