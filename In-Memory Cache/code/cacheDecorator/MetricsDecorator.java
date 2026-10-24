package code.cacheDecorator;

import code.cache.Cache;
import code.entities.MetricsCollector;

public class MetricsDecorator extends CacheDecorator {
    private MetricsCollector collector;

    public MetricsDecorator(Cache wrappedCache, MetricsCollector collector) {
        super(wrappedCache);
        this.collector = collector;
    }

    @Override
    public void put(String key, String value, long ttl) {
        super.put(key, value, ttl);
    }

    @Override
    public String get(String key) {
        String result = super.get(key);
        if (collector != null) {
            if (result != null) {
                collector.recordHits();
            } else {
                collector.recordMiss();
            }
        }
        return result;
    }

    @Override
    public boolean remove(String key) {
        return super.remove(key);
    }

    public MetricsCollector getCollector() {
        return collector;
    }
}
