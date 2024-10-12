package rpg.entities.items.armors;

import rpg.entities.items.Item;
import rpg.enu.ItemType;

public class Armor extends Item {
    private int defensePower;

    public Armor(String name, int i) {
        super(name);
        this.defensePower = defensePower;
    }

    @Override
    public ItemType getType() {
        return ItemType.valueOf("Armadura");
    }

    public int getDefensePower() {
        return defensePower;
    }

    public Integer getDefenseBonus() {
        return 0;
    }

    public Integer getDefense() {
        return 0;
    }
}
