package gui;

import rpg.GameCharacter;
import rpg.enu.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JuegoDePeleaGUI {
    private JFrame frame;
    private JTextArea textDisplay;
    private Luchador luchador1;
    private Luchador luchador2;
    private JButton attackButton;

    public JuegoDePeleaGUI() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoDePeleaGUI().crearVentana());
    }

    public void crearVentana() {
        // Crear la ventana principal
        frame = new JFrame("Juego de Pelea");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Crear el área de texto para mostrar los mensajes de batalla
        textDisplay = new JTextArea();
        textDisplay.setEditable(false);
        textDisplay.setLineWrap(true);
        textDisplay.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textDisplay);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Crear los botones de ataque
        // Dentro de la clase JuegoDePeleaGUI, en el método crearVentana()

// Crear los botones de ataque
        // En tu clase JFrame, configuras el botón
        attackButton = new JButton("Atacar");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la pelea cuando se presiona el botón
                realizarAtaque(); // Llamar al método que gestiona los turnos de los luchadores
            }
        });



        JPanel panelBotones = new JPanel();
        panelBotones.add(attackButton);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Inicializar los luchadores
        GameCharacter currentEnemy = null;
        luchador1 = new Luchador("Luchador 1", 100, 20, currentEnemy.getStats().get(Stats.ATTACK));
        luchador2 = new Luchador("Luchador 2", 100, 15, currentEnemy.getStats().get(Stats.ATTACK));

        // Mostrar la información inicial en el área de texto
        textDisplay.append("Comienza la pelea entre " + luchador1.nombre + " y " + luchador2.nombre + "!\n");
        textDisplay.append("Que comience la batalla...\n");

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private void realizarAtaque() {
        // Deshabilitar el botón de ataque para evitar múltiples clics
        attackButton.setEnabled(false);

        // Crear un SwingWorker para manejar el ataque de forma asíncrona
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Mientras ambos luchadores estén vivos
                while (luchador1.estaVivo() && luchador2.estaVivo()) {
                    // Turno de luchador1
                    luchador1.atacar(luchador2, textDisplay);
                    publish(luchador1.nombre + " ataca a " + luchador2.nombre + "!\n");

                    // Esperar un poco antes de que luchador2 reciba el daño
                    Thread.sleep(1500); // Pausa de 1.5 segundos

                    // Mostrar la reacción de luchador2
                    luchador2.recibirAtaque(luchador1.ataque, textDisplay);
                    publish(luchador2.nombre + " recibe " + luchador1.ataque + " puntos de daño. Ahora tiene "
                            + luchador2.puntosVida + " puntos de vida.\n");

                    if (!luchador2.estaVivo()) {
                        publish(luchador2.nombre + " ha sido derrotado. ¡" + luchador1.nombre + " gana!\n");
                        break; // Terminar la pelea si luchador2 ha sido derrotado
                    }

                    // Esperar un poco antes de que luchador1 ataque nuevamente
                    Thread.sleep(1500); // Pausa de 1.5 segundos

                    // Turno de luchador2
                    luchador2.atacar(luchador1, textDisplay);
                    publish(luchador2.nombre + " ataca a " + luchador1.nombre + "!\n");

                    // Esperar un poco antes de que luchador1 reciba el daño
                    Thread.sleep(1500); // Pausa de 1.5 segundos

                    // Mostrar la reacción de luchador1
                    luchador1.recibirAtaque(luchador2.ataque, textDisplay);
                    publish(luchador1.nombre + " recibe " + luchador2.ataque + " puntos de daño. Ahora tiene "
                            + luchador1.puntosVida + " puntos de vida.\n");

                    if (!luchador1.estaVivo()) {
                        publish(luchador1.nombre + " ha sido derrotado. ¡" + luchador2.nombre + " gana!\n");
                        break; // Terminar la pelea si luchador1 ha sido derrotado
                    }

                    // Actualizar la pantalla después de cada ataque
                    actualizarPantalla();
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String chunk : chunks) {
                    textDisplay.append(chunk);
                    textDisplay.setCaretPosition(textDisplay.getDocument().getLength()); // Desplazar el texto hacia abajo
                }
            }

            @Override
            protected void done() {
                // Reactivar el botón de ataque después de que se termine la pelea
                attackButton.setEnabled(true);
            }
        };

        // Iniciar el worker para manejar el flujo de ataques
        worker.execute();
    }

    private void actualizarPantalla() {
    }

    public String atacar() {
        return null;
    }
}
