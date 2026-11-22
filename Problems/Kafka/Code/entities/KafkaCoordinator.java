package Code.entities;

import Code.repositories.BrokerRepository;
import Code.repositories.ConsumerGroupRepository;

public class KafkaCoordinator {
    private ConsumerGroupRepository groupRepository;
    private BrokerRepository brokerRepository;
    private OffsetManager offsetManager;

    public KafkaCoordinator(ConsumerGroupRepository groupRepository, BrokerRepository brokerRepository,
            OffsetManager offsetManager) {
        this.groupRepository = groupRepository;
        this.brokerRepository = brokerRepository;
        this.offsetManager = offsetManager;
    }
}