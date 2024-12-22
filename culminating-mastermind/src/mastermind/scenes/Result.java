package src.mastermind.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.utils.SceneUtils;

import javax.swing.*;
import java.awt.*;

public class Result extends Scene {
    private final Code correctCode;

    public Result(final JFrame frame, MastermindSolver.Status status, final Code correctCode) {
        super(frame);

        this.correctCode = correctCode;

        if (status == MastermindSolver.Status.Win) {
            drawWinScreen();
        } else if (status == MastermindSolver.Status.Lose) {
            drawLoseScreen();
        } else {
            throw new IllegalArgumentException("Only Status.Win and Status.Lose can yield results.");
        }

        drawCorrectCode();

        drawProceedButton();

        refreshFrame();
    }

    private void drawWinScreen() {
        final JLabel title = new JLabel("The code breaker wins!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(title);
    }

    private void drawLoseScreen() {
        final JLabel title = new JLabel("The code breaker did not guess the code in " + Mastermind.MAX_GUESSES + " attempts.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(title);
    }

    private void drawCorrectCode() {
        final JLabel correctCodeLabel = new JLabel("The correct code was:");
        correctCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(correctCodeLabel);

        final JPanel correctCodePanel = new JPanel(new FlowLayout());
        correctCodePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(correctCodePanel);

        for (final Code.Color codeColor : correctCode.getColors()) {
            final Color awtColor = SceneUtils.codeColorAwtColorMap.get(codeColor);
            final JLabel circle = SceneUtils.makeGuessPanelCircle(awtColor);
            correctCodePanel.add(circle);
        }
    }

    private void drawProceedButton() {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(proceedButton);

        proceedButton.addActionListener(_ -> new GameModeSelector(frame));
    }
}