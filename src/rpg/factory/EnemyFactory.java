package rpg.factory;

import rpg.entities.enemies.Enemy;
import rpg.entities.enemies.RookieGoblin;
import rpg.entities.enemies.WarriorOrc;

import java.util.Random;

public class EnemyFactory {
    private static final Random RANDOM = new Random();

    public static Enemy getEnemy() {
        /** Lista de enemigos posibles **/
        Enemy[] enemies = {
                new RookieGoblin(),
                new WarriorOrc(),
        };

        /** Selección aleatoria **/
        int index = RANDOM.nextInt(enemies.length);
        return enemies[index];
    }
}
