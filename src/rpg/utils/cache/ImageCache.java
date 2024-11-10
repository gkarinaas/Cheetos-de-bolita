package rpg.utils.cache;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

    private static final Map<String, ImageIcon> cache = new HashMap<>();

    // Añadir una imagen a la caché
    public static BufferedImage addImage(String key, String path) {
        try {
            // Asegurarse de que la ruta sea correcta
            ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(path));
            cache.put(key, imageIcon);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen desde la ruta: " + path);
        }
        return null;
    }

    // Obtener un icono de imagen desde la caché
    public static ImageIcon getImageIcon(String key) {
        return cache.getOrDefault(key, null);
    }
}
