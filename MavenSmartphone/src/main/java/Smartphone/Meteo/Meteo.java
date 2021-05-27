package Smartphone.Meteo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Meteo extends JPanel {
    private JButton buttonGo;
    private JTextField tfSearchBar = new JTextField(15);

    private static final String apiKey = "282e58d6c4f45693bc47da6aa566cab5";

    private JPanel searchPanel = new JPanel();
    private JPanel meteoPanel = new JPanel();

    private JPanel mainPanel = new JPanel();

    private JPanel panelMeteoInfo = new JPanel();

    private int idxLine = 1;

    private ImageIcon imageSearch;

    private JPanel jpCityInfo;
    private JPanel jpCityWeek;

    public Meteo() {
        mainPanel.setLayout(new BorderLayout());

        //ajout d'une image à un JButton
        imageSearch = new ImageIcon("src/main/resources/Image/searchLoupe.png");
//        imageSearch.getImage().getScaledInstance(10,10,Image.SCALE_SMOOTH);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        imageSearch = new ImageIcon(newTest);
        buttonGo = new JButton(imageSearch);
        buttonGo.setBorderPainted(false);
        buttonGo.setFocusPainted(false);
        buttonGo.setContentAreaFilled(false);
        buttonGo.setPreferredSize(new Dimension(100, 100));


//        buttonGo = new JButton(new ImageIcon(new ImageIcon("/src/searchLogo.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
//        buttonGo.setPreferredSize(new Dimension(100,100));

//        buttonGo.setBorderPainted(false);
//        buttonGo.setFocusPainted(false);
//        buttonGo.setContentAreaFilled(false);

        //ajout panel au BorderLayout
        searchPanel.add(tfSearchBar);
        searchPanel.add(buttonGo);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        panelMeteoInfo.setBackground(Color.cyan);
        panelMeteoInfo.setPreferredSize(new Dimension(300, 300));


        JScrollPane jScrollPane = new JScrollPane(meteoPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(300, 100));


        buttonGo.addActionListener(new ClicGo());
        mainPanel.add(panelMeteoInfo, BorderLayout.CENTER);
        mainPanel.add(jScrollPane, BorderLayout.SOUTH);
        add(mainPanel);


    }

    class ClicGo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String lieu = "";
            lieu = tfSearchBar.getText();
            MeteoRessource mr = new MeteoRessource();

            jpCityWeek = mr.getSelectedForecastInfo(lieu, Meteo.apiKey);
            jpCityInfo = mr.getSelectedMeteoInfo(lieu, Meteo.apiKey);

            meteoPanel.setLayout(new GridLayout(idxLine++, 1));
            panelMeteoInfo.add(jpCityInfo);
            meteoPanel.add(jpCityWeek);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

    }
}
