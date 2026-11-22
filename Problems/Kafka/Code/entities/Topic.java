package Code.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Code.repositories.BrokerRepository;

public class Topic {
    private String name;
    private final Map<UUID, Partition> partitions;

    public Topic(String name, int partitionsCount, BrokerRepository brokerRepository) {
        if (partitionsCount <= 0)
            throw new IllegalArgumentException("Number of partitions must be greater than 0");

        this.name = name;
        this.partitions = new HashMap<>();
        List<Broker> brokers = brokerRepository.getAllBrokers();

        for (int i = 0; i < partitionsCount; i++) {
            Broker broker = brokers.get(i % brokers.size());
            Partition partition = new Partition(UUID.randomUUID());
            broker.addPartition(partition);
            partitions.put(partition.getId(), partition);
        }
    }

    public String getName() {
        return name;
    }

    public List<Partition> getPartitions() {
        return new ArrayList<>(partitions.values());
    }

    public Partition gePartition(int id) {
        if (id < 0 || id >= partitions.size())
            throw new IllegalArgumentException("Invalid Partition id");

        return partitions.get(id);
    }
}
