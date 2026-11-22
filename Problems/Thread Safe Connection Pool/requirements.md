# Thread Safe Connection Pool

## Functional Requirements

1. The system will manage a pool of reuseable connections configured with minIdle and maxPoolSize limits. Plus a graceful shutdown mechanism.
2. Application threads can request a connection via aquire. If idle connection exist, return connection object, if at capacity new thread is created else return connection failed.
3. The system will check the health of connection present in pool before handling it to a thread. If unhealthy destroy the physical connection and tranparently provision a fresh one.
4. When caller releases a connection, intercept the call to return the unerlying connection to the idle queue.
5. The system will wrap raw connections to record metrices.

## Non Functional Requirements

1. The execution of queries should be thread safe.
2. Avoid deadlock situations.

## Entities

1. Connection Pool
2. Connection Interface
3. Basic Connection
4. Connection Decorator
5. Pooled Connection
6. Metrices Connection
7. Health Check Connection
8. Connection Factory
9. Metrics Collector
