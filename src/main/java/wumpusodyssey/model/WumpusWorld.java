package wumpusodyssey.model;

import wumpusodyssey.gui.GamePanel;
import wumpusodyssey.gui.listener.GamePanelEvent;

import javax.swing.*;

import java.util.HashMap;

public class WumpusWorld {

    public static final int GAME_OK = 0;
    public static final int GAME_WON = 1;
    public static final int GAME_OVER = 2;
    public static final int SAME_MOVE = 3;
    public static final int INVALID_MOVE = 4;

    public static final int WUMPUS_KILLED = 5;
    public static final int WUMPUS_NOT_KILLED = 6;
    public static final int BULLET_ALREADY_USED = 7;

    private final JButton[] squares;
    private final HashMap<Integer, Icon> squareMapping;

    boolean hasArrow;

    private int previousSquareIndex;
    private int clickedSquareIndex;
    private int status;

    public WumpusWorld(JButton[] squares, HashMap<Integer, Icon> squareMapping) {
        this.squares = squares;
        this.squareMapping = squareMapping;

        start();
    }

    public int getStatus() {
        return status;
    }

    public void move(GamePanelEvent e) {
        status = GAME_OK;
        previousSquareIndex = searchPreviousSquareIndex();
        clickedSquareIndex = searchClickedSquareIndex(e);

        checkSameMove();
        if (status == SAME_MOVE)
            return;

        checkInvalidMove();
        if (status == INVALID_MOVE)
            return;

        updatePreviousSquare();
        updateClickedSquare();
    }

    public void shoot(GamePanelEvent e) {
        if (!hasArrow) {
            status = BULLET_ALREADY_USED;
            return;
        }

        hasArrow = false;

        clickedSquareIndex = searchClickedSquareIndex(e);
        if (squareMapping.get(clickedSquareIndex) == GamePanel.WUMPUS_ICON) {
            squareMapping.put(clickedSquareIndex, GamePanel.BLANK_ICON);
            status = WUMPUS_KILLED;

        } else {
            status = WUMPUS_NOT_KILLED;
        }
    }

    private void start() {
        hasArrow = true;

        for (int i = 0; i < 16; i++) {
            if (squareMapping.get(i) == GamePanel.PLAYER_ICON) {
                squares[i].setIcon(GamePanel.PLAYER_ICON);
                squares[i].setBackground(GamePanel.VISITED_SQUARE);
            }
        }
    }

    private int searchPreviousSquareIndex() {
        for (int i = 0; i < 16; i++) {
            Icon icon = squares[i].getIcon();

            if (icon == GamePanel.PLAYER_ICON || icon == GamePanel.PLAYER_BREEZE_ICON)
                return i;
            if (icon == GamePanel.PLAYER_STENCH_ICON || icon == GamePanel.PLAYER_BREEZE_STENCH_ICON)
                return i;
        }
        return -1;
    }

    private int searchClickedSquareIndex(GamePanelEvent e) {
        for (int i = 0; i < 16; i++)
            if (squares[i] == e.getClickedSquare())
                return i;
        return -1;
    }

    private void checkSameMove() {
        if (clickedSquareIndex == previousSquareIndex)
            status = SAME_MOVE;
    }

    private void checkInvalidMove() {
        if (previousSquareIndex == 0 && (clickedSquareIndex != 4 && clickedSquareIndex != 1))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 3 && (clickedSquareIndex != 2 && clickedSquareIndex != 7))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 12 && (clickedSquareIndex != 8 && clickedSquareIndex != 13))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 15 && (clickedSquareIndex != 14 && clickedSquareIndex != 11))
            status = INVALID_MOVE;

        else if (previousSquareIndex == 1 && (clickedSquareIndex != 0 && clickedSquareIndex != 5 && clickedSquareIndex != 2))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 2 && (clickedSquareIndex != 1 && clickedSquareIndex != 6 && clickedSquareIndex != 3))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 4 && (clickedSquareIndex != 8 && clickedSquareIndex != 0 && clickedSquareIndex != 5))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 7 && (clickedSquareIndex != 6 && clickedSquareIndex != 11 && clickedSquareIndex != 3))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 8 && (clickedSquareIndex != 12 && clickedSquareIndex != 9 && clickedSquareIndex != 4))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 11 && (clickedSquareIndex != 15 && clickedSquareIndex != 10 && clickedSquareIndex != 7))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 13 && (clickedSquareIndex != 12 && clickedSquareIndex != 9 && clickedSquareIndex != 14))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 14 && (clickedSquareIndex != 13 && clickedSquareIndex != 10 && clickedSquareIndex != 15))
            status = INVALID_MOVE;

        else if (previousSquareIndex == 5 && (clickedSquareIndex != 4 && clickedSquareIndex != 9 && clickedSquareIndex != 1 && clickedSquareIndex != 6))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 6 && (clickedSquareIndex != 5 && clickedSquareIndex != 10 && clickedSquareIndex != 7 && clickedSquareIndex != 2))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 9 && (clickedSquareIndex != 8 && clickedSquareIndex != 13 && clickedSquareIndex != 10 && clickedSquareIndex != 5))
            status = INVALID_MOVE;
        else if (previousSquareIndex == 10 && (clickedSquareIndex != 9 && clickedSquareIndex != 14 && clickedSquareIndex != 6 && clickedSquareIndex != 11))
            status = INVALID_MOVE;
    }

    private void updatePreviousSquare() {
        Icon previousSquareIcon = squares[previousSquareIndex].getIcon();

        if (previousSquareIcon == GamePanel.PLAYER_ICON)
            squares[previousSquareIndex].setIcon(GamePanel.BLANK_ICON);
        else if (previousSquareIcon == GamePanel.PLAYER_BREEZE_ICON)
            squares[previousSquareIndex].setIcon(GamePanel.BREEZE_ICON);
        else if (previousSquareIcon == GamePanel.PLAYER_STENCH_ICON)
            squares[previousSquareIndex].setIcon(GamePanel.STENCH_ICON);
        else if (previousSquareIcon == GamePanel.PLAYER_BREEZE_STENCH_ICON)
            squares[previousSquareIndex].setIcon(GamePanel.BREEZE_STENCH_ICON);
    }

    private void updateClickedSquare() {
        Icon mappedIcon = squareMapping.get(clickedSquareIndex);
        squares[clickedSquareIndex].setBackground(GamePanel.VISITED_SQUARE);

        checkGameWin(mappedIcon);
        checkGameOver(mappedIcon);
        if (status != GAME_OK) {
            squares[clickedSquareIndex].setIcon(mappedIcon);
            return;
        }

        if (mappedIcon == GamePanel.BREEZE_ICON)
            squares[clickedSquareIndex].setIcon(GamePanel.PLAYER_BREEZE_ICON);
        else if (mappedIcon == GamePanel.STENCH_ICON)
            squares[clickedSquareIndex].setIcon(GamePanel.PLAYER_STENCH_ICON);
        else if (mappedIcon == GamePanel.BREEZE_STENCH_ICON)
            squares[clickedSquareIndex].setIcon(GamePanel.PLAYER_BREEZE_STENCH_ICON);
        else if (mappedIcon == GamePanel.GOLD_ICON)
            squares[clickedSquareIndex].setIcon(GamePanel.GOLD_ICON);
        else
            squares[clickedSquareIndex].setIcon(GamePanel.PLAYER_ICON);
    }

    private void checkGameWin(Icon mappedIcon) {
        if (mappedIcon == GamePanel.GOLD_ICON)
            status = GAME_WON;
    }

    private void checkGameOver(Icon mappedIcon) {
        if (mappedIcon == GamePanel.PIT_ICON)
            status = GAME_OVER;
        else if (mappedIcon == GamePanel.WUMPUS_ICON)
            status = GAME_OVER;
    }

    public void reset() {
        status = GAME_OK;

        for (JButton square : squares) {
            square.setBackground(GamePanel.UNVISITED_SQUARE);
            square.setIcon(null);
        }

        start();
    }
}
