package code;

import code.generationStrategy.Base62Generator;
import code.generationStrategy.GenerationStrategy;
import code.generationStrategy.RandomGenerator;
import code.repositories.InMemoryShortCodeRepository;
import code.repositories.InMemoryUserRepository;
import code.repositories.ShortCodeRepository;
import code.repositories.UserRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortner {
    public static final String DEFAULT_STRATEGY = "random";

    private final UserRepository users;
    private final ShortCodeRepository shortCodes;
    private final Map<String, GenerationStrategy> strategies = new ConcurrentHashMap<>();
    private final Clock clock;

    public UrlShortner() {
        this(new InMemoryUserRepository(), new InMemoryShortCodeRepository(), Clock.systemDefaultZone());
    }

    public UrlShortner(UserRepository users, ShortCodeRepository shortCodes) {
        this(users, shortCodes, Clock.systemDefaultZone());
    }

    public UrlShortner(UserRepository users, ShortCodeRepository shortCodes, Clock clock) {
        this.users = Objects.requireNonNull(users, "users");
        this.shortCodes = Objects.requireNonNull(shortCodes, "shortCodes");
        this.clock = Objects.requireNonNull(clock, "clock");
        registerStrategy(DEFAULT_STRATEGY, new RandomGenerator());
        registerStrategy("base62", new Base62Generator());
    }

    public void registerStrategy(String name, GenerationStrategy strategy) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Strategy name must not be blank");
        }
        strategies.put(name.toLowerCase(Locale.ROOT), Objects.requireNonNull(strategy, "strategy"));
    }

    public String generate(UUID userId, String originalUrl) {
        return generate(userId, originalUrl, null);
    }

    public String generate(UUID userId, String originalUrl, String strategyName) {
        Objects.requireNonNull(userId, "userId");
        validateUrl(originalUrl);
        GenerationStrategy strategy = getStrategy(strategyName);
        User user = getOrCreateUser(userId);

        synchronized (user) {
            ShortCode existing = user.findActiveByUrl(originalUrl);
            if (existing != null) {
                return existing.getCode();
            }

            while (true) {
                String code = strategy.generateCode();
                if (code == null || code.isBlank()) {
                    throw new IllegalStateException("Generation strategy returned an empty code");
                }
                ShortCode link = new ShortCode(code, originalUrl, LocalDateTime.now(clock), clock);
                if (shortCodes.add(link)) {
                    user.add(link);
                    return code;
                }
            }
        }
    }

    public String get(String shortCode) {
        ShortCode link = shortCodes.get(shortCode);
        if (link == null) {
            throw new NoSuchElementException("Short code does not exist: " + shortCode);
        }
        if (!link.isValid()) {
            shortCodes.remove(shortCode);
            throw new NoSuchElementException("Short code has expired: " + shortCode);
        }
        return link.getURL();
    }

    public boolean delete(UUID userId, String shortCode) {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(shortCode, "shortCode");
        User user = users.get(userId);
        if (user == null) {
            return false;
        }
        synchronized (user) {
            ShortCode owned;
            try {
                owned = user.get(shortCode);
            } catch (NoSuchElementException e) {
                return false;
            }
            if (!shortCodes.remove(shortCode)) {
                return false;
            }
            return user.delete(owned.getCode());
        }
    }

    public List<ShortCode> getUserShortCodes(UUID userId) {
        Objects.requireNonNull(userId, "userId");
        User user = users.get(userId);
        return user == null ? List.of() : user.getShortCodes();
    }

    private User getOrCreateUser(UUID userId) {
        User user = users.get(userId);
        if (user != null) {
            return user;
        }
        users.add(new User(userId));
        user = users.get(userId);
        if (user == null) {
            throw new IllegalStateException("User repository failed to store user " + userId);
        }
        return user;
    }

    private GenerationStrategy getStrategy(String strategyName) {
        String key = strategyName == null || strategyName.isBlank()
                ? DEFAULT_STRATEGY
                : strategyName.toLowerCase(Locale.ROOT);
        GenerationStrategy strategy = strategies.get(key);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown generation strategy: " + strategyName);
        }
        return strategy;
    }

    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL must not be blank");
        }
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            if (!uri.isAbsolute()
                    || uri.getHost() == null
                    || !(scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
                throw new IllegalArgumentException("URL must be an absolute HTTP or HTTPS URL: " + url);
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Malformed URL: " + url, e);
        }
    }
}