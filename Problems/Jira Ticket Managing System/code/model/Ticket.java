package code.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private TicketType type;

    private String title;
    private String description;

    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;

    private Agent assignedAgent;

    public Ticket(String title, String description, TicketType type) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.type = type;

        this.creationDate = LocalDateTime.now();
        this.status = TaskStatus.OPEN;

        System.out.println("Ticket '" + title + "' description: '" + description + "' created at " + creationDate
                + " and will be assigned to agent very soon.");
    }

    public UUID getId() {
        return id;
    }

    public TicketType getType() {
        return type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
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
        if (status != TaskStatus.OPEN && status != TaskStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only an OPEN or IN_PROGRESS ticket can be started. Current status: " + status);
        }

        status = TaskStatus.IN_PROGRESS;
    }

    public boolean resolve() {
        if (status == TaskStatus.RESOLVED) {
            return true;
        }

        if (status != TaskStatus.IN_PROGRESS && status != TaskStatus.OPEN) {
            throw new IllegalStateException(
                    "Only an OPEN or IN_PROGRESS ticket can be resolved. Current status: " + status);
        }

        status = TaskStatus.RESOLVED;
        return true;
    }

    public void updateDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
