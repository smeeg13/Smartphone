package Smartphone.Meteo;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * This class is used to provide a JPanel for the daily weather info
 * @author Thomas Cheseaux
 */
public class PanelMeteo extends JPanel{

    private JLabel jlSunrise;
    private JLabel jlSunset ;
    private JLabel jlCountryFlag;
    private ToolBox toolBox = new ToolBox();
    private ImageIcon iconFlag;
    private String pathFlag;
    private final int IMAGE_WIDTH = 60;
    private final int IMAGE_HEIGTH = 60;

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

        pathFlag = "CountryFlag/"+country.getText().toLowerCase(Locale.ROOT)+".png";
        pathFlag = ClassLoader.getSystemResource(pathFlag).getPath();
        iconFlag = toolBox.addPictureIcon(pathFlag,IMAGE_WIDTH,IMAGE_HEIGTH);

        jlCountryFlag = new JLabel(iconFlag);
        jlCountryFlag.setBounds(120,20,IMAGE_WIDTH,IMAGE_HEIGTH);

        city.setBounds(130,80,100,30);

        country.setBounds(130,120,100,30);

        sunrise.setBounds(130,160,100,30);
        jlSunrise = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Sunrise.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        jlSunrise.setBounds(210,160,100,30);

        sunset.setBounds(130,200,100,30);
        jlSunset = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Sunset.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        jlSunset.setBounds(210,200,100,30);

        tempMin.setBounds(130,240,150,30);

        tempMax.setBounds(130,280,150,30);

        add(country);add(sunrise);add(sunset);add(tempMin);add(tempMax);add(jlSunrise);add(jlSunset);add(city);add(jlCountryFlag);

        setPreferredSize(new Dimension(380,400));
        setOpaque(true);
    }
}
