package code.entities;

import java.time.LocalDateTime;

public class CacheEntry {
    private String key;
    private String value;
    private LocalDateTime timestamp;
    private CacheEntry prev;
    private CacheEntry next;

    public CacheEntry(String key, String value, LocalDateTime timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public boolean isExpired() {
        return timestamp != null && LocalDateTime.now().isAfter(timestamp);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public CacheEntry getPrev() {
        return prev;
    }

    public void setPrev(CacheEntry prev) {
        this.prev = prev;
    }

    public CacheEntry getNext() {
        return next;
    }

    public void setNext(CacheEntry next) {
        this.next = next;
    }
}