package gui;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {
    private JPanel mainPanel;

    public StartWindow() {
        setTitle("Java RPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear los componentes principales de la interfaz
        createUIComponents();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void createUIComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(218, 195, 214));

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Java RPG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Pixelated", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        centerPanel.setBackground(new Color(218, 170, 202));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        for (int i = 0; i < 5; i++) {
            // Etiqueta para mostrar información del archivo
            JLabel fileLabel = new JLabel((i < 2 ? "Jugador 1" + (i + 2) : "-- Vacío --"), SwingConstants.CENTER);
            fileLabel.setFont(new Font("Pixelated", Font.BOLD, 18));
            fileLabel.setForeground(Color.WHITE);
            fileLabel.setOpaque(false);
            fileLabel.setBorder(BorderFactory.createLineBorder(new Color(170, 73, 138), 2, true));

            // Botón para cargar partida
            JButton loadButton = createButton("Cargar Partida");
            loadButton.setEnabled(i < 2); // Habilitar solo si hay una partida guardada

            // Botón para nueva partida
            JButton newGameButton = createButton("Nueva Partida");

            // Añadir al panel
            centerPanel.add(fileLabel);
            centerPanel.add(loadButton);
            centerPanel.add(newGameButton);
        }

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Pixelated", Font.BOLD, 14));
        button.setBackground(new Color(67, 13, 54));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(131, 1, 101), 2, true));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartWindow window = new StartWindow();
            window.setVisible(true);
        });
    }
}
