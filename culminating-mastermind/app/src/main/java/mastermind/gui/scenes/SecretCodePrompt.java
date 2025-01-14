package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.MastermindSolver;
import mastermind.gui.panels.CodeCircle;
import mastermind.gui.panels.CodeInput;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a scene where the user is prompted to enter the correct code.
 */
public class SecretCodePrompt extends Scene {
    private Code secretCode = null;
    private final MastermindSolver.Status status;
    private final List<Code> guesses;
    private final List<Response> responses;

    /**
     * Initializes a new SecretCodePrompt scene with the specified JFrame, game
     * status, guesses, and responses.
     *
     * @param frame     The JFrame to be associated with this scene.
     * @param status    The current game status.
     * @param guesses   The list of guesses made by the code breaker.
     * @param responses The list of responses corresponding to each guess.
     */
    public SecretCodePrompt(final JFrame frame,
                            final MastermindSolver.Status status,
                            final List<Code> guesses,
                            final List<Response> responses) {
        super(frame);

        Log.info("Creating CorrectCodePrompt scene");

        this.status = status;
        this.guesses = guesses;
        this.responses = responses;

        final JLabel promptLabel = new JLabel("Please enter the correct code:");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(promptLabel);

        final JPanel codePanel = drawCodePanel();
        frame.add(codePanel);

        final JPanel promptPanel = drawPromptPanel(codePanel);
        frame.add(promptPanel);

        final JButton proceedButton = drawProceedButton();
        frame.add(proceedButton);

        Help.drawHelpButton(frame);
        Help.registerHelpHandlers();

        refreshFrame();
    }

    /**
     * Draws the code panel that displays the code the user is entering.
     *
     * @return The code panel.
     */
    private static JPanel drawCodePanel() {
        final JPanel codePanel = new JPanel(new FlowLayout());

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final CodeCircle codeCircle = new CodeCircle(Color.lightGray);
            codePanel.add(codeCircle);
        }

        return codePanel;
    }

    /**
     * Draws the prompt panel that allows the user to enter the correct code.
     *
     * @param codePanel The code panel where the code is displayed.
     * @return The prompt panel.
     */
    private JPanel drawPromptPanel(final JPanel codePanel) {
        final CodeInput codeInput = new CodeInput();
        final JPanel buttonsPanel = codeInput.drawButtons();

        final JPanel buttonsPanelWrapper = new JPanel(new BorderLayout());
        buttonsPanelWrapper.add(buttonsPanel, BorderLayout.PAGE_START);

        codeInput.addActionListener(codeIdxList -> {
            final Code code = new Code(codeIdxList);

            if (codeIdxList.size() >= Mastermind.CODE_LENGTH) {
                secretCode = code;
            } else {
                secretCode = null;
            }

            final List<Color> codeAWTColors = code.getColors()
                .stream()
                .map(GameBoard.CODE_COLOR_TO_AWT_COLOR::get)
                .collect(Collectors.toList());

            while (codeAWTColors.size() < Mastermind.CODE_LENGTH) {
                codeAWTColors.add(Color.lightGray);
            }

            GameBoard.drawGuess(codePanel, codeAWTColors);
        });

        return buttonsPanelWrapper;
    }

    /**
     * Draws the proceed button that allows the user to proceed to the next
     * scene.
     *
     * @return The proceed button.
     */
    private JButton drawProceedButton() {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        proceedButton.addActionListener(e -> {
            if (secretCode == null) {
                JOptionPane.showMessageDialog(frame,
                    "Please choose all 4 colors for the correct code",
                    "Incomplete Code",
                    JOptionPane.ERROR_MESSAGE);

                return;
            }

            new CodeBreakerResult(frame, status, secretCode, guesses, responses);
        });

        return proceedButton;
    }
}
