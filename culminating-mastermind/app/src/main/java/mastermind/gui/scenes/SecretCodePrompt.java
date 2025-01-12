package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.gui.panels.CodeCircle;
import mastermind.gui.panels.CodeInput;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SecretCodePrompt extends Scene {
    private Code secretCode = null;

    public SecretCodePrompt(final JFrame frame) {
        super(frame);

        Mastermind.log.info("Creating CorrectCodePrompt scene");

        final JLabel failureLabel = new JLabel("The program failed to guess the code.");
        failureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(failureLabel);

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

    private static JPanel drawCodePanel() {
        final JPanel codePanel = new JPanel(new FlowLayout());

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final CodeCircle codeCircle = new CodeCircle(Color.lightGray);
            codePanel.add(codeCircle);
        }

        return codePanel;
    }

    private JPanel drawPromptPanel(final JPanel codePanel) {
        final CodeInput codeInput = new CodeInput();
        final JPanel buttonsPanel = codeInput.drawButtons();

        final JPanel buttonsPanelWrapper = new JPanel(new BorderLayout());
        buttonsPanelWrapper.add(buttonsPanel, BorderLayout.PAGE_START);

        codeInput.addActionListener(codeIdxList -> {
            final Code code = new Code(codeIdxList);

            final List<Color> codeAWTColors = code.getColors()
                .stream()
                .map(GameBoard.codeColorToAwtColor::get)
                .collect(Collectors.toList());

            while (codeAWTColors.size() < Mastermind.CODE_LENGTH) {
                codeAWTColors.add(Color.lightGray);
            }

            GameBoard.drawGuess(codePanel, codeAWTColors);
            secretCode = code;
        });

        return buttonsPanelWrapper;
    }

    private JButton drawProceedButton() {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        proceedButton.addActionListener(e -> {
            if (secretCode == null || !secretCode.isValid()) {
                JOptionPane.showMessageDialog(frame,
                    "Please Make sure it has a length of " + Mastermind.CODE_LENGTH + ".",
                    "Entered code is invalid",
                    JOptionPane.ERROR_MESSAGE);

                return;
            }

            // TODO: transition to code breaker result
            new GameModeSelector(frame);
        });

        return proceedButton;
    }
}
