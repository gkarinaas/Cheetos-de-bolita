package rpg.utils;

import rpg.entities.enemies.Enemy;
import rpg.entities.enemies.enemyTypeA.EnemyA;
import rpg.entities.enemies.enemyTypeC.EnemyC;
import rpg.entities.enemies.enemyTypeB.EnemyB;
import rpg.entities.enemies.enemyTypeC.EnemyC;
import rpg.entities.enemies.enemyTypeD.EnemyD;
import rpg.entities.enemies.enemyTypeE.EnemyE;

import java.util.Random;

public class Randomize {

    public static Enemy getRandomEnemy() {
        Random rand = new Random();
        int enemyType = rand.nextInt(5); // Genera un número entre 0 y 4

        return switch (enemyType) {
            case 0 -> new EnemyA();
            case 1 -> new EnemyB();
            case 2 -> new EnemyC();
            case 3 -> new EnemyD();
            case 4 -> new EnemyE();
            default -> new EnemyC(); // Por defecto si algo falla
        };
    }
}
