package wumpusodyssey;

import wumpusodyssey.gui.Frame;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Unable to start the game due to a Look and Feel error.",
                    GameConstants.NAME, JOptionPane.ERROR_MESSAGE
            );
        }

        SwingUtilities.invokeLater(() -> new Frame(new GameDefaultLevel()).setVisible(true));
    }
}
