package Code.entities;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OffsetManager {
    private final Map<UUID, Map<UUID, Integer>> offsets;

    public OffsetManager() {
        offsets = new ConcurrentHashMap<>();
    }

    public void commitOffset(UUID groupId, Partition partition, int offset) {
        offsets.computeIfAbsent(groupId, key -> new ConcurrentHashMap<>()).put(partition.getId(), offset);
    }

    public int getOffset(UUID groupId, Partition partition) {
        Map<UUID, Integer> groupOffsets = offsets.get(groupId);

        if (groupOffsets == null)
            return 0;

        return groupOffsets.getOrDefault(partition.getId(), 0);
    }
}
