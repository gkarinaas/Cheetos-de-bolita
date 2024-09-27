// Orc.java
public class Orc extends Enemy {
    public Orc(String name) {
        super(name);
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 100);
        stats.put(Stats.HP, 100);
        stats.put(Stats.ATTACK, 25);
        stats.put(Stats.DEFENSE, 15);
        stats.put(Stats.SPEED, 7);
    }
}
