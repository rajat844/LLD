package Code.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Partition {
    private UUID id;
    private List<Message> messages;
    private int nextOffset;

    public Partition(UUID id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.nextOffset = 0;
    }

    public synchronized int append(Message message) {
        int offset = nextOffset++;
        message.setOffset(offset);
        messages.add(message);

        return offset;
    }

    public synchronized List<Message> read(int offset) {
        if (offset < 0 || offset >= messages.size())
            return new ArrayList<>();

        return new ArrayList<>(messages.subList(offset, messages.size()));
    }

    public UUID getId() {
        return this.id;
    }

}
