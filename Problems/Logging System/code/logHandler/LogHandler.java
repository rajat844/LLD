package code.logHandler;

import code.entities.LogMessage;
import code.entities.LogType;
import code.outputStrategy.OutputStrategy;

public abstract class LogHandler {
    private LogType handledType;
    private LogHandler next;
    private OutputStrategy output;

    public LogHandler(LogType handledType) {
        this.handledType = handledType;
    }

    public LogHandler setNext(LogHandler next) {
        this.next = next;
        return next;
    }

    public void getOutput(OutputStrategy out) {
        this.output = out;
    }

    public void handle(LogMessage mssg) {
        if(mssg.getType() == handledType) {
            output.append(mssg);
        } else if(next != null) {
            next.handle(mssg);
        }
    }
}
