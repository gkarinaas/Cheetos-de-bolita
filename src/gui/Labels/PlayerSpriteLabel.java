package gui.Labels;

import gui.Labels.PortraitLabel;
import rpg.utils.cache.ImageCache;

import java.awt.*;

public class PlayerSpriteLabel extends PortraitLabel {

    public PlayerSpriteLabel() {
        super();
    } /** etiqueta del jugador **/

    @Override
    public void initComponents() {
        // Cargar la imagen del sprite del jugador
        ImageCache.addImage("playerSprite", "/personaje.png"); /** imagen del jugador **/
        icon = ImageCache.getImageIcon("playerSprite");
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setIcon(icon);
    }
}
