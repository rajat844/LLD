package code.model;

public class Subtask implements Task {
    private String name;
    private TaskStatus status;
    private int pendingHours;

    public Subtask(String name, int pendingHours) {
        this.name = name;
        this.pendingHours = pendingHours;
        this.status = TaskStatus.OPEN;
    }

    public Subtask(String name, int pendingHours, TaskStatus status) {
        this.name = name;
        this.pendingHours = pendingHours;
        this.status = status != null ? status : TaskStatus.OPEN;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPendingWorkingHours() {
        if (status == TaskStatus.RESOLVED) {
            return 0;
        }
        return pendingHours;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    public void startProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    @Override
    public boolean resolve() {
        this.status = TaskStatus.RESOLVED;
        return true;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", pendingHours=" + pendingHours +
                '}';
    }
}
