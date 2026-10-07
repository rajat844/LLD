package code.outputStrategy;

import code.entities.LogMessage;

public interface OutputStrategy {
    void append(LogMessage msg);

}
