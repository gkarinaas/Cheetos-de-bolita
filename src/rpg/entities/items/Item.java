package rpg.entities.items;

import rpg.enu.ItemType;

public abstract class Item {
    protected String name;
    protected ItemType type;

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }
}
