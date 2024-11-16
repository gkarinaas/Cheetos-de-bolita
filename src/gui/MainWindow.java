package gui;

import gui.Labels.BarLabel;
import rpg.enu.BarType;
import gui.Labels.NameLabel;
import gui.Labels.GoldLabel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel topPanel, middlePanel, bottomPanel, juegoPanel;
    private JButton button1, b2, b3, atacarButton, habilidadesButton, huirButton;

    // Las barras de estado
    private BarLabel lifeLabel, magicLabel, expLabel;

    // Etiquetas de texto para cada barra
    private JLabel lifeLabelText, magicLabelText, expLabelText;

    // Etiquetas personalizadas
    private NameLabel nameLabel;   // Agregar etiqueta de nombre
    private GoldLabel goldLabel;   // Agregar etiqueta de oro

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

        // Establece los layouts de los paneles
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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

        // Crear las etiquetas de texto
        lifeLabelText = new JLabel("Vida:");
        magicLabelText = new JLabel("Magia:");
        expLabelText = new JLabel("Experiencia:");

        // Establecer el color de texto para las etiquetas
        lifeLabelText.setForeground(new Color(117, 80, 21));
        magicLabelText.setForeground(new Color(67, 13, 54));
        expLabelText.setForeground(new Color(67, 13, 54));

        // Crear etiquetas personalizadas para nombre y oro
        nameLabel = new NameLabel("Cowboy");
        goldLabel = new GoldLabel("1000");  // Aquí pasas el valor de oro al constructor
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

        // Añade los paneles a la ventana principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel();
        panelJuego.setBackground(new Color(216, 191, 163));
        panelJuego.setPreferredSize(new Dimension(400, 500));

        JLabel imagenJuego = new JLabel(cargarImagenDesdeClasspath("/walleotravez.png", 300, 400));
        panelJuego.add(imagenJuego);

        return panelJuego;
    }

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
