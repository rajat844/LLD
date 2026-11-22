package Code.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Broker {
    private final UUID id;
    private final Map<UUID, Partition> partitions;

    public Broker(UUID id) {
        this.id = id;
        this.partitions = new HashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public void addPartition(Partition partition) {
        partitions.put(partition.getId(), partition);
    }

    public Partition getPartition(UUID partitionId) {
        return partitions.get(partitionId);
    }

    public int append(UUID partitionId, Message message) {
        Partition partition = partitions.get(partitionId);

        if (partition == null)
            throw new IllegalArgumentException("Partition not found");

        return partition.append(message);
    }

    public List<Message> read(UUID partitionId, int offset) {
        Partition partition = partitions.get(partitionId);

        if (partition == null)
            throw new IllegalArgumentException("Partition not found");

        return partition.read(offset);
    }
}
