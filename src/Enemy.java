// Enemy.java
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

public abstract class Enemy {
    protected String name;
    protected HashMap<Stats, Integer> stats;

    public Enemy(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    /** Metodo que deben implementar las subclases **/
    protected abstract void initializeStats();

    public String getName() {
        return name;
    }

    /**Calcula ataque**/
    public void attack(Player player) {
        int damage = calculateDamage(player);
        player.getStats().put(Stats.HP, Math.max(player.getStats().get(Stats.HP) - damage, 0));
        JOptionPane.showMessageDialog(null, name + " ataca a " + player.getName() + " e inflige " + damage + " de daño.");
    }

    /**Calcula daño**/
    protected int calculateDamage(Player player) {
        int attackPower = stats.get(Stats.ATTACK);
        int defensePower = player.getStats().get(Stats.DEFENSE);
        Random rand = new Random();
        return Math.max(attackPower - defensePower + rand.nextInt(5), 0);
    }

    /** Recibir daño **/
    public void takeDamage(int damage) {
        stats.put(Stats.HP, Math.max(stats.get(Stats.HP) - damage, 0));
    }

    /**Mantener vivo**/
    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }
}
