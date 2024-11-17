package gui;

import gui.Labels.BarLabel;
import rpg.enu.BarType;
import gui.Labels.NameLabel;
import gui.Labels.GoldLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel topPanel, middlePanel, bottomPanel, juegoPanel;
    private JButton button1, b2, b3, atacarButton, habilidadesButton, huirButton;
    private BarLabel lifeLabel, magicLabel, expLabel;
    private JLabel lifeLabelText, magicLabelText, expLabelText;
    private NameLabel nameLabel;
    private GoldLabel goldLabel;

    // Nuevos componentes para el área de mensajes y botones de acción
    private JTextArea textDisplay;
    private JScrollPane textScroll;
    private JPanel actionButtonsPanel;
    private JPanel messageAreaPanel;

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
        juegoPanel = crearPanelJuego();

        // Instancia de los paneles para el área de mensajes y botones de acción
        actionButtonsPanel = new JPanel();
        messageAreaPanel = new JPanel();

        // Establece los layouts de los paneles
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new BorderLayout()); // Modificamos el layout del panel inferior

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

        // Crear las etiquetas de texto
        lifeLabelText = new JLabel("Vida:");
        magicLabelText = new JLabel("Magia:");
        expLabelText = new JLabel("Experiencia:");

        // Establecer el color de texto para las etiquetas
        lifeLabelText.setForeground(new Color(67, 13, 54));
        magicLabelText.setForeground(new Color(67, 13, 54));
        expLabelText.setForeground(new Color(67, 13, 54));

        // Crear etiquetas personalizadas para nombre y oro
        nameLabel = new NameLabel("Cowboy");
        goldLabel = new GoldLabel("1000");

        // Crear el área de mensajes
        textDisplay = new JTextArea();
        textScroll = new JScrollPane(textDisplay);
        initComponents(); // Llamar para inicializar el área de texto
    }

    private void initComponents() {
        // Inicializar el área de texto
        textScroll.getViewport().setOpaque(false);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 22));
        textDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));
        textDisplay.setForeground(Color.WHITE);
        textDisplay.setLineWrap(true);
        textDisplay.setWrapStyleWord(true);
        textDisplay.setEditable(false);  // No permitir la edición por parte del usuario
    }

    private void addComponentsToFrame() {
        // Agrega las barras de estado y las etiquetas al panel superior (topPanel)
        JPanel lifePanel = new JPanel();
        lifePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lifePanel.add(lifeLabelText);
        lifePanel.add(lifeLabel);

        JPanel magicPanel = new JPanel();
        magicPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        magicPanel.add(magicLabelText);
        magicPanel.add(magicLabel);

        JPanel expPanel = new JPanel();
        expPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        expPanel.add(expLabelText);
        expPanel.add(expLabel);

        // Agregar las barras de estado a topPanel
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

        // Agrega las etiquetas de nombre y oro a middlePanel o donde las desees
        middlePanel.add(nameLabel);  // Agrega la etiqueta del nombre
        middlePanel.add(goldLabel);  // Agrega la etiqueta del oro

        // Crear un contenedor en el centro para agregar tanto el panel de botones como el panel de juego
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(middlePanel, BorderLayout.NORTH);
        centerPanel.add(juegoPanel, BorderLayout.CENTER);

        // Configurar los paneles para el área de mensajes y los botones de acción
        actionButtonsPanel.setLayout(new GridLayout(1, 3));  // Crear una fila de botones
        actionButtonsPanel.add(atacarButton);
        actionButtonsPanel.add(habilidadesButton);
        actionButtonsPanel.add(huirButton);

        // Añadir los botones y el área de mensajes en el panel inferior
        messageAreaPanel.setLayout(new BorderLayout());
        messageAreaPanel.add(actionButtonsPanel, BorderLayout.WEST);
        messageAreaPanel.add(textScroll, BorderLayout.CENTER);

        // Añade los paneles a la ventana principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(messageAreaPanel, BorderLayout.SOUTH);  // Agregar el panel de mensajes al sur
    }

    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel();
        panelJuego.setBackground(new Color(255, 233, 245));
        panelJuego.setPreferredSize(new Dimension(400, 500));
        panelJuego.setLayout(new BorderLayout());

        // Crear un panel que contendrá las imágenes
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 0)); // Mayor espaciado horizontal
        imagesPanel.setOpaque(false);

        // Agregar las dos imágenes al panel de imágenes
        JLabel imagenJuego = new JLabel(cargarImagenDesdeClasspath("/personaje.png", 80, 80));
        JLabel segundaImagen = new JLabel(cargarImagenDesdeClasspath("/RookieGoblin.png", 80, 80));

        imagesPanel.add(imagenJuego);
        imagesPanel.add(segundaImagen);

        // Agregar las imágenes en la parte inferior del panel
        panelJuego.add(imagesPanel, BorderLayout.SOUTH);

        return panelJuego;
    }

    private ImageIcon cargarImagenDesdeClasspath(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // Función para mostrar los mensajes en el JTextArea
    public void appendText(String text) {
        // Añadimos el texto al textDisplay
        textDisplay.append(text + "\n");
        // Hacemos que el textDisplay se posicione en la última línea
        textDisplay.setCaretPosition(textDisplay.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
