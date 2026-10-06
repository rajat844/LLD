package repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import entities.User;

public final class UserRepository {
    private final Map<UUID, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public Optional<User> findById(UUID userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
