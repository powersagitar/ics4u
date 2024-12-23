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
import java.util.function.Function;

public class CodeBreaker extends Scene {
    private final static String CORRECT_LABEL_TEXT = "Correct Key Pegs: ";
    private final static String MISPLACEMENT_LABEL_TEXT = "Misplaced Key Pegs: ";

    private final static Function<Integer, Boolean> validateCounterValue = value ->
        value >= 0 && value <= Mastermind.CODE_LENGTH;

    private final MastermindAlgorithm solver;
    private final GameBoard gameBoard = new GameBoard();
    private final AtomicInteger correctCount = new AtomicInteger(2);
    private final AtomicInteger misplacementCount = new AtomicInteger(2);
    private final JButton proceedButton = new JButton("Proceed");

    public CodeBreaker(final JFrame frame, final CodeBreakerSelector.Algorithm algorithm) {
        super(frame);

        switch (algorithm) {
            case DonaldKnuth -> this.solver = new DonaldKnuthAlgorithm();
            default -> this.solver = new DonaldKnuthAlgorithm();
        }

        final JPanel flowPanel = new JPanel(new FlowLayout());
        frame.add(flowPanel);

        drawBoardPanel(flowPanel);

        drawControlPanel(flowPanel);

        registerGuessHandler();

        refreshFrame();
    }

    private void drawBoardPanel(final JPanel parent) {
        parent.add(gameBoard.getBoardPanel());
    }

    private void drawControlPanel(final JPanel parent) {
        final JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        parent.add(boxPanel);

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxPanel.add(title);

        final JPanel correctPanel = new JPanel(new FlowLayout());
        final JPanel misplacementPanel = new JPanel(new FlowLayout());

        boxPanel.add(correctPanel);
        boxPanel.add(misplacementPanel);

        drawResponseControls(correctPanel, CORRECT_LABEL_TEXT, correctCount);
        drawResponseControls(misplacementPanel, MISPLACEMENT_LABEL_TEXT, misplacementCount);

        drawProceedButton(parent);
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
            } else if (!validateCounterValue.apply(newCount)) {
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
            } else if (!validateCounterValue.apply(newCount)) {
                return;
            }

            count.set(newCount);
            label.setText(labelPrefix + newCount);
        });

        parent.add(decrementButton);
        parent.add(incrementButton);
    }

    private void drawProceedButton(final JPanel parent) {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(proceedButton);
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