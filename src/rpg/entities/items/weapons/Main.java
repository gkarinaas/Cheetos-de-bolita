package rpg.entities.items.weapons;

public class Main {
    public static void main(String[] args) {
        // Crear un personaje sin arma
        Character personaje = new Character("Carlos", 20, 10);
        personaje.imprimirDatos();
        personaje.atacar();  // Atacar sin arma

        // Crear un arma
        Weapon espada = new Weapon("Espada de fuego", "Una espada mágica", 30);

        // Asignar el arma al personaje
        personaje.setArma(espada);

        // Imprimir los datos del personaje con el arma
        System.out.println("\nDespués de asignar el arma:");
        personaje.imprimirDatos();

        // Atacar con el arma
        personaje.atacar();
    }
}
