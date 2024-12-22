package src.mastermind.gui.panels;

import src.mastermind.Mastermind;
import src.mastermind.utils.SceneUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private final ArrayList<JPanel> rowPanels = new ArrayList<>(Mastermind.MAX_GUESSES);
    private final JPanel boardPanel = new JPanel(new GridLayout(Mastermind.MAX_GUESSES, 0));

    public GameBoard() {
        for (int row = 0; row < Mastermind.MAX_GUESSES; ++row) {
            final JPanel rowPanel = new JPanel(new FlowLayout());
            boardPanel.add(rowPanel);
            rowPanels.add(rowPanel);

            rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            for (int col = 0; col < Mastermind.CODE_LENGTH; ++col) {
                final JLabel colLabel = SceneUtils.makeGuessPanelCircle(Color.lightGray);
                rowPanel.add(colLabel);
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public ArrayList<JPanel> getRowPanels() {
        return rowPanels;
    }
}