//Teach Inheritance
//Date" Nov. 22, 2023

import java.awt.*;


public class TestBarbie{
  

  
  public static void main (String[] args) 
  {
   // Object type
    
    Barbie Doll2 = new Barbie ("Irene", 20, 30);
     Barbie doll = new Barbie ("Sarah", 10, 12);
     
     
     System.out.println("Name: "+ Doll2.getName());
    System.out.println("Height: "+ Doll2.getHeight());
      System.out.println("Cost: "+ Doll2.getCost());
      
    System.out.println(doll.getName());
    System.out.println(doll.getHeight());
    
    //Re-setting the attribute for the new toy the height
   doll.setHeight(300.0);
   
    doll.displayInfo();
    
    //Re-printing the height attribute for the new toy
    System.out.println(doll.getHeight());
    
  }
}