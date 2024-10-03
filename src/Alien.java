public class Alien extends Enemy {
    public Alien() {
        super("Alienígena");
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 90);
        stats.put(Stats.HP, 90);
        stats.put(Stats.ATTACK, 22);
        stats.put(Stats.DEFENSE, 12);
        stats.put(Stats.SPEED, 9);
    }
}
