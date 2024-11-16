package rpg.utils.cache;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

    private static final Map<String, ImageIcon> imageCache = new HashMap<>();

    public static void addImage(String key, String path) {
        if (!imageCache.containsKey(key)) {
            imageCache.put(key, new ImageIcon(ClassLoader.getSystemResource(path)));
        }
    }

    public static ImageIcon getImageIcon(String key) {
        return imageCache.getOrDefault(key, null);
    }
}
