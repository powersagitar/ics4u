package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
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
 * Represents the game scene for the CodeMaker role in Mastermind.
 */
public class CodeMaker extends Scene {
    /**
     * The secret code generated for user to guess.
     */
    private final Code secretCode = Code.generateRandomCode(List.of());
    /**
     * The solver used to process the user's guesses in the game.
     */
    private final HumanSolver solver = new HumanSolver(secretCode);
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
     * Constructs a new CodeMaker instance, initializing the game environment,
     * components, and event listeners for the Mastermind game.
     *
     * <p>
     * This constructor creates a random secret code and initializes a human solver
     * for gameplay. It sets up the user interface using the provided JFrame, including
     * the game board, color selection controls, and the proceed button. Additionally,
     * it establishes handlers for user interactions with the color selection buttons
     * and the proceed button.
     *
     * @param frame the JFrame to which this game's components will be added
     */
    public CodeMaker(final JFrame frame) {
        super(frame);

        Log.info("Creating CodeMaker scene");

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
        proceedButton.addActionListener(event -> {
            if (nextGuess.size() < Mastermind.CODE_LENGTH) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Please choose all 4 colors for your guess",
                    "Incomplete Guess",
                    JOptionPane.ERROR_MESSAGE);

                return;
            }

            final Code guess = new Code(nextGuess);
            nextGuess.clear();

            final int attempt = solver.getAttempts();

            final Tuple2<Status, Response> result = solver.guess(guess);
            final Status status = result.first();
            final Response response = result.second();

            if (status == Status.Continue) {
                gameBoard.updateHints(attempt, response);
                return;
            }

            new CodeMakerResult(frame, status, secretCode);
        });
    }
}
