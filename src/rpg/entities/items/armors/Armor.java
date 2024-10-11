package rpg.entities.items.armors;

import rpg.entities.items.Item;
import rpg.enu.ItemType;

public abstract class Armor extends Item {
    private int defensePower;

    public Armor(String name, int defensePower) {
        super(name, ItemType.ARMOR);
        this.defensePower = defensePower;
    }

    public int getDefensePower() {
        return defensePower;
    }
}
