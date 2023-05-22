package wumpusodyssey.gui;

import wumpusodyssey.gui.listener.GamePanelEvent;
import wumpusodyssey.gui.listener.GamePanelListener;
import wumpusodyssey.gui.util.ImageIconLoader;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.HashMap;

public class GamePanel extends JPanel {

    public static final Dimension SQUARE_DIMENSION = new Dimension(160, 160);

    public static final Color UNVISITED_SQUARE = new Color(187, 222, 251);
    public static final Color VISITED_SQUARE = new Color(0, 0, 0);

    public static final ImageIcon BLANK_ICON = null;

    public static final ImageIcon BREEZE_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/breeze.png");
    public static final ImageIcon STENCH_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/stench.png");
    public static final ImageIcon BREEZE_STENCH_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/breeze-stench.png");

    public static final ImageIcon GOLD_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/gold.png");

    public static final ImageIcon PIT_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/pit.png");
    public static final ImageIcon WUMPUS_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/wumpus.png");

    public static final ImageIcon PLAYER_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/player.png");
    public static final ImageIcon PLAYER_BREEZE_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/player-breeze.png");
    public static final ImageIcon PLAYER_STENCH_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/player-stench.png");
    public static final ImageIcon PLAYER_BREEZE_STENCH_ICON = ImageIconLoader.loadImageIcon("/icons/play-panel/player-breeze-stench.png");

    private final HashMap<Integer, Icon> squareMapping;
    private JButton[] squares;

    private GamePanelListener listener;

    public GamePanel(GameLevel level) {
        squareMapping = level.getSquareMapping();

        configureComponents();
        layoutComponents();
    }

    public JButton[] getSquares() {
        return squares;
    }

    public HashMap<Integer, Icon> getSquareMapping() {
        return squareMapping;
    }

    public void setListener(GamePanelListener listener) {
        this.listener = listener;
    }

    public void configureComponents() {
        configPlayPanel();
        configureSquares();
    }

    public void layoutComponents() {
        for (JButton square : squares) {
            add(square);
        }
    }

    public void configPlayPanel() {
        setLayout(new GridLayout(4, 4));
    }

    public void configureSquares() {
        squares = new JButton[16];

        for (int i = 0; i < 16; i++) {
            squares[i] = new JButton();
        }

        for (JButton square : squares) {
            square.setOpaque(true);
            square.setPreferredSize(SQUARE_DIMENSION);
            square.setBackground(UNVISITED_SQUARE);

            square.setFocusable(false);

            square.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (listener == null)
                        return;

                    GamePanelEvent event = new GamePanelEvent(this, true, (JButton) e.getSource());
                    listener.gamePanelEventOccurred(event);
                }
            });

            square.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (listener == null)
                        return;

                    if (e.getButton() != MouseEvent.BUTTON3)
                        return;

                    GamePanelEvent event = new GamePanelEvent(this, false, (JButton) e.getSource());
                    listener.gamePanelEventOccurred(event);
                }
            });
        }
    }
}
