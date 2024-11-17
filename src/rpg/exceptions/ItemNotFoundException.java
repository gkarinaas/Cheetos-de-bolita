package rpg.exceptions;

/**
 * Excepción que se lanza cuando un ítem no se encuentra en el inventario.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("Item not found");
    }
}
