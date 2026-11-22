# Factory Pattern

## Core Intent

* The Factory Pattern is a creationaly design pattern that defines an interface or abstract method for creating an object, defferring the actual instantiation to subclass.
* Instead of the caller directly invoking a concrete class, a subclass overrides the factory method to decide which concrete object to instantiate at runtime.

## Why Use It?

* **Runtime Object Resolution**: Let's system decide which concrete implementation to instantiate dynamically based on configuration, user input or environment without hardcoding inside the business logic.
* **Decouples Creation from Usage**: Separates the code that uses an object from the code that knows how to construct it.
* **Follow OCP**: Adding a new product type only requires adding a new product class and a new creator subclass.
* **Follow SRP**: Centralizes object creation logic in dedicated creator subclass rather than scattering `if-else` blocks across the codebase.
