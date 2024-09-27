// Zombie.java
public class Zombie extends Enemy {
    public Zombie(String name) {
        super(name);
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 50);
        stats.put(Stats.HP, 50);
        stats.put(Stats.ATTACK, 10);
        stats.put(Stats.DEFENSE, 5);
        stats.put(Stats.SPEED, 2);
    }
}
