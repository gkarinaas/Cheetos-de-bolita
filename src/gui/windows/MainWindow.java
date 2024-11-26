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

    private void createBottomPanel() {
        // Crear el panel principal en la parte inferior
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(173, 132, 154));
        bottomPanel.setPreferredSize(new Dimension(1400, 200));

        // Crear el panel que contendrá los botones
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Espaciado entre los botones
        actionPanel.setOpaque(false);

        // Definir los botones y sus textos explícitamente
        attackButton = new AttackButton(this); // Aseguramos que el texto esté definido en AttackButton
        fleeButton = new JButton("Huir");
        abilitiesButton = new JButton("Habilidades");

        // Personalizar los botones
        styleButton(attackButton);
        styleButton(fleeButton);
        styleButton(abilitiesButton);

        // Añadir ActionListeners a los botones
        attackButton.addActionListener(e -> handleAttack()); // Acción de atacar
        fleeButton.addActionListener(e -> handleFlee());   // Acción de huir
        abilitiesButton.addActionListener(e -> handleAbilities()); // Acción de habilidades

        // Añadir los botones al panel de acciones
        actionPanel.add(attackButton);
        actionPanel.add(abilitiesButton);
        actionPanel.add(fleeButton);

        // Crear área de texto y su JScrollPane
        textDisplay = new JTextArea();
        textScroll = new JScrollPane(textDisplay);
        configureMessageArea(); // Método para configurar el área de texto

        // Añadir el panel de acciones y el área de texto al panel principal
        bottomPanel.add(actionPanel, BorderLayout.WEST);
        bottomPanel.add(textScroll, BorderLayout.CENTER);
    }

    // Acción de ataque
    private void handleAttack() {
        // Generar un daño aleatorio entre 10 y 50
        int damage = (int) (Math.random() * 41) + 10; // Daño entre 10 y 50

        // Cambiar el color del botón para simular un efecto visual
        attackButton.setBackground(Color.RED); // Cambiar a color rojo
        Timer timer = new Timer(500, e -> attackButton.setBackground(null)); // Volver al color original después de 500 ms
        timer.setRepeats(false); // Asegurarse de que solo se ejecute una vez
        timer.start();

        // Mostrar un mensaje en el área de texto con el daño infligido
        textDisplay.setText("¡Has atacado al enemigo y le has infligido " + damage + " puntos de daño!");

        // Si deseas que el área de texto se actualice en tiempo real, puedes agregar un salto de línea y el mensaje
        textDisplay.append("\n¡Es tu turno para seguir luchando!");
    }



    // Acción de huir
    private void handleFlee() {
        System.out.println("¡Huir!");
        // Aquí puedes añadir la lógica de lo que ocurre cuando se presiona "Huir"
        // Por ejemplo, cerrar la ventana o cambiar el estado de la interfaz:
        textDisplay.setText("¡Has huido!");
        // Si quieres cerrar la ventana:
        System.exit(0); // Cierra la aplicación
    }

    // Acción de habilidades
    private void handleAbilities() {
        System.out.println("¡Habilidades!");
        // Aquí puedes añadir la lógica de lo que ocurre cuando se presiona "Habilidades"
        textDisplay.setText("Seleccionaste habilidades.");
    }




    // Función para estilizar los botones
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));  // Usar una fuente de tamaño 16
        button.setPreferredSize(new Dimension(180, 60));  // Tamaño mayor para los botones
        button.setMargin(new Insets(10, 10, 10, 10));  // Ajuste de espaciado interno
        button.setBackground(new Color(100, 100, 100));  // Color de fondo del botón
        button.setForeground(Color.WHITE);  // Color del texto
        button.setFocusPainted(false);  // Eliminar borde de enfoque
        button.setBorder(BorderFactory.createLineBorder(new Color(252, 232, 242), 2));  // Borde del botón
        button.setHorizontalTextPosition(SwingConstants.CENTER);  // Centrado horizontal
        button.setVerticalTextPosition(SwingConstants.CENTER);  // Centrado vertical
    }



    private void configureMessageArea() {
        textDisplay.setEditable(false);  // No permitir la edición del área de texto
        textDisplay.setLineWrap(true);    // Permitir el salto de línea
        textDisplay.setWrapStyleWord(true); // Ajustar las palabras correctamente
        textDisplay.setPreferredSize(new Dimension(400, 100)); // Ajustar el tamaño si es necesario
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