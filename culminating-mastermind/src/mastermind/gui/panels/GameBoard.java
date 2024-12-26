package src.mastermind.gui.panels;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.utils.Tuple2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameBoard {
    private final ArrayList<Tuple2<JPanel, JPanel>> boardRowPanels = new ArrayList<>(Mastermind.MAX_GUESSES);
    private final JPanel boardPanel = new JPanel(new GridBagLayout());

    public static final HashMap<Code.Color, Color> codeColorToAwtColor = new HashMap<>(Mastermind.TOTAL_COLORS);

    static {
        codeColorToAwtColor.put(Code.Color.Blue, Color.blue);
        codeColorToAwtColor.put(Code.Color.Green, Color.green);
        codeColorToAwtColor.put(Code.Color.Orange, Color.orange);
        codeColorToAwtColor.put(Code.Color.Purple, new Color(139, 0, 255));
        codeColorToAwtColor.put(Code.Color.Red, Color.red);
        codeColorToAwtColor.put(Code.Color.Yellow, Color.yellow);
    }

    /**
     * Constructs a new GameBoard instance and initializes the game board layout.
     * The board consists of rows representing guesses and corresponding hint panels.
     * Each row is divided into two panels:
     * - A panel for guesses, where the player inputs their code guesses.
     * - A panel for hints, which provides feedback on the guesses.
     *
     * <p>
     * The GameBoard layout is created using the GridBagLayout manager, and each row
     * is appended to the board with the necessary constraints.
     * </p>
     *
     * <p>
     * Initializes four placeholders (light gray color) for guesses and four placeholders
     * (black and white feedback pegs) for hints in each row.
     * </p>
     *
     * <p>
     * Additionally, individual rows of the board are stored in an internal list
     * for ease of future updates to guess and hint panels.
     * </p>
     */
    public GameBoard() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        for (int row = 0; row < Mastermind.MAX_GUESSES; ++row) {
            gridBagConstraints.gridy = row;

            gridBagConstraints.gridx = 0;
            final JPanel guessPanel = new JPanel(new FlowLayout());
            guessPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            boardPanel.add(guessPanel, gridBagConstraints);
            drawGuess(guessPanel, List.of(Color.lightGray, Color.lightGray, Color.lightGray, Color.lightGray));

            gridBagConstraints.gridx = 1;
            final JPanel hintsPanel = new JPanel(new GridBagLayout());
            hintsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            boardPanel.add(hintsPanel, gridBagConstraints);
            drawHints(hintsPanel, new Response(new Tuple2<>(0, 0)));

            boardRowPanels.add(new Tuple2<>(guessPanel, hintsPanel));
        }
    }

    /**
     * Renders a visual representation of a guess on the given panel.
     * Updates the specified parent JPanel to display a row of color-coded squares,
     * where each square corresponds to a color in the provided list.
     *
     * <p>
     * If the list of colors contains fewer elements than the required code length,
     * an IllegalArgumentException is thrown.
     * </p>
     *
     * <p>
     * Each square is rendered as a JLabel with a border colored according to the
     * corresponding color in the list. The size of each square is standardized.
     * The updated panel layout is refreshed after rendering.
     * </p>
     *
     * @param parent The JPanel where the guess will be rendered. This panel's
     *               content will be cleared before rendering.
     * @param colors A list of Color objects representing the player's guess.
     *               Must contain at least the number of colors defined by
     *               {@code Mastermind.CODE_LENGTH}.
     * @throws IllegalArgumentException if the size of the colors list is less than
     *                                  {@code Mastermind.CODE_LENGTH}.
     */
    public static void drawGuess(final JPanel parent, final List<Color> colors) {
        if (colors.size() < Mastermind.CODE_LENGTH) {
            throw new IllegalArgumentException("colors should contain at least 4 colors representing the code.");
        }

        parent.removeAll();

        for (int col = 0; col < Mastermind.CODE_LENGTH; ++col) {
            final JLabel colLabel = new JLabel();
            colLabel.setBorder(BorderFactory.createLineBorder(colors.get(col), 10));
            colLabel.setPreferredSize(new Dimension(20, 20));
            parent.add(colLabel);
        }

        parent.revalidate();
        parent.repaint();
    }

    /**
     * Updates the specified parent JPanel to display feedback hints in a grid format,
     * based on the given response. The hints include black, white, and light gray markers,
     * corresponding to exact matches, partial matches, and empty spaces respectively.
     *
     * <p>
     * The method determines the number of black and white pegs needed from the response
     * and fills the remaining spaces with light gray. It then organizes these hints into
     * a 2x2 grid visual representation within the panel.
     * </p>
     *
     * <p>
     * Each hint is represented as a small JLabel with a border colored according to the
     * response values. Black borders represent correct colors in the correct position,
     * white borders represent correct colors in the wrong position, and light gray borders
     * represent no matches.
     * </p>
     *
     * <p>
     * The panel is refreshed to reflect the updated content after rendering the hints.
     * </p>
     *
     * @param parent   The JPanel to which the hints will be added. This panel is cleared
     *                 before rendering the new hints.
     * @param response The Response object containing the number of exact matches (black pegs)
     *                 and partial matches (white pegs) to be represented.
     */
    public static void drawHints(final JPanel parent, final Response response) {
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


    /**
     * Updates the guessed colors for a specific row on the game board based on the provided color indices.
     *
     * <p>
     * This method converts the given list of color indices into a corresponding list of
     * {@link Code.Color} objects, which represent the color codes used in the game.
     * It then delegates the process of updating the guess visually on the game board to
     * {@link #updateGuess(int, List)}.
     * </p>
     *
     * <p>
     * The indices in the list must correspond to valid colors defined in the {@link Code.Color} enumeration.
     * </p>
     *
     * @param rowNumber    The row number on the game board where the guess should be updated.
     *                     Must be within the bounds of the game board's rows.
     * @param colorIndices A list of integers representing the indices of the colors for the guess.
     *                     Each index corresponds to a color code as defined in {@link Code.Color}.
     */
    public void updateGuessFromColorIndices(final int rowNumber, final List<Integer> colorIndices) {
        final ArrayList<Code.Color> colors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int idx : colorIndices) {
            final Code.Color codeColor = Code.Color.fromIndex(idx);
            colors.add(codeColor);
        }

        updateGuess(rowNumber, colors);
    }

    /**
     * Updates the guessed colors for a specified row on the game board.
     *
     * <p>
     * This method takes a list of {@link Code.Color} values representing the guess. It converts these
     * game-specific color representations into corresponding {@link java.awt.Color} instances to render
     * on the visual game board. If the given list of colors contains fewer entries than the code length
     * required by the game, the remaining entries are filled with a placeholder color (light gray).
     * </p>
     *
     * <p>
     * The method retrieves the associated guess panel for the specified row and updates it by calling
     * the {@link #drawGuess(JPanel, List)} method with the converted colors.
     * </p>
     *
     * @param rowNumber The row number on the game board to update. This specifies which row will
     *                  display the given guess. Must be within the bounds of the game board's rows.
     * @param colors    A list of {@link Code.Color} objects representing the guessed colors for the row.
     *                  The list can contain fewer entries than the required code length, in which case
     *                  placeholder colors are used for the remaining positions.
     */
    public void updateGuess(final int rowNumber, final List<Code.Color> colors) {
        final ArrayList<Color> awtColors = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final Code.Color codeColor : colors) {
            final Color awtColor = codeColorToAwtColor.get(codeColor);
            awtColors.add(awtColor);
        }

        while (awtColors.size() < Mastermind.CODE_LENGTH) {
            awtColors.add(Color.lightGray);
        }

        final Tuple2<JPanel, JPanel> rowPanel = boardRowPanels.get(rowNumber);
        final JPanel guessPanel = rowPanel.first;

        drawGuess(guessPanel, awtColors);
    }

    /**
     * Updates the hint panel for a specified row on the game board based on the provided feedback.
     *
     * <p>
     * This method retrieves the hint panel corresponding to the specified row number and updates it
     * with visual feedback based on the given {@link Response}. The feedback typically consists of black,
     * white, and light gray markers to represent exact matches, partial matches, and no matches, respectively.
     * </p>
     *
     * <p>
     * The visual update is performed by delegating the rendering of hints to the {@link #drawHints(JPanel, Response)} method.
     * </p>
     *
     * @param rowNumber The row number on the game board whose hint panel should be updated. Must be
     *                  within the bounds of the game board's rows.
     * @param hints     The {@link Response} object containing feedback information to be displayed as hints
     *                  for the specified row. It specifies the number of exact matches and partial matches.
     */
    public void updateHints(final int rowNumber, final Response hints) {
        final Tuple2<JPanel, JPanel> rowPanel = boardRowPanels.get(rowNumber);
        final JPanel hintsPanel = rowPanel.second;

        drawHints(hintsPanel, hints);
    }

    /**
     * Retrieves the main game board panel.
     * 
     * <p>
     * The returned panel, structured using a {@link GridBagLayout}, serves as the primary component
     * that visually represents the game board. It contains rows of panels, each consisting of a 
     * guess panel and a hint panel representing player guesses and feedback respectively.
     * </p>
     * 
     * <p>
     * This panel is initialized and updated internally within the {@link GameBoard} class, 
     * reflecting the current state of the game.
     * </p>
     * 
     * @return The {@link JPanel} representing the main game board.
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }
}