package code.cache;

public interface Cache {
    void put(String key, String value, long ttl);

    String get(String key);

    boolean remove(String key);
}
