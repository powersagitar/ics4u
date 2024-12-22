package src.mastermind.gui.scenes;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.core.solvers.HumanSolver;
import src.mastermind.core.solvers.MastermindSolver;
import src.mastermind.gui.panels.GameBoard;
import src.mastermind.utils.SceneUtils;
import src.mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeMaker extends Scene {
    final HumanSolver solver;
    ArrayList<JPanel> gameBoardRowPanels = null;
    final ArrayList<JButton> colorSelectionButtons = new ArrayList<>(Mastermind.TOTAL_COLORS);
    ArrayList<Integer> nextGuess = new ArrayList<>(Mastermind.CODE_LENGTH);

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

        final GameBoard gameBoard = new GameBoard();
        flowPanel.add(gameBoard.getBoardPanel());
        gameBoardRowPanels = gameBoard.getRowPanels();

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
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
                    nextGuess.add(colorIndex);

                    final JPanel currentPanel = gameBoardRowPanels.get(solver.getAttempts());
                    updateGameBoardRowPanel(currentPanel);
                }
            });
        }
    }

    private void updateGameBoardRowPanel(final JPanel row) {
        row.removeAll();

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            if (i < nextGuess.size()) {
                final Code.Color codeColor = Code.Color.fromIndex(nextGuess.get(i));
                final Color awtColor = SceneUtils.codeColorAwtColorMap.get(codeColor);
                row.add(SceneUtils.makeGuessPanelCircle(awtColor));
            } else {
                row.add(SceneUtils.makeGuessPanelCircle(Color.lightGray));
            }
        }

        row.revalidate();
        row.repaint();
    }

    private void drawProceedButton(JPanel parent) {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(proceedButton);

        proceedButton.addActionListener(_ -> {
            if (nextGuess.size() >= Mastermind.CODE_LENGTH) {
                final Code guess = new Code(nextGuess);
                nextGuess.clear();

                final Tuple2<MastermindSolver.Status, Response> result = solver.guess(guess);

                if (result.first == MastermindSolver.Status.Continue) {
                    System.out.println("Resopnse: " + result.second);
                    return;
                }

                new Result(frame, result.first, guess);
            } else {
                throw new IllegalArgumentException("Haven't chosen all 4 colors for a guess yet");
            }
        });
    }
}