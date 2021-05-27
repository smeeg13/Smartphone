package Smartphone.Meteo;

import javax.swing.*;
import java.awt.*;

public class PanelForecast extends JPanel{
    public PanelForecast(JLabel tempOfTheDay, JLabel dayOfTheWeek, JLabel iconDayWeather){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(dayOfTheWeek);
        add(tempOfTheDay);
        add(iconDayWeather);
        setPreferredSize(new Dimension(48,70));
        setBackground(Color.WHITE);
    }
}
