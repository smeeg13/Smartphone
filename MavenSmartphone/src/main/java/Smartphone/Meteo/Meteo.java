package Smartphone.Meteo;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

public class Meteo extends JPanel {
    private static final String apiKey = "282e58d6c4f45693bc47da6aa566cab5";

    private JButton buttonGo;
    private CheckboxGroup cbg = new CheckboxGroup();
    private JTextField rechecherBar = new JTextField(15);
    private JPanel searchPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel checkBoxPanel = new JPanel();
    private JPanel meteoPanel = new JPanel();
    private JPanel panelMeteoInfo = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel jpCityInfo;      // panel pour les infos quotidienne
    private JPanel jpCityWeek;      //panel pour les infos de la semaine
    private ToolBox toolBox = new ToolBox();

    public Meteo() {
        mainPanel.setLayout(new BorderLayout());
        meteoPanel.setLayout(null);

        //ajout d'un focus listener dans le jTextField
        ClickRecherche clicRecherche = new ClickRecherche(rechecherBar);
        rechecherBar.addFocusListener(clicRecherche);


        //northPanel
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));


        //ajout du bouton, rechereBar dans le searchPanel
        buttonGo = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Search.png", 30, 30));
        buttonGo.setBorderPainted(false);
        buttonGo.setFocusPainted(false);
        buttonGo.setContentAreaFilled(false);
        buttonGo.addActionListener(new ClicGo());
        rechecherBar.setOpaque(false);
        searchPanel.add(rechecherBar);
        searchPanel.add(buttonGo);


        //ajout des JCheckbox au checkbox group
        checkBoxPanel.add(new Checkbox("metric", cbg, true));
        checkBoxPanel.add(new Checkbox("imperial", cbg, false));
        checkBoxPanel.add(new Checkbox("standard", cbg, false));


        //ajout au northPanel
        northPanel.add(searchPanel);
        northPanel.add(checkBoxPanel);


        //définition du format du JTextField de la barre de recherhe
        Font policeRecherche = new Font("Arial", Font.PLAIN, 18);
        rechecherBar.setFont(policeRecherche);


//        panelMeteoInfo.setBackground(Color.cyan);
        panelMeteoInfo.setPreferredSize(new Dimension(380, 400));


        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(panelMeteoInfo, BorderLayout.CENTER);
        mainPanel.add(meteoPanel, BorderLayout.SOUTH);

        add(mainPanel);

    }

    public String checkBoxSelected() {
        Checkbox checkbox;

        checkbox = cbg.getSelectedCheckbox();

        return checkbox.getLabel();
    }

    class ClicGo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                String unit = "";
                String lieu = "";
                lieu = rechecherBar.getText();
                MeteoRessource mr = new MeteoRessource();

                unit = checkBoxSelected();

                jpCityInfo = mr.getSelectedMeteoInfo(lieu, Meteo.apiKey, unit);

                jpCityWeek = mr.getSelectedForecastInfo(lieu, Meteo.apiKey);


                panelMeteoInfo.removeAll();
                meteoPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();

                meteoPanel.setLayout(new BoxLayout(meteoPanel, BoxLayout.PAGE_AXIS));
                panelMeteoInfo.add(jpCityInfo);
                meteoPanel.add(jpCityWeek);
                mainPanel.revalidate();
                mainPanel.repaint();

        }
    }
}
