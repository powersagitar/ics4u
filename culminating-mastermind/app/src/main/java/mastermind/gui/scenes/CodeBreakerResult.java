package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeBreakerResult extends Scene {
    private final Code secretCode;
    private final List<Code> guesses;
    private final List<Response> responses;
    private final List<Integer> invalidResponses;

    public CodeBreakerResult(final JFrame frame,
                             final MastermindSolver.Status status,
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

            if (status == MastermindSolver.Status.Win) {
                descriptionLabel = new JLabel("Program successfully guessed the code.");
            } else {
                descriptionLabel = new JLabel("Program failed to guess the code.");
            }

            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            frame.add(descriptionLabel);

            drawCorrectCode();
        }

        drawProceedButton();

        refreshFrame();
    }

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

        return invalidResponses;
    }

    private void drawProceedButton() {
        final JButton button = new JButton("Proceed");
        frame.add(button);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(e -> new GameModeSelector(frame));
    }

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

    private void drawCorrectCode() {
        final List<Color> codeAWTColors = secretCode
            .getColors()
            .stream()
            .map(GameBoard.codeColorToAwtColor::get)
            .toList();

        final JPanel codePanel = new JPanel(new FlowLayout());
        frame.add(codePanel);

        GameBoard.drawGuess(codePanel, codeAWTColors);
    }
}
