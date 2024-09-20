import javax.swing.*;

/** Define clase rpg **/
public class RPGGame {
  private final Player player;
  private final Enemy enemy;


  public RPGGame(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
  }

  /** inicia juego **/
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

  /** Asigna turno del jugador **/
  private void playerTurn() {
    int option = JOptionPane.showConfirmDialog(null, "¿Quieres atacar?", "Turno de " + player.getName(), JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
      player.attack(enemy);
    }
  }

  /** Asigna turno del enemigo **/
  private void enemyTurn() {
    JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
    enemy.attack(player);
  }

  public static void main(String[] args) {
    Player player = new Player("Cowboy");
    Enemy enemy = new Enemy("Enemigo");

    RPGGame game = new RPGGame(player, enemy);
    game.startGame();
  }
}
