package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.GameBoard;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Result extends Scene {
    private final Code correctCode;
    /**
     * Constructs a new Result scene to display the outcome of the Mastermind game.
     *
     * @param frame       The parent JFrame that serves as the container for this scene.
     * @param status      The game status, indicating whether the code breaker has won or lost.
     * @param correctCode The correct code that was to be guessed in the game.
     *
     * @throws IllegalArgumentException If the provided status is not MastermindSolver.Status.Win or MastermindSolver.Status.Lose.
     */
    public Result(final JFrame frame, MastermindSolver.Status status, final Code correctCode) {
        super(frame);

        this.correctCode = correctCode;

        if (status == MastermindSolver.Status.Win) {
            drawWinScreen();
        } else if (status == MastermindSolver.Status.Lose) {
            drawLoseScreen();
        } else {
            throw new IllegalArgumentException("Only Status.Win and Status.Lose can yield results.");
        }

        drawCorrectCode();

        drawProceedButton();

        refreshFrame();
    }

    /**
     * Displays the win screen for the game.
     * This method adds a label to the frame indicating that the code breaker has won.
     * The label is centrally aligned within the frame.
     */
    private void drawWinScreen() {
        final JLabel title = new JLabel("The code breaker wins!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(title);
    }

    /**
     * Displays the losing screen for the Mastermind game.
     * This method adds a label to the frame indicating that the code breaker failed to
     * guess the correct code within the maximum number of attempts allowed.
     * The label is centrally aligned within the frame.
     */
    private void drawLoseScreen() {
        final JLabel title = new JLabel("The code breaker did not guess the code in " + Mastermind.MAX_GUESSES + " attempts.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(title);
    }

    /**
     * Displays the correct code from the Mastermind game in the scene.
     *
     * <p>
     * This method creates and displays a label indicating "The correct code was:"
     * and dynamically generates a panel containing the visual representation of the
     * correct code using the colors from the {@code correctCode} object. Each color
     * in the code is mapped to its corresponding AWT color and displayed using the
     * {@code drawGuess} method from the {@code GameBoard} class. The components
     * are centrally aligned to match the layout of the scene.
     * </p>
     */
    private void drawCorrectCode() {
        final JLabel correctCodeLabel = new JLabel("The correct code was:");
        correctCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(correctCodeLabel);

        final JPanel correctCodePanel = new JPanel(new FlowLayout());
        correctCodePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(correctCodePanel);

        final ArrayList<Color> codeAwtColors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final Code.Color codeColor : correctCode.getColors()) {
            final Color awtColor = GameBoard.codeColorToAwtColor.get(codeColor);
            codeAwtColors.add(awtColor);
        }

        GameBoard.drawGuess(correctCodePanel, codeAwtColors);
    }

    /**
     * Adds a "Proceed" button to the frame and sets up its action listener.
     *
     * <p>
     * The button is centrally aligned and appended to the frame. When clicked,
     * the button transitions the application to the game mode selection screen
     * by instantiating a new {@code GameModeSelector} with the current frame.
     * </p>
     */
    private void drawProceedButton() {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(proceedButton);

        proceedButton.addActionListener(event -> new GameModeSelector(frame));
    }
}
