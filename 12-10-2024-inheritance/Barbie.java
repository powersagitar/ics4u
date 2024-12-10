/*Name: J. Anandarajan
 * File: Barbie.java
 *Purpose: Introduce Inheritance using Toy.java and Barbie
 * Date: Nov. 22, 2023
 * */

import java.awt.*;

//Inheritance - extends means the new class inherits all the attributes and methods of the parent class

public class Barbie extends Toy{
  //New attributes in addition to the attributes declared in Toy
   private int stock_num;
   private double height;
  
   //Constructor method
  public Barbie(String defaultName, double startCost, double startSellingPrice) 
  { 
    //Why super() - beacause you need to construct the base class first before building the subclass
    super(defaultName,startCost,startSellingPrice);//super ()Calls the constructor of the BaseClass
    height= 100;
    //setSellingPrice(200); You should have added it to your Toy object
  }
 public Barbie(String tName) 
  { 
    super();//Calls the constructor of the BaseClass with no parameters
    height= 100;
    //setName(tName);
    name = tName;
  }
  
 //NEw methods
  public  void setHeight (double pHeight)
  {
    height= pHeight;
  }
  public double getHeight ()
  {
    return height;
  }
  public  void displayInfo ()
  {
    System.out.println("height "+height+ "  name: "+ getName()+ " cost: "+getCost());
  }
  
}