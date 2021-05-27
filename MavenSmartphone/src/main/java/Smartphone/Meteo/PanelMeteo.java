package Smartphone.Meteo;

import javax.swing.*;
import java.awt.*;

public class PanelMeteo extends JPanel{
    public PanelMeteo(JLabel country, JLabel sunrise, JLabel sunset, JLabel tempMin, JLabel tempMax){
        setLayout(null);
        country.setBounds(50,100,100,30);
        add(country);
        add(sunrise);
        add(sunset);
        add(tempMin);add(tempMax);
        setPreferredSize(new Dimension(380,400));
        setOpaque(true);
    }
}
