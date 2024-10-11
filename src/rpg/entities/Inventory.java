package rpg.entities;

import rpg.entities.items.Item;
import rpg.entities.items.armors.Armor;
import rpg.entities.items.weapons.Weapon;
import rpg.entities.items.misc.Misc;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!isFull()) {
            items.add(item);
        } else {
            System.out.println("El inventario está lleno.");
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public void increaseCapacity(int amount) {
        this.capacity += amount;
    }

    public ArrayList<Armor> listArmors() {
        ArrayList<Armor> armors = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Armor) {
                armors.add((Armor) item);
            }
        }
        return armors;
    }

    public ArrayList<Weapon> listWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            }
        }
        return weapons;
    }

    public ArrayList<Misc> listMisc() {
        ArrayList<Misc> miscItems = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Misc) {
                miscItems.add((Misc) item);
            }
        }
        return miscItems;
    }
}
