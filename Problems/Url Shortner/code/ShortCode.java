package code;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public final class ShortCode {
    private static final Duration LIFETIME = Duration.ofDays(10);

    private final String shortCode;
    private final String originalUrl;
    private final LocalDateTime createdAt;
    private final Clock clock;

    public ShortCode(String shortCode, String originalUrl) {
        this(shortCode, originalUrl, LocalDateTime.now(), Clock.systemDefaultZone());
    }

    public ShortCode(String shortCode, String originalUrl, LocalDateTime createdAt) {
        this(shortCode, originalUrl, createdAt, Clock.systemDefaultZone());
    }

    public ShortCode(String shortCode, String originalUrl, LocalDateTime createdAt, Clock clock) {
        this.shortCode = Objects.requireNonNull(shortCode, "shortCode");
        this.originalUrl = Objects.requireNonNull(originalUrl, "originalUrl");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    public String getCode() {
        return shortCode;
    }

    public String getURL() {
        return originalUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isValid() {
        return LocalDateTime.now(clock).isBefore(createdAt.plus(LIFETIME));
    }
}