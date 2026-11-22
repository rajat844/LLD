package Code.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Code.entities.Topic;

public class TopicRepository {
    private final Map<String, Topic> topics;

    public TopicRepository() {
        this.topics = new ConcurrentHashMap<>();
    }

    public void addTopic(Topic topic) {
        topics.put(topic.getName(), topic);
    }

    public Topic getTopic(String name) {
        Topic topic = topics.get(name);

        if (topic == null)
            throw new IllegalArgumentException("Topic doesn't exist");

        return topic;
    }

    public void deleteTopic(String name) {
        topics.remove(name);
    }
}
