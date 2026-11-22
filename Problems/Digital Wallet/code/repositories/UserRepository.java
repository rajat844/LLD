package code.repositories;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import code.entities.User;

public class UserRepository {
    private final ConcurrentMap<UUID, User> users = new ConcurrentHashMap<>();

    public void addUser(User user) {
        Objects.requireNonNull(user, "user");
        if (users.putIfAbsent(user.getUserId(), user) != null) {
            throw new IllegalArgumentException("User already exists: " + user.getUserId());
        }
    }

    public Optional<User> getUserById(UUID userId) {
        if (userId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(userId));
    }
}
