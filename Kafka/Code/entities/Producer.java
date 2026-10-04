package Code.entities;

import java.util.UUID;

import Code.partitionStrategy.PartitionStrategy;
import Code.repositories.TopicRepository;

public class Producer {
    private UUID id;
    private PartitionStrategy strategy;
    private TopicRepository topicRepository;

    public Producer(UUID id, PartitionStrategy strategy, TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
        this.strategy = strategy;
        this.id = id;
    }

    public void send(String topicName, Message message) {
        Topic topic = topicRepository.getTopic(topicName);
        if (topic == null)
            throw new IllegalArgumentException("Topic doesn't exist");

        Partition partition = strategy.selectPartition(topic.getPartitions(), message);
        partition.append(message);
    }

    public UUID getId() {
        return id;
    }

}
