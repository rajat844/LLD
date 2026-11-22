# Builder Pattern

## Core Intent

* The Builder Pattern is a creational design pattern that lets us construct complex object step-by-step. It saperates the construction of an object from its representation, allowing the same construction process to produce different configurations.

## Why Use It?

* **Solves the telescoping constructor problem**: Avoids writing a dozen overloaded constructors.
* **Support Immutability**: The target object's fields are marked `private final` with no public settters, making the constructor object immutable and inherently thread-safe.
* **Enforces Validation Before Creation**: The build method act as a single checkpoint to validate invariants before object is ever instantiated.
* **Improves Readability**: Method chaining makes object creation read like sentence.
