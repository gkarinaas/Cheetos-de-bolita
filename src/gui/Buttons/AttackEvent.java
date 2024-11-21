package gui.Buttons;

import rpg.Player;
import rpg.entities.enemies.Enemy;
import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AttackEvent implements ActionListener {
    private MainWindow game;

    public AttackEvent(MainWindow game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Enemy enemy = game.getCurrentEnemy();
        Player player = game.getPlayer();

        if (enemy != null) {
            game.appendText(player.attack(enemy)); // Realiza el ataque del jugador
            if (enemy.isAlive()) {
                game.appendText(enemy.attack(player)); // Contraataque del enemigo
            } else {
                game.appendText("¡Has derrotado al enemigo!");
                game.updateEnemyPanel(); // Generar nuevo enemigo
            }
            game.checkGameStatus();
        } else {
            game.appendText("No hay enemigo para atacar.");
        }
    }
}

