# Kafka Messaging Queue

## Requirements

1. A publisher can publish the message into the topic.
2. A Topic can have multiple partitions.
3. The partitions can exist in multiple brokers.
4. The partition to the topic will be assigned by the hash key.
5. The consumer group contains list of consumers.
6. The consumer group poll the messages from partition and can process them.
7. The consumer group keep pinging about the last successfully processed message.
8. Kafka keep track of last successfully polled message.
9. If acknowledgement wont arrive till the threshold period kafka will repoll allow repolling of that message.

## Core Objects

1. Publisher
2. Broker
3. Topic
4. Partition
5. Consumer

