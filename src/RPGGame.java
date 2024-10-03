import javax.swing.JOptionPane;
import java.util.Random;

public class RPGGame {
  private Player player;
  private IEnemy enemy;

  public RPGGame(Player player, IEnemy enemy) {
    this.player = player;
    this.enemy = enemy;
  }

  /** Iniciar juego **/
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
  /** Turno de atacar del jugador **/
  private void playerTurn() {
    int option = JOptionPane.showConfirmDialog(null, "¿Quieres atacar?", "Turno de " + player.getName(), JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
      player.attack(enemy);
    }
  }
 /** Turno de atacar del enemigo **/
  private void enemyTurn() {
    JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
    enemy.attack(player);
  }

  /** Método para seleccionar un enemigo aleatorio **/
  private static IEnemy getRandomEnemy() {
    Random rand = new Random();
    int enemyType = rand.nextInt(5); // Cinco tipos de enemigos disponibles

    switch (enemyType) {
      case 0: return new Undead();
      case 1: return new Beast();
      case 2: return new Demon();
      case 3: return new Robot();
      case 4: return new Alien();
      default: return new Undead(); // Por si acaso
    }
  }

  public static void main(String[] args) {
    Player player = new Player("Cowboy");

    /** Selecciona el enemigo de manera aleatoria **/
    IEnemy enemy = getRandomEnemy();

    RPGGame game = new RPGGame(player, enemy);
    game.startGame();
  }
}
