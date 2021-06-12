package Smartphone.Meteo;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;

public class PanelMeteo extends JPanel{

    private JLabel jlSunrise;
    private JLabel jlSunset;


    public PanelMeteo(JLabel city, JLabel country, JLabel sunrise, JLabel sunset, JLabel tempMin, JLabel tempMax){


        setLayout(null);
        ToolBox tb = new ToolBox();

        city.setBounds(150,40,100,30);

        country.setBounds(150,80,100,30);

        sunrise.setBounds(150,120,100,30);
        jlSunrise = new JLabel(tb.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Sunrise.png",30,30));
        jlSunrise.setBounds(250,120,100,30);

        sunset.setBounds(150,160,100,30);
        jlSunset = new JLabel(tb.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Sunset.png",30,30));
        jlSunset.setBounds(250,160,100,30);

        tempMin.setBounds(150,200,150,30);

        tempMax.setBounds(150,240,150,30);

        add(country);add(sunrise);add(sunset);add(tempMin);add(tempMax);add(jlSunrise);add(jlSunset);add(city);

        setPreferredSize(new Dimension(380,400));
        setOpaque(true);
    }
}
