package src.mastermind.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.core.solvers.DonaldKnuthAlgorithm;
import src.mastermind.core.solvers.MastermindAlgorithm;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.utils.Tuple2;
import src.mastermind.utils.Tuple3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
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

        final Tuple2<JPanel, JPanel> subpanels = makeGuessAndControlPanel();

        final ArrayList<JPanel> guessPanels = makeGuessPanel(subpanels.first);

        final Tuple3<JButton, AtomicInteger, AtomicInteger> controlPanel = drawControlPanel(subpanels.second);

        registerGuessHandler(controlPanel.first, controlPanel.second, controlPanel.third, guessPanels);

        refreshFrame();
    }

    private Tuple2<JPanel, JPanel> makeGuessAndControlPanel() {
        final JPanel flowPanel = new JPanel(new FlowLayout());
        frame.add(flowPanel);

        final JPanel guessPanel = new JPanel(new GridLayout(Mastermind.MAX_GUESSES, 0));

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        flowPanel.add(guessPanel);
        flowPanel.add(controlPanel);

        return new Tuple2<>(guessPanel, controlPanel);
    }

    private JLabel makeGuessPanelCircle(Color color) {
        final JLabel circle = new JLabel();
        circle.setBorder(BorderFactory.createLineBorder(color, 10));
        circle.setPreferredSize(new Dimension(20, 20));

        return circle;
    }

    private ArrayList<JPanel> makeGuessPanel(final JPanel parent) {
        ArrayList<JPanel> panels = new ArrayList<>(Mastermind.MAX_GUESSES);

        for (int panelIdx = 0; panelIdx < Mastermind.MAX_GUESSES; ++panelIdx) {
            final JPanel panel = new JPanel(new FlowLayout());
            parent.add(panel);
            panels.add(panel);

            final int HEIGHT_ADJUSTMENT_CONSTANT = 50;

            panel.setPreferredSize(new Dimension(frame.getWidth() / 5, (frame.getHeight() - HEIGHT_ADJUSTMENT_CONSTANT) / Mastermind.MAX_GUESSES));
            panel.setBorder(BorderFactory.createLineBorder(Color.black));

            for (int colorIdx = 0; colorIdx < Mastermind.CODE_LENGTH; ++colorIdx) {
                final JLabel circle = makeGuessPanelCircle(Color.lightGray);
                panel.add(circle);
            }
        }

        return panels;
    }

    private void updateGuessPanel(final JPanel panel, final Code code) {
        final HashMap<Code.Color, Color> colorMap = new HashMap<>(Mastermind.TOTAL_COLORS);
        colorMap.put(Code.Color.Blue, Color.blue);
        colorMap.put(Code.Color.Green, Color.green);
        colorMap.put(Code.Color.Orange, Color.orange);
        colorMap.put(Code.Color.Purple, new Color(139, 0, 255));
        colorMap.put(Code.Color.Red, Color.red);
        colorMap.put(Code.Color.Yellow, Color.yellow);

        panel.removeAll();

        for (final Code.Color color : code.getColors()) {
            final JLabel circle = makeGuessPanelCircle(colorMap.get(color));
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
            updateGuessPanel(guessPanels.get(attempt), result.second);
        });
    }

//    private void drawControlPanel(final JPanel parent) {
//        final JLabel title = new JLabel("Controls");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        parent.add(title);
//
//        final int rowWidth = Mastermind.TOTAL_COLORS / 2;
//
//        for (int rowIndex = 0; rowIndex < Mastermind.TOTAL_COLORS / rowWidth; ++rowIndex) {
//            final JPanel colorPanel = new JPanel(new FlowLayout());
//            parent.add(colorPanel);
//
//            for (int colorIndex = rowWidth * rowIndex; colorIndex < rowWidth * (rowIndex + 1); ++colorIndex) {
//                final JButton colorButton = new JButton(Code.Color.toString(colorIndex));
//                colorPanel.add(colorButton);
//            }
//        }
//    }
}