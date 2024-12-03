/*Name: J. Anandarajan
 * File: Toy.java
 *Purpose: Introduce OOP using Toy.java
 *
 * */

import java.util.ArrayList; //Import library

public class MainToy {

    public static void main(String[] args) {
        ArrayList<Toy> toys = new ArrayList<Toy>(3);
        toys.add(new Toy("Toy 3", 15.0, 30.0));
        toys.add(new Toy("Toy 2", 10.0, 20.0));
        toys.add(new Toy("Toy 1", 5.0, 10.0));

        toys.sort(new Toy.SortByProfit());

        displayToys(toys);
    }

    static void displayToys(ArrayList<Toy> toys) {
        for (Toy toy : toys) {
            System.out.println(toy.getName() + ":");
            System.out.println("Cost: " + toy.getCost());
        }
    }
}
/**
 * A class is a blueprint for an object. It defines a data type and the methods that can be used with that data type.
 * An object is an instance of a class. It is a concrete entity that exists in memory.
 *
 * Instance variables are variables that are unique to each instance of a class. They define the state of an object.
 * Class variables are variables that are shared among all instances of a class. They define the characteristics of a class. Class variables are static.
 *
 * Public methods are visible from outside the class. Public methods are inherited by the derived class.
 * Protected methods are visible only from within the class and its derived classes. Protected methods are inherited by the derived class.
 */
