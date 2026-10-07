package code.formatStrategy;

import code.entities.LogMessage;
import org.json.JSONObject;

public class JsonFormatter implements FormatStrategy {
    @Override
    public String getLogLine(LogMessage mssg) {
            return new JSONObject()
        .put("type", mssg.getType())
        .put("timestamp", mssg.getTimestamp())
        .put("message", mssg.getMessage())
        .toString();
    }

}
