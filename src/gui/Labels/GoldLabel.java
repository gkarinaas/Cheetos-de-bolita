package gui.Labels;

import javax.swing.*;
import java.awt.*;

public class GoldLabel extends JLabel {

    private int gold;

    public GoldLabel(String number) {
        this.gold = 0; // Inicializar con 0 oro
        actualizarTexto();
        configurarEstilo();
    }

    public void setGold(int gold) {
        this.gold = gold;
        actualizarTexto();
    }

    public int getGold() {
        return gold;
    }

    private void actualizarTexto() {
        setText("Oro: " + gold);
    }

    private void configurarEstilo() {
        setForeground(new Color(117, 80, 21)); // Color dorado
        setFont(new Font("Arial", Font.BOLD, 14));
    }
}
