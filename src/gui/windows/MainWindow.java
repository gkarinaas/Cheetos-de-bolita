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
        topPanel.setBackground(new Color(165, 76, 140));
        topPanel.setPreferredSize(new Dimension(1000, 100));

        /** Panel para las etiquetas superiores **/
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);

        playerNameLevelLabel = new JLabel("Cowboy (Nivel 10)");
        playerGoldLabel = new JLabel("Oro: 1000");
        playerPortraitLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/iconohongo.png"))
                .getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));

        lifeBar = createProgressBar(100, 100, (new Color(194, 236, 171))); /** barra de vida **/
        energyBar = createProgressBar(50, 100, (new Color(211, 203, 135))); /** barra de energía **/
        expBar = createProgressBar(25, 100, (new Color(109, 155, 213))); /** barra de experiencia **/

        infoPanel.add(playerPortraitLabel);
        infoPanel.add(playerNameLevelLabel);
        infoPanel.add(playerGoldLabel);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.add(wrapComponentWithLabel("Vida:", lifeBar));
        statsPanel.add(wrapComponentWithLabel("Energía:", energyBar));
        statsPanel.add(wrapComponentWithLabel("Experiencia:", expBar));
        statsPanel.setOpaque(false);

        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.EAST);
    }


    private void createCenterPanel() {
        centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(new Color(218, 170, 202)); // Fondo del panel central

        // Panel del jugador
        JPanel playerPanel = new JPanel(new GridBagLayout());
        playerPanel.setOpaque(false);

        // Imagen del jugador centrada
        playerSpriteLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/personaje.png"))
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        playerPanel.add(playerSpriteLabel, new GridBagConstraints());

        // Panel del enemigo
        JPanel enemyPanel = new JPanel(new GridBagLayout());
        enemyPanel.setOpaque(false);

        // Imagen del enemigo centrada
        enemySpriteLabel = new JLabel();
        enemyNameLabel = new JLabel("Nombre del enemigo");
        enemyLifeLabel = new JLabel("Vida del enemigo");

        // Agregar los componentes del enemigo centrados
        enemyPanel.add(enemyNameLabel, createGridBagConstraints(0, 0));
        enemyPanel.add(enemySpriteLabel, createGridBagConstraints(0, 1));
        enemyPanel.add(enemyLifeLabel, createGridBagConstraints(0, 2));

        // Añadir los paneles al panel central
        centerPanel.add(playerPanel);
        centerPanel.add(enemyPanel);
    }

    // Método auxiliar para crear GridBagConstraints predeterminados
    private GridBagConstraints createGridBagConstraints(int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centrados
        return gbc;
    }

    private void createBottomPanel() {
        // Crear el panel principal en la parte inferior
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(170, 73, 138));
        bottomPanel.setPreferredSize(new Dimension(1400, 200));

        // Crear el panel que contendrá los botones
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout para una sola línea
        actionPanel.setOpaque(false);

        // Definir los botones y sus textos explícitamente
        attackButton = new JButton("Atacar");
        fleeButton = new JButton("Huir");
        JButton saveButton = new JButton("Guardar");
        JButton startWindowButton = new JButton("Inicio");
        JButton exitButton = new JButton("Salir");

        // Personalizar los botones
        Dimension buttonSize = new Dimension(100, 30); // Tamaño personalizado
        styleSmallButton(attackButton, buttonSize);
        styleSmallButton(fleeButton, buttonSize);
        styleSmallButton(saveButton, buttonSize);
        styleSmallButton(startWindowButton, buttonSize);
        styleSmallButton(exitButton, buttonSize);

        // Añadir ActionListeners a los botones
        attackButton.addActionListener(e -> handleAttack());
        fleeButton.addActionListener(e -> handleFlee());
        saveButton.addActionListener(e -> saveGame());
        startWindowButton.addActionListener(e -> returnToStartWindow());
        exitButton.addActionListener(e -> exitGame());

        // Añadir los botones al panel de acciones
        actionPanel.add(attackButton);
        actionPanel.add(saveButton);
        actionPanel.add(fleeButton);
        actionPanel.add(startWindowButton);
        actionPanel.add(exitButton);

        // Crear área de texto y su JScrollPane
        textDisplay = new JTextArea();
        textScroll = new JScrollPane(textDisplay);
        configureMessageArea();

        // Añadir el panel de acciones y el área de texto al panel principal
        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        bottomPanel.add(textScroll, BorderLayout.SOUTH);
    }


    // Método para personalizar botones con tamaño
    private void styleSmallButton(JButton button, Dimension size) {
        button.setPreferredSize(size);
        button.setBackground(new Color(86, 0, 39)); // Color de fondo
        button.setForeground(Color.WHITE);         // Color del texto
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente más pequeña
    }



    private void styleSmallButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 12)); // Tamaño de fuente más pequeño
        button.setPreferredSize(new Dimension(100, 40));  // Tamaño más pequeño para los botones
        button.setBackground(new Color(82, 3, 47));  // Color de fondo del botón
        button.setForeground(Color.WHITE);  // Color del texto
        button.setFocusPainted(false);  // Eliminar borde de enfoque
        button.setBorder(BorderFactory.createLineBorder(new Color(239, 239, 234), 1));  // Borde del botón
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
    }


    private void returnToStartWindow() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas regresar al menú principal? Se perderá el progreso actual.",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Cierra la ventana actual
            new StartWindow().setVisible(true); // Abre StartWindow
        }
    }



    // Modificar la acción de atacar para hacer el daño aleatorio
    private void handleAttack() {
        if (currentEnemy == null) {
            appendText("No hay enemigo para atacar.");
            return;
        }

        // Infligir daño al enemigo (daño aleatorio entre 10 y 50)
        int playerAttackDamage = (int) (Math.random() * 41) + 10; // Daño entre 10 y 50
        int enemyCurrentHP = currentEnemy.getStats().get(Stats.HP);
        enemyCurrentHP -= playerAttackDamage;

        appendText("¡Atacaste al enemigo y le infligiste " + playerAttackDamage + " puntos de daño!");
        currentEnemy.getStats().put(Stats.HP, enemyCurrentHP); // Actualiza la vida del enemigo

        // Aumentar experiencia del jugador (ejemplo: 10% del daño causado)
        int currentExp = player.getStats().get(Stats.EXPERIENCE);
        currentExp += (int) (playerAttackDamage * 0.1);
        player.getStats().put(Stats.EXPERIENCE, currentExp);
        expBar.setValue(currentExp); // Actualiza la barra de experiencia

        // Verificar si el enemigo fue derrotado
        if (enemyCurrentHP <= 0) {
            appendText("¡Has derrotado al enemigo!");
            attackButton.setEnabled(false);  // Desactivar el botón de atacar
            updateEnemyPanel(); // Genera un nuevo enemigo
            return;
        }

        appendText("El enemigo tiene ahora " + enemyCurrentHP + " puntos de vida.");

        // El enemigo contraataca y el jugador recibe daño (daño aleatorio entre 5 y 25)
        int enemyAttackDamage = (int) (Math.random() * 21) + 5; // Daño entre 5 y 25
        int playerCurrentHP = player.getStats().get(Stats.HP);
        playerCurrentHP -= enemyAttackDamage;

        appendText("El enemigo te atacó y te infligió " + enemyAttackDamage + " puntos de daño.");
        player.getStats().put(Stats.HP, playerCurrentHP); // Actualiza la vida del jugador
        lifeBar.setValue(playerCurrentHP); // Actualiza la barra de vida

        // Reducir energía del jugador (ejemplo: 5 puntos por ataque recibido)
        int currentEnergy = player.getStats().get(Stats.ENERGY);
        currentEnergy -= 5;
        player.getStats().put(Stats.ENERGY, currentEnergy);
        energyBar.setValue(currentEnergy); // Actualiza la barra de energía

        // Verificar si el jugador fue derrotado
        if (playerCurrentHP <= 0) {
            appendText("¡Has sido derrotado!");
            attackButton.setEnabled(false); // Desactiva el botón de ataque si el jugador muere
        }
    }


    private void endGame(String message) {
        // Mostrar el mensaje de fin de juego y deshabilitar la interacción
        appendText(message);
        textDisplay.setEditable(false);
        attackButton.setEnabled(false); // Desactivar el botón de ataque
        fleeButton.setEnabled(false);   // Desactivar el botón de huir
    }

    // Modificar la función que crea el enemigo para darle vida aleatoria
    public void updateEnemyPanel() {
        /** Generar un enemigo de manera aleatoria **/
        currentEnemy = EnemyFactory.getEnemy();

        if (currentEnemy == null) {
            appendText("No se pudo generar un enemigo.");
            return;
        }

        // Asignar vida aleatoria entre 50 y 150 puntos
        int randomLife = (int) (Math.random() * 101) + 50; // Entre 50 y 150
        currentEnemy.getStats().put(Stats.HP, randomLife);

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


    // Método para guardar la partida
    private void saveGame() {
        try {
            // Simula el guardado de la partida
            appendText("¡Partida guardada exitosamente!");
        } catch (Exception e) {
            appendText("Error al guardar la partida: " + e.getMessage());
        }
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
        button.setBackground(new Color(82, 3, 47));  // Color de fondo del botón
        button.setForeground(Color.WHITE);  // Color del texto
        button.setFocusPainted(false);  // Eliminar borde de enfoque
        button.setBorder(BorderFactory.createLineBorder(new Color(239, 239, 234), 2));  // Borde del botón
        button.setHorizontalTextPosition(SwingConstants.CENTER);  // Centrado horizontal
        button.setVerticalTextPosition(SwingConstants.CENTER);  // Centrado vertical
    }

    private void configureMessageArea() {
        textDisplay.setEditable(false);  // No permitir la edición del área de texto
        textDisplay.setLineWrap(true);    // Permitir el salto de línea
        textDisplay.setWrapStyleWord(true); // Ajustar las palabras correctamente
        textDisplay.setPreferredSize(new Dimension(400, 100)); // Ajustar el tamaño si es necesario

        // Asegúrate de que el JScrollPane tenga barras de desplazamiento automáticas
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Siempre mostrar la barra vertical
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Evitar barra horizontal
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
        bar.setBackground((new Color(57, 37, 6)));
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(200, 20));
        return bar;
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