package Smartphone.Meteo;

import javax.swing.*;
import java.awt.*;

public class PanelMeteo extends JPanel{
    public PanelMeteo(JLabel country, JLabel sunrise, JLabel sunset, JLabel tempMin, JLabel tempMax){
        add(country);
        add(sunrise);
        add(sunset);
        add(tempMin);add(tempMax);
        setPreferredSize(new Dimension(100,200));
        setBackground(Color.WHITE);
    }
}
