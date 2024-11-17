package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;
import rpg.enu.Stats;
import rpg.exceptions.EnemyDeathException;
import rpg.utils.cache.ImageCache;

import javax.swing.*;

public class RookieGoblin extends Enemy {

    public RookieGoblin() {
        super();
        ImageCache.addImage("rookie_goblin", "enemies/goblins/rookie_goblin.png");
    }

    @Override
    public void getLoot() {
        System.out.println("The Rookie Goblin drops a small bag of coins.");
    }

    @Override
    protected void initCharacter() {
        this.type = EnemyType.BASIC;
        stats.put(Stats.HP, 30);
        stats.put(Stats.MAX_HP, 30);
        stats.put(Stats.ATTACK, 7);
        stats.put(Stats.DEFENSE, 3);
        stats.put(Stats.EXPERIENCE, 10);
        stats.put(Stats.GOLD, 5);
    }

    @Override
    public String attack(GameCharacter enemy) {
        String message = "";
        int damage = stats.get(Stats.ATTACK) - enemy.getStats().get(Stats.DEFENSE);
        if (damage > 0) {
            try {
                int newHP = reduceHP(enemy, damage);
                message += String.format("""
                        The %s attacks for %d damage!
                        The enemy now has %d HP.
                        """, this.name, damage, newHP);
            } catch (EnemyDeathException e) {
                message += String.format("""
                        The %s attacks for %d damage!
                        The enemy has 0 HP and has died.
                        """, this.name, damage);
            }
        }
        return message;
    }

    @Override
    public ImageIcon getSprite() {
        return new ImageIcon(getClass().getResource("/resources/enemies/goblins/rookie_goblin.png"));
    }
}
