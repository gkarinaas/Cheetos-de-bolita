package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/** Clase principal para la ventana del juego **/
public class jframe extends JFrame {

    /** Constantes de tamaño de ventana y altura de los paneles **/
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600; /**Suma de las alturas de los paneles **/
    private static final int BARRA_ESTADO_HEIGHT = 40;
    private static final int PANEL_JUEGO_HEIGHT = 370;
    private static final int BARRA_ACCION_HEIGHT = 40;

    /** Caché para almacenar imágenes **/
    private HashMap<String, ImageIcon> imageCache = new HashMap<>();

    public jframe() {
        /**Configuración de la ventana**/
        setTitle("monito");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /**Crear y agregar los paneles con tamaños específicos**/
        JPanel barraEstado = crearBarraEstado();
        JPanel panelJuego = crearPanelJuego();
        JPanel barraAccion = crearBarraAccion();

        /** Agregar los paneles a la ventana **/
        add(barraEstado, BorderLayout.NORTH);
        add(panelJuego, BorderLayout.CENTER);
        add(barraAccion, BorderLayout.SOUTH);

        /**Mostrar la ventana**/
        setVisible(true);
    }

    /**Método para crear la barra de estado (sección superior)**/
    private JPanel crearBarraEstado() {
        JPanel barraEstado = new JPanel();
        barraEstado.setBackground(new Color(26, 169, 21)); /** color verde**/
        barraEstado.setPreferredSize(new Dimension(WINDOW_WIDTH, BARRA_ESTADO_HEIGHT));

        /**Etiquetas de estado del juego**/
        JLabel saludJugador = new JLabel("Salud: 100");
        barraEstado.add(saludJugador);

        return barraEstado;
    }

    /**Método para crear el panel de juego (sección central)**/
    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel();
        panelJuego.setBackground(new Color(142, 151, 141)); /** color gris **/
        panelJuego.setPreferredSize(new Dimension(WINDOW_WIDTH, PANEL_JUEGO_HEIGHT));

        /** cargar una imagen en el panel de juego **/
        JLabel jugador = new JLabel(cargarImagen("monito.png"));
        panelJuego.add(jugador);

        return panelJuego;
    }

    /** Método para crear la barra de acción (sección inferior) **/
    private JPanel crearBarraAccion() {
        JPanel barraAccion = new JPanel();
        barraAccion.setBackground(new Color(67, 35, 61)); /** morado **/
        barraAccion.setPreferredSize(new Dimension(WINDOW_WIDTH, BARRA_ACCION_HEIGHT));

        /** botones de acción **/
        JButton botonAtacar = new JButton("Atacar");
        JButton botonDefender = new JButton("Defender");
        JButton botonUsarObjeto = new JButton("Usar Objeto");

        barraAccion.add(botonAtacar);
        barraAccion.add(botonDefender);
        barraAccion.add(botonUsarObjeto);

        return barraAccion;
    }

    /**Método para cargar imágenes utilizando una caché**/
    private ImageIcon cargarImagen(String ruta) {
        if (imageCache.containsKey(ruta)) {
            return imageCache.get(ruta); /** Recuperar imagen de la caché **/
        } else {
            ImageIcon imagen = new ImageIcon(ruta); /** Cargar imagen desde disco **/
            imageCache.put(ruta, imagen); /** Almacenar en la caché **/
            return imagen;
        }
    }

    public static void main(String[] args) {
        /** Iniciar la ventana del juego **/
        SwingUtilities.invokeLater(() -> new jframe());
    }
}
