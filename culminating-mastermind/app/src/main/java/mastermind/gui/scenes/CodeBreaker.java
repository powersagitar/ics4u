package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.InvalidHintsException;
import mastermind.core.solvers.MastermindAlgorithm;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.gui.panels.KeyPegsPrompt;
import mastermind.utils.Log;
import mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Code Breaker gameplay.
 */
public class CodeBreaker extends Scene {
    /**
     * The algorithm to be used for solving the Mastermind puzzle.
     */
    private final MastermindAlgorithm solver;

    /**
     * The list of guesses made by the {@link #solver} in the game.
     */
    private final List<Code> guesses = new ArrayList<>(Mastermind.MAX_GUESSES);

    /**
     * The list of responses provided by the player in the game.
     */
    private final List<Response> responses = new ArrayList<>(Mastermind.MAX_GUESSES);

    /**
     * The game board panel used to display the game state and hints.
     */
    private final GameBoard gameBoard = new GameBoard();

    /**
     * The key pegs counter panel used to manage user inputs for black and white pegs.
     */
    private final KeyPegsPrompt keyPegsCounter = new KeyPegsPrompt();

    /**
     * The "Proceed" button used to trigger the next step in the game.
     */
    private final JButton proceedButton = new JButton("Proceed");

    /**
     * The main panel for the {@link CodeBreaker} scene, containing the game
     * board and control panel.
     */
    private final JPanel flowPanel = new JPanel(new FlowLayout());

    /**
     * The control panel for managing user inputs and game progression.
     */
    private final JPanel controlPanel = new JPanel();

    /**
     * Represents the CodeBreaker scene in the application, inheriting from the
     * parent `Scene` class.
     *
     * <p>
     * This scene manages the game logic and user interface for a Mastermind
     * game implementation. It initializes necessary UI components, handles
     * game interactions, and updates the game state.
     *
     * <p>
     * The class uses a selected algorithm to solve the Mastermind puzzle and provides controls
     * for managing user responses and progression in the game.
     *
     * @param frame     The parent `JFrame` that contains this scene.
     * @param algorithm The algorithm to be used for solving the Mastermind puzzle.
     *                  Supported algorithms include `DonaldKnuth`, `Medium`, and `Basic`.
     * @throws IllegalArgumentException if the provided algorithm is null or unsupported.
     */
    public CodeBreaker(final JFrame frame, final MastermindAlgorithm algorithm) {
        super(frame);

        Log.info("Creating CodeBreaker scene");

        // Initialize the solver with the provided algorithm
        this.solver = algorithm;

        // Add the main flow panel to the frame
        frame.add(flowPanel);

        // Draw the game board on the flow panel
        drawGameBoard();

        // Draw the control panel on the flow panel
        drawControlPanel();

        // Add the key pegs counter to the control panel
        controlPanel.add(keyPegsCounter);

        // Draw the "Proceed" button on the control panel
        drawProceedButton();

        // Draw the home button on the frame
        HomeButton.drawHomeButton(frame);

        // Draw the help button on the frame
        Help.drawHelpButton(frame);

        // Register handlers for the home button
        HomeButton.registerHomeHandlers(frame);

        // Register handlers for the help button
        Help.registerHelpHandlers();

        // Register the handler for the "Proceed" button
        registerProceedHandler();

        // Refresh the frame to apply changes
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
     */
    private void drawControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        controlPanel.add(title);
        flowPanel.add(controlPanel);
    }

    /**
     * Adds and aligns the "Proceed" button to the control panel in the CodeBreaker game scene.
     *
     * <p>
     * This method sets the horizontal alignment of the "Proceed" button to be centered within
     * the control panel. Once aligned, the button is added to the control panel to allow user
     * interaction for proceeding to the next step or action in the game workflow.
     *
     * <p>
     * The button plays a key role in enabling the player to confirm their response or trigger
     * the next algorithmic action in the CodeBreaker game logic.
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
     *
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
     * If the solver determines that the puzzle is not yet solved
     * ({@link Status#Continue}), the game board updates
     * hints for the previous guess and displays the next guess. Otherwise,
     * the game concludes, and transitions to the next scene.
     *
     * <p>
     * This handler links the user interface with the solver algorithm, enabling interactive and
     * iterative gameplay through the "Proceed" button.
     */
    private void registerProceedHandler() {
        // Make the first guess using the solver algorithm
        final Code firstGuess = solver.guess();
        gameBoard.updateGuess(0, firstGuess.getColors());
        guesses.add(firstGuess);

        Log.info("Guess 0: " + firstGuess);

        // Add an action listener to the "Proceed" button for subsequent guesses
        proceedButton.addActionListener(event -> {
            Log.trace("Proceed button pressed");

            // Create a response for the previous guess using the key pegs counter
            final Response responseForPreviousGuess =
                new Response(new Tuple2<>(
                    keyPegsCounter.getBlackPegs(),
                    keyPegsCounter.getWhitePegs()));

            responses.add(responseForPreviousGuess);

            Log.info("Response: " + responseForPreviousGuess);

            // Get the number of attempts before making the next guess
            final int attempt = solver.getAttempts();

            // Make the next guess based on the response
            final Tuple2<Status, Code> result =
                makeSubsequentGuess(responseForPreviousGuess);

            Log.info("Solver status: " + result.first());

            // If the solver status is "Continue", update the game board with the next guess
            if (result.first() == Status.Continue) {
                Log.info("Guess " + attempt + ": " + result.second());

                guesses.add(result.second());

                gameBoard.updateHints(attempt - 1, responseForPreviousGuess);
                gameBoard.updateGuess(attempt, result.second().getColors());
                return;
            }

            // If the solver status is not "Continue", show the secret code prompt
            new SecretCodePrompt(frame, result.first(), guesses, responses);
        });
    }

    /**
     * Makes a subsequent guess based on the provided response.
     *
     * @param response The response to the previous guess.
     * @return A tuple containing the status of the solver and the next guess.
     */
    private Tuple2<Status, Code> makeSubsequentGuess(final Response response) {
        try {
            return solver.guess(response);
        } catch (final InvalidHintsException e) {
            Log.error("Invalid hints provided: " + e.getMessage());
            return new Tuple2<>(Status.Lose, null);
        }
    }
}
