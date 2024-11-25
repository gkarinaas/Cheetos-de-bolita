package gui.Labels;

import javax.swing.*;

import gui.ui.BarLabelUI;
import rpg.enu.BarType;

public class BarLabel extends JLabel {

    private int barValue;
    private int maxValue;
    private final BarType type;

    public BarLabel(int value, int maxValue, BarType type) { /** barra de etiquetas **/
        this.barValue = value; /** valor de la barra **/
        this.maxValue = maxValue; /** valor maximo **/
        this.type = type;
        initComponents();
    }

    public void initComponents() { /** inicializar componentes **/
        setBarValue(barValue);
        setUI(new BarLabelUI(type));
    }

    public void setBarValue(int value) { /** valor de la barra **/
        this.barValue = value;
        setText(String.format("%d / %d", value, maxValue));
    }

    public int getBarValue() { /** obtener valor de la barra **/
        return barValue; /** regresar valor de la barra **/
    }

    public int getMaxValue() { /** obtener valor maximo **/
        return maxValue;
    }

    public void setValue(Integer integer) {
    }
}
