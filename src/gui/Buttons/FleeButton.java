package gui.Buttons;

import gui.MainWindow;
import gui.Buttons.FleeEvent;
import gui.ui.UserHoverUI;

public class FleeButton extends UserButton {

    public FleeButton(MainWindow game) {
        super("Huir");
        this.setUI(new UserHoverUI()); // Aplicar estilo visual
        this.setOpaque(false); // Quitar opacidad
        this.addActionListener(new gui.Buttons.FleeEvent(game)); // Asignar evento de huida
    }
}
