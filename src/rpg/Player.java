package rpg;

import rpg.entities.Inventory;
import rpg.entities.enemies.Enemy;
import rpg.entities.items.armors.Armor;
import rpg.entities.items.weapons.Sword;
import rpg.entities.items.armors.Helmet;
import rpg.entities.items.weapons.Weapon;
import rpg.enu.Stats;

public class Player extends GameCharacter {
    private Inventory inventory;

    public Player(String name) {
        super(name);
        inventory = new Inventory(10); // Capacidad inicial de 10 ítems.
        initializeStats();
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 20);
        stats.put(Stats.HP, 20);
        stats.put(Stats.ATTACK, 20);
        stats.put(Stats.DEFENSE, 10);
        stats.put(Stats.SPEED, 5);
    }

    public void equipInitialItems() {
        inventory.addItem(new Sword());
        inventory.addItem(new Helmet());
    }

    public void attack(Enemy enemy) {
    }

    public void equipArmor(Armor item) {
    }

    public void equipWeapon(Weapon item) {
    }
}
