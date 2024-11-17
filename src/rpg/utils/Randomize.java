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
                protected void initCharacter() {

                }

                @Override
                public String attack(GameCharacter enemy) {

                    return null;
                }
            };
            case 2 -> new EnemyC() {
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
                protected void initCharacter() {

                }

                @Override
                public String attack(GameCharacter enemy) {

                    return null;
                }
            };
            case 3 -> new EnemyD() {
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
                protected void initCharacter() {
                }

                @Override
                public String attack(GameCharacter enemy) {

                    return null;
                }
            };
            case 4 -> new EnemyE() {
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                protected void initCharacter() {

                }

                @Override
                public String attack(GameCharacter enemy) {

                    return null;
                }
            };
            default -> new EnemyC() {
                protected void initializeStats() {

                }

                @Override
                public ImageIcon getSprite() {
                    return null;
                }

                @Override
                public void getLoot() {

                }

                protected void initCharacter() {

                }

                @Override
                public String attack(GameCharacter enemy) {

                    return null;
                }
            }; // Por defecto si algo falla
        };
    }

    public static Integer getRandomInt(int i, int i1) {
        return null;
    }

    public static boolean getRandomBoolean() {
        return false;
    }
}