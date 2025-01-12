package mastermind.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Help {
    private final static JButton helpButton = new JButton("Help");
    private final static JPanel helpPanel = new JPanel();
    private final static JFrame popupFrame = new JFrame("Help");
    private final static JLabel helpTitle = new JLabel("Mastermind Help", SwingConstants.CENTER);
    private final static JButton gameplayInstructionsButton = new JButton("Gameplay Instructions");
    private final static JButton navigationInstructionsButton = new JButton("Navigation Instructions");
    private final static JPanel popupPanel = new JPanel();

    static {
        popupFrame.setLayout(new BorderLayout());

        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
        helpTitle.setFont(new Font("Default", Font.BOLD, 20));
        popupPanel.add(helpTitle);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        popupPanel.add(gameplayInstructionsButton);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        popupPanel.add(navigationInstructionsButton);
        popupFrame.add(popupPanel, BorderLayout.CENTER);

        helpPanel.add(helpButton);

        registerHelpHandlers();
        registerGameplayInstructionsHandler();
        registerNavigationInstructionsHandler();
    }

    public static void drawHelpButton(final JFrame frame) {
        frame.add(helpPanel);
    }

    public static void registerHelpHandlers() {
        helpButton.addActionListener(event -> {
            popupFrame.setSize(400, 400);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popupFrame.setVisible(true);
        });
    }

    private static void registerGameplayInstructionsHandler() {
        gameplayInstructionsButton.addActionListener(event -> showGameplayInstructions());
    }

    private static void registerNavigationInstructionsHandler() {
        navigationInstructionsButton.addActionListener(event -> showNavigationInstructions());
    }

    private static void showNavigationInstructions() {
        popupPanel.removeAll();

        JLabel instructionsLabel = new JLabel("<html>" + getNavigationInstructionsText() + "</html>", SwingConstants.CENTER);
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> showHelpMenu());

        popupPanel.setLayout(new BorderLayout());
        popupPanel.add(instructionsLabel, BorderLayout.CENTER);
        popupPanel.add(backButton, BorderLayout.SOUTH);

        popupPanel.revalidate();
        popupPanel.repaint();
    }

    private static void showGameplayInstructions() {
        popupPanel.removeAll();

        JLabel instructionsLabel = new JLabel("<html>" + getGameplayInstructionsText() + "</html>", SwingConstants.CENTER);
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> showHelpMenu());

        popupPanel.setLayout(new BorderLayout());
        popupPanel.add(instructionsLabel, BorderLayout.CENTER);
        popupPanel.add(backButton, BorderLayout.SOUTH);

        popupPanel.revalidate();
        popupPanel.repaint();
    }

    private static void showHelpMenu() {
        popupPanel.removeAll();

        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
        popupPanel.add(helpTitle);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        popupPanel.add(gameplayInstructionsButton);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        popupPanel.add(navigationInstructionsButton);

        popupPanel.revalidate();
        popupPanel.repaint();
    }

    private static String getGameplayInstructionsText() {
        return """
            Mastermind is a code-breaking game where a setter sets a secret code and a guesser must guess that secret code.
            The code consists of a sequence of 4 colored pegs, and the guesser must guess the correct sequence.
            After each guess, the guesser receives feedback on the correctness of their guess.
            A black peg indicates a correct color in the correct position,
            while a white peg indicates a correct color in the wrong position.
            The guesser has 10 guesses to crack the code and win the game.
            
            Good luck!
            """.replace("\n", "<br>");
    }

    private static String getNavigationInstructionsText() {
        return """
            In the first screen, you can choose to play as the code maker or the code breaker.
            As the code breaker, you will set a secret code for the code breaker (the computer) to guess.
            As the code maker, you will guess the secret code that the code breaker (the computer) creates.
            
            If you selected code breaker, you will choose the difficulty of the computer guesser algorithm.
            You will secretly select a code for the computer to guess.
            You will then view the code breaker's guesses and provide feedback on each guess, using the white and black pegs.
            If the computer is unsuccessful in guessing, then you will input the correct code.
            
            If you selected code maker, you will guess the code that the computer secretly selected.
            You will input your guesses and receive feedback on each guess, using the white and black pegs.
            
            There is a home button on each screen (except the starting screen) that will take you back to the starting screen.
            
            Please enjoy!
            """.replace("\n", "<br>");
    }
}