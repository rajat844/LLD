# Singleton Pattern

## Core Intent

* The Singleton pattern is a creational design pattern that ensures a class has only one instance across the entire application lifecycle and provides a global point of access to it.

## Why Use It?

* **Controlled Access to Shared Resources**: Prevents conflicting writes or resource exhaustion when multiple components access a single shared resource.
* **Avoid Repeated Object Creation**: Eliminates the memory and latency overhead of repeatedly instantiating heavy objects.
* **Consistent Global State**: Ensures all parts of the system read from and write to the exact same state coordinator.