package src.mastermind.gui.panels;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.utils.SceneUtils;
import src.mastermind.utils.Tuple2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final ArrayList<Tuple2<JPanel, JPanel>> boardRowPanels = new ArrayList<>(Mastermind.MAX_GUESSES);
    private final JPanel boardPanel = new JPanel(new GridBagLayout());

    public GameBoard() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        for (int row = 0; row < Mastermind.MAX_GUESSES; ++row) {
            gridBagConstraints.gridy = row;

            gridBagConstraints.gridx = 0;
            final JPanel guessPanel = new JPanel(new FlowLayout());
            boardPanel.add(guessPanel, gridBagConstraints);
            drawGuess(guessPanel, List.of(Color.lightGray, Color.lightGray, Color.lightGray, Color.lightGray));

            gridBagConstraints.gridx = 1;
            final JPanel hintsPanel = new JPanel(new GridBagLayout());
            boardPanel.add(hintsPanel, gridBagConstraints);
            drawHints(hintsPanel, new Response(new Tuple2<>(0, 0)));

            boardRowPanels.add(new Tuple2<>(guessPanel, hintsPanel));
        }
    }

    private void drawGuess(final JPanel parent, final List<Color> colors) {
        if (colors.size() < Mastermind.CODE_LENGTH) {
            throw new IllegalArgumentException("colors should contain at least 4 colors representing the code.");
        }

        parent.removeAll();

        parent.setBorder(BorderFactory.createLineBorder(Color.black));

        for (int col = 0; col < Mastermind.CODE_LENGTH; ++col) {
            final JLabel colLabel = new JLabel();
            colLabel.setBorder(BorderFactory.createLineBorder(colors.get(col), 10));
            colLabel.setPreferredSize(new Dimension(20, 20));
            parent.add(colLabel);
        }

        parent.revalidate();
        parent.repaint();
    }

    private void drawHints(final JPanel parent, final Response response) {
        parent.removeAll();

        final Tuple2<Integer, Integer> hints = response.getResponse();
        final ArrayList<Color> colors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < hints.first; ++i) {
            colors.add(Color.black);
        }

        for (int i = 0; i < hints.second; ++i) {
            colors.add(Color.white);
        }

        while (colors.size() < Mastermind.CODE_LENGTH) {
            colors.add(Color.lightGray);
        }

        parent.setBorder(BorderFactory.createLineBorder(Color.black));

        final GridBagConstraints hintConstraints = new GridBagConstraints();

        int colorIdx = 0;

        for (int row = 0; row < Mastermind.CODE_LENGTH / 2; ++row) {
            for (int col = 0; col < Mastermind.CODE_LENGTH / 2; ++col) {
                hintConstraints.gridx = col;
                hintConstraints.gridy = row;

                final JLabel hint = new JLabel();
                parent.add(hint, hintConstraints);

                hint.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(2, 2, 2, 2), new LineBorder(colors.get(colorIdx), 99)));
                hint.setPreferredSize(new Dimension(15, 15));

                ++colorIdx;
            }
        }

        parent.revalidate();
        parent.repaint();
    }

    public void update(final int rowNumber, final List<Integer> guessColorIndices) {
        final ArrayList<Color> guessColors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int idx : guessColorIndices) {
            final Code.Color codeColor = Code.Color.fromIndex(idx);
            final Color awtColor = SceneUtils.codeColorAwtColorMap.get(codeColor);
            guessColors.add(awtColor);
        }

        while (guessColors.size() < Mastermind.CODE_LENGTH) {
            guessColors.add(Color.lightGray);
        }

        update(rowNumber, guessColors, new Response(new Tuple2<>(0, 0)));
    }

    public void update(final int rowNumber, final Code guess) {
        final ArrayList<Color> guessColors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final Code.Color codeColor : guess.getColors()) {
            final Color awtColor = SceneUtils.codeColorAwtColorMap.get(codeColor);
            guessColors.add(awtColor);
        }

        update(rowNumber, guessColors, new Response(new Tuple2<>(0, 0)));
    }

//    public void update(final int rowNumber, final Code guess, final Response hints) {
//        final ArrayList<Color> guessColors = new ArrayList<>(Mastermind.CODE_LENGTH);
//
//        for (final Code.Color codeColor : guess.getColors()) {
//            final Color awtColor = SceneUtils.codeColorAwtColorMap.get(codeColor);
//            guessColors.add(awtColor);
//        }
//
//        update(rowNumber, guessColors, hints);
//    }

    public void update(final int rowNumber, final List<Color> guessColors, final Response hints) {
        final Tuple2<JPanel, JPanel> rowPanel = boardRowPanels.get(rowNumber);
        final JPanel guessPanel = rowPanel.first;
        final JPanel hintsPanel = rowPanel.second;

        drawGuess(guessPanel, guessColors);
        drawHints(hintsPanel, hints);
    }

    public void update(final int rowNumber, final Response hints) {
        final Tuple2<JPanel, JPanel> rowPanel = boardRowPanels.get(rowNumber);
        final JPanel hintsPanel = rowPanel.second;
        drawHints(hintsPanel, hints);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}