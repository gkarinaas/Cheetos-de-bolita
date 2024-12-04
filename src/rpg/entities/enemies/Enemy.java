package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;

import javax.swing.*;

public abstract class Enemy extends GameCharacter {

    protected EnemyType type;
    protected int maxLife; // Vida máxima del enemigo
    protected int currentLife; // Vida actual del enemigo

    public Enemy(String name, int maxLife, int currentLife, EnemyType type) {
        super();
        this.name = name;
        this.maxLife = maxLife;
        this.currentLife = Math.min(currentLife, maxLife); // Asegura que la vida actual no supere la máxima
        this.type = type;
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void getLoot();

    public abstract String attack(GameCharacter enemy);

    public abstract ImageIcon getSprite();

    // Métodos relacionados con la vida del enemigo
    public int getMaxLife() {
        return maxLife;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int life) {
        this.currentLife = Math.max(0, Math.min(life, maxLife)); // Limita la vida entre 0 y la vida máxima
    }

    // Getter y setter del tipo
    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    // Getter y setter del nombre
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return 0;
    }
}
