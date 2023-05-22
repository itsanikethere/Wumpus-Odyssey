package wumpusodyssey.gui;

import wumpusodyssey.GameConstants;
import wumpusodyssey.controller.Controller;
import wumpusodyssey.gui.dialog.AboutDialog;
import wumpusodyssey.gui.util.ImageIconLoader;

import javax.swing.*;
import java.awt.*;

import java.util.Objects;

public class Frame extends JFrame {

    private final GameLevel gameLevel;
    private JMenuBar menuBar;
    private GamePanel gamePanel;
    private Controller controller;

    public Frame(GameLevel gameLevel) {
        super(GameConstants.NAME);
        this.gameLevel = Objects.requireNonNull(gameLevel);

        configureComponents();
        layoutComponents();
    }

    private void configureComponents() {
        configureFrame();
        configureMenuBar();
        configureGamePanel();
        configureController();
    }

    private void layoutComponents() {
        add(gamePanel, BorderLayout.CENTER);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    private void configureFrame() {
        setIconImage(GameConstants.ICON.getImage());
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureMenuBar() {
        menuBar = new JMenuBar();

        JMenu help = new JMenu("Help");
        help.setIcon(ImageIconLoader.loadImageIcon("/icons/menu/help.png"));

        JMenuItem about = new JMenuItem("About");
        about.setIcon(ImageIconLoader.loadImageIcon("/icons/menu-items/about.png"));

        about.addActionListener(e -> new AboutDialog(this).setVisible(true));

        help.add(about);

        menuBar.add(help);
    }

    private void configureGamePanel() {
        gamePanel = new GamePanel(gameLevel);
        gamePanel.setListener(e -> controller.update(e));
    }

    private void configureController() {
        controller = new Controller(this, gamePanel.getSquares(), gamePanel.getSquareMapping());
    }
}
