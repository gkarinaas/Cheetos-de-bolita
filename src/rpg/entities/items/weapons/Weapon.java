package rpg.entities.items.weapons;

import rpg.entities.items.Item;
import rpg.enu.ItemType;

public abstract class Weapon extends Item {
    private int attackPower;

    public Weapon(String name, int attackPower) {
        super(name, ItemType.WEAPON);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
