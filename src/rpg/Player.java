package rpg;

import rpg.entities.Inventory;
import rpg.entities.items.Item;
import rpg.entities.items.armors.Armor;
import rpg.entities.items.misc.Misc;
import rpg.entities.items.weapons.Weapon;
import rpg.enu.Stats;
import rpg.utils.Randomize;

import javax.swing.*;
import java.io.Serializable;

public class Player extends GameCharacter implements Serializable {
    private final Inventory inventory;

    // Límites máximos para estadísticas
    public static final int MAX_ENERGY = 100;
    public static final int MAX_EXPERIENCE = 100;

    public Player(String name, int i, int i1, int i2) {
        super();
        inventory = new Inventory();
    }

    public boolean tryToFlee() {
        return Randomize.getRandomBoolean();
    }

    public void levelUp() {
        stats.put(Stats.LEVEL, stats.get(Stats.LEVEL) + 1);
        stats.put(Stats.MAX_HP, stats.get(Stats.MAX_HP) + Randomize.getRandomInt(5, 10));
        stats.put(Stats.HP, stats.get(Stats.MAX_HP));
        stats.put(Stats.MAX_MP, stats.get(Stats.MAX_MP) + Randomize.getRandomInt(2, 5));
        stats.put(Stats.MP, stats.get(Stats.MAX_MP));
        stats.put(Stats.ATTACK, stats.get(Stats.ATTACK) + Randomize.getRandomInt(1, 3));
        stats.put(Stats.DEFENSE, stats.get(Stats.DEFENSE) + Randomize.getRandomInt(1, 3));
        stats.put(Stats.NEEDED_EXPERIENCE, stats.get(Stats.NEEDED_EXPERIENCE) + 50);
        stats.put(Stats.EXPERIENCE, 0);

        // Recargar energía al subir de nivel
        stats.put(Stats.ENERGY, MAX_ENERGY);
    }

    @Override
    protected void initCharacter() {
        stats.put(Stats.LEVEL, 1);
        stats.put(Stats.MAX_HP, 100);
        stats.put(Stats.HP, 100);
        stats.put(Stats.MAX_MP, 50);
        stats.put(Stats.MP, 50);
        stats.put(Stats.ATTACK, 10);
        stats.put(Stats.DEFENSE, 5);
        stats.put(Stats.EXPERIENCE, 0);
        stats.put(Stats.NEEDED_EXPERIENCE, 100);
        stats.put(Stats.GOLD, 0);
        stats.put(Stats.ENERGY, MAX_ENERGY); // Energía inicial
    }

    public void adjustHP(int amount) {
        int newHP = stats.get(Stats.HP) + amount;
        stats.put(Stats.HP, Math.max(0, Math.min(newHP, stats.get(Stats.MAX_HP))));
    }

    public void adjustEnergy(int amount) {
        int newEnergy = stats.get(Stats.ENERGY) + amount;
        stats.put(Stats.ENERGY, Math.max(0, Math.min(newEnergy, MAX_ENERGY)));
    }

    public void adjustExperience(int amount) {
        int newExperience = stats.get(Stats.EXPERIENCE) + amount;
        if (newExperience >= stats.get(Stats.NEEDED_EXPERIENCE)) {
            levelUp();
        } else {
            stats.put(Stats.EXPERIENCE, Math.min(newExperience, MAX_EXPERIENCE));
        }
    }

    public void addItemToInventory(Item item) {
        if (item instanceof Misc misc) {
            if (misc.isStackable()) {
                boolean found = false;
                for (Item i : inventory.getMiscs()) {
                    if (i.getName().equals(misc.getName())) {
                        misc.increaseQuantity(1);
                        inventory.removeItem(i);
                        inventory.addItem(misc);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    inventory.addItem(item);
                }
            } else {
                inventory.addItem(item);
            }
        } else {
            inventory.addItem(item);
        }
    }

    public void removeItemFromInventory(Item item) {
        if (item instanceof Misc misc) {
            if (misc.isStackable()) {
                for (Item i : inventory.getMiscs()) {
                    if (i.getName().equals(item.getName())) {
                        misc.decreaseQuantity(1);
                        if ((int) misc.getQuantity() == 0) {
                            inventory.removeItem(i);
                        }
                        break;
                    }
                }
            } else {
                inventory.removeItem(item);
            }
        } else {
            inventory.removeItem(item);
        }
    }

    public void showInventory() {
        StringBuilder content = new StringBuilder("Inventory: \n");
        String format = """
                Name: %s, Price: %d
                Description: %s
                """;
        String formatQuantity = """
                Name: %s, Price: %d, Quantity: %d
                Description: %s
                """;
        for (Item item : inventory.getItems()) {
            if (item instanceof Misc misc) {
                if (misc.isStackable()) {
                    content.append(String.format(formatQuantity, item.getName(),
                            item.getPrice(), misc.getQuantity(), item.getDescription()));
                } else {
                    content.append(String.format(format, item.getName(), item.getPrice(),
                            item.getDescription()));
                }
            } else {
                content.append(String.format(format, item.getName(), item.getPrice(),
                        item.getDescription()));
            }
        }
        JOptionPane.showMessageDialog(null, content.toString());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void equipArmor(Armor item) {
    }

    public void equipWeapon(Weapon item) {
    }

    public boolean isDead() {
        return stats.get(Stats.HP) <= 0;
    }

    public int getAttack() {
        return stats.get(Stats.ATTACK);
    }

    public int getMaxHP() {
        return stats.get(Stats.MAX_HP);
    }

    public int getCurrentHP() {
        return stats.get(Stats.HP);
    }
}
