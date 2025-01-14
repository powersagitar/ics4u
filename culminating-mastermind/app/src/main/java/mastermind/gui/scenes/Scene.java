package mastermind.gui.scenes;

import mastermind.Mastermind;

import javax.swing.*;

/**
 * The ultimate base class of all {@link mastermind.gui.scenes}.
 * <p>
 * Every scene derives from {@code Scene}.
 */
public abstract class Scene {
    /**
     * The JFrame associated with this scene.
     */
    protected final JFrame frame;

    /**
     * Creates and returns a default configured JFrame instance.
     *
     * <p>
     * The returned JFrame uses a vertical BoxLayout for its content pane, and its size, title, and visibility
     * settings are predefined. The JFrame is also resizable.
     *
     * @return A configured JFrame instance.
     */
    public static JFrame createDefaultScene() {
        final JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setSize(Mastermind.CANVAS_DIMENSION);
        frame.setTitle("Mastermind");
        frame.setResizable(true);
        frame.setVisible(true);

        return frame;
    }

    /**
     * Initializes a new Scene instance with the specified JFrame.
     *
     * <p>
     * This constructor assigns the given JFrame to the Scene instance and ensures
     * that the content pane of the provided JFrame is cleared of all existing components.
     *
     * @param frame The JFrame to be associated with this Scene. The content pane
     *              of this frame will be cleared during instantiation.
     */
    Scene(final JFrame frame) {
        this.frame = frame;
        this.frame.getContentPane().removeAll();
    }

    /**
     * Refreshes the associated JFrame by repainting and revalidating it.
     *
     * <p>
     * This method ensures that the visual representation of the frame is updated to
     * reflect any changes made to its components or layout. It is typically used
     * after modifying the frame's contents or its structure to force an immediate update.
     */
    void refreshFrame() {
        frame.repaint();
        frame.revalidate();
    }
}
