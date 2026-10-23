package code.entities;

import java.util.concurrent.atomic.AtomicLong;

public class MetricsCollector {
    private AtomicLong totalQueries;
    private AtomicLong totalLatency;

    public MetricsCollector() {
        this.totalQueries = new AtomicLong(0);
        this.totalLatency = new AtomicLong(0);
    }

    public void recordQuery(long latency) {
        // Trecord query
    }

    public void dumpMetrics() {
        //dump metrics
    }

    public long getTotalQueries() {
        return totalQueries.get();
    }

    public long getTotalLatency() {
        return totalLatency.get();
    }
}

