# Adapter Pattern

## Core Intent

* The Adapter pattern is a structural design pattern that converts the interface of an existing class into another interface that client expects.
* It acts as a bridge between 2 incompatible interfaces so they can work together without modifying their existing source code.

## Why Use It?

* **Integrate Third-Party SDKs**: Lets you plug in external libraries or legacy classes whose method signatures can't be modified.
* **Decouples Business Logic from Vendor APIs**: Core application talks only to clean target interface, isolating vendor-specific data formats or method names inside adapter.
* **Follow SRP & OCP**: Data transalation lives in a dedicated adapter class rather than cluttering core business logic, and can swap vendors by writing a new adapter without touching existing callers.

## Class Adapter

* Inheritance + Interface Implementation
* Breaks if base class is abstract class
* It is needed when we want to change behaviour of existing class that we can't modify.

## Object Class

* Interface Implementation
* Works on interface
* Default choice for streamling the behaviour of existing class.
