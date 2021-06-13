package Smartphone.Meteo;

import Smartphone.Errors.BusinessException;
import Smartphone.ToolBox;
import lombok.SneakyThrows;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This Class display the weather for different country
 * @author Thomas Cheseaux
 */

public class Meteo extends JPanel {

    private static final String apiKey = "282e58d6c4f45693bc47da6aa566cab5";
    private final int IMAGE_WIDTH = 30;
    private final int IMAGE_HEIGHT = 30;


    private JButton buttonGo;
    private CheckboxGroup cbg = new CheckboxGroup(); //object contening the future checkBox
    private JTextField rechecherBar = new JTextField(15);
    private JPanel searchPanel = new JPanel();       //panel which contain searchPanel,CheckBox,Jtextfield
    private JPanel northPanel = new JPanel();


    private JPanel checkBoxPanel = new JPanel();


    private JPanel meteoWeekPanel = new JPanel();   //panel for the weekly meteo info
    private JPanel panelMeteoInfo = new JPanel(); //panel who contains the meteoPanel


    private JPanel mainPanel = new JPanel();


    private JPanel jpCityInfo;      //panel pour les infos quotidienne
    private JPanel jpCityWeek;      //panel pour les infos de la semaine


    /**
     * This constructor provides the meteo display
     */
    public Meteo() {
        mainPanel.setLayout(new BorderLayout());
        meteoWeekPanel.setLayout(null);

        ClickRecherche clicRecherche = new ClickRecherche(rechecherBar);
        rechecherBar.addFocusListener(clicRecherche);


        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));


        ToolBox toolBox = new ToolBox();
        buttonGo = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Search.png")).getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
        buttonGo.addActionListener(new ClicGo());
        buttonGo.setBorderPainted(false);
        buttonGo.setFocusPainted(false);
        buttonGo.setContentAreaFilled(false);
        rechecherBar.setOpaque(false);
        searchPanel.add(rechecherBar);
        searchPanel.add(buttonGo);


        checkBoxPanel.add(new Checkbox("metric", cbg, true));
        checkBoxPanel.add(new Checkbox("imperial", cbg, false));
        checkBoxPanel.add(new Checkbox("standard", cbg, false));


        northPanel.add(searchPanel);
        northPanel.add(checkBoxPanel);


        Font policeRecherche = new Font("Arial", Font.PLAIN, 18);
        rechecherBar.setFont(policeRecherche);


        mainPanel.add(northPanel, BorderLayout.NORTH);


        panelMeteoInfo.setPreferredSize(new Dimension(380, 350));

        mainPanel.add(panelMeteoInfo, BorderLayout.CENTER);
        mainPanel.add(meteoWeekPanel, BorderLayout.SOUTH);
        add(mainPanel);

    }

    /**
     * This method look which checkBox is selected
     * @return a String with the information like (imperial,standard,metric)ß
     */
    public String checkBoxSelected() {
        Checkbox checkbox;

        checkbox = cbg.getSelectedCheckbox();

        return checkbox.getLabel();
    }


    /**
     * This class is used for the JButton ClicGo to get information about
     * the weekly and daily weather info
     */
    class ClicGo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String unit = "";
            String lieu = "";
            MeteoRessource mr = new MeteoRessource();

            lieu = rechecherBar.getText();
            unit = checkBoxSelected();

            try {
                jpCityInfo = mr.getSelectedMeteoInfo(lieu, Meteo.apiKey, unit);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try {
                jpCityWeek = mr.getSelectedForecastInfo(lieu, Meteo.apiKey);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }

            panelMeteoInfo.removeAll();
            meteoWeekPanel.removeAll();

            meteoWeekPanel.setLayout(new BoxLayout(meteoWeekPanel, BoxLayout.PAGE_AXIS));

            panelMeteoInfo.add(jpCityInfo);
            meteoWeekPanel.add(jpCityWeek);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

    }
}
