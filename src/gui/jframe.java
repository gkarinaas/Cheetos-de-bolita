package gui;

import gui.Buttons.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class jframe extends JFrame {

    /** Constantes de tamaño de ventana y altura de los paneles **/
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BARRA_ESTADO_HEIGHT = 50;
    private static final int PANEL_JUEGO_HEIGHT = 370;
    private static final int BARRA_ACCION_HEIGHT = 50;

    /** Caché para almacenar imágenes **/
    private HashMap<String, ImageIcon> imageCache = new HashMap<>();

    public jframe() {
        /** Configuración de la ventana **/
        setTitle("Juego con imágenes");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /** Crear y agregar los paneles **/
        JPanel barraEstado = crearBarraEstado();
        JPanel panelJuego = crearPanelJuego();
        JPanel barraAccion = crearBarraAccion();

        /** Agregar los paneles a la ventana **/
        add(barraEstado, BorderLayout.NORTH);
        add(panelJuego, BorderLayout.CENTER);
        add(barraAccion, BorderLayout.SOUTH);

        /** Mostrar la ventana **/
        setVisible(true);
    }

    /** Método para crear la barra de estado (sección superior) **/
    private JPanel crearBarraEstado() {
        JPanel barraEstado = new JPanel();
        barraEstado.setBackground(new Color(216, 191, 163)); /** Color cafe **/
        barraEstado.setPreferredSize(new Dimension(WINDOW_WIDTH, BARRA_ESTADO_HEIGHT));

        /** Cargar y agregar imagen a la barra de estado **/
        JLabel imagenEstado = new JLabel(cargarImagenDesdeClasspath("/coraz.png", 30, 30));
        barraEstado.add(imagenEstado);

        /** Etiquetas de estado del juego **/
        JLabel saludJugador = new JLabel("Salud: 100");
        barraEstado.add(saludJugador);

        return barraEstado;
    }

    /** Método para crear el panel de juego (sección central) **/
    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel();
        panelJuego.setBackground(new Color(234, 239, 234)); /** Color gris **/
        panelJuego.setPreferredSize(new Dimension(WINDOW_WIDTH, PANEL_JUEGO_HEIGHT));

        /** Cargar y agregar imagen al panel de juego **/
        JLabel imagenJuego = new JLabel(cargarImagenDesdeClasspath("/walleotravez.png", 400, 500));
        panelJuego.add(imagenJuego);

        return panelJuego;
    }

    /** Método para crear la barra de acción (sección inferior) **/
    private JPanel crearBarraAccion() {
        JPanel barraAccion = new JPanel();
        barraAccion.setBackground(new Color(156, 135, 110)); /** Color cafe **/
        barraAccion.setPreferredSize(new Dimension(WINDOW_WIDTH, BARRA_ACCION_HEIGHT));

        /** Cargar y agregar imagen a la barra de acción **/
        JLabel imagenAccion = new JLabel(cargarImagenDesdeClasspath("/muñequito.png", 30, 40)); /** Ajustar tamaño aquí de la imagen **/
        barraAccion.add(imagenAccion);

        /** Botones de acción personalizados **/
        AttackButton botonAtacar = new AttackButton();
        SaveButton botonGuardar = new SaveButton();
        ExitButton botonSalir = new ExitButton();
        InventoryButton botonInventario = new InventoryButton();
        StatsButton botonEstadisticas = new StatsButton();

        barraAccion.add(botonAtacar);
        barraAccion.add(botonGuardar);
        barraAccion.add(botonSalir);
        barraAccion.add(botonInventario);
        barraAccion.add(botonEstadisticas);

        return barraAccion;
    }

    /** Método para cargar imágenes desde el classpath y redimensionarlas **/
    private ImageIcon cargarImagenDesdeClasspath(String ruta, int ancho, int alto) {
        if (imageCache.containsKey(ruta)) {
            return imageCache.get(ruta); /** Recuperar imagen de la caché **/
        } else {
            java.net.URL imgURL = getClass().getResource(ruta); /** Buscar la imagen en el classpath **/
            if (imgURL != null) {
                ImageIcon imagen = new ImageIcon(imgURL);
                // Redimensionar la imagen
                Image imagenEscalada = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                ImageIcon imagenFinal = new ImageIcon(imagenEscalada);
                imageCache.put(ruta, imagenFinal);
                return imagenFinal;
            } else {
                System.err.println("No se encontró la imagen: " + ruta);
                return null;
            }
        }
    }


    public static void main(String[] args) {
        /** Iniciar la ventana del juego **/
        SwingUtilities.invokeLater(() -> new jframe());
    }
}
