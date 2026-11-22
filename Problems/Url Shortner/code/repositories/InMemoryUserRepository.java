package code.repositories;

import code.User;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserRepository implements UserRepository {
    private final ConcurrentMap<UUID, User> users = new ConcurrentHashMap<>();

    @Override
    public User get(UUID userId) {
        return users.get(userId);
    }

    @Override
    public boolean isPresent(UUID userId) {
        return users.containsKey(userId);
    }

    @Override
    public boolean remove(UUID userId) {
        return users.remove(userId) != null;
    }

    @Override
    public boolean add(User user) {
        return users.putIfAbsent(user.getUserId(), user) == null;
    }
}
