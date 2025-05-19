# 🎯 Design Patterns – Course Overview

Welcome to the **Design Patterns** course repository!  
This course provides a deep dive into **object-oriented design principles** and explores how to write maintainable, scalable, and flexible software using time-tested **Design Patterns**.

---

## 🧠 Why Design Patterns?

Design patterns provide reusable solutions to common software design problems. Each pattern represents a best practice for specific challenges faced during software development.

---

## 📌 Table of Contents

### 🏗️ Creational Patterns
1. [Factory Method](#1-factory-method)
2. [Abstract Factory](#2-abstract-factory)
3. [Builder](#3-builder)
4. [Prototype](#4-prototype)
5. [Singleton](#5-singleton)

### 🧱 Structural Patterns
1. [Adapter](#1-adapter)
2. [Bridge](#2-bridge)
3. [Composite](#3-composite)
4. [Decorator](#4-decorator)
5. [Facade](#5-facade)
6. [Proxy](#6-proxy)

### 🔁 Behavioral Patterns
1. [Chain of Responsibility](#1-chain-of-responsibility)
2. [Command](#2-command)
3. [Iterator](#3-iterator)
4. [Mediator](#4-mediator)
5. [Observer](#5-observer)
6. [Strategy](#6-strategy)
7. [Template Method](#7-template-method)

---

## 🏗️ Creational Patterns

### 1. Factory Method
- Defines an interface for creating an object but lets subclasses alter the type of objects that will be created.
- **Use Case**: When the exact type of object isn't known until runtime.

### 2. Abstract Factory
- Produces families of related objects without specifying their concrete classes.
- **Use Case**: UI toolkit where different platforms need different widgets.

### 3. Builder
- Separates the construction of a complex object from its representation.
- **Use Case**: Creating an object step by step like a meal order or document generation.

### 4. Prototype
- Creates new objects by copying an existing object (clone).
- **Use Case**: When object creation is costly and you want to reuse instances.

### 5. Singleton
- Ensures a class has only one instance and provides a global access point.
- **Use Case**: Configuration manager, logging, thread pool.

---

## 🧱 Structural Patterns

### 1. Adapter
- Converts one interface into another interface that a client expects.
- **Use Case**: Integrating legacy code with modern APIs.

### 2. Bridge
- Decouples abstraction from its implementation so the two can vary independently.
- **Use Case**: Device and remote control example (e.g., TV & Remote).

### 3. Composite
- Composes objects into tree structures and treats individual and composite objects uniformly.
- **Use Case**: File system hierarchy (files and folders).

### 4. Decorator
- Adds responsibilities to objects dynamically without altering their structure.
- **Use Case**: Adding features to GUI components like scrollbars.

### 5. Facade
- Provides a simplified interface to a complex subsystem.
- **Use Case**: Wrapper for a complicated library (e.g., HTTPClient).

### 6. Proxy
- Provides a surrogate or placeholder for another object to control access.
- **Use Case**: Virtual proxy for lazy loading, protection proxy for access control.

---

## 🔁 Behavioral Patterns

### 1. Chain of Responsibility
- Passes request along a chain of handlers until one handles it.
- **Use Case**: Logging levels (INFO, WARNING, ERROR).

### 2. Command
- Encapsulates a request as an object, allowing undo/redo functionality.
- **Use Case**: GUI buttons, remote control operations.

### 3. Iterator
- Provides a way to access elements of a collection sequentially without exposing underlying structure.
- **Use Case**: Looping over different types of collections uniformly.

### 4. Mediator
- Reduces coupling between components by having them communicate via a central mediator object.
- **Use Case**: Chat room with users communicating via a server.

### 5. Observer
- Allows multiple observers to react to changes in subject's state.
- **Use Case**: Event systems, stock ticker, GUI frameworks.

### 6. Strategy
- Allows selecting an algorithm’s behavior at runtime.
- **Use Case**: Payment methods (Credit Card, PayPal), sorting strategies.

### 7. Template Method
- Defines the skeleton of an algorithm in a base class and allows subclasses to override specific steps.
- **Use Case**: Game initialization steps, report generation.

---

## 📚 Summary

This course offered hands-on exposure to:
- Creating flexible object hierarchies using **Creational Patterns**
- Structuring code efficiently using **Structural Patterns**
- Managing complex object behaviors with **Behavioral Patterns**

Mastering design patterns helps in writing clean, maintainable, and scalable object-oriented code.

---

## 👨‍🎓 Student Info

- **Name**: Munshi Md Arafat Hussain  
- **Course**: Design Patterns  
- **Topics Covered**: 23 Classic Patterns across Creational, Structural, and Behavioral Categories

---

