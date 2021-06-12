package Smartphone.Meteo;

import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Meteo extends JPanel {
    private static final String apiKey = "282e58d6c4f45693bc47da6aa566cab5";

    //variable pour la searchPanel
    private JButton buttonGo;
    private CheckboxGroup cbg = new CheckboxGroup();
    private JTextField rechecherBar = new JTextField(15);
    private JPanel searchPanel = new JPanel();       //panel qui contient bouton, textfield, checkbox
    private JPanel northPanel = new JPanel();

    private JPanel checkBoxPanel = new JPanel();

    private JPanel meteoPanel = new JPanel();   //panel pour contenir les infos quotidienne
    private JPanel panelMeteoInfo = new JPanel(); //panel pour

    private JPanel mainPanel = new JPanel();

    private JPanel jpCityInfo;      // panel pour les infos quotidienne
    private JPanel jpCityWeek;      //panel pour les infos de la semaine

    private int idxLine = 1;

    private ToolBox toolBox = new ToolBox();


    public Meteo() {
        mainPanel.setLayout(new BorderLayout());
        meteoPanel.setLayout(null);

        //
        ClickRecherche clicRecherche = new ClickRecherche(rechecherBar);
        rechecherBar.addFocusListener(clicRecherche);

        //northPanel
        northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.PAGE_AXIS));

        //searchPanel
        buttonGo = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Search.png",30,30));
        buttonGo.setBorderPainted(false);
        buttonGo.setFocusPainted(false);
        buttonGo.setContentAreaFilled(false);
        rechecherBar.setOpaque(false);        //modification du JTextField
        searchPanel.add(rechecherBar);
        searchPanel.add(buttonGo);

        //ajout des JCheckbox au checkbox group
        checkBoxPanel.add(new Checkbox("metric",cbg,true));
        checkBoxPanel.add(new Checkbox("imperial",cbg,false));
        checkBoxPanel.add(new Checkbox("standard",cbg,false));


        //ajout au northPanel
        northPanel.add(searchPanel);
        northPanel.add(checkBoxPanel);

        //définition du format du JTextField de la recherche
        Font policeRecherche = new Font("Arial", Font.PLAIN, 18);
        rechecherBar.setFont(policeRecherche);




        mainPanel.add(northPanel, BorderLayout.NORTH);

        panelMeteoInfo.setBackground(Color.cyan);
        panelMeteoInfo.setPreferredSize(new Dimension(380, 400));


        buttonGo.addActionListener(new ClicGo());
        mainPanel.add(panelMeteoInfo, BorderLayout.CENTER);
        mainPanel.add(meteoPanel, BorderLayout.SOUTH);
        add(mainPanel);


    }

    public String checkBoxSelected(){
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

            jpCityInfo = mr.getSelectedMeteoInfo(lieu, Meteo.apiKey,unit);

            jpCityWeek = mr.getSelectedForecastInfo(lieu, Meteo.apiKey);


            panelMeteoInfo.removeAll();
            meteoPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            meteoPanel.setLayout(new BoxLayout(meteoPanel,BoxLayout.PAGE_AXIS));
            panelMeteoInfo.add(jpCityInfo);
            meteoPanel.add(jpCityWeek);
            mainPanel.revalidate();
            mainPanel.repaint();

        }
    }
}
