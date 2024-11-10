package rpg.enu;

import rpg.utils.cache.ImageCache;

import java.awt.image.BufferedImage;

public enum BarType {

    LIFE, MAGIC, EXPERIENCE;

    BufferedImage container;
    BufferedImage icon;
    BufferedImage bar;

    BarType() {
        switch (this) {
            case LIFE -> {
                container = ImageCache.addImage("life_container", "bars/life_container.png");
                icon = ImageCache.addImage("life_icon", "bars/life_icon.png");
                bar = ImageCache.addImage("life_bar", "bars/life_bar.png");
            }
            case MAGIC -> {
                // Cargar imágenes para la barra de magia
                container = ImageCache.addImage("magic_container", "bars/magic_container.png");
                icon = ImageCache.addImage("magic_icon", "bars/magic_icon.png");
                bar = ImageCache.addImage("magic_bar", "bars/magic_bar.png");
            }
            case EXPERIENCE -> {
                // Cargar imágenes para la barra de experiencia
                container = ImageCache.addImage("experience_container", "bars/experience_container.png");
                icon = ImageCache.addImage("experience_icon", "bars/experience_icon.png");
                bar = ImageCache.addImage("experience_bar", "bars/experience_bar.png");
            }
        }
    }

    public BufferedImage getContainer() {
        return container;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public BufferedImage getBar() {
        return bar;
    }
}
