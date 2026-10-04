package Code.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consumer {
    private UUID id;
    private UUID groupId;
    private OffsetManager offsetManager;

    private final List<Partition> assignedPartitions;

    public Consumer(UUID id, UUID groupId, OffsetManager offsetManager) {
        this.id = id;
        this.groupId = id;
        this.offsetManager = offsetManager;
        this.assignedPartitions = new ArrayList<>();
    }

    public List<Message> poll() {
        List<Message> result = new ArrayList<>();
        for (Partition partition : assignedPartitions) {
            int offset = offsetManager.getOffset(groupId, partition);
            result.addAll(partition.read(offset));
        }

        return result;
    }

    public void commitOffset(Partition partition, int offset) {
        offsetManager.commitOffset(groupId, partition, offset);
    }

    public void addPartition(Partition partition) {
        if (!assignedPartitions.contains(partition)) {
            assignedPartitions.add(partition);
        }
    }

    public void removePartition(Partition partition) {
        assignedPartitions.remove(partition);
    }

    public void clearPartitions() {
        assignedPartitions.clear();
    }

    public List<Partition> getAssignedPartitions() {
        return assignedPartitions;
    }

    public UUID getId() {
        return id;
    }

    public UUID getGroupId() {
        return groupId;
    }

}
