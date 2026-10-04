package Code.partitionStrategy;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Code.entities.Message;
import Code.entities.Partition;

public class RoundRobinPartitionStrategy implements PartitionStrategy{
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Partition selectPartition(List<Partition> partitions, Message message) {
        if (partitions.isEmpty())
            throw new IllegalArgumentException("No Partitions Available");

        if (message.getKey() == null)
            return partitions.get(0);

        int index = Math.floorMod(counter.getAndIncrement(), partitions.size());
        return partitions.get(index);
    }
}
