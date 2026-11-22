package code;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class User {
    private final UUID userId;
    private final ConcurrentMap<String, ShortCode> shortCodes = new ConcurrentHashMap<>();

    public User(UUID userId) {
        this.userId = Objects.requireNonNull(userId, "userId");
    }

    public UUID getUserId() {
        return userId;
    }

    public ShortCode get(String shortCode) {
        ShortCode link = shortCodes.get(shortCode);
        if (link == null) {
            throw new NoSuchElementException("Short code is not owned by this user: " + shortCode);
        }
        if (!link.isValid()) {
            shortCodes.remove(shortCode, link);
            throw new NoSuchElementException("Short code has expired: " + shortCode);
        }
        return link;
    }

    public void add(ShortCode shortCode) {
        Objects.requireNonNull(shortCode, "shortCode");
        if (shortCodes.putIfAbsent(shortCode.getCode(), shortCode) != null) {
            throw new IllegalStateException("Short code already exists for this user: " + shortCode.getCode());
        }
    }

    public boolean delete(String shortCode) {
        return shortCodes.remove(shortCode) != null;
    }

    public ShortCode findActiveByUrl(String originalUrl) {
        for (ShortCode shortCode : shortCodes.values()) {
            if (!shortCode.isValid()) {
                shortCodes.remove(shortCode.getCode(), shortCode);
            } else if (shortCode.getURL().equals(originalUrl)) {
                return shortCode;
            }
        }
        return null;
    }

    public List<ShortCode> getShortCodes() {
        List<ShortCode> active = new ArrayList<>();
        for (ShortCode shortCode : shortCodes.values()) {
            if (shortCode.isValid()) {
                active.add(shortCode);
            } else {
                shortCodes.remove(shortCode.getCode(), shortCode);
            }
        }
        return List.copyOf(active);
    }
}
