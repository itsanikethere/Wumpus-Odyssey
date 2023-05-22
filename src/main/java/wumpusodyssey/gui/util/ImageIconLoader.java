package wumpusodyssey.gui.util;

import javax.swing.*;

import java.util.Objects;

public class ImageIconLoader {
    public static ImageIcon loadImageIcon(String path) {
        return new ImageIcon(Objects.requireNonNull(ImageIconLoader.class.getResource(path)));
    }
}
