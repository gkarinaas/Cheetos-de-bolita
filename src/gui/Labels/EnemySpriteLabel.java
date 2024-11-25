package gui.Labels;

import rpg.entities.enemies.Enemy;
import gui.ui.EnemyLabelUI;

import javax.swing.*;
import java.awt.*;

public class EnemySpriteLabel extends JLabel {

    private ImageIcon icon;
    private Enemy enemy;

    public EnemySpriteLabel(Enemy enemy) { /** etiqueta de objeto */
        this.enemy = enemy;
        initComponents();
        setUI(new EnemyLabelUI(icon));
    }

    private void initComponents() { /** inicializar componentes **/
        icon = enemy.getSprite();
        setPreferredSize(getMinDimension());
        setSize(getMinDimension());
        setIcon(icon);
    }

    private Dimension getMinDimension() {
        if (icon.getIconWidth() > 350 || icon.getIconHeight() > 184) {
            icon = new ImageIcon(icon.getImage().getScaledInstance(450, 250, Image.SCALE_SMOOTH)); /** dimensiones de imagen **/
        }
        return new Dimension(icon.getIconWidth(), icon.getIconHeight());
    }
}
