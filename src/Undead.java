public class Undead extends Enemy {
    public Undead() {
        super("Zombie");
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 50);
        stats.put(Stats.HP, 50);
        stats.put(Stats.ATTACK, 12);
        stats.put(Stats.DEFENSE, 5);
        stats.put(Stats.SPEED, 2);
    }
}
