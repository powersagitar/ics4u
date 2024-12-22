package src.mastermind.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.core.solvers.DonaldKnuthAlgorithm;
import src.mastermind.core.solvers.MastermindAlgorithm;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.utils.Tuple2;
import src.mastermind.utils.Tuple3;
import src.mastermind.utils.SceneUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class CodeBreaker extends Scene {
    final static String CORRECT_LABEL_TEXT = "Correct Key Pegs: ";
    final static String MISPLACEMENT_LABEL_TEXT = "Misplaced Key Pegs: ";

    final static Function<Integer, Boolean> validateCounterValue = value ->
            value >= 0 && value <= Mastermind.CODE_LENGTH;

    final MastermindAlgorithm solver;

    public CodeBreaker(final JFrame frame, final CodeBreakerSelector.Algorithm algorithm) {
        super(frame);

        switch (algorithm) {
            case DonaldKnuth -> this.solver = new DonaldKnuthAlgorithm();
            default -> this.solver = new DonaldKnuthAlgorithm();
        }

        final Tuple2<ArrayList<JPanel>, JPanel> subpanels = makeGuessAndControlPanel();

        final ArrayList<JPanel> gameBoardRowPanels = subpanels.first;

        final Tuple3<JButton, AtomicInteger, AtomicInteger> controlPanel = drawControlPanel(subpanels.second);

        registerGuessHandler(controlPanel.first, controlPanel.second, controlPanel.third, gameBoardRowPanels);

        refreshFrame();
    }

    private Tuple2<ArrayList<JPanel>, JPanel> makeGuessAndControlPanel() {
        final JPanel flowPanel = new JPanel(new FlowLayout());
        frame.add(flowPanel);

        final GameBoardPanel gameBoardPanel = new GameBoardPanel();

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        flowPanel.add(gameBoardPanel.getBoardPanel());
        flowPanel.add(controlPanel);

        return new Tuple2<>(gameBoardPanel.getRowPanels(), controlPanel);
    }

    private void updateGuessPanel(final JPanel panel, final Code code) {
        panel.removeAll();

        for (final Code.Color color : code.getColors()) {
            final JLabel circle = SceneUtils.makeGuessPanelCircle(SceneUtils.codeColorAwtColorMap.get(color));
            panel.add(circle);
        }

        panel.revalidate();
        panel.repaint();
    }

    private Tuple3<JButton, AtomicInteger, AtomicInteger> drawControlPanel(final JPanel parent) {
        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(title);

        final JPanel correctPanel = new JPanel(new FlowLayout());
        final JPanel misplacementPanel = new JPanel(new FlowLayout());

        parent.add(correctPanel);
        parent.add(misplacementPanel);

        final AtomicInteger correctCount = drawResponseControls(correctPanel, CORRECT_LABEL_TEXT);
        final AtomicInteger misplacementCount = drawResponseControls(misplacementPanel, MISPLACEMENT_LABEL_TEXT);

        final JButton proceedButton = drawProceedButton(parent);

        return new Tuple3<>(proceedButton, correctCount, misplacementCount);
    }

    private AtomicInteger drawResponseControls(final JPanel parent, final String labelPrefix) {
        AtomicInteger count = new AtomicInteger(2);

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

        return count;
    }

    private JButton drawProceedButton(final JPanel parent) {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(proceedButton);

        return proceedButton;
    }

    private void registerGuessHandler(final JButton proceedButton, final AtomicInteger correctCount, final AtomicInteger incorrectCount, final ArrayList<JPanel> guessPanels) {
//        first guess
        final Code guess = solver.guess();
        updateGuessPanel(guessPanels.getFirst(), guess);

//        subsequent guesses
        proceedButton.addActionListener(_ -> {
            final Response response = new Response(new Tuple2<>(correctCount.get(), incorrectCount.get()));
            final int attempt = solver.getAttempts();
            final Tuple2<MastermindSolver.Status, Code> result = solver.guess(response);

            if (result.first == MastermindSolver.Status.Continue) {
                updateGuessPanel(guessPanels.get(attempt), result.second);
                return;
            }

            new Result(frame, result.first, result.second);
        });
    }
}