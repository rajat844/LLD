package code.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import code.entities.User;

public class UserRepository {
    private final Map<UUID, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        if (user != null) {
            users.put(user.getUserId(), user);
        }
    }

    public User getUser(UUID userId) {
        return users.get(userId);
    }
}

