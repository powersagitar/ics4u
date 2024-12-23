package src.mastermind.gui.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.core.solvers.HumanSolver;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.gui.panels.GameBoard;
import src.mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeMaker extends Scene {
    private final HumanSolver solver;
    private final ArrayList<JButton> colorSelectionButtons = new ArrayList<>(Mastermind.TOTAL_COLORS);
    private final GameBoard gameBoard = new GameBoard();
    private ArrayList<Integer> nextGuess = new ArrayList<>(Mastermind.CODE_LENGTH);

    public CodeMaker(final JFrame frame) {
        super(frame);

        final Code secretCode = Code.generateRandomCode(List.of());
        solver = new HumanSolver(secretCode);

        drawGuessAndControlPanel();

        registerColorSelectionHandlers();

        refreshFrame();
    }

    private void drawGuessAndControlPanel() {
        final JPanel flowPanel = new JPanel(new FlowLayout());
        frame.add(flowPanel);

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        flowPanel.add(gameBoard.getBoardPanel());
        flowPanel.add(controlPanel);

        drawControlPanel(controlPanel);
    }

    private void drawControlPanel(final JPanel parent) {
        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(title);

        final int rowWidth = Mastermind.TOTAL_COLORS / 2;

        for (int rowIndex = 0; rowIndex < Mastermind.TOTAL_COLORS / rowWidth; ++rowIndex) {
            final JPanel colorPanel = new JPanel(new FlowLayout());
            parent.add(colorPanel);

            for (int colorIndex = rowWidth * rowIndex; colorIndex < rowWidth * (rowIndex + 1); ++colorIndex) {
                final JButton colorButton = new JButton(Code.Color.toString(colorIndex));
                colorPanel.add(colorButton);
                colorSelectionButtons.add(colorButton);
            }
        }

        drawProceedButton(parent);
    }

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

    private void drawProceedButton(JPanel parent) {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(proceedButton);

        proceedButton.addActionListener(_ -> {
            if (nextGuess.size() >= Mastermind.CODE_LENGTH) {
                final Code guess = new Code(nextGuess);
                nextGuess.clear();

                final int attempt = solver.getAttempts();

                final Tuple2<MastermindSolver.Status, Response> result = solver.guess(guess);

                if (result.first == MastermindSolver.Status.Continue) {
                    gameBoard.updateHints(attempt, result.second);
                    return;
                }

                new Result(frame, result.first, guess);
            } else {
                throw new IllegalArgumentException("Haven't chosen all 4 colors for a guess yet");
            }
        });
    }
}