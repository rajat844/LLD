package code.entities;

import java.util.concurrent.atomic.AtomicLong;

public class MetricsCollector {
    private AtomicLong hits;
    private AtomicLong misses;

    public MetricsCollector() {
        this.hits = new AtomicLong(0);
        this.misses = new AtomicLong(0);
    }

    public void recordHits() {
        hits.incrementAndGet();
    }

    public void recordMiss() {
        misses.incrementAndGet();
    }

    public void dumpMetrics(String query) {
        long currentHits = hits.get();
        long currentMisses = misses.get();
        long total = currentHits + currentMisses;
        double hitRatio = total > 0 ? ((double) currentHits / total) * 100.0 : 0.0;

        System.out.println("=== Cache Metrics (query: '" + query + "') ===");
        System.out.println("Hits: " + currentHits);
        System.out.println("Misses: " + currentMisses);
        System.out.println("Total Requests: " + total);
        System.out.printf("Hit Ratio: %.2f%%\n", hitRatio);
    }

    public long getHits() {
        return hits.get();
    }

    public long getMisses() {
        return misses.get();
    }
}
