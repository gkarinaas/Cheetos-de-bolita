package gui.ui;

import gui.Labels.BarLabel;
import rpg.enu.BarType;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BarLabelUI extends BasicLabelUI {

    private final BarType type;

    public BarLabelUI(BarType type) {
        this.type = type;
    }

    @Override
    protected void installDefaults(JLabel c) {
        c.setOpaque(false);
        c.setBorder(null);
        c.setForeground(new Color(218, 195, 214));
        c.setFont(new Font("Arial", Font.BOLD, 12));  // Ajusta la fuente
        c.setVerticalAlignment(JLabel.BOTTOM);
        c.setVerticalTextPosition(JLabel.BOTTOM);
        c.setHorizontalAlignment(JLabel.RIGHT);
        c.setHorizontalTextPosition(JLabel.RIGHT);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(150, 50);  // Ajusta el tamaño de la barra
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        BarLabel barLabel = (BarLabel) c;
        BufferedImage icon = type.getIcon();
        BufferedImage container = type.getContainer();
        BufferedImage bar = type.getBar();

        int barWidth = (int) ((double) barLabel.getBarValue() / barLabel.getMaxValue() * 150);  // Ajuste de ancho de la barra
        g2d.drawImage(icon, 0, 0, 20, 20, null);  // Icono
        g2d.drawImage(container, 25, 0, 150, 20, null);  // Contenedor
        g2d.setColor(new Color(143, 94, 18));  // Color de la barra
        g2d.fillRect(25, 0, barWidth, 20);  // Barra de estado
    }
}
