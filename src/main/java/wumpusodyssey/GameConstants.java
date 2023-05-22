package wumpusodyssey;

import wumpusodyssey.gui.util.ImageIconLoader;

import javax.swing.*;

import java.time.LocalDate;

public class GameConstants {
    public static final ImageIcon ICON
            = ImageIconLoader.loadImageIcon("/icons/game.png");

    public static final String NAME
            = "Wumpus Odyssey";

    public static final float BUILD_VERSION
            = 1.0f;

    public static final LocalDate BUILD_DATE
            = LocalDate.of(2023, 5, 22);

    public static final String BUILD_RUNTIME_VERSION
            = System.getProperty("java.version");
}
