package rpg;

import rpg.enu.Stats;
import rpg.exceptions.EnemyDeathException;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Clase que representa a un personaje del juego.
 */
public abstract class GameCharacter implements Serializable {
    protected String name;
    protected HashMap<Stats, Integer> stats;

    public GameCharacter() {
        this.name = name;
        this.stats = new HashMap<>();
        initCharacter();
    }

    protected abstract void initCharacter();

    public boolean isAlive() {
        return stats.get(Stats.HP) > 0;
    }

    public String attack(GameCharacter enemy) {
        String message = "";
        String enemyName = enemy.getName();
        int damage = this.stats.get(Stats.ATTACK) - enemy.getStats().get(Stats.DEFENSE);
        int newHP = enemy.getStats().get(Stats.HP);
        if (damage > 0) {
            try {
                newHP = reduceHP(enemy, damage);
                message += String.format("""
                        ¡%s ataca a %s por %d de daño!
                        %s tiene %d HP restantes.
                        """, this.name, enemyName, damage, enemyName, newHP);
            } catch (EnemyDeathException e) {
                enemy.getStats().put(Stats.HP, 0);
                message += String.format("""
                        %s attacks %s for %d damage!
                        %s has 0 HP left.
                        %s has died.
                        """, this.name, enemyName, damage, enemyName, enemyName);
            }
        } else {
            message += String.format("""
                    ¡%s ataca a %s pero no hace daño!
                    %s tiene %d HP restantes.
                    """, this.name, enemyName, enemyName, newHP);
        }
        return message;
    }

    protected final int reduceHP(GameCharacter enemy, int damage) throws EnemyDeathException {
        int newHP = enemy.getStats().get(Stats.HP) - damage;
        enemy.getStats().put(Stats.HP, newHP);
        if (!enemy.isAlive())
            throw new EnemyDeathException();
        return newHP;
    }

    public final String getName() {
        return name;
    }

    public final HashMap<Stats, Integer> getStats() {
        return stats;
    }
}
