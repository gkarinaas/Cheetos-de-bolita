package rpg.entities.items.weapons;

public class Weapon {
    private String nombre;
    private String descripcion;
    private int puntosDeAtaque;

    // Constructor sin argumentos
    public Weapon(String espada, int i) {
        this.nombre = "Puño limpio";
        this.descripcion = "Sin arma";
        this.puntosDeAtaque = 0;
    }

    // Constructor con parámetros
    public Weapon(String nombre, String descripcion, int puntosDeAtaque) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntosDeAtaque = puntosDeAtaque;
    }

    // Método para imprimir los datos del arma
    public void imprimirDatos() {
        System.out.println("Nombre del arma: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Puntos de ataque: " + puntosDeAtaque);
    }

    // Método para usar el arma
    public void usarArma(Character personaje) {
        if (personaje != null) {
            int totalAtaque = personaje.getPuntosDeAtaque() + this.puntosDeAtaque;
            System.out.println(personaje.getNombre() + " está atacando con " + nombre + " causando un total de " + totalAtaque + " puntos de daño.");
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntosDeAtaque() {
        return puntosDeAtaque;
    }

    public void setPuntosDeAtaque(int puntosDeAtaque) {
        this.puntosDeAtaque = puntosDeAtaque;
    }
}
