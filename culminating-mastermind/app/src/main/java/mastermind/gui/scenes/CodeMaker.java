package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.HumanSolver;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.GameBoard;
import mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeMaker extends Scene {
    private final HumanSolver solver;
    private final ArrayList<Integer> nextGuess = new ArrayList<>(Mastermind.CODE_LENGTH);

    private final ArrayList<JButton> colorSelectionButtons = new ArrayList<>(Mastermind.TOTAL_COLORS);
    private final GameBoard gameBoard = new GameBoard();
    private final JButton proceedButton = new JButton("Proceed");
    private final JButton deleteButton = new JButton("Delete");
    private final JPanel flowPanel = new JPanel(new FlowLayout());
    private final JPanel controlPanel = new JPanel();

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

        final Code secretCode = Code.generateRandomCode(List.of());
        solver = new HumanSolver(secretCode);

        frame.add(flowPanel);

        drawGameBoard();

        drawControlPanel();

        registerColorSelectionHandlers();

        registerDeleteHandler();

        drawProceedButton();

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

    /**
     * Constructs and populates the control panel for the Mastermind game interface.
     *
     * <p>
     * This method sets up a vertically aligned control panel within the user interface.
     * It includes the following components:
     * - A title label ("Controls") displayed at the top of the panel.
     * - Color selection buttons grouped into rows. Each button corresponds to a color
     *   and allows the player to make selections for their guesses.
     * </p>
     *
     * <p>
     * The control panel is added to the primary layout (`flowPanel`) of the game.
     * Buttons for color selection are dynamically created and stored in the
     * `colorSelectionButtons` list for event handling and future access. The rows
     * are organized based on the total number of colors defined in the game (`Mastermind.TOTAL_COLORS`).
     * </p>
     */
    private void drawControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        flowPanel.add(controlPanel);

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(title);

        final int rowWidth = Mastermind.TOTAL_COLORS / 2;

        for (int rowIndex = 0; rowIndex < Mastermind.TOTAL_COLORS / rowWidth; ++rowIndex) {
            final JPanel colorPanel = new JPanel(new FlowLayout());
            controlPanel.add(colorPanel);

            for (int colorIndex = rowWidth * rowIndex; colorIndex < rowWidth * (rowIndex + 1); ++colorIndex) {
                JButton colorButton = new JButton(" ");
                colorButton.setBackground(GameBoard.codeColorToAwtColor.get(Code.Color.values()[colorIndex]));
                colorButton.setForeground(Color.BLACK);
                colorButton.setOpaque(true);
                colorButton.setContentAreaFilled(true);
                colorPanel.add(colorButton);
                colorSelectionButtons.add(colorButton);
            }
        }

        drawDeleteButton();
    }

    /**
     * Registers action handlers for the color selection buttons to manage user guesses.
     *
     * <p>
     * This method iterates over the list of color selection buttons (`colorSelectionButtons`)
     * and attaches an `ActionListener` to each button. When a button is pressed, the associated
     * color index is added to the current guess (`nextGuess`), provided that the guess has
     * not yet reached the maximum length (`Mastermind.CODE_LENGTH`).
     * </p>
     *
     * <p>
     * The handler also updates the game board visually to reflect the selected colors
     * by modifying the guess displayed on the corresponding row of the game board, based
     * on the number of attempts made by the solver (`solver.getAttempts()`).
     * </p>
     *
     * <p>
     * It ensures that:
     * - The user's guess does not exceed the expected number of colors.
     * - The game board is updated in real-time to indicate the progress of the guess.
     * </p>
     */
    private void registerColorSelectionHandlers() {
        for (int i = 0; i < colorSelectionButtons.size(); ++i) {
            final JButton button = colorSelectionButtons.get(i);
            final int colorIndex = i;
            button.addActionListener(_ -> {
                if (nextGuess.size() < Mastermind.CODE_LENGTH) {
                    final int gameBoardRowNumber = solver.getAttempts();
                    nextGuess.add(colorIndex);
                    gameBoard.updateGuessFromColorIndices(gameBoardRowNumber, nextGuess);
                }
            });
        }
    }

    private void drawDeleteButton() {
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(deleteButton);
    }

    private void registerDeleteHandler() {
        deleteButton.addActionListener(_ -> {
            if (!nextGuess.isEmpty()) {
                final int gameBoardRowNumber = solver.getAttempts();
                nextGuess.removeLast();
                gameBoard.updateGuessFromColorIndices(gameBoardRowNumber, nextGuess);
            }
        });
    }

    /**
     * Draws and configures the "Proceed" button within the control panel of the game interface.
     *
     * <p>
     * This method aligns the "Proceed" button to the center horizontally within the control panel
     * and adds it to the panel. The button is integral to the gameplay, allowing the player to
     * submit their guess during each turn after selecting the desired colors.
     * </p>
     *
     * <p>
     * The control panel is a vertically aligned part of the user interface, and the "Proceed"
     * button is added at the appropriate position to enable ease of interaction for the player.
     * </p>
     */
    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(proceedButton);
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
     *   is reached.
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
        proceedButton.addActionListener(_ -> {
            if (nextGuess.size() < Mastermind.CODE_LENGTH) {
                throw new IllegalArgumentException("Haven't chosen all 4 colors for a guess yet");
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

            new Result(frame, status, guess);
        });
    }
}