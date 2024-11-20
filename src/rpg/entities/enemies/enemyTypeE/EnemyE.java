package rpg.entities.enemies.enemyTypeE;

import rpg.entities.enemies.Enemy;
import rpg.enu.EnemyType;

public abstract class EnemyE extends Enemy {
    public EnemyE() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);
    }
}
