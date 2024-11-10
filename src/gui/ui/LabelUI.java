package gui.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class LabelUI extends BasicLabelUI {

    private Dimension size;
    private ImageIcon icon;

    public LabelUI(Dimension size, ImageIcon icon) {
        this.size = size;
        this.icon = icon;
    }

    @Override
    protected void installDefaults(JLabel c) {
        c.setOpaque(false); // Hace que el JLabel sea transparente
        c.setBorder(null);  // Elimina el borde del JLabel para una apariencia más limpia
    }
}
