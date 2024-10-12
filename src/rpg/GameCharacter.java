package rpg;

import rpg.enu.Stats;
import java.util.HashMap;

public abstract class GameCharacter {
    protected String name;
    protected HashMap<Stats, Integer> stats;

    public GameCharacter(String name) {
        this.name = name;
        this.stats = new HashMap<>();
        initializeStats();
    }

    protected abstract void initializeStats();

    public String getName() {
        return name;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }

    public void takeDamage(int damage) {
        stats.put(Stats.HP, Math.max(stats.get(Stats.HP) - damage, 0));
    }

    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }
}