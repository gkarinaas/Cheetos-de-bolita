package rpg.enu;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class BarType {

    /** Definición de las barras de estado con imágenes predeterminadas **/
    public static final BarType LIFE = new BarType("/corazon.png");
    public static final BarType MAGIC = new BarType("/estrella.png");
    public static final BarType EXPERIENCE = new BarType("/medalla.png");

    private ImageIcon icon;

    /** Constructor que carga la imagen desde el classpath **/
    public BarType(String imagePath) {
        try {
            /** Intentar cargar la imagen desde el classpath **/
            URL url = getClass().getResource(imagePath);

            if (url == null) {
                /** Si la URL es nula, significa que la imagen no fue encontrada **/
                throw new IllegalArgumentException("No se pudo encontrar la imagen: " + imagePath);
            }

            /** Crear el icono a partir de la URL **/
            icon = new ImageIcon(url);

            /** Verificar si la imagen se cargó correctamente **/
            if (icon.getIconWidth() == -1) {
                /** Si la imagen tiene un tamaño de -1, significa que la imagen no pudo ser cargada correctamente **/
                throw new IllegalArgumentException("La imagen no tiene un tamaño válido: " + imagePath);
            }

        } catch (Exception e) {
            /** Imprimir el error y mostrar un mensaje si hay problemas cargando la imagen **/
            System.err.println("Error al cargar la imagen: " + imagePath);
            e.printStackTrace();
        }
    }

    /** Método para obtener el icono de la barra **/
    public ImageIcon getIcon() {
        return icon;
    }

    public BufferedImage getContainer() {
        return null;
    }

    public BufferedImage getBar() {
        return null;
    }
}
