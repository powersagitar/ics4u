/**
 * The Circle class represents a circle shape and extends the Shape class.
 * It provides implementations for the draw and erase methods.
 *
 * <p>
 * This class overrides the following methods from the Shape class:
 * </p>
 * <ul>
 * <li>{@link #draw()} - Prints "O" to represent drawing a circle.</li>
 * <li>{@link #erase()} - Prints a message indicating the circle has been
 * erased.</li>
 * </ul>
 */
public class Circle extends Shape {
    // Implementation of abstract Shape::draw()
    public void draw() {
        System.out.println("O");
    }

    // Implementation of abstract Shape::erase()
    public void erase() {
        System.out.println("We erased the O");
    }
}
