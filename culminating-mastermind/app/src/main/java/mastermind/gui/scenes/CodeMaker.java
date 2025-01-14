/*
    Authors: Mohan Dong
    Date: 01/15/2024
    Title: CodeMaker.java
 */

package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.core.solvers.HumanSolver;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.CodeInput;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Log;
import mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The Code Maker gameplay.
 */
public class CodeMaker extends Scene {
    /**
     * The secret code generated for user to guess.
     */
    private final Code secretCode;
    /**
     * The solver used to process the user's guesses in the game.
     */
    private final HumanSolver solver;
    /**
     * The game board panel that displays the current game state.
     */
    private final GameBoard gameBoard = new GameBoard();
    /**
     * The "Proceed" button that allows the user to submit their guess.
     */
    private final JButton proceedButton = new JButton("Proceed");
    /**
     * The primary panel that manages the layout of the game's components.
     */
    private final JPanel flowPanel = new JPanel(new FlowLayout());
    /**
     * The next guess from user to be processed by the game.
     */
    private List<Integer> nextGuess = null;

    /**
     * Constructs the CodeMaker scene with the secret code.
     * @param frame The main frame of the application.
     * @param secretCode The secret code generated for the user to guess.
     */
    public CodeMaker(final JFrame frame, final Code secretCode) {
        super(frame);

        this.secretCode = secretCode;
        this.solver = new HumanSolver(secretCode);

        Log.info("Creating CodeMaker scene");
        Log.info("Secret code: " + secretCode);

        frame.add(flowPanel);

        drawGameBoard();

        drawControlPanel();

        HomeButton.drawHomeButton(frame);

        Help.drawHelpButton(frame);

        HomeButton.registerHomeHandlers(frame);

        Help.registerHelpHandlers();

        registerProceedHandlers();

        refreshFrame();
    }

    /**
     * Draws the game board panel and adds it to the flow panel of the user interface.
     *
     * <p>
     * This method initializes the visual representation of the game board by
     * retrieving the panel component from the `GameBoard` object and attaching it
     * to the primary `JPanel` (`flowPanel`) that manages the layout of the game's
     * components. This ensures the game board is displayed as part of the overall
     * interface.
     */
    private void drawGameBoard() {
        flowPanel.add(gameBoard.getBoardPanel());
    }

    /**
     * Draws the control panel for the game, including the color selection
     * buttons and the "Proceed" button.
     */
    private void drawControlPanel() {
        final CodeInput codeInput = new CodeInput();
        final JPanel controlPanel = codeInput.drawButtons();

        codeInput.addActionListener(guess -> {
            Log.debug("User selected intermediate code: " + guess);
            gameBoard.updateGuessFromColorIndices(solver.getAttempts(), guess);
            nextGuess = guess;
        });

        controlPanel.add(proceedButton);
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        flowPanel.add(controlPanel);
    }

    /**
     * Registers action handlers for the "Proceed" button to process user guesses in the game.
     *
     * <p>
     * This method attaches an `ActionListener` to the "Proceed" button. When the button is clicked:
     * <br>
     * - It verifies that the current guess (`nextGuess`) meets the required length (`Mastermind.CODE_LENGTH`).
     * <br>
     * - If the guess is incomplete, an `IllegalArgumentException` is thrown to indicate the error.
     * <br>
     * - If the guess is valid, it creates a new `Code` object from the selected colors and clears the `nextGuess`.
     *
     * <p>
     * The registered handler then processes the player's guess by invoking the `solver.guess` method, which
     * returns a tuple containing:
     * - A status (`MastermindSolver.Status`) indicating whether the game should continue, or if a win/loss condition
     * is reached.
     * - A feedback response (`Response`) with hints for the current guess.
     *
     * <p>
     * Based on the status:
     * - If the game is to continue, the method updates the game board with the provided response hints.
     * - If the game ends (win or loss), it displays a result dialog (`Result`) summarizing the outcome.
     *
     * <p>
     * This method ensures that:
     * <br>
     * - The game enforces a full guess before submission.
     * <br>
     * - Hints are provided to guide the player for subsequent guesses.
     * <br>
     * - The game flow transitions smoothly between turns or to an end condition.
     */
    private void registerProceedHandlers() {
        // Add an action listener to the proceed button
        proceedButton.addActionListener(event -> {
            // Check if the current guess is incomplete
            if (nextGuess.size() < Mastermind.CODE_LENGTH) {
                // Log a warning about the incomplete guess
                Log.warning("User submitted incomplete guess: " + nextGuess);

                // Show a dialog to inform the user about the incomplete guess
                JOptionPane.showMessageDialog(
                    frame,
                    "Please choose all 4 colors for your guess",
                    "Incomplete Guess",
                    JOptionPane.ERROR_MESSAGE
                );

                // Exit the event handler early
                return;
            }

            // Create a new Code object from the selected colors
            final Code guess = CodeFactory.fromColorIndices(nextGuess);
            // Clear the next guess list
            nextGuess.clear();

            // Get the current attempt number from the solver
            final int attempt = solver.getAttempts();

            // Process the guess using the solver and get the result
            final Tuple2<Status, Response> result = solver.guess(guess);
            // Extract the status and response from the result
            final Status status = result.first();
            final Response response = result.second();

            // Log the user's submission, response, and status
            Log.info("User submission(" + attempt + "): " + guess);
            Log.info("Response: " + response);
            Log.info("Status: " + status);

            // Check if the game should continue
            if (status == Status.Continue) {
                // Update the game board with the response hints
                gameBoard.updateHints(attempt, response);
                // Exit the event handler early
                return;
            }

            // Display the result dialog if the game ends (win or loss)
            new CodeMakerResult(frame, status, secretCode);
        });
    }
}

