package rpg.exceptions;

/**
 * Excepción que se lanza cuando el inventario está lleno.
 */
public class InventoryFullException extends Exception {
    public InventoryFullException() {
        super("Inventory is full");
    }
}
