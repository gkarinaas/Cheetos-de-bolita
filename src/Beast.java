public class Beast extends Enemy {
    public Beast() {
        super("Lobo Salvaje");
    }

    @Override
    protected void initializeStats() {
        stats.put(Stats.MAX_HP, 70);
        stats.put(Stats.HP, 70);
        stats.put(Stats.ATTACK, 18);
        stats.put(Stats.DEFENSE, 10);
        stats.put(Stats.SPEED, 7);
    }
}

