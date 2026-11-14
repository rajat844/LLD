package code.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeTicket extends Ticket implements Task {
    private List<Task> tickets;
    private String name;

    public CompositeTicket(String name, String description, TicketType type) {
        super(name, description, type);
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    public CompositeTicket(String name, TicketType type) {
        this(name, "", type);
    }

    @Override
    public String getName() {
        return name != null ? name : getTitle();
    }

    public void addTask(Task task) {
        if (task != null) {
            tickets.add(task);
        }
    }

    public void removeTask(Task task) {
        tickets.remove(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tickets);
    }

    public int getTasksCount() {
        return tickets.size();
    }

    @Override
    public int getCount() {
        if (tickets.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Task task : tickets) {
            total += task.getCount();
        }
        return total;
    }

    @Override
    public int getPendingWorkingHours() {
        int totalHours = 0;
        for (Task task : tickets) {
            totalHours += task.getPendingWorkingHours();
        }
        return totalHours;
    }

    @Override
    public TaskStatus getStatus() {
        if (tickets.isEmpty()) {
            return super.getStatus();
        }

        boolean allResolved = true;
        boolean allOpen = true;

        for (Task task : tickets) {
            TaskStatus s = task.getStatus();
            if (s != TaskStatus.RESOLVED) {
                allResolved = false;
            }
            if (s != TaskStatus.OPEN) {
                allOpen = false;
            }
        }

        if (allResolved) {
            return TaskStatus.RESOLVED;
        }

        if (super.getStatus() == TaskStatus.IN_PROGRESS) {
            return TaskStatus.IN_PROGRESS;
        }

        if (allOpen) {
            return TaskStatus.OPEN;
        }

        return TaskStatus.IN_PROGRESS;
    }

    @Override
    public boolean resolve() {
        for (Task task : tickets) {
            task.resolve();
        }
        super.resolve();
        return true;
    }
}
