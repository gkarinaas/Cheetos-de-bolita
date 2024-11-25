package gui.Labels;

import gui.ui.LabelUI;
import rpg.utils.cache.ImageCache;

import javax.swing.*;
import java.awt.*;

public class PortraitLabel extends JLabel { /** etiqueta de retrato **/

    protected ImageIcon icon;

    public PortraitLabel() {
        initComponents();
        System.out.println("Iniciando el PortraitLabel...");
        setUI(new LabelUI(getPreferredSize(), icon));
    }

    public void initComponents() {
        // Ruta de la imagen en el recurso
        String imagePath = "/iconohongo.png";  /** ruta de la ubicación exacta **/
        System.out.println("Cargando imagen desde: " + imagePath);

        /** Cargar la imagen a través del ImageCache **/
        ImageCache.addImage("portrait", imagePath);
        icon = ImageCache.getImageIcon("portrait");

        if (icon != null) {
            System.out.println("Imagen cargada correctamente.");
            setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            setIcon(icon);
        } else {
            System.err.println("Error: Imagen no encontrada en " + imagePath);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(icon.getImage(), 0, 0, getPreferredSize().width, getPreferredSize().height, this);
        } else {
            System.err.println("No se puede pintar la imagen porque no se cargó correctamente.");
        }
    }
}