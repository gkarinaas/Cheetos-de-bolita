package gui.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class UserHoverUI extends BasicButtonUI {
    @Override
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setFont(new Font("Arial", Font.BOLD, 18));
        b.setForeground(Color.BLACK);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        AbstractButton button = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** Dibujar el fondo del botón al pasar el mouse **/
        if (button.getModel().isRollover()) {
            g2.setColor(new Color(200, 200, 200, 100)); /** Color de fondo al pasar el mouse **/
            g2.fillRect(0, 0, button.getWidth(), button.getHeight());
        }

        g2.setColor(button.getForeground());
        g2.drawString(button.getText(), button.getInsets().left, button.getHeight() / 2 + g.getFontMetrics().getAscent() / 2 - 2);
    }
}
