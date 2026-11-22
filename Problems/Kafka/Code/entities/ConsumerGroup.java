package Code.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ConsumerGroup {
    private UUID id;
    private final List<Consumer> consumers;
    private final Map<Partition, Consumer> assignments;

    public ConsumerGroup(UUID id) {
        this.id = id;
        this.consumers = new ArrayList<>();
        this.assignments = new HashMap<>();
    }

    public void addConsumer(Consumer consumer) {
        if (!consumers.contains(consumer))
            consumers.add(consumer);
    }

    public void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }

    public void assign(Partition partition, Consumer consumer) {
        assignments.put(partition, consumer);
        consumer.addPartition(partition);
    }

    public void clearAssignments() {
        assignments.clear();
        for (Consumer consumer : consumers) {
            consumer.clearPartitions();
        }
    }

    public UUID getId() {
        return id;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public Map<Partition, Consumer> getAssignments() {
        return assignments;
    }
}
