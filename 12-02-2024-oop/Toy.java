/*Name: J. Anandarajan
 * File: Toy.java
 * Purpose: Introduce OOP using Toy.java
 * Date: April 4, 2022
 * */

import java.util.Comparator;

public class Toy {

    public static class SortByProfit implements Comparator<Toy> {

        public int compare(Toy a, Toy b) {
            return (int) (a.getProfit() - b.getProfit());
        }
    }

    //Class variable
    static String nameOfCo = "Toys R Us";

    //Characteristics/State - (Instance variables)
    private String name;
    private double cost;
    private double sellingPrice;

    //Constructor method - has the same name as the class, has parameters that are passed
    public Toy(String defaultName, double startCost, double startSellingPrice) { //This is the constructor
        name = defaultName;
        cost = startCost;
        sellingPrice = startSellingPrice;
    }

    //Method Over loading: When two or more methods have the same name, but differ in
    // the parameters or the number of parameters then it is called Method Overloading

    //Another constructor method (or any method)- You will notice - same method name  differs by parameters passed, or the
    //number of parameters passed - this is called Method Over loading

    public Toy() { //This is the constructor
        name = "default";
        cost = 0.00;
        sellingPrice = 0.00;
    }

    //Behaviours: Communicators and Actions

    //Return type methods to get values - Accessor Method
    public String getName() { //returns name of the object Toy - Accessor method (getter)
        return name;
    }

    public double getCost() { //returns name of the object Toy - Accessor method (getter)
        return cost;
    }

    //Void type methods to set values and also actions to perform - Modifier method (setter0
    public void setName(String newName) { //sets a new name for the object Toy - Modifier method (setter)
        name = newName;
    }

    public void setCost(double newCost) { //sets a new name for the object Toy - Modifier method (setter)
        cost = newCost;
    }

    public double getSellingPrice() { //returns name of the object Toy - Accessor method (getter)
        return sellingPrice;
    }

    public void setSellingPrice(double newSellingPrice) { //sets a new name for the object Toy - Modifier method (setter)
        sellingPrice = newSellingPrice;
    }

    public double getProfit() {
        return sellingPrice - cost;
    }
}
