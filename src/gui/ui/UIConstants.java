package gui.ui;

import java.awt.Font;
import rpg.utils.cache.FontCache;

public class UIConstants {

    // Asegúrate de que esta constante se inicialice correctamente
    public static final Font FONT = loadFont();

    private static Font loadFont() {
        // Ruta de la fuente
        String fontPath = "path/to/your/font.ttf"; // Asegúrate de que la ruta sea correcta
        Font font = FontCache.addFont("CustomFont", fontPath);

        if (font == null) {
            // Si la fuente no se carga correctamente, se usa una fuente predeterminada
            System.out.println("Usando fuente predeterminada debido a error al cargar la fuente personalizada.");
            return new Font("Arial", Font.PLAIN, 12);  // Fuente predeterminada
        }

        return font.deriveFont(12f);  // Ajusta el tamaño si es necesario
    }
}
