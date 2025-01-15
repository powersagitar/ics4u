/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: Scene.java
 */

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
        // Create a new JFrame instance
        final JFrame frame = new JFrame();

        // Set the layout of the frame's content pane to a vertical BoxLayout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Set the size of the frame using a predefined dimension from the Mastermind class
        frame.setSize(Mastermind.CANVAS_DIMENSION);

        // Set the title of the frame
        frame.setTitle("Mastermind");

        // Allow the frame to be resizable
        frame.setResizable(true);

        // Make the frame visible
        frame.setVisible(true);

        // Return the configured JFrame instance
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
        // Assign the provided JFrame to the frame field
        this.frame = frame;

        // Clear all existing components from the content pane of the frame
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
        // Repaint the frame to update its visual representation
        frame.repaint();

        // Revalidate the frame to ensure its layout is updated
        frame.revalidate();
    }
}