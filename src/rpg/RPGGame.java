package rpg;

import rpg.Player;
import rpg.entities.enemies.Enemy;
import rpg.entities.items.Item;
import rpg.entities.items.armors.Armor;
import rpg.entities.items.weapons.Weapon;

import javax.swing.*;
import java.util.ArrayList;

public class RPGGame {
    private Player player;
    private Enemy enemy;

    public RPGGame(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startGame() {
        JOptionPane.showMessageDialog(null, "¡Comienza la batalla entre " + player.getName() + " y " + enemy.getName() + "!");

        // Bucle del juego hasta que uno de los personajes sea derrotado
        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();

            if (enemy.isAlive()) {
                enemyTurn();
            }
        }

        // Determinamos quién ganó
        if (player.isAlive()) {
            JOptionPane.showMessageDialog(null, player.getName() + " ha derrotado a " + enemy.getName() + "!");
        } else {
            JOptionPane.showMessageDialog(null, enemy.getName() + " ha derrotado a " + player.getName() + "!");
        }

        // Fin del juego
        JOptionPane.showMessageDialog(null, "El juego ha terminado.");
    }

    // Turno del jugador
    private void playerTurn() {
        String[] options = {"Atacar", "Mostrar Inventario"};
        int option = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Turno de " + player.getName(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (option == 0) {
            player.attack(enemy);
        } else if (option == 1) {
            showInventory();  // Mostrar el inventario
        }
    }

    // Turno del enemigo
    private void enemyTurn() {
        JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
        enemy.attack(player);
    }

    // Mostrar el inventario
    private void showInventory() {
        ArrayList<Item> items = player.getInventory().getItems();
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
            return;
        }

        StringBuilder inventoryDisplay = new StringBuilder("Inventario:\n");
        for (Item item : items) {
            inventoryDisplay.append(item.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(null, inventoryDisplay.toString(), "Inventario", JOptionPane.PLAIN_MESSAGE);
    }

    // Equipar un ítem desde el inventario
    private void equipItem(String itemName) {
        Item item = player.getInventory().getItemByName(itemName);
        if (item instanceof Armor) {
            player.equipArmor((Armor) item);
            JOptionPane.showMessageDialog(null, itemName + " equipado como armadura.");
        } else {
            JOptionPane.showMessageDialog(null, "No puedes equipar este ítem.");
        }
    }
}
