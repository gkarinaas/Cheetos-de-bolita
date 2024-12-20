package gui.Labels;

import javax.swing.*;
import java.awt.*;

public class NameLabel extends JLabel {

    private String playerName;

    public NameLabel(String playerName) { /** etiqueta de nombre **/
        this.playerName = playerName;
        actualizarTexto();
        configurarEstilo();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        actualizarTexto();
    }

    public String getPlayerName() {
        return playerName;
    }

    private void actualizarTexto() {
        setText(playerName);
    }

    private void configurarEstilo() {
        setForeground(new Color(165, 76, 140)); /** Color azul **/
        setFont(new Font("Arial", Font.BOLD, 14)); /** fuente **/
    }
}
