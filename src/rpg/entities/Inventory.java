package rpg.entities;

import rpg.entities.items.Item;
import rpg.entities.items.weapons.Weapon;
import rpg.entities.items.armors.Armor;
import rpg.entities.items.misc.Misc;
import javax.swing.*;
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

    // Mostrar el inventario en pantalla
    public void showInventory() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
            return;
        }

        StringBuilder inventoryList = new StringBuilder("Inventario:\n");
        for (int i = 0; i < items.size(); i++) {
            inventoryList.append(i + 1).append(". ").append(items.get(i).getName()).append("\n");
        }

        JOptionPane.showMessageDialog(null, inventoryList.toString());
    }

    // Seleccionar un ítem del inventario
    public Item selectItem() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
            return null;
        }

        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getName();
        }

        String selectedItem = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona un ítem:",
                "Inventario",
                JOptionPane.QUESTION_MESSAGE,
                null,
                itemNames,
                itemNames[0]
        );

        if (selectedItem != null) {
            for (Item item : items) {
                if (item.getName().equals(selectedItem)) {
                    return item;
                }
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return null;
    }

    public Item getItemByName(String itemName) {
        return null;
    }
}
