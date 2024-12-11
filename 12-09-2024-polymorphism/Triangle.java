/**
 * The Triangle class represents a triangle shape and extends the Shape class.
 * It provides implementations for the draw and erase methods.
 *
 * <p>
 * Example usage:
 *
 * <pre>
 * Triangle triangle = new Triangle();
 * triangle.draw(); // Output: /\
 * triangle.erase(); // Output: We erased the /
 * \
 * </pre>
 * </p>
 */
public class Triangle extends Shape {
    // Implementation of abstract Shape::draw()
    public void draw() {
        System.out.println("/\\");
    }

    // Implementation of abstract Shape::erase()
    public void erase() {
        System.out.println("We erased the /\\");
    }
}
