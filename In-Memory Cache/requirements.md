# In Memory Cache

## Functional Requirements

1. System will store key value pair and support get, put and remove operations.
2. The cache is initialized with a fixed maxCapacity. When full, inserting a key triggers eviction policy to evict oldest accessed entry.
3. Each cache entry stores an expiryTimeStamp. If an entry's TTL has elapsed when accessed, the cache lazily evict the entry and treat it as the cache miss.
4. System will wrap the cache to track real-time performance matrics.
5. System will wrap the cache to log lifecycle event.

## Non Functional Requirements

1. All the operation should be thread safe.

## Entities

1. Cache Manager
2. Cache(Interface)
3. LRU Cache
4. Cache Decorator(Abstract Class)
5. Logger Decorator
6. Metrics Decorator
7. Cache Builder
8. Metrics Collector
9. Log Collector
10. Cache Entry
