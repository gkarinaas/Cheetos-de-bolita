package gui.Labels;

import gui.Labels.PortraitLabel;
import rpg.utils.cache.ImageCache;

import java.awt.*;

public class PlayerSpriteLabel extends PortraitLabel {

    public PlayerSpriteLabel() {
        super();
    }

    @Override
    public void initComponents() {
        // Cargar la imagen del sprite del jugador
        ImageCache.addImage("playerSprite", "image/personaje.png");
        icon = ImageCache.getImageIcon("playerSprite");
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setIcon(icon);
    }
}
