package rpg.utils;

import rpg.GameCharacter;
import rpg.entities.enemies.Enemy;
import rpg.entities.enemies.enemyTypeC.EnemyC;
import rpg.entities.enemies.enemyTypeB.EnemyB;
import rpg.entities.enemies.enemyTypeD.EnemyD;
import rpg.entities.enemies.enemyTypeE.EnemyE;

import javax.swing.*;
import java.util.Random;

public class Randomize {

    public static Enemy getRandomEnemy() {
        Random rand = new Random();
        int enemyType = rand.nextInt(5); // Genera un número entre 0 y 4

        return switch (enemyType) {
            case 0 -> new WoodBear();
            case 1 -> new EnemyB() {
                @Override
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                @Override
                public void attack(GameCharacter enemy) {

                }
            };
            case 2 -> new EnemyC() {
                @Override
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                @Override
                public void attack(GameCharacter enemy) {

                }
            };
            case 3 -> new EnemyD() {
                @Override
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                @Override
                public void attack(GameCharacter enemy) {

                }
            };
            case 4 -> new EnemyE() {
                @Override
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                @Override
                public void attack(GameCharacter enemy) {

                }
            };
            default -> new EnemyC() {
                @Override
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                @Override
                public void attack(GameCharacter enemy) {

                }
            }; // Por defecto si algo falla
        };
    }
}