package Smartphone.Meteo;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;

public class PanelMeteo extends JPanel{

    private JLabel jlSunrise;
    private JLabel jlSunset ;

    /**
     * This constructor build the JPanel for the weather display
     * @param city location we want to show
     * @param country country of the city
     * @param sunrise time of the Sunrise
     * @param sunset time of the Sunset
     * @param tempMin temperature minimal of the day
     * @param tempMax temperature maximal of the day
     */
    public PanelMeteo(JLabel city, JLabel country, JLabel sunrise, JLabel sunset, JLabel tempMin, JLabel tempMax){
        setLayout(null);
        ToolBox tb = new ToolBox();
        city.setBounds(50,0,100,30);

        country.setBounds(50,20,100,30);

        sunrise.setBounds(50,50,100,30);
        jlSunrise = new JLabel(tb.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Sunrise.png",30,30));
        jlSunrise.setBounds(150,50,100,30);

        sunset.setBounds(50,100,100,30);
        jlSunset = new JLabel(tb.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Sunset.png",30,30));
        jlSunset.setBounds(150,100,100,30);

        tempMin.setBounds(50,160,150,30);

        tempMax.setBounds(50,180,150,30);

        add(country);add(sunrise);add(sunset);add(tempMin);add(tempMax);add(jlSunrise);add(jlSunset);add(city);

        setPreferredSize(new Dimension(380,400));
        setOpaque(true);
    }
}
