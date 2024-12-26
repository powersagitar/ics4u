package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.MastermindAlgorithm;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.GameBoard;
import mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeBreaker extends Scene {
    private final MastermindAlgorithm solver;
    private final GameBoard gameBoard = new GameBoard();
    private final AtomicInteger correctCount = new AtomicInteger(2);
    private final AtomicInteger misplacementCount = new AtomicInteger(2);
    private final JButton proceedButton = new JButton("Proceed");
    private final JPanel flowPanel = new JPanel(new FlowLayout());
    private final JPanel controlPanel = new JPanel();

    /**
     * Represents the CodeBreaker scene in the application, inheriting from the parent `Scene` class.
     * This scene manages the game logic and user interface for a Mastermind game implementation.
     * It initializes necessary UI components, handles game interactions, and updates the game state.
     *
     * <p>
     * The class uses a selected algorithm to solve the Mastermind puzzle and provides controls
     * for managing user responses and progression in the game.
     * </p>
     *
     * @param frame     The parent `JFrame` that contains this scene.
     * @param algorithm The algorithm to be used for solving the Mastermind puzzle.
     *                  Supported algorithms include `DonaldKnuth`, `Medium`, and `Basic`.
     *
     * @throws IllegalArgumentException if the provided algorithm is null or unsupported.
     */
    public CodeBreaker(final JFrame frame, final CodeBreakerSelector.Algorithm algorithm) {
        super(frame);

        switch (algorithm) {
            case DonaldKnuth -> this.solver = new DonaldKnuthAlgorithm();
            case Medium -> this.solver = new DonaldKnuthAlgorithm();
            case Basic -> this.solver = new DonaldKnuthAlgorithm();
            default ->
                    throw new IllegalArgumentException("algorithm is not nullable.");
        }

        frame.add(flowPanel);

        drawGameBoard();

        drawControlPanel();

        drawCorrectControls();

        drawMisplacementControls();

        drawProceedButton();

        registerGuessHandler();

        refreshFrame();
    }

    /**
     * Draws the game board panel onto the game's flow panel.
     *
     * <p>
     * This method retrieves the board panel from the `GameBoard` instance
     * and adds it to the `flowPanel`, which is part of the main user interface.
     * It ensures that the visual representation of the game board is
     * displayed within the current scene.
     * </p>
     */
    private void drawGameBoard() {
        flowPanel.add(gameBoard.getBoardPanel());
    }

    /**
     * Draws the control panel for the CodeBreaker game scene.
     *
     * <p>
     * This method initializes the layout for the control panel using a vertical `BoxLayout`,
     * adds a title label ("Controls") to it, and integrates the panel into the main `flowPanel`.
     * The control panel serves as a container for various UI components, such as controls for
     * adjusting user inputs and the "Proceed" button.
     * </p>
     */
    private void drawControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        controlPanel.add(title);
        flowPanel.add(controlPanel);
    }

    /**
     * Draws the UI controls for managing the "Black Key Pegs" count in the CodeBreaker game.
     *
     * <p>
     * This method creates and configures a dedicated panel within the main control panel to
     * display and adjust the number of correctly positioned key pegs ("Black Key Pegs").
     * It utilizes buttons for incrementing and decrementing the count, ensuring that the
     * user can control the precise feedback for the game's logic.
     * </p>
     *
     * <p>
     * The panel aligns its components centrally and interacts with the `drawResponseControls`
     * method to populate the panel with the necessary UI elements linked to the `correctCount`
     * variable.
     * </p>
     *
     * <p>
     * This method is a part of the initialization of the game's control panel and contributes
     * to providing feedback for the solver algorithm's next move.
     * </p>
     */
    private void drawCorrectControls() {
        final JPanel correctPanel = new JPanel(new FlowLayout());
        correctPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(correctPanel);

        drawResponseControls(correctPanel, "Black Key Pegs: ", correctCount);
    }

    /**
     * Draws the UI controls for managing the "White Key Pegs" count in the CodeBreaker game.
     *
     * <p>
     * This method creates and configures a dedicated panel within the main control panel to
     * display and adjust the number of key pegs that are misplaced but still valid ("White Key Pegs").
     * It uses buttons for incrementing and decrementing the count, ensuring that the user can
     * provide accurate feedback for the solver's algorithm processing.
     * </p>
     *
     * <p>
     * The panel aligns its components centrally and interacts with the `drawResponseControls`
     * method, which populates the panel with the appropriate UI elements tied to the `misplacementCount` variable.
     * </p>
     *
     * <p>
     * This method is part of the initialization of the game's control panel and contributes to the feedback
     * system used by the algorithm to refine its guesses.
     * </p>
     */
    private void drawMisplacementControls() {
        final JPanel misplacementPanel = new JPanel(new FlowLayout());
        misplacementPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(misplacementPanel);

        drawResponseControls(misplacementPanel, "White Key Pegs: ", misplacementCount);
    }

    /**
     * Determines if the given count is invalid based on predefined constraints.
     *
     * <p>
     * A count is considered invalid if it is less than 0 or greater than the constant
     * `Mastermind.CODE_LENGTH`. This method is used to validate user input or game logic
     * related to the permissible range of counts in the Mastermind game.
     * </p>
     *
     * @param value The count to validate.
     * @return {@code true} if the count is invalid; {@code false} otherwise.
     */
    private boolean isInvalidCount(final int value) {
        return value < 0 || value > Mastermind.CODE_LENGTH;
    }

    /**
     * Draws a set of response controls for managing a numeric value in a user interface.
     *
     * <p>
     * This method creates and displays a label representing a count alongside increment
     * and decrement buttons within a specified parent panel. The controls are used
     * to adjust the integer count while adhering to predefined constraints to ensure
     * validity of the numeric value. Updates to the count are reflected dynamically
     * in the label. The increment and decrement buttons are enabled or disabled based
     * on the current value and its validity.
     * </p>
     *
     * <p>
     * The constraints ensure that the count cannot exceed a maximum value (defined by
     * `Mastermind.CODE_LENGTH`) or fall below zero. When the count is updated to the maximum
     * or minimum permissible values, the respective button (increment or decrement) will be
     * disabled automatically to prevent further invalid changes.
     * </p>
     *
     * @param parent      The JPanel container to which the label and buttons are added.
     * @param labelPrefix The prefix text to display before the count in the label.
     * @param count       An AtomicInteger representing the current count value, which
     *                    can be incremented or decremented using the created controls.
     */
    private void drawResponseControls(final JPanel parent, final String labelPrefix, final AtomicInteger count) {
        final JLabel label = new JLabel(labelPrefix + count);
        parent.add(label);

        final JButton decrementButton = new JButton("-");
        final JButton incrementButton = new JButton("+");

        decrementButton.addActionListener(_ -> {
            incrementButton.setEnabled(true);

            final int newCount = count.get() - 1;

            if (newCount == 0) {
                decrementButton.setEnabled(false);
            } else if (isInvalidCount(newCount)) {
                return;
            }

            count.set(newCount);
            label.setText(labelPrefix + newCount);
        });

        incrementButton.addActionListener(_ -> {
            decrementButton.setEnabled(true);

            final int newCount = count.get() + 1;

            if (newCount == Mastermind.CODE_LENGTH) {
                incrementButton.setEnabled(false);
            } else if (isInvalidCount(newCount)) {
                return;
            }

            count.set(newCount);
            label.setText(labelPrefix + newCount);
        });

        parent.add(decrementButton);
        parent.add(incrementButton);
    }

    /**
     * Adds and aligns the "Proceed" button to the control panel in the CodeBreaker game scene.
     *
     * <p>
     * This method sets the horizontal alignment of the "Proceed" button to be centered within
     * the control panel. Once aligned, the button is added to the control panel to allow user
     * interaction for proceeding to the next step or action in the game workflow.
     * </p>
     *
     * <p>
     * The button plays a key role in enabling the player to confirm their response or trigger
     * the next algorithmic action in the CodeBreaker game logic.
     * </p>
     */
    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(proceedButton);
    }

    /**
     * Registers a handler for processing guesses and updating the game state in the CodeBreaker game.
     * 
     * <p>
     * This method sets up the game logic for the Mastermind puzzle by performing the following actions:
     * </p>
     * <ul>
     *     <li>
     *         It initiates the first guess by invoking the `guess` method of the solver algorithm and updates
     *         the game board with the resulting colors for the first attempt.
     *     </li>
     *     <li>
     *         It adds an action listener to the "Proceed" button, which triggers subsequent steps in the game round. 
     *         When the "Proceed" button is clicked, the game retrieves feedback on the previous guess, including 
     *         the number of correct positions and misplaced pieces. Using this feedback, the solver makes the next 
     *         guess and updates the game board with that guess.
     *     </li>
     * </ul>
     * 
     * <p>
     * If the solver determines that the puzzle is not yet solved (`MastermindSolver.Status.Continue`), 
     * the game board updates hints for the previous guess and displays the next guess. Otherwise, 
     * the game concludes, and a result window is displayed with the final outcome.
     * </p>
     * 
     * <p>
     * This handler links the user interface with the solver algorithm, enabling interactive and 
     * iterative gameplay through the "Proceed" button.
     * </p>
     */
    private void registerGuessHandler() {
//        first guess
        final Code firstGuess = solver.guess();
        gameBoard.updateGuess(0, firstGuess.getColors());

//        subsequent guesses
        proceedButton.addActionListener(_ -> {
            final Response responseForPreviousGuess = new Response(new Tuple2<>(correctCount.get(), misplacementCount.get()));
            final int currentAttempt = solver.getAttempts();
            final Tuple2<MastermindSolver.Status, Code> result = solver.guess(responseForPreviousGuess);

            if (result.first == MastermindSolver.Status.Continue) {
                gameBoard.updateHints(currentAttempt - 1, responseForPreviousGuess);
                gameBoard.updateGuess(currentAttempt, result.second.getColors());
                return;
            }

            new Result(frame, result.first, result.second);
        });
    }
}