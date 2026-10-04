package Code.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import Code.entities.Broker;

public class BrokerRepository {
    private final Map<UUID, Broker> brokers;

    public BrokerRepository() {
        this.brokers = new ConcurrentHashMap<>();
    }

    public void addBroker(Broker broker) {
        brokers.put(broker.getId(), broker);
    }

    public Broker getBroker(UUID id) {
        Broker broker = brokers.get(id);

        if (broker == null)
            throw new IllegalArgumentException("Broker not found");

        return broker;
    }

    public List<Broker> getAllBrokers() {
        return new ArrayList<>(brokers.values());
    }

}
