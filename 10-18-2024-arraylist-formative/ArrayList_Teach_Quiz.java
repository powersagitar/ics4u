
/**
 * ArrayList Formative Quiz
 *
 * Author: Mohan Dong
 * Date: 10/18/2024
 *
 * Marks:
 * 12 A, 4 K, 2 C
 */

import java.util.Scanner;

import java.util.ArrayList; //Import library

public class ArrayList_Teach_Quiz {

  public static void main(String[] args) {

    @SuppressWarnings("resource")
    Scanner kb = new Scanner(System.in);

    // Declare an arrayList called a1 that can store items Quiz / 12

    ArrayList<String> a1 = new ArrayList<String>();// Generic declaration /2

    @SuppressWarnings("unused")
    String input;

    // Fill in the answers for the blanks below

    System.out.println("Initial size of a1 after declaration is: " + a1.size()); // Output would be "Initial size of a1
                                                                                 // after declaration is: 0" /1
    System.out.println("Press a key to continue");
    input = kb.nextLine();

    // Adding items to the arraylist
    a1.add("C");// position 0
    a1.add("A");// 1
    a1.add("E");// 2
    a1.add("B");// 3
    a1.add("D");
    a1.add("F");
    // Inserting an item A2 in the 2nd position of the array (in between C and A
    a1.add(1, "A2"); // Insert "A2" to index 1 /2

    System.out.println("Size of a1 after adding 7 items  is: " + a1.size()); // Output would be "Size of a1 after adding
                                                                             // 7 items is: 7" /2
    System.out.println("Press a key to continue");
    input = kb.nextLine();

    System.out.println("The contents of a1 is: " + a1); // Output would be "The contents of a1 is: ["C", "A2", "A", "E",
                                                        // "B", "D", "F"]" /2
    System.out.println("Press a key to continue");
    input = kb.nextLine();

    // Remove elements from the array
    a1.remove("F");
    a1.remove(2);

    System.out.println("Size of a1 after  deletions  is: " + a1.size());// Output would be "Size of a1 after deletion
                                                                        // is: 5" /1
    System.out.println("Press a key to continue");
    input = kb.nextLine();

    System.out.println("The contents of a1 after removing F and the 2nd item is: " + a1);// Output would be "The
                                                                                         // contents of a1 after
                                                                                         // removing F and the 2nd item
                                                                                         // is: ["C", "A2", "E", "B",
                                                                                         // "D"]" /2
    System.out.println("Press a key to continue");
    input = kb.nextLine();

  }
}

/**
 * Pros and Cons
 * Pros:
 * - More utility methods for manipulating the data structure
 * - Dynamic, size is no longer fixed after definition
 * - Easier to print
 * Cons:
 * - Slower than normal arrays
 * - Primitive types are not supported
 */
