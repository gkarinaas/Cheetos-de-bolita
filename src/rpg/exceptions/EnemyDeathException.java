package rpg.exceptions;

/**
 * Excepción que se lanza cuando un enemigo muere.
 */
public class EnemyDeathException extends Exception {
    public EnemyDeathException() {
        super("El enemigo ha muerto");
    }
}
