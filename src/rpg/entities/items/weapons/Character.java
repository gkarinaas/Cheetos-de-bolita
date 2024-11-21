package rpg.entities.items.weapons;

import rpg.entities.items.weapons.Weapon;

public class Character {
    private String nombre;
    private int puntosDeAtaque;
    private int puntosDeDefensa;
    private Weapon arma;

    // Constructor sin argumentos
    public Character() {
        this.nombre = "Desconocido";
        this.puntosDeAtaque = 10;
        this.puntosDeDefensa = 5;
        this.arma = null;  // Sin arma por defecto
    }

    // Constructor con parámetros
    public Character(String nombre, int puntosDeAtaque, int puntosDeDefensa) {
        this.nombre = nombre;
        this.puntosDeAtaque = puntosDeAtaque;
        this.puntosDeDefensa = puntosDeDefensa;
        this.arma = null;  // Sin arma al principio
    }

    // Método para imprimir los datos del personaje
    public void imprimirDatos() {
        System.out.println("Nombre del personaje: " + nombre);
        System.out.println("Puntos de ataque: " + puntosDeAtaque);
        System.out.println("Puntos de defensa: " + puntosDeDefensa);
        if (arma != null) {
            arma.imprimirDatos();
        } else {
            System.out.println("No tiene arma asignada.");
        }
    }

    // Método para atacar
    public void atacar() {
        if (arma != null) {
            arma.usarArma(this);  // Usar el arma, si existe
        } else {
            System.out.println(nombre + " está atacando a puño limpio causando " + puntosDeAtaque + " puntos de daño.");
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosDeAtaque() {
        return puntosDeAtaque;
    }

    public void setPuntosDeAtaque(int puntosDeAtaque) {
        this.puntosDeAtaque = puntosDeAtaque;
    }

    public int getPuntosDeDefensa() {
        return puntosDeDefensa;
    }

    public void setPuntosDeDefensa(int puntosDeDefensa) {
        this.puntosDeDefensa = puntosDeDefensa;
    }

    public Weapon getArma() {
        return arma;
    }

    public void setArma(Weapon arma) {
        this.arma = arma;
    }
}
