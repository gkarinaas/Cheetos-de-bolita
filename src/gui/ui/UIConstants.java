package gui.ui;

import java.awt.Font;
import rpg.utils.cache.FontCache;

public class UIConstants {

    public static final Font FONT = loadFont();

    private static Font loadFont() {
        /** Ruta de la fuente **/
        String fontPath = "path/to/your/font.ttf";
        Font font = FontCache.addFont("CustomFont", fontPath);

        /**  Si la fuente no se carga correctamente, se usa una fuente predeterminada **/
        if (font == null) {
            System.out.println("Usando fuente predeterminada debido a error al cargar la fuente personalizada.");
            return new Font("Arial", Font.PLAIN, 12);  /** Fuente predeterminada **/
        }

        return font.deriveFont(12f);  /** Ajusta el tamaño si es necesario **/
    }
}
