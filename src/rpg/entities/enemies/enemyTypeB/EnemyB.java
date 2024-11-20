package rpg.entities.enemies.enemyTypeB;

import rpg.entities.enemies.Enemy;
import rpg.enu.EnemyType;

public abstract class EnemyB extends Enemy {
    public EnemyB() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);
    }
}
