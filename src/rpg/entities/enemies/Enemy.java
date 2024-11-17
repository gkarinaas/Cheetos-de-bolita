package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;

import javax.swing.*;

public abstract class Enemy extends GameCharacter {

    protected EnemyType type;

    public Enemy() {
        super();
    }

    public abstract void getLoot();

    public abstract String attack(GameCharacter enemy);

    public EnemyType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract ImageIcon getSprite();
}
