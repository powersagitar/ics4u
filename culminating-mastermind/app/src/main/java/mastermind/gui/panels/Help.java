package mastermind.gui.panels;

import javax.swing.*;

public class Help {
    final private JButton helpButton;
    final private JPanel helpPanel;
    final private JFrame popupFrame;
    final private JLabel HELP_CONTENT = new JLabel("Welcome to Mastermind! The goal of the game is to guess the secret code. The code maker will create a secret code of 4 colors. The code breaker will have 10 chances to guess the code. After each guess, the code maker will provide feedback to the code breaker. The feedback consists of black and white pegs. A black peg indicates that a color is correct and in the correct position. A white peg indicates that a color is correct but in the wrong position. Good luck!"); // TODO: Add more information

    public Help() {
        helpButton = new JButton("Help");
        helpPanel = new JPanel();
        popupFrame = new JFrame();
        popupFrame.add(HELP_CONTENT);
        helpPanel.add(helpButton);
    }

    public void drawHelpButton(final JFrame frame) {
        frame.add(helpPanel);
    }

    public void registerHelpHandlers() {
        helpButton.addActionListener( event -> {
            popupFrame.setSize(300, 300);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popupFrame.setVisible(true);
        });
    }


}
