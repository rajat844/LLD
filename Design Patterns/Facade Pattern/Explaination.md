# Facade Pattern

## Core Intent

* The Facade Pattern provides a simplified, unified interface to a complex subsystem of classes, libraries or APIs. Instead if forcing client code to instantiate, configure and coordinate a dozen intenal object in the right order. The facade exposes high-level methods that delegate the work to the underlying subsystem component.

## Why Use It?

* **Hide Subsystem Complexity**: Shields client from intricate multi-step workflows.
* **Reduces Coupling**: Client code depends only on the facade rather than dozen on internal subsystem classes. If internal subsystem classes changes, client code stay untouched.
* **Centralized Orchestration**: Acts as a clean entry point for coordinating multiple services.
