package code.repositories;

import code.User;
import java.util.UUID;

public interface UserRepository {
    User get(UUID userId);

    boolean isPresent(UUID userId);

    boolean remove(UUID userId);

    boolean add(User user);
}
