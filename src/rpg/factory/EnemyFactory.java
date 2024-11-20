package rpg.factory;

import rpg.entities.enemies.Enemy;
import rpg.entities.enemies.RookieGoblin;
import rpg.entities.enemies.WarriorOrc;
import java.util.Random;

public class EnemyFactory {

    private static final Random random = new Random();

    public static Enemy getEnemy() {
        int randomNumber = random.nextInt(2);  // Ajusta el rango según la cantidad de enemigos

        switch (randomNumber) {
            case 0:
                return new RookieGoblin();  // Retorna un RookieGoblin
            case 1:
                return new WarriorOrc();    // Retorna un WarriorOrc
            default:
                return null;  // Debería ser imposible llegar aquí
        }
    }
}
