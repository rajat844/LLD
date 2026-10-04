package Code.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private TicketType type;

    private String title;
    private String description;

    private TicketStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;

    private Agent assignedAgent;

    public Ticket(String title, String description, TicketType type) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.type = type;

        this.creationDate = LocalDateTime.now();
        this.status = TicketStatus.OPEN;

        System.out.println("Ticket" + title + " description" + description + "created at" + creationDate
                + " and will be assigned to agent very soon.");
    }

    public UUID getId() {
        return id;
    }

    public TicketType getType() {
        return type;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void assignTo(Agent agent) {
        this.assignedAgent = agent;
    }

    public void removeAgent() {
        this.assignedAgent = null;
    }

    public void startProgress() {
        if (status != TicketStatus.OPEN) {
            throw new IllegalStateException(
                    "Only an OPEN ticket can be moved to IN_PROGRESS");
        }

        status = TicketStatus.IN_PROGRESS;
    }

    public void resolve() {
        if (status != TicketStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only an IN_PROGRESS ticket can be resolved");
        }

        status = TicketStatus.RESOLVED;
    }

    public void updateDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
