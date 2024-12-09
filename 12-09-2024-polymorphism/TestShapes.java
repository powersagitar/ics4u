import java.util.ArrayList;
import java.util.Scanner;

public class TestShapes {
    static Scanner scanner = new Scanner(System.in);
    static final int MAX_SHAPES = 3;

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<Shape>(3);

        for (int i = 0; i < MAX_SHAPES; ++i) {
            System.err.println("What kind of shape would you like to draw? c = circle, s = square, t = triangle");

            final char input = scanner.nextLine().charAt(0);

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

        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
