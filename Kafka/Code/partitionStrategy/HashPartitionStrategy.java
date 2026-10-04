package Code.partitionStrategy;

import java.util.List;

import Code.entities.Message;
import Code.entities.Partition;

public class HashPartitionStrategy implements PartitionStrategy {

    @Override
    public Partition selectPartition(List<Partition> partitions, Message message) {
        if (partitions.isEmpty())
            throw new IllegalArgumentException("No Partitions Available");

        if (message.getKey() == null)
            return partitions.get(0);

        int hash = message.getKey().hashCode();
        int index = Math.floorMod(hash, partitions.size());
        return partitions.get(index);
    }
}
