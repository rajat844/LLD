package code.decorator;

import code.connection.Connection;
import code.entities.MetricsCollector;

public class MetricsConnection extends ConnectionDecorator {
    private MetricsCollector metricsCollector;

    public MetricsConnection(Connection wrappedConnection, MetricsCollector metricsCollector) {
        super(wrappedConnection);
        this.metricsCollector = metricsCollector;
    }

    @Override
    public void execute(String query) {
        super.execute(query);
        // Measure latency
    }

    public MetricsCollector getMetricsCollector() {
        return metricsCollector;
    }
}

