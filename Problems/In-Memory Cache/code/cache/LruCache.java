package code.cache;

import code.entities.CacheEntry;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LruCache implements Cache {
    private HashMap<String, CacheEntry> cacheMap;
    private CacheEntry head;
    private CacheEntry tail;
    private int capacity;
    private ReentrantLock lock;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.lock = new ReentrantLock();

        this.head = new CacheEntry(null, null, null);
        this.tail = new CacheEntry(null, null, null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    @Override
    public void put(String key, String value, long ttl) {
        lock.lock();
        try {
            LocalDateTime expiry = (ttl > 0) ? LocalDateTime.now().plusSeconds(ttl) : null;

            if (cacheMap.containsKey(key)) {
                CacheEntry entry = cacheMap.get(key);
                entry.setValue(value);
                entry.setTimestamp(expiry);
                moveToHead(entry);
                return;
            }

            if (cacheMap.size() >= capacity) {
                evictLru();
            }

            CacheEntry newEntry = new CacheEntry(key, value, expiry);
            cacheMap.put(key, newEntry);
            addToHead(newEntry);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String get(String key) {
        lock.lock();
        try {
            if (!cacheMap.containsKey(key)) {
                return null;
            }

            CacheEntry entry = cacheMap.get(key);
            if (entry.isExpired()) {
                removeNode(entry);
                cacheMap.remove(key);
                return null;
            }

            moveToHead(entry);
            return entry.getValue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(String key) {
        lock.lock();
        try {
            if (!cacheMap.containsKey(key)) {
                return false;
            }

            CacheEntry entry = cacheMap.remove(key);
            removeNode(entry);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void addToHead(CacheEntry entry) {
        entry.setNext(head.getNext());
        entry.setPrev(head);
        head.getNext().setPrev(entry);
        head.setNext(entry);
    }

    private void removeNode(CacheEntry entry) {
        if (entry.getPrev() != null) {
            entry.getPrev().setNext(entry.getNext());
        }
        if (entry.getNext() != null) {
            entry.getNext().setPrev(entry.getPrev());
        }
    }

    private void moveToHead(CacheEntry entry) {
        removeNode(entry);
        addToHead(entry);
    }

    private void evictLru() {
        CacheEntry lru = tail.getPrev();
        if (lru != head) {
            removeNode(lru);
            cacheMap.remove(lru.getKey());
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
