import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/** Enumeración de las estadísticas **/
enum Stats {
    HP, MAX_HP, ATTACK, DEFENSE, SPEED
}

/** Clase Player **/
class Player {
    private String name;
    private HashMap<Stats, Integer> stats;

    public Player(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    /** inizializar estadisticas del jugador **/
    private void initializeStats() {
        stats.put(Stats.MAX_HP, 20);
        stats.put(Stats.HP, 20);
        stats.put(Stats.ATTACK, 20);
        stats.put(Stats.DEFENSE, 10);
        stats.put(Stats.SPEED, 5);
    }


    public String getName() {
        return name;
    }

    public void attack(Enemy enemy) {
        int damage = calculateDamage(enemy);
        enemy.takeDamage(damage);
        JOptionPane.showMessageDialog(null, name + " ataca a " + enemy.getName() + " e inflige " + damage + " de daño.");
    }

    /** Calcular el daño **/
    private int calculateDamage(Enemy enemy) {
        int attackPower = stats.get(Stats.ATTACK);
        int defensePower = enemy.getStats().get(Stats.DEFENSE);
        Random rand = new Random();
        return Math.max(attackPower - defensePower + rand.nextInt(5), 0);
    }

    /**mantener vivo **/
    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }
}

/** Clase Enemy **/
class Enemy {
    private String name;
    private HashMap<Stats, Integer> stats;

    public Enemy(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    /** inizializar estadisticas del enemigo **/
    private void initializeStats() {
        stats.put(Stats.MAX_HP, 80);
        stats.put(Stats.HP, 80);
        stats.put(Stats.ATTACK, 15);
        stats.put(Stats.DEFENSE, 8);
        stats.put(Stats.SPEED, 4);
    }

    public String getName() {
        return name;
    }

    public void attack(Player player) {
        int damage = calculateDamage(player);
        player.getStats().put(Stats.HP, Math.max(player.getStats().get(Stats.HP) - damage, 0));
        JOptionPane.showMessageDialog(null, name + " ataca a " + player.getName() + " e inflige " + damage + " de daño.");
    }

    /** Calcular el daño **/
    private int calculateDamage(Player player) {
        int attackPower = stats.get(Stats.ATTACK);
        int defensePower = player.getStats().get(Stats.DEFENSE);
        Random rand = new Random();
        return Math.max(attackPower - defensePower + rand.nextInt(5), 0);
    }

    /**  Recibir daño **/
    public void takeDamage(int damage) {
        stats.put(Stats.HP, Math.max(stats.get(Stats.HP) - damage, 0));
    }

    /** Mantener vivo **/
    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }
}

/** Clase Game **/
public class RPGGame {
    private Player player;
    private Enemy enemy;

    public RPGGame(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /** Inicia el juego **/
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

    /** Asigna el turno del jugador **/
    private void playerTurn() {
        int option = JOptionPane.showConfirmDialog(null, "¿Quieres atacar?", "Turno de " + player.getName(), JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            player.attack(enemy);
        }
    }

    /** Asigna el turno del enemigo **/
    private void enemyTurn() {
        JOptionPane.showMessageDialog(null, enemy.getName() + " se prepara para atacar.");
        enemy.attack(player);
    }

    public static void main(String[] args) {

        /** Crear los personajes **/
        Player player = new Player("Cowboy");
        Enemy enemy = new Enemy("Enemigo");

        /** Iniciar el juego **/
        RPGGame game = new RPGGame(player, enemy);
        game.startGame();
    }
}
