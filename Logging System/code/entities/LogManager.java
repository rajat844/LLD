package code.entities;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

import code.logHandler.DebugHandler;
import code.logHandler.ErrorHandler;
import code.logHandler.FatalHandler;
import code.logHandler.InfoHandler;
import code.logHandler.LogHandler;
import code.logHandler.WarnHandler;
import code.outputStrategy.OutputStrategy;

class LogManager {
    private static final class Holder {
        static final LogManager INSTANCE = new LogManager();
    }

    private LogHandler firstHandler;
    private final Map<LogType, LogHandler> handlers = new EnumMap<>(LogType.class);

    private LogManager() {
        LogHandler fatal = new FatalHandler();
        LogHandler error = new ErrorHandler();
        LogHandler warn = new WarnHandler();
        LogHandler debug = new DebugHandler();
        LogHandler info = new InfoHandler();
        fatal.setNext(error).setNext(warn).setNext(debug).setNext(info);

        handlers.put(LogType.FATAL, fatal);
        handlers.put(LogType.ERROR, error);
        handlers.put(LogType.WARN, warn);
        handlers.put(LogType.DEBUG, debug);
        handlers.put(LogType.INFO, info);

        this.firstHandler = fatal;
    }

    public static synchronized LogManager getInstance() {
        return Holder.INSTANCE;
    }

    void setOutput(LogType type, OutputStrategy out) {
        handlers.get(type).getOutput(out);
    }

    private void log(LogType type, String msg) {
        firstHandler.handle(new LogMessage(type, msg, LocalDateTime.now()));
    }

    void fatal(String msg) {
        log(LogType.FATAL, msg);
    }

    void error(String msg) {
        log(LogType.ERROR, msg);
    }

    void warn(String msg) {
        log(LogType.WARN, msg);
    }

    void debug(String msg) {
        log(LogType.DEBUG, msg);
    }

    void info(String msg) {
        log(LogType.INFO, msg);
    }

}