package wumpusodyssey.gui.listener;

import javax.swing.*;

import java.util.EventObject;

public class GamePanelEvent extends EventObject {

    private final boolean isActionEvent;
    private final JButton clickedSquare;

    public GamePanelEvent(Object source, boolean isActionEvent, JButton clickedSquare) {
        super(source);
        this.isActionEvent = isActionEvent;
        this.clickedSquare = clickedSquare;
    }

    public boolean isActionEvent() {
        return isActionEvent;
    }

    public JButton getClickedSquare() {
        return clickedSquare;
    }
}
