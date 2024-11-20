package rpg.entities.enemies.enemyTypeD;

import rpg.entities.enemies.Enemy;
import rpg.enu.EnemyType;

public abstract class EnemyD extends Enemy {
    public EnemyD() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);
    }
}
