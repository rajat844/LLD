
# Design Principles

## DRY Principle

- There should be only one source of knowledge in whole system
- Whenever we need to use that knowledge we should refer to that source
- Redundancy make a system prone to bugs
- Extract the shared logic on if that knowledge is used more than twice.

## KISS Principle(Keep It Simple, Stupid)

- Keep the code simple, don't add unnecessary complexities
- Code should be easy to read, understand and modify
- With complicated code there comes the bugs in the system.

## YAGNI Principle

- You ain't gonna need it, It means when there is no need to abstraction today, don't just build it because you will need it tomorrow.
- Software are constantly changing, you can build it tomorrow and that time you will have better knowledge of its actual requirements.

## SOLID Design Principles

### Single Responsibility Principle

- A class should have only one responsibility
- The only reason to change that class is when we need to change that single responsibility
- Keeps code maintainable and clean

### Open Close Principle

- A class should be open for extension and closed for modification
- That means when new requirement comes, the current code shall not be modified to add new feature.
- The new requirement shall only adds up to the class
- The modification leads to bugs

### Liskov's Substitution Principle

- A instance of derived class can replace all the instances of the base class without breaking the application.
- That means a derived class shall implement all the data-members and functions of the base class.
- If a child class overrides a parent method just to throw and exception, it voilates LSP, 

### Interface Segregation Principle

- No class shall implement the functions it doesn't need.
- The idea behind this principle is that all the interface the class implements shall be small and focused
- Interfaces shall not have methods the class doesn't need

#### Dependency Inversion Principle

- The modules of higher level shall not be dependent on low level modules. But both shall depend on abstract classes.
- A class shall not be dependent on other class instead an abstraction of that class.
