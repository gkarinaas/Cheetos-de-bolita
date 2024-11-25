package gui;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        /** Configuración inicial del panel **/
        setLayout(new BorderLayout()); /** Ajustar el diseño a BorderLayout **/
        setBackground(new Color(173, 132, 154)); /** color **/
        setPreferredSize(new Dimension(1400, 100));

        /** Crear panel de botones a la izquierda **/
        JPanel leftButtonsPanel = new JPanel(new GridLayout(2, 1, 10, 10)); /** Dos botones, guardar y salir **/
        leftButtonsPanel.setOpaque(false);

        /** Botón Guardar **/
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveGame()); /** Acción para guardar **/
        leftButtonsPanel.add(saveButton);

        /** Botón Salir **/
        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> exitGame()); /** Acción para salir **/
        leftButtonsPanel.add(exitButton);

        /** Agregar el panel de botones a la izquierda del TopPanel **/
        this.add(leftButtonsPanel, BorderLayout.WEST);

        /** Espacio para otros componentes (por ejemplo, información del jugador) **/
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false); /** Fondo transparente **/
        JLabel titleLabel = new JLabel("Información del jugador", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        centerPanel.add(titleLabel);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    /** Método para guardar el estado del juego **/
    private void saveGame() {
        JOptionPane.showMessageDialog(this, "Juego guardado con éxito.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Método para salir del juego **/
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); /** Salir de la aplicación **/
        }
    }
}
