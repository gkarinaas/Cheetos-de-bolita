package gui;

import gui.Labels.BarLabel;
import rpg.enu.BarType;
import rpg.enu.Stats;
import gui.Labels.NameLabel;
import gui.Labels.GoldLabel;
import rpg.Player;
import rpg.entities.enemies.Enemy;
import rpg.factory.EnemyFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    private JPanel topPanel, middlePanel, bottomPanel, juegoPanel;
    private JButton button1, b2, b3, atacarButton, habilidadesButton, huirButton;
    private BarLabel lifeLabel, magicLabel, expLabel;
    private JLabel lifeLabelText, magicLabelText, expLabelText;
    private NameLabel nameLabel;
    private GoldLabel goldLabel;
    private JTextArea textDisplay;
    private JScrollPane textScroll;
    private JPanel actionButtonsPanel, messageAreaPanel;

    // Jugador y enemigo
    private Player player;
    private Enemy enemy;

    // JLabel para mostrar la imagen del enemigo
    private JLabel enemyImageLabel;

    public MainWindow() {
        setTitle("RPG Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar jugador
        player = new Player("Cowboy", 100, 20, 10); // Ajustar según constructor de Player

        createUIComponents();
        addComponentsToFrame();
    }

    private void createUIComponents() {
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();
        juegoPanel = crearPanelJuego();
        actionButtonsPanel = new JPanel();
        messageAreaPanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new BorderLayout());

        button1 = new JButton("Botón 1");
        b2 = new JButton("Tiendas");
        b3 = new JButton("Inventario");
        atacarButton = new JButton("Atacar");
        habilidadesButton = new JButton("Habilidades");
        huirButton = new JButton("Salir");

        lifeLabel = new BarLabel(100, 100, BarType.LIFE);
        magicLabel = new BarLabel(30, 100, BarType.MAGIC);
        expLabel = new BarLabel(50, 100, BarType.EXPERIENCE);

        lifeLabelText = new JLabel("Vida:");
        magicLabelText = new JLabel("Magia:");
        expLabelText = new JLabel("Experiencia:");

        lifeLabelText.setForeground(new Color(67, 13, 54));
        magicLabelText.setForeground(new Color(67, 13, 54));
        expLabelText.setForeground(new Color(67, 13, 54));

        nameLabel = new NameLabel(player.getName());
        goldLabel = new GoldLabel("1000");

        textDisplay = new JTextArea();
        textScroll = new JScrollPane(textDisplay);
        initComponents();

        // Asignar acciones a botones
        atacarButton.addActionListener(this::handleAttackAction);
        habilidadesButton.addActionListener(e -> appendText("Habilidades aún no implementadas."));
        huirButton.addActionListener(e -> appendText("¡Huiste del combate!"));
    }

    private void initComponents() {
        textScroll.getViewport().setOpaque(false);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 22));
        textDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));
        textDisplay.setForeground(Color.WHITE);
        textDisplay.setLineWrap(true);
        textDisplay.setWrapStyleWord(true);
        textDisplay.setEditable(false);
    }

    private void addComponentsToFrame() {
        JPanel lifePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lifePanel.add(lifeLabelText);
        lifePanel.add(lifeLabel);

        JPanel magicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        magicPanel.add(magicLabelText);
        magicPanel.add(magicLabel);

        JPanel expPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        expPanel.add(expLabelText);
        expPanel.add(expLabel);

        topPanel.add(lifePanel);
        topPanel.add(magicPanel);
        topPanel.add(expPanel);

        middlePanel.add(button1);
        middlePanel.add(b2);
        middlePanel.add(b3);
        middlePanel.add(atacarButton);
        middlePanel.add(habilidadesButton);
        middlePanel.add(huirButton);
        middlePanel.add(nameLabel);
        middlePanel.add(goldLabel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(middlePanel, BorderLayout.NORTH);
        centerPanel.add(juegoPanel, BorderLayout.CENTER);

        actionButtonsPanel.setLayout(new GridLayout(1, 3));
        actionButtonsPanel.add(atacarButton);
        actionButtonsPanel.add(habilidadesButton);
        actionButtonsPanel.add(huirButton);

        messageAreaPanel.setLayout(new BorderLayout());
        messageAreaPanel.add(actionButtonsPanel, BorderLayout.WEST);
        messageAreaPanel.add(textScroll, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(messageAreaPanel, BorderLayout.SOUTH);
    }

    private JPanel crearPanelJuego() {
        JPanel panelJuego = new JPanel(new BorderLayout());
        panelJuego.setBackground(new Color(255, 233, 245));
        panelJuego.setPreferredSize(new Dimension(400, 500));

        JPanel imagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 0));
        imagesPanel.setOpaque(false);

        // Imagen del jugador
        JLabel imagenJuego = new JLabel(cargarImagenDesdeClasspath("/personaje.png", 80, 80));

        // JLabel para el enemigo
        enemyImageLabel = new JLabel();

        // Agregar las imágenes al panel
        imagesPanel.add(imagenJuego);
        imagesPanel.add(enemyImageLabel);

        panelJuego.add(imagesPanel, BorderLayout.SOUTH);
        return panelJuego;
    }



    private ImageIcon cargarImagenDesdeClasspath(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void handleAttackAction(ActionEvent e) {
        // Generar un enemigo aleatorio
        enemy = EnemyFactory.getEnemy();

        if (enemy == null) {
            appendText("No se pudo generar un enemigo.");
            return;
        }

        // Mostrar la imagen del enemigo
        ImageIcon sprite = enemy.getSprite();
        if (sprite == null) {
            appendText("No se pudo cargar la imagen del enemigo: " + enemy.getName());
        } else {
            // Establecer la imagen del enemigo
            enemyImageLabel.setIcon(sprite);
            enemyImageLabel.revalidate();
            enemyImageLabel.repaint();
        }

        // Mostrar los ataques y resultados
        String playerAttack = player.attack(enemy);
        String enemyAttack = enemy.attack(player);

        appendText("Te enfrentas a: " + enemy.getName());
        appendText(playerAttack);
        appendText(enemyAttack);

        // Actualizar la barra de vida del jugador
        lifeLabel.setValue(player.getStats().get(Stats.HP));
        if (enemy.getStats().get(Stats.HP) <= 0) {
            appendText("¡Has derrotado al enemigo!");
        } else if (player.getStats().get(Stats.HP) <= 0) {
            appendText("¡Has sido derrotado!");
        }
    }

    public void appendText(String text) {
        textDisplay.append(text + "\n");
        textDisplay.setCaretPosition(textDisplay.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
