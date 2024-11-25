package gui.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Buttons.AttackButton;
import gui.Buttons.FleeButton;
import gui.ui.UserHoverUI;
import rpg.Player;
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
    private Player player;

    public MainWindow() {
        setTitle("RPG Game");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /** Inicialización de jugador para que pueda interactuar **/
        this.player = new Player("Cowboy", 10, 100, 50);

        createTopPanel();
        createCenterPanel();
        createBottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        /** Mostrar el enemigo inicial **/
        updateEnemyPanel();
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 132, 154));
        topPanel.setPreferredSize(new Dimension(1000, 100));

        /** Panel para las etiquetas superiores **/
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);

        playerNameLevelLabel = new JLabel("Cowboy (Nivel 10)");
        playerGoldLabel = new JLabel("Oro: 1000");
        playerPortraitLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/iconohongo.png"))
                .getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));

        lifeBar = createProgressBar(100, 100, (new Color(252, 232, 242))); /** barra de vida **/
        energyBar = createProgressBar(50, 100, (new Color(116, 143, 86))); /** barra de energia **/
        expBar = createProgressBar(25, 100, (new Color(189, 169, 29))); /** barra de experiencia **/

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

        topPanel.add(leftButtonsPanel, BorderLayout.WEST);
        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.EAST);
    }

    private void createCenterPanel() {
        centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(new Color(197, 165, 183)); /** fondo del panel superior **/

        /** Panel de sprites del jugador **/
        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playerSpriteLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/personaje.png")) /** imagen del personaje **/
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        playerPanel.add(playerSpriteLabel);

        /** Panel de sprites del enemigo **/
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

    private void createBottomPanel() { /** panel de botones **/
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(173, 132, 154)); /** color de fondo de panel **/
        bottomPanel.setPreferredSize(new Dimension(1400, 200));

        /** Panel de botones de acción **/
        JPanel actionPanel = new JPanel(new GridLayout(1, 3));
        actionPanel.setPreferredSize(new Dimension(250, 200));
        actionPanel.setOpaque(false);

        attackButton = new AttackButton(this); /** Botón de ataque **/
        fleeButton = new FleeButton(this);     /** Botón de huir **/

        abilitiesButton = new JButton("Habilidades");
        abilitiesButton.setUI(new UserHoverUI()); /** Estilización **/
        abilitiesButton.setOpaque(false);

        /** Acción de botones **/
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAttackAction();
            }
        });

        abilitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendText("¡Has usado una habilidad!");
            }
        });

        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendText("¡Has huido del combate!");
            }
        });

        actionPanel.add(attackButton);
        actionPanel.add(abilitiesButton);
        actionPanel.add(fleeButton);

        /** Área de mensajes **/
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
        bar.setBackground((new Color(67, 13, 54)));
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(200, 20));
        return bar;
    }

    public void updateEnemyPanel() {
        /** Generar un enemigo de manera aleatoria **/
        currentEnemy = EnemyFactory.getEnemy();

        if (currentEnemy == null) {
            appendText("No se pudo generar un enemigo.");
            return;
        }

        /** Configurar el sprite del enemigo **/
        ImageIcon sprite = currentEnemy.getSprite();
        if (sprite != null) {
            Image scaledImage = sprite.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            enemySpriteLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            enemySpriteLabel.setIcon(null);
        }

        /** Configurar el nombre y la vida del enemigo **/
        enemyNameLabel.setText("Enemigo: " + currentEnemy.getName());
        enemyLifeLabel.setText("Vida: " + currentEnemy.getStats().get(Stats.HP));
    }

    private void handleAttackAction() {
        if (currentEnemy == null) {
            appendText("No hay enemigo para atacar.");
            return;
        }

        /** Simula el daño del jugador al enemigo **/
        int playerAttackDamage = player.getAttack(); /** Obtén el daño de ataque del jugador **/
        int enemyCurrentHP = currentEnemy.getStats().get(Stats.HP);
        enemyCurrentHP -= playerAttackDamage;

        appendText("Atacaste al enemigo y le infligiste " + playerAttackDamage + " de daño.");
        currentEnemy.getStats().put(Stats.HP, enemyCurrentHP); /** Actualiza la vida del  **/

        /** Verifica si el enemigo sigue vivo **/
        if (enemyCurrentHP <= 0) {
            appendText("¡Has derrotado al enemigo!");
            updateEnemyPanel(); // Genera un nuevo enemigo
            return;
        }

        appendText("El enemigo tiene ahora " + enemyCurrentHP + " de vida.");

        /** Si el enemigo sigue vivo, contraatacará **/
        int enemyAttackDamage = currentEnemy.getStats().get(Stats.ATTACK);
        int playerCurrentHP = player.getStats().get(Stats.HP);
        playerCurrentHP -= enemyAttackDamage;

        appendText("El enemigo te contraatacó e infligió " + enemyAttackDamage + " de daño.");
        player.getStats().put(Stats.HP, playerCurrentHP); /** Actualiza la vida del jugador **/

        /** Actualiza la barra de vida del jugador **/
        lifeBar.setValue(playerCurrentHP);

        /** Verifica si el jugador sigue vivo **/
        if (playerCurrentHP <= 0) {
            appendText("¡Has sido derrotado!");
            attackButton.setEnabled(false); /** Desactiva el botón de ataque si el jugador muere **/
        }
    }


    // Método que devuelve el enemigo actual
    public Enemy getCurrentEnemy() {
        return currentEnemy; /** Devuelve el enemigo que está siendo combatido **/
    }

    // Método que devuelve el jugador actual
    public Player getPlayer() {
        return player; /** Devuelve el jugador que está en el juego **/
    }

    /** Método para verificar el estado del juego **/
    public void checkGameStatus() {
        if (player.getStats().get(Stats.HP) <= 0) {
            appendText("¡Has sido derrotado!");
        } else if (currentEnemy == null) {
            appendText("No hay enemigo para luchar.");
        } else {
            appendText("Todo está en orden, el combate continúa.");
        }
    }

    /** Método para iniciar una partida nueva **/
    public void startNewGame() {
        this.player = new Player("Cowboy", 1, 100, 50); /** Reemplaza con tu lógica de reinicio **/
        updateEnemyPanel(); /** Genera un enemigo nuevo **/
        appendText("¡Nueva partida iniciada!");
        updatePlayerBars(); /** Actualiza las barras del jugador **/
    }

    /** Método para cargar una partida desde un archivo **/
    public void loadGame(String saveFilePath) {
        try {
            // Lógica para cargar el estado desde un archivo (por simplicidad, usa datos fijos aquí)
            this.player = new Player("Cowboy Cargado", 5, 80, 40); // Ejemplo de datos cargados
            updateEnemyPanel(); // Genera un enemigo nuevo
            appendText("¡Partida cargada desde " + saveFilePath + "!");
            updatePlayerBars();
        } catch (Exception e) {
            appendText("Error al cargar la partida: " + e.getMessage());
        }
    }

    /** método para guardar la partida **/
    private void saveGame() {
        try {
            /** Simula el guardado **/
            appendText("¡Partida guardada exitosamente!");
        } catch (Exception e) {
            appendText("Error al guardar la partida: " + e.getMessage());
        }
    }

    /** botón para salir del juego **/
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); /** Cierra la aplicación **/
        }
    }

    /** Método para actualizar las barras del jugador **/
    private void updatePlayerBars() {
        lifeBar.setValue(player.getStats().get(Stats.HP)); /** Actualiza barra de vida **/
        energyBar.setValue(player.getStats().get(Stats.ENERGY)); /** Actualiza barra de energía **/
    }

    public void appendText(String text) {
        textDisplay.append(text + "\n");
        textDisplay.setCaretPosition(textDisplay.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
