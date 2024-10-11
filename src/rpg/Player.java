package rpg;

import rpg.enu.Stats;
import rpg.entities.enemies.Enemy;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

public class Player {
    private String name;
    private HashMap<Stats, Integer> stats;

    public Player(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    /** inicializar estadisticas **/
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
 /** atacar */
    public void attack(Enemy enemy) {
        int damage = calculateDamage(enemy);
        enemy.takeDamage(damage);
        JOptionPane.showMessageDialog(null, name + " ataca a " + enemy.getName() + " e inflige " + damage + " de daño.");
    }
 /** calcular daño **/
    private int calculateDamage(Enemy enemy) {
        int attackPower = stats.get(Stats.ATTACK);
        int defensePower = enemy.getStats().get(Stats.DEFENSE);
        Random rand = new Random();
        return Math.max(attackPower - defensePower + rand.nextInt(5), 0);
    }
/** mantener vivo **/
    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }
}
