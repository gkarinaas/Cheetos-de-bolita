package rpg;

import rpg.entities.enemies.Enemy;
import rpg.utils.Randomize;
import javax.swing.JOptionPane;

public class Game {
    private Player player;
    private Enemy enemy;

    public Game(Player player) {
        this.player = player;
        this.enemy = Randomize.getRandomEnemy();
    }

    /** iniciar juego **/
    public void startGame() {
        JOptionPane.showMessageDialog(null, "¡Comienza la batalla entre " + player.getName() + " y " + enemy.getName() + "!");
        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }

        if (player.isAlive()) { /** se mantiene vivo el jugador **/
            JOptionPane.showMessageDialog(null, player.getName() + " ha derrotado a " + enemy.getName() + "!");
        } else { /** es derrotado el jugador **/
            JOptionPane.showMessageDialog(null, enemy.getName() + " ha derrotado a " + player.getName() + "!");
        }
    }

    /** turno del jugador **/
    private void playerTurn() {
        int option = JOptionPane.showConfirmDialog(null, "¿Quieres atacar?", "Turno de " + player.getName(), JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            player.attack(enemy);
        }
    }

    /** turno del enemigo **/
    private void enemyTurn() {
        JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
        enemy.attack(player);
    }

    public static void main(String[] args) {
        Player player = new Player("Cowboy");
        Game game = new Game(player);
        game.startGame();
    }
}
