package code.entities;

import java.util.ArrayList;

public class LogCollector {
    private ArrayList<String> storage;

    public LogCollector() {
        this.storage = new ArrayList<>();
    }

    public synchronized void addLogs(String log) {
        storage.add(log);
    }

    public synchronized void dumpLogs(String query) {
        System.out.println("=== Cache Logs (query: '" + query + "') ===");
        for (String log : storage) {
            if (query == null || query.isEmpty() || log.contains(query)) {
                System.out.println(log);
            }
        }
    }

    public synchronized ArrayList<String> getStorage() {
        return new ArrayList<>(storage);
    }
}
