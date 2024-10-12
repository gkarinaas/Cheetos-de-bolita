package rpg;

import javax.swing.JOptionPane;
import java.util.ArrayList;

abstract class Item {
    private final String nombre;
    private final String descripcion;

    public Item(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return nombre.equals(item.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}

class ItemMiscelaneo extends Item {
    public ItemMiscelaneo(String nombre, String descripcion) {
        super(nombre, descripcion);
    }
}

class Armadura extends Item {
    private final int defensa;

    public Armadura(String nombre, String descripcion, int defensa) {
        super(nombre, descripcion);
        this.defensa = defensa;
    }

    public int getDefensa() {
        return defensa;
    }
}

class Arma extends Item {
    private final int ataque;

    public Arma(String nombre, String descripcion, int ataque) {
        super(nombre, descripcion);
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }
}

class Inventario {
    private final ArrayList<Item> items;

    public Inventario() {
        items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
            JOptionPane.showMessageDialog(null, "El item ya está en el inventario.");
        }
    }

    public void mostrarInventario() {
        StringBuilder inventarioStr = new StringBuilder("Inventario:\n");
        for (Item item : items) {
            inventarioStr.append(item.getNombre())
                    .append(": ")
                    .append(item.getDescripcion())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, inventarioStr.toString());
    }
}

public class Game {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Nuevos items
        Arma espadaHierro = new Arma("Espada de Hierro", "Espada estándar de hierro.", 15);
        Armadura cuero = new Armadura("Armadura de Cuero", "Protección ligera.", 8);
        ItemMiscelaneo pocionSalud = new ItemMiscelaneo("Poción de Salud", "Restaura 20 puntos de vida.");
        Arma espadaPlata = new Arma("Espada de Plata", "Espada efectiva contra criaturas sobrenaturales.", 18);
        Armadura placas = new Armadura("Armadura de Placas", "Protección pesada.", 20);
        ItemMiscelaneo pocionMana = new ItemMiscelaneo("Poción de Mana", "Restaura 30 puntos de mana.");
        Arma espadaMagica = new Arma("Espada Mágica", "Espada imbuida con magia.", 25);
        Armadura dragon = new Armadura("Armadura de Dragón", "Protección suprema.", 30);
        ItemMiscelaneo elixirFuerza = new ItemMiscelaneo("Elixir de Fuerza", "Aumenta la fuerza temporalmente.");
        Arma dagaEnvenenada = new Arma("Daga Envenenada", "Causa daño por veneno.", 12);
        Armadura escudoMithril = new Armadura("Escudo de Mithril", "Escudo impenetrable.", 25);
        ItemMiscelaneo antidoto = new ItemMiscelaneo("Antídoto", "Cura envenenamiento.");
        Arma hachaBatalla = new Arma("Hacha de Batalla", "Gran poder de ataque.", 20);
        Armadura cascoGladiador = new Armadura("Casco de Gladiador", "Protección de gladiador.", 15);
        ItemMiscelaneo piedraFilosofal = new ItemMiscelaneo("Piedra Filosofal", "Otorga poder alquímico.");

        inventario.agregarItem(espadaHierro);
        inventario.agregarItem(cuero);
        inventario.agregarItem(pocionSalud);
        inventario.agregarItem(espadaPlata);
        inventario.agregarItem(placas);
        inventario.agregarItem(pocionMana);
        inventario.agregarItem(espadaMagica);
        inventario.agregarItem(dragon);
        inventario.agregarItem(elixirFuerza);
        inventario.agregarItem(dagaEnvenenada);
        inventario.agregarItem(escudoMithril);
        inventario.agregarItem(antidoto);
        inventario.agregarItem(hachaBatalla);
        inventario.agregarItem(cascoGladiador);
        inventario.agregarItem(piedraFilosofal);

        // Mostrar el inventario
        inventario.mostrarInventario();
    }
}
