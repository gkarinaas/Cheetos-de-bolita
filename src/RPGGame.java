import javax.swing.*;
import java.util.Random;

public class RPGGame {
  private Player player;
  private Enemy enemy;

  public RPGGame(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
  }

  public void startGame() {
    JOptionPane.showMessageDialog(null, "¡Comienza la batalla entre " + player.getName() + " y " + enemy.getName() + "!");
    while (player.isAlive() && enemy.isAlive()) {
      playerTurn();
      if (enemy.isAlive()) {
        enemyTurn();
      }
    }

    if (player.isAlive()) {
      JOptionPane.showMessageDialog(null, player.getName() + " ha derrotado a " + enemy.getName() + "!");
    } else {
      JOptionPane.showMessageDialog(null, enemy.getName() + " ha derrotado a " + player.getName() + "!");
    }
  }

  private void playerTurn() {
    int option = JOptionPane.showConfirmDialog(null, "¿Quieres atacar?", "Turno de " + player.getName(), JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
      player.attack(enemy);
    }
  }

  private void enemyTurn() {
    JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
    enemy.attack(player);
  }

  /** Método para generar un enemigo aleatorio **/
  public static Enemy getRandomEnemy() {
    Random rand = new Random();
    int randomIndex = rand.nextInt(5); /**Selecciona un numero aleatorio entre 0 y 4 **/

    switch (randomIndex) {
      case 0:
        return new Orc("Orco Guerrero");
      case 1:
        return new Dragon("Dragón Rojo");
      case 2:
        return new Zombie("Zombi Lento");
      case 3:
        return new Skeleton("Esqueleto Arquero");
      case 4:
        return new DarkMage("Mago Oscuro");
      default:
        return new Orc("Orco Guerrero"); /** Por si se devuelve por defecto**/
    }
  }

  public static void main(String[] args) {
    Player player = new Player("Cowboy");

    /** Gernerar enemigo aleatorio **/
    Enemy randomEnemy = getRandomEnemy();

    /** Iniciar el juego con enemigo aleatorio **/
    RPGGame game = new RPGGame(player, randomEnemy);
    game.startGame();
  }
}
