package mastermind.gui.panels;

import mastermind.Mastermind;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A panel that allows the user to set the number of black and white key pegs.
 */
public class KeyPegsCounter extends JPanel {
    /**
     * The number of black pegs.
     */
    private final AtomicInteger blackPegs =
        new AtomicInteger(Mastermind.CODE_LENGTH / 2);

    /**
     * The number of white pegs.
     */
    private final AtomicInteger whitePegs =
        new AtomicInteger(Mastermind.CODE_LENGTH / 2);

    /**
     * The decrement button for the black pegs.
     */
    private final JButton blackDecrementButton = new JButton("-");

    /**
     * The increment button for the black pegs.
     */
    private final JButton blackIncrementButton = new JButton("+");

    /**
     * The decrement button for the white pegs.
     */
    private final JButton whiteDecrementButton = new JButton("-");

    /**
     * The increment button for the white pegs.
     */
    private final JButton whiteIncrementButton = new JButton("+");

    /**
     * Creates a new key pegs counter panel.
     */
    public KeyPegsCounter() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        drawPegControl("Black Pegs: ", blackDecrementButton, blackIncrementButton, blackPegs);
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
        final JLabel label = new JLabel(prefix + pegs.get());

        decrementButton.addActionListener(e -> {
            final int newCount = pegs.decrementAndGet();
            label.setText(prefix + newCount);
            toggleButtons();
        });

        incrementButton.addActionListener(e -> {
            final int newCount = pegs.incrementAndGet();
            label.setText(prefix + newCount);
            toggleButtons();
        });

        final JPanel hPanel = new JPanel(new FlowLayout());
        hPanel.add(label);
        hPanel.add(decrementButton);
        hPanel.add(incrementButton);

        add(hPanel);
    }

    /**
     * Toggles the buttons based on the current state of the pegs.
     */
    private void toggleButtons() {
        blackDecrementButton.setEnabled(blackPegs.get() > 0);
        whiteDecrementButton.setEnabled(whitePegs.get() > 0);

        if (blackPegs.get() + whitePegs.get() == Mastermind.CODE_LENGTH) {
            blackIncrementButton.setEnabled(false);
            whiteIncrementButton.setEnabled(false);
        } else {
            blackIncrementButton.setEnabled(true);
            whiteIncrementButton.setEnabled(true);
        }
    }
}