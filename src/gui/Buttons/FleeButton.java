package gui.Buttons;

import gui.windows.MainWindow;
import gui.ui.UserHoverUI;

public class FleeButton extends UserButton {

    public FleeButton(MainWindow game) {
        super("Huir");
        this.setUI(new UserHoverUI()); /** Aplica estilo visual **/
        this.setOpaque(false); /** Quita opacidad **/
        this.addActionListener(new gui.Buttons.FleeEvent(game)); /** Asigna evento de huida **/
    }
}
