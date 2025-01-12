package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.HumanSolver;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.CodeInput;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CodeMaker extends Scene {
    private List<Integer> nextGuess = null;
    private final Code secretCode = Code.generateRandomCode(List.of());
    private final HumanSolver solver = new HumanSolver(secretCode);

    private final GameBoard gameBoard = new GameBoard();
    private final JButton proceedButton = new JButton("Proceed");
    private final JPanel flowPanel = new JPanel(new FlowLayout());

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
     * </p>
     *
     * @param frame the JFrame to which this game's components will be added
     */
    public CodeMaker(final JFrame frame) {
        super(frame);

        Mastermind.log.info("Creating CodeMaker scene");

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
     * </p>
     */
    private void drawGameBoard() {
        flowPanel.add(gameBoard.getBoardPanel());
    }

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
     * - It verifies that the current guess (`nextGuess`) meets the required length (`Mastermind.CODE_LENGTH`).
     * - If the guess is incomplete, an `IllegalArgumentException` is thrown to indicate the error.
     * - If the guess is valid, it creates a new `Code` object from the selected colors and clears the `nextGuess`.
     *
     * <p>
     * The registered handler then processes the player's guess by invoking the `solver.guess` method, which
     * returns a tuple containing:
     * - A status (`MastermindSolver.Status`) indicating whether the game should continue, or if a win/loss condition
     * is reached.
     * - A feedback response (`Response`) with hints for the current guess.
     * </p>
     *
     * <p>
     * Based on the status:
     * - If the game is to continue, the method updates the game board with the provided response hints.
     * - If the game ends (win or loss), it displays a result dialog (`Result`) summarizing the outcome.
     * </p>
     *
     * <p>
     * This method ensures that:
     * - The game enforces a full guess before submission.
     * - Hints are provided to guide the player for subsequent guesses.
     * - The game flow transitions smoothly between turns or to an end condition.
     * </p>
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

            final Tuple2<MastermindSolver.Status, Response> result = solver.guess(guess);
            final MastermindSolver.Status status = result.first;
            final Response response = result.second;

            if (status == MastermindSolver.Status.Continue) {
                gameBoard.updateHints(attempt, response);
                return;
            }

            new CodeMakerResult(frame, status, secretCode);
        });
    }
}
