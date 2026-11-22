package code.model;

public interface Task {
    String getName();
    TaskStatus getStatus();
    int getPendingWorkingHours();
    int getCount();
    boolean resolve();
}
