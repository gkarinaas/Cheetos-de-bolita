import javax.swing.*;

// Skeleton.java
public class Skeleton extends Enemy {
    public Skeleton(String name) {
        super(name);
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 60);
        stats.put(Stats.HP, 60);
        stats.put(Stats.ATTACK, 15);
        stats.put(Stats.DEFENSE, 10);
        stats.put(Stats.SPEED, 5);
    }

    @Override
    public void attack(Player player) {
        JOptionPane.showMessageDialog(null, name + " dispara una flecha a " + player.getName());
        super.attack(player);
    }
}
