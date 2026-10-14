# Notification System

## Requirements

1. The system shall send notification to users across supported channels and remain extensible to new channels.
2. User shall be able to subscribe or unsubscribe from specific channel, the system must dispatch notification to active subscribed channels.
3. Every notification shall be assigned a severity level and system must process and dispatch higher priority notifications ahead of lower priority ones
4. If a notification fails to send on a given channel, the system shall automatically retry delivery based on a configurable retry policy before marking the attempt failed.
5. The notification status can be logged.
6. The system shall persist and update the state of every notification delivery attempt per channel for auditing and querying.

## Entities

1. Notification
2. User
3. Notification channels
4. Notification Level
5. Notification status
6. Notification Manager
7. Notification Handler
8. Notification Dispatcher
9. Notification Channel
10. Channel Factory
11. Retry Policy
12. User Repository
13. Notification Repository
