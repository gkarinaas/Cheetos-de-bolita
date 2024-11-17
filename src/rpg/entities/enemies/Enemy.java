package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;

import javax.swing.*;

/**
 * Clase abstracta Enemy.
 * Representa a un enemigo en el juego y define métodos y propiedades básicas que todos los enemigos deben tener.
 */
public abstract class Enemy extends GameCharacter {

    protected EnemyType type; // Tipo del enemigo

    /**
     * Constructor para crear un enemigo.
     *
     * @param name Nombre del enemigo
     */
    public Enemy(String name) {
        super();
    }

    public Enemy() {
        super();
    }

    /**
     * Método abstracto para obtener el sprite del enemigo.
     *
     * @return La imagen que representa al enemigo
     */
    public abstract ImageIcon getSprite();

    /**
     * Método abstracto para obtener el botín tras derrotar al enemigo.
     */
    public abstract void getLoot();

    /**
     * Método abstracto para definir el ataque del enemigo.
     *
     * @param enemy El personaje del juego que será atacado
     */
    public abstract void attack(GameCharacter enemy);

    /**
     * Obtiene el tipo del enemigo.
     *
     * @return Tipo del enemigo
     */
    public EnemyType getType() {
        return type;
    }

    /**
     * Establece un nuevo nombre para el enemigo.
     *
     * @param name Nombre del enemigo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método opcional para inicializar atributos específicos del enemigo.
     * Puede ser implementado o sobreescrito en subclases.
     */
    protected void initCharacter() {
        // Por defecto, no hace nada. Las subclases pueden sobrescribir este método.
    }
}
