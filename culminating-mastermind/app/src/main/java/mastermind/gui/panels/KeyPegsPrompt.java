/*
    Authors: Mohan Dong
    Date: 01/15/2024
    Title: KeyPegsPrompt.java
 */

package mastermind.gui.panels;

import mastermind.Mastermind;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A panel that allows the user to set the number of black and white key pegs.
 */
public class KeyPegsPrompt extends JPanel {
    /**
     * The number of black pegs.
     * Uses AtomicInteger for thread-safe operations.
     */
    private final AtomicInteger blackPegs = new AtomicInteger(1);

    /**
     * The number of white pegs.
     * Uses AtomicInteger for thread-safe operations.
     */
    private final AtomicInteger whitePegs = new AtomicInteger(1);

    /**
     * The decrement button for the black pegs.
     * Clicking this button decreases the count of black pegs.
     */
    private final JButton blackDecrementButton = new JButton("-");

    /**
     * The increment button for the black pegs.
     * Clicking this button increases the count of black pegs.
     */
    private final JButton blackIncrementButton = new JButton("+");

    /**
     * The decrement button for the white pegs.
     * Clicking this button decreases the count of white pegs.
     */
    private final JButton whiteDecrementButton = new JButton("-");

    /**
     * The increment button for the white pegs.
     * Clicking this button increases the count of white pegs.
     */
    private final JButton whiteIncrementButton = new JButton("+");

    /**
     * Creates a new key pegs counter panel.
     * Initializes the layout and adds controls for black and white pegs.
     */
    public KeyPegsPrompt() {
        super();
        // Set the layout of the panel to BoxLayout (Y_AXIS)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Draw controls for black pegs
        drawPegControl("Black Pegs: ", blackDecrementButton, blackIncrementButton, blackPegs);
        // Draw controls for white pegs
        drawPegControl("White Pegs: ", whiteDecrementButton, whiteIncrementButton, whitePegs);
    }

    /**
     * Returns the number of black pegs.
     *
     * @return the number of black pegs
     */
    public int getBlackPegs() {
        return blackPegs.get();
    }

    /**
     * Returns the number of white pegs.
     *
     * @return the number of white pegs
     */
    public int getWhitePegs() {
        return whitePegs.get();
    }

    /**
     * Draws a peg control.
     * Creates a label and buttons for incrementing and decrementing the peg count.
     *
     * @param prefix          the prefix for the label
     * @param decrementButton the decrement button
     * @param incrementButton the increment button
     * @param pegs            the number of pegs
     */
    private void drawPegControl(final String prefix,
            final JButton decrementButton,
            final JButton incrementButton,
            final AtomicInteger pegs) {
        // Create a label with the initial peg count
        final JLabel label = new JLabel(prefix + pegs.get());

        // Add action listener to decrement button
        decrementButton.addActionListener(e -> {
            // Decrement the peg count and update the label
            final int newCount = pegs.decrementAndGet();
            label.setText(prefix + newCount);
            // Toggle the state of the buttons
            toggleButtons();
        });

        // Add action listener to increment button
        incrementButton.addActionListener(e -> {
            // Increment the peg count and update the label
            final int newCount = pegs.incrementAndGet();
            label.setText(prefix + newCount);
            // Toggle the state of the buttons
            toggleButtons();
        });

        // Create a horizontal panel to hold the label and buttons
        final JPanel hPanel = new JPanel(new FlowLayout());
        hPanel.add(label);
        hPanel.add(decrementButton);
        hPanel.add(incrementButton);

        // Add the horizontal panel to the main panel
        add(hPanel);
    }

    /**
     * Toggles the buttons based on the current state of the pegs.
     * Disables increment buttons if the total peg count reaches the code length.
     */
    private void toggleButtons() {
        // Enable/disable the black decrement button based on the black peg count
        blackDecrementButton.setEnabled(blackPegs.get() > 0);
        // Enable/disable the white decrement button based on the white peg count
        whiteDecrementButton.setEnabled(whitePegs.get() > 0);

        // Disable increment buttons if the total peg count reaches the code length
        if (blackPegs.get() + whitePegs.get() == Mastermind.CODE_LENGTH) {
            blackIncrementButton.setEnabled(false);
            whiteIncrementButton.setEnabled(false);
        } else {
            blackIncrementButton.setEnabled(true);
            whiteIncrementButton.setEnabled(true);
        }
    }
}