package rpg.entities.items.misc;

import rpg.entities.items.Item;

public abstract class Misc extends Item {
    private Integer quantity;

    public Misc(String name) {
        super(name);
    }

    public boolean isStackable() {
        return false;
    }

    public Integer getQuantity() {
        return this.quantity;
    }


    public void decreaseQuantity(int i) {
    }
}