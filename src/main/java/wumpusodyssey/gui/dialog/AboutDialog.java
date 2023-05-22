package wumpusodyssey.gui.dialog;

import wumpusodyssey.GameConstants;

import javax.swing.*;
import java.awt.*;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AboutDialog extends JDialog {

    private static final String DIALOG_TITLE = "About";

    private final JFrame dialogParent;
    private JLabel gameIconLabel;
    private JPanel gameInfoPanel;

    public AboutDialog(JFrame dialogParent) {
        super(dialogParent, DIALOG_TITLE);
        this.dialogParent = dialogParent;

        configureComponents();
        layoutComponents();
    }

    private void configureComponents() {
        configureDialog();
        configureGameIconLabel();
        configureGameInfoPanel();
    }

    private void layoutComponents() {
        add(gameIconLabel, BorderLayout.WEST);
        add(gameInfoPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(dialogParent);
    }

    private void configureDialog() {
        setIconImage(GameConstants.ICON.getImage());
        setResizable(false);
    }

    private void configureGameIconLabel() {
        gameIconLabel = new JLabel(GameConstants.ICON);
        gameIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void configureGameInfoPanel() {
        gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));

        Font font = UIManager.getDefaults().getFont("Label.font");

        JLabel gameNameLabel = new JLabel(GameConstants.NAME);
        Font gameNameLabelFont = font.deriveFont(Font.BOLD, 20f);
        gameNameLabel.setFont(gameNameLabelFont);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
        String buildDate = GameConstants.BUILD_DATE.format(formatter);

        JLabel gameInfoLabel = new JLabel(String.format("""
                <html>
                <p>
                Find the treasure and finish the adventure.<br>
                </p>
                            
                <br>
                            
                <p>
                Build #%.1f, built on %s.<br>
                Runtime version: %s.
                </p>
                            
                <br>
                            
                <p>
                Designed by Aniket P.<br>
                </p>
                """, GameConstants.BUILD_VERSION, buildDate, GameConstants.BUILD_RUNTIME_VERSION));

        gameNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 10));
        gameInfoLabel.setBorder(BorderFactory.createEmptyBorder(2, 0, 10, 10));

        gameInfoPanel.add(Box.createVerticalGlue());
        gameInfoPanel.add(gameNameLabel);
        gameInfoPanel.add(gameInfoLabel);
        gameInfoPanel.add(Box.createVerticalGlue());
    }
}
