package gui.ui;

import rpg.utils.cache.FontCache;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public interface UIConstants {
    Font FONT = FontCache.addFont("PIXM", "fonts/M6X.ttf");
    Font BAR_LABEL_FONT = FontCache.addFont("PAE", "fonts/PixelAE.ttf").deriveFont(16f);
    int WINDOW_WIDTH = 1500;
    int TOP_HEIGHT = 150;
    int MIDDLE_HEIGHT = 320;
    int BOTTOM_HEIGHT = 350;
    Dimension BAR_ICON = new Dimension(58, 58);
    Dimension BAR_DISPLAY = new Dimension(179, 58);
    EmptyBorder EMPTY_BORDER = new EmptyBorder(14, 18, 18, 18);
}
