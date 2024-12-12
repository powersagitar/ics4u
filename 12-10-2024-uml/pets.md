# Pets

## Requirements

- Create a class diagram for a house that can have zero to many pets living in it
- There are two types of pets that can live in a house: dogs and cats
- Each pet will have a name and can make a noise, eat, and sleep
- Pets can only live in one home

## UML

```mermaid
classDiagram

House "1" -- "*" Pet

Dog --|> Pet
Cat --|> Pet

class House {
-name: String
-rooms: int
-numDogs: int
-numCats: int

+getName(): String
+setName(name): void

+getRooms(): int
+setRooms(num): void

+getNumDogs(): int
+setNumDogs(num): void

+getNumCats(): int
+setNumCats(num): void
}

class Pet {
<<abstract>>

-name: String

+getName(): String
+setName(name): void

+makeNoise()*: void
+eat()*: void
+sleep()*: void
}

class Dog {
+makeNoise(): void
+eat(): void
+sleep(): void
}

class Cat {
+makeNoise(): void
+eat(): void
+sleep(): void
}
```
