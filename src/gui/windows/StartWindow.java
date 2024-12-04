package gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    private JPanel mainPanel;

    public StartWindow() {
        setTitle("RPG");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear los componentes principales de la interfaz
        createUIComponents();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void createUIComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(165, 76, 140));

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Cowboy", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Pixelated", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel central con 4 filas en lugar de 5
        JPanel centerPanel = new JPanel(new GridLayout(4, 3, 10, 10)); // 4 filas
        centerPanel.setBackground(new Color(165, 76, 140));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        for (int i = 0; i < 4; i++) { // Cambiar a 4 iteraciones
            // Etiqueta para mostrar información del archivo
            JLabel fileLabel = new JLabel((i < 1 ? "Cowboy " + (i + 1) : "-- Personaje --"), SwingConstants.CENTER);
            fileLabel.setFont(new Font("Pixelated", Font.BOLD, 16)); // Fuente más pequeña
            fileLabel.setForeground(Color.WHITE);
            fileLabel.setOpaque(false);
            fileLabel.setBorder(BorderFactory.createLineBorder(new Color(82, 3, 47), 2, true));

            // Botón para cargar partida
            JButton loadButton = createButton("Cargar Partida");
            loadButton.setEnabled(i < 1); // Habilitar solo si hay una partida guardada

            // Botón para nueva partida
            JButton newGameButton = createButton("Nueva Partida");

            // Acción para cargar partida
            loadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openMainWindow();
                }
            });

            // Acción para nueva partida
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openMainWindow();
                }
            });

            // Añadir al panel
            centerPanel.add(fileLabel);
            centerPanel.add(loadButton);
            centerPanel.add(newGameButton);
        }

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Pixelated", Font.PLAIN, 12)); // Fuente más pequeña
        button.setBackground(new Color(82, 3, 47));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(218, 170, 202), 2, true));
        button.setPreferredSize(new Dimension(80, 25)); // Tamaño reducido
        return button;
    }

    private void openMainWindow() {
        // Ocultar la ventana actual
        this.dispose();
        // Abrir la ventana principal
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartWindow window = new StartWindow();
            window.setVisible(true);
        });
    }
}