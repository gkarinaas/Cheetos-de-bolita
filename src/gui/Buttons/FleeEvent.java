package gui.Buttons;

import gui.windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FleeEvent implements ActionListener {

    private final MainWindow game;

    public FleeEvent(MainWindow game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.appendText("¡Has huido del combate!");
        game.dispose(); // Cerrar la ventana de combate
    }
}
