package wumpusodyssey.controller;

import wumpusodyssey.GameConstants;
import wumpusodyssey.gui.listener.GamePanelEvent;
import wumpusodyssey.model.WumpusWorld;

import javax.swing.*;

import java.util.HashMap;

public class Controller {

    private final WumpusWorld wumpusWorld;
    private final JFrame dialogParent;

    public Controller(JFrame dialogParent, JButton[] squares, HashMap<Integer, Icon> mappingList) {
        this.dialogParent = dialogParent;

        wumpusWorld = new WumpusWorld(squares, mappingList);
    }

    public void update(GamePanelEvent e) {
        if (wumpusWorld.getStatus() == WumpusWorld.GAME_WON || wumpusWorld.getStatus() == WumpusWorld.GAME_OVER)
            return;

        if (e.isActionEvent()) {
            wumpusWorld.move(e);

        } else {
            if (JOptionPane.showConfirmDialog(dialogParent, "Do you want shoot arrow?", GameConstants.NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
                return;

            wumpusWorld.shoot(e);
        }

        int status = wumpusWorld.getStatus();

        String message = null;
        switch (status) {
            case WumpusWorld.GAME_WON -> message = " Congratulations! You have won the game.";
            case WumpusWorld.GAME_OVER -> message = "Unfortunately, you have lost the game.";
            case WumpusWorld.SAME_MOVE -> message = "Oops! You're already on that square.";
            case WumpusWorld.INVALID_MOVE -> message = "Oops! That move is not valid.";

            case WumpusWorld.WUMPUS_KILLED -> message = "Success! Wumpus eliminated.";
            case WumpusWorld.WUMPUS_NOT_KILLED -> message = "Uh-oh! Your arrow missed the Wumpus.";
            case WumpusWorld.BULLET_ALREADY_USED -> message = "Sorry, you have already used your arrow.";
        }

        if (status == WumpusWorld.GAME_WON || status == WumpusWorld.GAME_OVER || status == WumpusWorld.WUMPUS_KILLED || status == WumpusWorld.WUMPUS_NOT_KILLED)
            JOptionPane.showMessageDialog(dialogParent, message, GameConstants.NAME, JOptionPane.INFORMATION_MESSAGE);
        else if (status == WumpusWorld.INVALID_MOVE || status == WumpusWorld.SAME_MOVE || status == WumpusWorld.BULLET_ALREADY_USED)
            JOptionPane.showMessageDialog(dialogParent, message, GameConstants.NAME, JOptionPane.ERROR_MESSAGE);

        if (status == WumpusWorld.GAME_WON || status == WumpusWorld.GAME_OVER)
            if (JOptionPane.showConfirmDialog(dialogParent, "Do you want replay?", GameConstants.NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                wumpusWorld.reset();
    }
}
