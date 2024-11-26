package gui.Buttons;

import gui.windows.MainWindow;

import javax.swing.*;
import java.awt.*;

public class AttackButton extends JButton {
    public AttackButton(MainWindow window) {
        super("Atacar");  // Define explícitamente el texto como "Atacar"
        // Personalización del botón
        setFont(new Font("Arial", Font.BOLD, 16));
        setPreferredSize(new Dimension(180, 60));
        setBackground(new Color(100, 100, 100));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(new Color(252, 232, 242), 2));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        // Lógica adicional según sea necesario
    }
        public AttackButton(JFrame frame) {
            super("Atacar");
            // Aquí puedes hacer algo con el frame si es necesario
        }
    }



