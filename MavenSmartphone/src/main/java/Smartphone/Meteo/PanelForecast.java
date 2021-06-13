package Smartphone.Meteo;

import javax.swing.*;
import java.awt.*;


/**
 * This class is used to create a panel for a 5 days forecast
 * @author Thomas Cheseaux
 */
public class PanelForecast extends JPanel{

    /**
     * This constructor build the weekly forecast
     * @param tempOfTheDay the temperature of the day
     * @param dayOfTheWeek the day of the week
     * @param iconDayWeather the icon for the day
     */
    public PanelForecast(JLabel tempOfTheDay, JLabel dayOfTheWeek, JLabel iconDayWeather){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(dayOfTheWeek);
        add(tempOfTheDay);
        add(iconDayWeather);
        setPreferredSize(new Dimension(48,70));
        setBackground(Color.WHITE);
    }
}
