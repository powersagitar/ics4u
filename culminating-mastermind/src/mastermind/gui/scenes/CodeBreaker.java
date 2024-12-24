package src.mastermind.gui.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.core.solvers.DonaldKnuthAlgorithm;
import src.mastermind.core.solvers.MastermindAlgorithm;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.gui.panels.GameBoard;
import src.mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeBreaker extends Scene {
    private final MastermindAlgorithm solver;
    private final GameBoard gameBoard = new GameBoard();
    private final AtomicInteger correctCount = new AtomicInteger(2);
    private final AtomicInteger misplacementCount = new AtomicInteger(2);
    private final JButton proceedButton = new JButton("Proceed");
    private final JPanel flowPanel = new JPanel(new FlowLayout());
    private final JPanel controlPanel = new JPanel();

    public CodeBreaker(final JFrame frame, final CodeBreakerSelector.Algorithm algorithm) {
        super(frame);

        switch (algorithm) {
            case DonaldKnuth -> this.solver = new DonaldKnuthAlgorithm();
            case Medium -> this.solver = new DonaldKnuthAlgorithm();
            case Basic -> this.solver = new DonaldKnuthAlgorithm();
            default ->
                    throw new IllegalArgumentException("algorithm is not nullable.");
        }

        frame.add(flowPanel);

        drawGameBoard();

        drawControlPanel();

        drawCorrectControls();

        drawMisplacementControls();

        drawProceedButton();

        registerGuessHandler();

        refreshFrame();
    }

    private void drawGameBoard() {
        flowPanel.add(gameBoard.getBoardPanel());
    }

    private void drawControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        controlPanel.add(title);
        flowPanel.add(controlPanel);
    }

    private void drawCorrectControls() {
        final JPanel correctPanel = new JPanel(new FlowLayout());
        correctPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(correctPanel);

        drawResponseControls(correctPanel, "Black Key Pegs: ", correctCount);
    }

    private void drawMisplacementControls() {
        final JPanel misplacementPanel = new JPanel(new FlowLayout());
        misplacementPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(misplacementPanel);

        drawResponseControls(misplacementPanel, "White Key Pegs: ", misplacementCount);
    }

    private boolean isInvalidCount(final int value) {
        return value < 0 || value > Mastermind.CODE_LENGTH;
    }

    private void drawResponseControls(final JPanel parent, final String labelPrefix, final AtomicInteger count) {
        final JLabel label = new JLabel(labelPrefix + count);
        parent.add(label);

        final JButton decrementButton = new JButton("-");
        final JButton incrementButton = new JButton("+");

        decrementButton.addActionListener(_ -> {
            incrementButton.setEnabled(true);

            final int newCount = count.get() - 1;

            if (newCount == 0) {
                decrementButton.setEnabled(false);
            } else if (isInvalidCount(newCount)) {
                return;
            }

            count.set(newCount);
            label.setText(labelPrefix + newCount);
        });

        incrementButton.addActionListener(_ -> {
            decrementButton.setEnabled(true);

            final int newCount = count.get() + 1;

            if (newCount == Mastermind.CODE_LENGTH) {
                incrementButton.setEnabled(false);
            } else if (isInvalidCount(newCount)) {
                return;
            }

            count.set(newCount);
            label.setText(labelPrefix + newCount);
        });

        parent.add(decrementButton);
        parent.add(incrementButton);
    }

    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(proceedButton);
    }

    private void registerGuessHandler() {
//        first guess
        final Code firstGuess = solver.guess();
        gameBoard.updateGuess(0, firstGuess.getColors());

//        subsequent guesses
        proceedButton.addActionListener(_ -> {
            final Response responseForPreviousGuess = new Response(new Tuple2<>(correctCount.get(), misplacementCount.get()));
            final int currentAttempt = solver.getAttempts();
            final Tuple2<MastermindSolver.Status, Code> result = solver.guess(responseForPreviousGuess);

            if (result.first == MastermindSolver.Status.Continue) {
                gameBoard.updateHints(currentAttempt - 1, responseForPreviousGuess);
                gameBoard.updateGuess(currentAttempt, result.second.getColors());
                return;
            }

            new Result(frame, result.first, result.second);
        });
    }
}