package code.cacheDecorator;

import code.cache.Cache;
import code.entities.LogCollector;

public class LoggerDecorator extends CacheDecorator {
    private LogCollector collector;

    public LoggerDecorator(Cache wrappedCache, LogCollector collector) {
        super(wrappedCache);
        this.collector = collector;
    }

    @Override
    public void put(String key, String value, long ttl) {
        if (collector != null) {
            collector.addLogs("PUT [key=" + key + ", value=" + value + ", ttl=" + ttl + "s]");
        }
        super.put(key, value, ttl);
    }

    @Override
    public String get(String key) {
        if (collector != null) {
            collector.addLogs("GET [key=" + key + "]");
        }
        return super.get(key);
    }

    @Override
    public boolean remove(String key) {
        if (collector != null) {
            collector.addLogs("REMOVE [key=" + key + "]");
        }
        return super.remove(key);
    }

    public LogCollector getCollector() {
        return collector;
    }
}
