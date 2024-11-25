package rpg.utils.cache;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FontCache {

    public static Font addFont(String fontName, String fontPath) {
        try {
            /** Verificamos si el archivo de la fuente existe **/
            File fontFile = new File(fontPath);
            if (!fontFile.exists()) {
                System.out.println("Error: El archivo de fuente no existe en la ruta: " + fontPath);
                return null;  /** Regresar null si el archivo no se encuentra **/
            }

            /** Cargar la fuente **/
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font;
        } catch (IOException | FontFormatException e) {
            System.out.println("Error al cargar la fuente desde: " + fontPath);
            e.printStackTrace();
            return null;  /** Retornar null en caso de error **/        }
    }
}
