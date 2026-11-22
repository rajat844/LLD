package Code.partitionStrategy;

import java.util.List;

import Code.entities.Message;
import Code.entities.Partition;

public interface PartitionStrategy {
    public Partition selectPartition(List<Partition> partitions, Message message);
}
