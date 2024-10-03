import java.util.HashMap;

public interface IEnemy {
    void attack(Player player);
    void takeDamage(int damage);
    boolean isAlive();
    String getName();
    HashMap<Stats, Integer> getStats();
}
