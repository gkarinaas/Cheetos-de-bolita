package rpg.entities.enemies.enemyTypeC;

import rpg.entities.enemies.Enemy;
import rpg.enu.EnemyType;

public abstract class EnemyC extends Enemy {
    public EnemyC() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);

    }
}
