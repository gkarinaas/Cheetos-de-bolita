package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import rpg.entities.enemies.Enemy;
import rpg.factory.EnemyFactory;
import rpg.enu.Stats;

public class MainWindow extends JFrame {

    private JPanel topPanel, centerPanel, bottomPanel;
    private JTextArea textDisplay;
    private JScrollPane textScroll;
    private JLabel playerPortraitLabel, playerNameLevelLabel, playerGoldLabel;
    private JProgressBar lifeBar, energyBar, expBar;
    private JLabel playerSpriteLabel, enemySpriteLabel, enemyNameLabel, enemyLifeLabel;
    private JButton attackButton, fleeButton, abilitiesButton;
    private Enemy currentEnemy;

    public MainWindow() {
        setTitle("RPG Game");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createTopPanel();
        createCenterPanel();
        createBottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Mostrar el enemigo inicial
        updateEnemyPanel();
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 233, 245));
        topPanel.setPreferredSize(new Dimension(1400, 150));

        // Panel para las etiquetas superiores
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);

        playerPortraitLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/personaje.png"))
                .getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        playerNameLevelLabel = new JLabel("Cowboy (Nivel 10)");
        playerGoldLabel = new JLabel("Oro: 1000");

        lifeBar = createProgressBar(100, 100, Color.RED);
        energyBar = createProgressBar(50, 100, Color.BLUE);
        expBar = createProgressBar(25, 100, Color.GREEN);

        infoPanel.add(playerPortraitLabel);
        infoPanel.add(playerNameLevelLabel);
        infoPanel.add(playerGoldLabel);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.add(wrapComponentWithLabel("Vida:", lifeBar));
        statsPanel.add(wrapComponentWithLabel("Energía:", energyBar));
        statsPanel.add(wrapComponentWithLabel("Experiencia:", expBar));
        statsPanel.setOpaque(false);

        JPanel leftButtonsPanel = new JPanel(new GridLayout(4, 1));
        leftButtonsPanel.setOpaque(false);
        leftButtonsPanel.add(new JButton("Inventario"));
        leftButtonsPanel.add(new JButton("Tienda"));
        leftButtonsPanel.add(new JButton("Herrero"));
        leftButtonsPanel.add(new JButton("Opciones"));

        topPanel.add(leftButtonsPanel, BorderLayout.WEST);
        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.EAST);
    }

    private void createCenterPanel() {
        centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(new Color(255, 233, 245));

        // Panel de sprites del jugador
        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playerSpriteLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/personaje.png"))
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        playerPanel.add(playerSpriteLabel);

        // Panel de sprites del enemigo
        JPanel enemyPanel = new JPanel();
        enemyPanel.setOpaque(false);
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));

        enemySpriteLabel = new JLabel();
        enemyNameLabel = new JLabel();
        enemyLifeLabel = new JLabel();

        enemyPanel.add(enemyNameLabel);
        enemyPanel.add(enemySpriteLabel);
        enemyPanel.add(enemyLifeLabel);

        centerPanel.add(playerPanel);
        centerPanel.add(enemyPanel);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(255, 233, 245));
        bottomPanel.setPreferredSize(new Dimension(1400, 200));

        // Panel de botones de acción
        JPanel actionPanel = new JPanel(new GridLayout(1, 3));
        actionPanel.setPreferredSize(new Dimension(250, 200));
        actionPanel.setOpaque(false);

        attackButton = new JButton("Atacar");
        attackButton.addActionListener(this::handleAttackAction);

        abilitiesButton = new JButton("Habilidades");
        fleeButton = new JButton("Huir");

        actionPanel.add(attackButton);
        actionPanel.add(abilitiesButton);
        actionPanel.add(fleeButton);

        // Área de mensajes
        textDisplay = new JTextArea();
        textScroll = new JScrollPane(textDisplay);
        configureMessageArea();

        bottomPanel.add(actionPanel, BorderLayout.WEST);
        bottomPanel.add(textScroll, BorderLayout.CENTER);
    }

    private void configureMessageArea() {
        textScroll.getViewport().setOpaque(false);
        textScroll.setBorder(null);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 28));
        textDisplay.setForeground(Color.WHITE);
        textDisplay.setColumns(1);
        textDisplay.setEditable(false);
        textDisplay.setLineWrap(true);
        textDisplay.setWrapStyleWord(true);
    }

    private JPanel wrapComponentWithLabel(String labelText, JProgressBar progressBar) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(progressBar);
        return panel;
    }

    private JProgressBar createProgressBar(int value, int max, Color color) {
        JProgressBar bar = new JProgressBar(0, max);
        bar.setValue(value);
        bar.setForeground(color);
        bar.setBackground(Color.DARK_GRAY);
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(200, 20));
        return bar;
    }

    private void updateEnemyPanel() {
        // Generar un enemigo aleatorio
        currentEnemy = EnemyFactory.getEnemy();

        if (currentEnemy == null) {
            appendText("No se pudo generar un enemigo.");
            return;
        }

        // Configurar el sprite del enemigo
        ImageIcon sprite = currentEnemy.getSprite();
        if (sprite != null) {
            Image scaledImage = sprite.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            enemySpriteLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            enemySpriteLabel.setIcon(null);
        }

        // Configurar el nombre y la vida del enemigo
        enemyNameLabel.setText("Enemigo: " + currentEnemy.getName());
        enemyLifeLabel.setText("Vida: " + currentEnemy.getStats().get(Stats.HP));
    }

    private void handleAttackAction(ActionEvent e) {
        if (currentEnemy == null) {
            appendText("No hay enemigo para atacar.");
            return;
        }

        appendText("Atacaste al enemigo y le infligiste daño.");

        int currentHP = currentEnemy.getStats().get(Stats.HP);
        if (currentHP <= 0) {
            appendText("¡Has derrotado al enemigo!");
            updateEnemyPanel(); // Generar un nuevo enemigo
        } else {
            appendText("El enemigo aún tiene vida.");
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
