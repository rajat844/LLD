package code.entities;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private Location location;

    public User(UUID id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
