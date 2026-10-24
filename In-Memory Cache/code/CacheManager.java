package code;

import code.cache.Cache;
import code.cache.LruCache;
import code.cacheBuilder.CacheBuilder;
import code.entities.LogCollector;
import code.entities.MetricsCollector;

public class CacheManager {
    private CacheBuilder builder;

    public CacheManager() {
        this.builder = new CacheBuilder().withBaseCache(new LruCache(100)).withLogCollector(new LogCollector())
                .withMetricsCollector(new MetricsCollector());
    }

    public Cache createCache() {
        return builder.build();
    }
}
