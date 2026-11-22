# Decorator Pattern

## Core Intent

* The Decorator pattern lets us attach new behaviors to an object dynamically by placing it inside special wrapper objects that share the exact same interface. Instead of creating subclass at complie time, you stack wrappers around a core object at runtime.

## Why Use It?

* **Prevents Class Explosion**: Instead of creating rigid subclass for every feature combination, compose behaviors at runtime.
* **Follows OCP**: Can extend an object's behaviour by creating new decorator without existing concrete component.
* **Supports Flexible Chaining**: Multiple decorators can be stacked in any order, or even applied twice.
* **Follow SRP**: Keeps the core class lean and focused on its primary job, isolating cross-cutting concerns into dedicated wrapper classes.
* **Precaution**: Decorators must not add new functions, they should just extend the existing methods of the base class.