package gui;

import javax.swing.*;

public class Luchador {
    String nombre;
    int puntosVida;
    int ataque;

    public Luchador(String nombre, int puntosVida, int ataque, Integer integer) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
    }

    void atacar(Luchador oponente, JTextArea textDisplay) {
        // Realizar ataque al oponente
        textDisplay.append(this.nombre + " ataca a " + oponente.nombre + "!\n");
    }

    void recibirAtaque(int ataque, JTextArea textDisplay) {
        this.puntosVida -= ataque;
        textDisplay.append(this.nombre + " recibe " + ataque + " puntos de daño.\n");

        if (this.puntosVida <= 0) {
            textDisplay.append(this.nombre + " ha sido derrotado.\n");
        }
    }

    boolean estaVivo() {
        return this.puntosVida > 0;
    }

    public int getSalud() {
        return 0;
    }
}
