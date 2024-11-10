package gui;

import gui.Labels.BarLabel;
import rpg.enu.BarType;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel topPanel, middlePanel, bottomPanel, juegoPanel;  // Añadido juegoPanel

    private JButton button1, b2, b3, atacarButton, habilidadesButton, huirButton;

    // Las barras de estado
    private BarLabel lifeLabel, magicLabel, expLabel;

    // Etiquetas de texto para cada barra
    private JLabel lifeLabelText, magicLabelText, expLabelText;

    public MainWindow() {
        setTitle("RPG Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createUIComponents();
        addComponentsToFrame();
    }

    private void createUIComponents() {
        // Instancia los paneles
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();
        juegoPanel = crearPanelJuego();  // Inicializar el panel de juego

        // Establece los layouts de los paneles
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Alineación a la izquierda
        middlePanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());

        // Crear los botones
        button1 = new JButton("Botón 1");
        b2 = new JButton("Tiendas");
        b3 = new JButton("Inventario");
        atacarButton = new JButton("Atacar");
        habilidadesButton = new JButton("Habilidades");
        huirButton = new JButton("Salir");

        // Crear las barras de estado
        lifeLabel = new BarLabel(100, 100, BarType.LIFE);
        magicLabel = new BarLabel(30, 100, BarType.MAGIC);
        expLabel = new BarLabel(50, 100, BarType.EXPERIENCE);

        // Reducir el tamaño de las barras
        lifeLabel.setPreferredSize(new Dimension(150, 20));
        magicLabel.setPreferredSize(new Dimension(150, 20));
        expLabel.setPreferredSize(new Dimension(150, 20));

        // Crear las etiquetas de texto
        lifeLabelText = new JLabel("Vida:");
        magicLabelText = new JLabel("Magia:");
        expLabelText = new JLabel("Experiencia:");

        // Establecer el color de texto para las etiquetas
        lifeLabelText.setForeground(new Color(57, 37, 6));
        magicLabelText.setForeground(new Color(57, 37, 6));
        expLabelText.setForeground(new Color(57, 37, 6));
    }

    private void addComponentsToFrame() {
        // Agrega las barras de estado y las etiquetas al panel superior (topPanel)
        JPanel lifePanel = new JPanel();
        lifePanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Alineación a la izquierda
        lifePanel.add(lifeLabelText);
        lifePanel.add(lifeLabel);

        JPanel magicPanel = new JPanel();
        magicPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Alineación a la izquierda
        magicPanel.add(magicLabelText);
        magicPanel.add(magicLabel);

        JPanel expPanel = new JPanel();
        expPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Alineación a la izquierda
        expPanel.add(expLabelText);
        expPanel.add(expLabel);

        // Agregar las barras de estado a topPanel (con separación reducida)
        topPanel.add(lifePanel);
        topPanel.add(magicPanel);
        topPanel.add(expPanel);

        // Agrega los botones al panel del medio
        middlePanel.add(button1);
        middlePanel.add(b2);
        middlePanel.add(b3);
        middlePanel.add(atacarButton);
        middlePanel.add(habilidadesButton);
        middlePanel.add(huirButton);

        // Crear un contenedor en el centro para agregar tanto el panel de botones como el panel de juego
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());  // Usar BorderLayout para dividir espacio
        centerPanel.add(middlePanel, BorderLayout.NORTH);  // Colocar los botones en la parte superior
        centerPanel.add(juegoPanel, BorderLayout.CENTER);  // Colocar el panel de juego en el centro

        // Añade los paneles a la ventana principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);  // Usar el contenedor para centrar los elementos
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel();
        panelJuego.setBackground(new Color(234, 239, 234)); /** Color gris **/
        panelJuego.setPreferredSize(new Dimension(400, 500));

        /** Cargar y agregar imagen al panel de juego **/
        JLabel imagenJuego = new JLabel(cargarImagenDesdeClasspath("/walleotravez.png", 300, 400));
        panelJuego.add(imagenJuego);

        return panelJuego;
    }

    // Método para cargar imágenes desde el classpath
    private ImageIcon cargarImagenDesdeClasspath(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
