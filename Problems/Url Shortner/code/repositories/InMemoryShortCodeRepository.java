package code.repositories;

import code.ShortCode;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryShortCodeRepository implements ShortCodeRepository {
    private final ConcurrentMap<String, ShortCode> shortCodes = new ConcurrentHashMap<>();

    @Override
    public ShortCode get(String shortCode) {
        return shortCodes.get(shortCode);
    }

    @Override
    public boolean isPresent(String shortCode) {
        return shortCodes.containsKey(shortCode);
    }

    @Override
    public boolean remove(String shortCode) {
        return shortCodes.remove(shortCode) != null;
    }

    @Override
    public boolean add(ShortCode shortCode) {
        Objects.requireNonNull(shortCode, "shortCode");
        return shortCodes.putIfAbsent(shortCode.getCode(), shortCode) == null;
    }
}
