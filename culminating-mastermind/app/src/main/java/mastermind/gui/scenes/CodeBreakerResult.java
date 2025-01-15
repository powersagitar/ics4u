package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.GameBoard;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scene that displays the result of the code breaker game mode.
 */
public class CodeBreakerResult extends Scene {
    /**
     * The secret code that the program was trying to guess.
     */
    private final Code secretCode;

    /**
     * The guesses made by the program.
     */
    private final List<Code> guesses;

    /**
     * The responses to the guesses made by the program.
     */
    private final List<Response> responses;

    /**
     * The indices of the guesses that have invalid responses.
     */
    private final List<Integer> invalidResponses;

    /**
     * Creates a new CodeBreakerResult scene.
     *
     * @param frame      The frame to draw the scene on.
     * @param status     The status of the game.
     * @param secretCode The secret code that the program was trying to guess.
     * @param guesses    The guesses made by the program.
     * @param responses  The responses to the guesses made by the program.
     */
    public CodeBreakerResult(final JFrame frame,
                             final Status status,
                             final Code secretCode,
                             final List<Code> guesses,
                             final List<Response> responses) {
        super(frame);

        this.secretCode = secretCode;
        this.guesses = guesses;
        this.responses = responses;

        this.invalidResponses = validateResponses();

        if (!invalidResponses.isEmpty()) {
            drawInvalidResponses();
        } else {
            final JLabel descriptionLabel;

            if (status == Status.Win) {
                Log.info("Program successfully guessed the code.");
                descriptionLabel = new JLabel("Program successfully guessed the code.");
            } else {
                Log.info("Program failed to guess the code.");
                descriptionLabel = new JLabel("Program failed to guess the code.");
            }

            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            frame.add(descriptionLabel);

            Log.info("Secret code: " + secretCode);
            drawCorrectCode();
        }

        drawProceedButton();

        refreshFrame();
    }

    /**
     * Validates the responses to the guesses.
     *
     * @return The indices of the guesses that have invalid responses.
     */
    private List<Integer> validateResponses() {
        if (guesses.size() != responses.size()) {
            throw new IllegalArgumentException("Guesses and responses must have the same size");
        }

        final List<Integer> invalidResponses = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < guesses.size(); ++i) {
            final Response expectedResponse = new Response(secretCode, guesses.get(i));

            if (!expectedResponse.equals(responses.get(i))) {
                invalidResponses.add(i);
            }
        }

        Log.info("Indices of invalid responses: " + invalidResponses);
        return invalidResponses;
    }

    /**
     * Draws the proceed button.
     */
    private void drawProceedButton() {
        final JButton button = new JButton("Proceed");
        frame.add(button);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(e -> new GameModeSelector(frame));
    }

    /**
     * Draws the invalid responses on a {@link GameBoard}.
     */
    private void drawInvalidResponses() {
        final String description = """
            You did not provide the correct hints for the following guesses,
            thus it is not possible to derive a win/lose endgame status:
            """.replaceAll("\n", "<br>");

        final JLabel descriptionLabel = new JLabel(
            "<html><div align=\"center\">" + description + "</div></html>",
            SwingConstants.CENTER);

        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(descriptionLabel);

        final GameBoard invalidGameBoard = new GameBoard();

        for (final int i : invalidResponses) {
            invalidGameBoard.updateGuess(i, guesses.get(i).getColors());
            invalidGameBoard.updateHints(i, responses.get(i));
        }

        final JPanel gameBoardPanel = invalidGameBoard.getBoardPanel();
        gameBoardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(gameBoardPanel);
    }

    /**
     * Draws the correct code.
     */
    private void drawCorrectCode() {
        final List<Color> codeAWTColors = secretCode
            .getColors()
            .stream()
            .map(GameBoard.CODE_COLOR_TO_AWT_COLOR::get)
            .toList();

        final JPanel codePanel = new JPanel(new FlowLayout());
        frame.add(codePanel);

        GameBoard.drawGuess(codePanel, codeAWTColors);
    }
}
