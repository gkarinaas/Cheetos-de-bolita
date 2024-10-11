package rpg.entities.items.misc;

import rpg.entities.items.Item;
import rpg.enu.ItemType;

public abstract class Misc extends Item {
    public Misc(String name) {
        super(name, ItemType.MISC);
    }
}
