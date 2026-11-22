package Code.repositories;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import Code.entities.ConsumerGroup;

public class ConsumerGroupRepository {
    private final Map<UUID, ConsumerGroup> groups;

    public ConsumerGroupRepository() {
        this.groups = new ConcurrentHashMap<>();
    }

    public ConsumerGroup createGroup(UUID id) {
        return groups.computeIfAbsent(id, ConsumerGroup::new);
    }

    public ConsumerGroup getGroup(UUID groupId) {
        ConsumerGroup group = groups.get(groupId);

        if (group == null)
            throw new IllegalArgumentException("Consumer group not found");

        return group;
    }

    public void deleteGroup(UUID id) {
        groups.remove(id);
    }

}
