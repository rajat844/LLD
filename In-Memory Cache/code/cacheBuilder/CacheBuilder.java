package code.cacheBuilder;

import code.cache.Cache;
import code.cache.LruCache;
import code.cacheDecorator.LoggerDecorator;
import code.cacheDecorator.MetricsDecorator;
import code.entities.LogCollector;
import code.entities.MetricsCollector;

public class CacheBuilder {
    private LogCollector logCollector;
    private MetricsCollector metricsCollector;
    private int capacity = 10;
    private Cache baseCache;

    public CacheBuilder() {
    }

    public CacheBuilder withBaseCache(Cache baseCache) {
        this.baseCache = baseCache;
        return this;
    }

    public CacheBuilder withLogCollector(LogCollector logCollector) {
        this.logCollector = logCollector;
        return this;
    }

    public CacheBuilder withMetricsCollector(MetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
        return this;
    }

    public Cache build() {
        Cache cache = (baseCache != null) ? baseCache : new LruCache(capacity);

        if (metricsCollector != null) {
            cache = new MetricsDecorator(cache, metricsCollector);
        }

        if (logCollector != null) {
            cache = new LoggerDecorator(cache, logCollector);
        }

        return cache;
    }

    public LogCollector getLogCollector() {
        return logCollector;
    }

    public MetricsCollector getMetricsCollector() {
        return metricsCollector;
    }
}

