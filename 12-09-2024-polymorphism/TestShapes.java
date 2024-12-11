
/**
 * TestShapes
 * Exercise on polymorphism.
 *
 * Shape is the superclass of Circle, Square, and Triangle,
 * with abstract methods draw() and erase().
 *
 * Author: Mohan Dong
 * Date:   12-09-2024
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TestShapes class demonstrates the use of polymorphism by allowing
 * the user to create and draw different shapes (Circle, Square, Triangle).
 */
public class TestShapes {
    static Scanner scanner = new Scanner(System.in);
    static final int MAX_SHAPES = 3;

    /**
     * The main method is the entry point of the program. It prompts the user
     * to input the type of shapes they want to create and then draws them.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<Shape>(3);

        /**
         * Loop to prompt the user to input the type of shape they want to create.
         * The user can choose between Circle, Square, and Triangle.
         */
        for (int i = 0; i < MAX_SHAPES; ++i) {
            System.err.println("What kind of shape would you like to draw? c = circle, s = square, t = triangle");

            final char input = scanner.nextLine().charAt(0);

            /**
             * Switch statement to create the appropriate shape based on user input.
             */
            switch (input) {
                case 'c':
                    shapes.add(new Circle());
                    break;

                case 't':
                    shapes.add(new Triangle());
                    break;

                case 's':
                    shapes.add(new Square());
                    break;
            }
        }

        System.err.println("Here are the shapes you entered.");

        /**
         * Loop to draw each shape that was created.
         */
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
