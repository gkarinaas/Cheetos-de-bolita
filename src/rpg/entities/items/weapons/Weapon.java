package rpg.entities.items.weapons;

import rpg.entities.items.Item;

public abstract class Weapon extends Item {
    private int attackPower;

    public Weapon(String name, int attackPower) {
        super(name);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }
}