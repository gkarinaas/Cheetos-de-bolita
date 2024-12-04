package gui;

import rpg.entities.enemies.Enemy;

import javax.swing.*;
import java.awt.*;

public class EnemyPanel extends JPanel {
    private JProgressBar lifeBar;
    private JLabel imageLabel;

    public EnemyPanel(Enemy enemy) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 180, 200)); // Color de fondo, opcional

        // Barra de vida
        lifeBar = new JProgressBar(0, enemy.getMaxLife());
        lifeBar.setValue(enemy.getLife());
        lifeBar.setStringPainted(true);
        lifeBar.setForeground(new Color(136, 39, 61)); // Color de la barra
        add(lifeBar, BorderLayout.NORTH);

        // Imagen del enemigo
        imageLabel = new JLabel(new ImageIcon(getClass().getResource("/ruta/a/imagen/enemigo.png")));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }

    // Método para actualizar la vida
    public void updateLife(int newLife) {
        lifeBar.setValue(newLife);
    }
}
