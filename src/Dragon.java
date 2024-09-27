import javax.swing.*;

public class Dragon extends Enemy {
    public Dragon(String name) {
        super(name);
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 200);
        stats.put(Stats.HP, 200);
        stats.put(Stats.ATTACK, 40);
        stats.put(Stats.DEFENSE, 30);
        stats.put(Stats.SPEED, 10);
    }

    /** calcula ataque **/
    @Override
    public void attack(Player player) {
        JOptionPane.showMessageDialog(null, name + " lanza una bola de fuego a " + player.getName());
        super.attack(player);
    }
}
