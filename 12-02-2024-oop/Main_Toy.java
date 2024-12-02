/*Name: J. Anandarajan
 * File: Toy.java
 *Purpose: Introduce OOP using Toy.java 
 *
 * */

import java.util.ArrayList; //Import library

import java.awt.*;
import java.util.Scanner;// scanner to get input

public class Main_Toy{
  
  public static void main (String[] args) 
  {
    ArrayList<Toy> list = new ArrayList<Toy>();
    
    //Declare an object using a constructor
    Toy bob = new Toy("Bobby", 100,120);
    
    Toy David = new Toy();
    
    System.out.println(bob.getName());
    bob.setName("Tom");
    
    System.out.println(bob.getName());
    //Output the characteristics of the Toy David
    
    System.out.println(David.getName());
    System.out.println(David.getCost());
    David.setName("David Wang");
    David.setCost(100.00);
    
    System.out.println(David.getName());
    System.out.println(David.getCost());
    
    System.out.println("Using an arraylist of objects");
    list.add(David);
    list.add(bob);
   
    
    //Output using a for loop each object's characteristics
    for (int i=0; i<list.size(); i++){
      
      System.out.println("The name of object: "+ list.get(i).getName()+" will have the cost of: "+list.get(i).getCost());
    }
  }
}
