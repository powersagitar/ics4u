/**
 * The Square class represents a square shape and extends the Shape class.
 * It provides implementations for drawing and erasing the square.
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * Square square = new Square();
 * square.draw(); // Output: []
 * square.erase(); // Output: We erased the []
 * </pre>
 */
public class Square extends Shape {
    // Implementation of abstract Shape::draw()
    public void draw() {
        System.out.println("[]");
    }

    // Implementation of abstract Shape::erase()
    public void erase() {
        System.out.println("We erased the []");
    }

}
