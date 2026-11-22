package code.factory;

import code.connection.BasicConnection;
import code.connection.Connection;
import code.decorator.HealthCheckConnection;
import code.decorator.MetricsConnection;
import code.decorator.PooledConnection;
import code.entities.ConnectionPool;
import code.entities.MetricsCollector;

public class ConnectionFactory {
    private String dbUrl;
    private MetricsCollector metricsCollector;

    public ConnectionFactory(String dbUrl, MetricsCollector metricsCollector) {
        this.dbUrl = dbUrl;
        this.metricsCollector = metricsCollector;
    }

    public Connection createConnection() {
        Connection conn = new BasicConnection(dbUrl);
        conn = new PooledConnection(conn, ConnectionPool.getInstance());
        conn = new HealthCheckConnection(conn, 50);
        conn = new MetricsConnection(conn, metricsCollector);

        return conn;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public MetricsCollector getMetricsCollector() {
        return metricsCollector;
    }
}
