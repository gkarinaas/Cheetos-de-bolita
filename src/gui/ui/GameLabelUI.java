package gui.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class GameLabelUI extends JLabel {

    public GameLabelUI(String text) {
        super(text);
        setFont(createFont());
        setForeground(new Color(57, 37, 6));  /** Establecer el color del texto **/
    }

    private Font createFont() {
        Font font = new Font("Serif", Font.BOLD, 20);  // Fuente base
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  /** Subrayado **/
        return font.deriveFont(attributes);  /** Aplicar atributos de la fuente **/
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
