package code.cacheDecorator;

import code.cache.Cache;

public abstract class CacheDecorator implements Cache {
    protected Cache wrappedCache;

    public CacheDecorator(Cache wrappedCache) {
        this.wrappedCache = wrappedCache;
    }

    @Override
    public void put(String key, String value, long ttl) {
        wrappedCache.put(key, value, ttl);
    }

    @Override
    public String get(String key) {
        return wrappedCache.get(key);
    }

    @Override
    public boolean remove(String key) {
        return wrappedCache.remove(key);
    }

    public Cache getWrappedCache() {
        return wrappedCache;
    }
}
