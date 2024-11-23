package gui.Buttons;

import gui.windows.MainWindow;
import gui.jframe;
import gui.ui.UserHoverUI;

public class AttackButton extends UserButton {

    public AttackButton(jframe mainWindow) {
        super("Atacar");
        this.setUI(new UserHoverUI()); // Aplicar estilo visual
        this.setOpaque(false); // Quitar opacidad
        MainWindow game = null;
        this.addActionListener(new AttackEvent(game)); // Asignar evento de ataque
    }

    public AttackButton(MainWindow mainWindow) {
        super(String.valueOf(mainWindow));
    }
}
