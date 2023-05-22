package wumpusodyssey;

import wumpusodyssey.gui.GameLevel;
import wumpusodyssey.gui.GamePanel;

import javax.swing.*;

import java.util.HashMap;

public class GameDefaultLevel implements GameLevel {
    @Override
    public HashMap<Integer, Icon> getSquareMapping() {
        HashMap<Integer, Icon> squareMapping = new HashMap<>();

        squareMapping.put(0, GamePanel.STENCH_ICON);
        squareMapping.put(1, GamePanel.BLANK_ICON);
        squareMapping.put(2, GamePanel.BREEZE_ICON);
        squareMapping.put(3, GamePanel.PIT_ICON);
        squareMapping.put(4, GamePanel.WUMPUS_ICON);
        squareMapping.put(5, GamePanel.GOLD_ICON);
        squareMapping.put(6, GamePanel.PIT_ICON);
        squareMapping.put(7, GamePanel.BREEZE_ICON);
        squareMapping.put(8, GamePanel.STENCH_ICON);
        squareMapping.put(9, GamePanel.BLANK_ICON);
        squareMapping.put(10, GamePanel.BREEZE_ICON);
        squareMapping.put(11, GamePanel.BLANK_ICON);
        squareMapping.put(12, GamePanel.PLAYER_ICON);
        squareMapping.put(13, GamePanel.BREEZE_ICON);
        squareMapping.put(14, GamePanel.PIT_ICON);
        squareMapping.put(15, GamePanel.BREEZE_ICON);

        return squareMapping;
    }
}
