# Class Relationships

## Dependency (The Temporary Interaction)

* The Vibe: "I need you for a second, then I’m done."
* Code Translation: The object is passed as a method parameter, returned, or created locally. It is never stored as a class field.
* Lifecycle: Zero connection.
* UML: Dashed arrow (--->).
* Example: A Chef uses a Knife inside the chop(Knife knife) method.

## Association (The Structural Link)

* The Vibe: "I know you, and I keep your contact info."
* Code Translation: The object is stored as a persistent class field. It can be 1-to-1, 1-to-many, unidirectional, or bidirectional.
* Lifecycle: Completely independent.
* UML: Solid line (_____>).
* Example: A Driver has a Vehicle.

## Aggregation (The Loose Whole-Part)

* The Vibe: "You are part of my group, but you have your own life."
* Code Translation: A specialized Association representing a container. The part is stored as a field, usually passed in via Dependency Injection (constructor parameter).
* Lifecycle: Independent. Destroy the container, and the parts survive.
* UML: Hollow diamond (◇—) on the container side.
* Example: A Department contains Teachers. Close the department, and the teachers just move to another school.

## Composition (The Strict Whole-Part)

* The Vibe: "I created you, and if I go down, you go down with me."
* Code Translation: The strongest relationship. The part is usually instantiated directly inside the whole's constructor using the new keyword.
* Lifecycle: Tightly bound ownership. Destroy the whole, and the parts are immediately garbage collected.
* UML: Filled diamond (◆—) on the owner side.
* Example: A House contains Rooms. Demolish the house, and the rooms cease to exist.

## Interface (The Contract)

* The Vibe: "I don't care who or what you are, as long as you can do exactly these things."
* Code Translation: A strict rulebook. Defines what needs to be done, but provides zero state (no instance variables) and historically no implementation. A class can implement multiple interfaces, making it perfect for plug-and-play behaviors.
* UML: Box with at the top. Connected via a dashed line with a hollow triangle (---|>).
* Example: PaymentStrategy. A CreditCard and CryptoWallet have nothing in common genetically, but both can sign the contract to implement pay().

## Abstract Class (The Shared DNA / The Template)

* The Vibe: "We belong to the same family. I’ve built half the house for you, but you need to finish the rest."
* Code Translation: A partially built class. It can hold state (instance variables, locks, constructors) and shared concrete logic, alongside empty abstract methods. A class can only extend one abstract class (strict IS-A relationship).
* UML: Class name written in Italics (or labeled {abstract}). Connected via a solid line with a hollow triangle (____|>).
* Example: Account. It provides the balance field, the lock, and the exact code for debit(). UpiAccount and CardAccount inherit all of that for free, but must write their own specific validate() logic.
