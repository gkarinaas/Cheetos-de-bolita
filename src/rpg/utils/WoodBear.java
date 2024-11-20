package rpg.utils;

import rpg.GameCharacter;
import rpg.entities.enemies.Enemy;
import rpg.enu.EnemyType;

import javax.swing.*;

public class WoodBear extends Enemy {
    public WoodBear() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);
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

    protected void initializeStats() {

    }
}
