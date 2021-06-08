package Smartphone;

import Smartphone.Calculatrice.Calculette;
import Smartphone.Contacts.PanelContact;
import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.UI.PanelGallery;
import Smartphone.Meteo.Meteo;
import Smartphone.Task.BatteryTask;
import Smartphone.Task.ClockTask;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public  class StructureFrame extends JFrame {

    //création JPanel pour le borderlayout
    private JPanel bandeHaut = new JPanel();
    private JPanel bandeBas = new JPanel();


    private CardLayout collectionEcrans = new CardLayout();
    private JPanel panelCont = new JPanel();
    private JPanel menu;

    {
        try {
            menu = new Menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel contacts = new PanelContact();
    private JPanel galerie = new PanelGallery();
    private JPanel meteo = new Meteo();
    private JPanel calculette = new Calculette();

    private ToolBox toolBox = new ToolBox();

    private JButton buttonContacts = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Contact2.png",55,55));
    private JButton buttonGalery = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Icone_Galerie.png",40,40));
    private JButton buttonMeteo = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Meteo.png",40,40));
    private JButton buttonMenu = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Icone_Menu.png",20,20));
    private JButton buttonCalculette = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Calculette.png",43,43));
    private JPanel panelMenu = new JPanel();
    private JPanel panelBoutons =new JPanel();
    private JPanel panelNoms = new JPanel();
    private JPanel panelBoutons2 =new JPanel();
    private JPanel panelNoms2 = new JPanel();

    private JLabel labContact = new JLabel("  Contact");
    private JLabel labGalerie = new JLabel("Galery");
    private JLabel labMeteo = new JLabel(  "Meteo   ");
    private JLabel labCalculette = new JLabel("Calculatrice");

    private DateFormat formatDate = new SimpleDateFormat("HH:mm");
    private Calendar maintenant = Calendar.getInstance();

    private BatteryTask batteryTask = new BatteryTask();    //Objet BatteryTask pour trouver le niveau de battery
    private JLabel batteryLevel;                            //Label pour contenir le niveau de la batterie

    private ClockTask clockTask = new ClockTask();
    private JLabel time;




    public StructureFrame() throws BusinessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension tailleBandeHaut = new Dimension(500, 30);
        Dimension tailleBande = new Dimension(500, 40);
        Dimension tailleBord = new Dimension(5, 750);

        //instanciation du début de la tache pour récupérer l'heure
        time = clockTask.getTime();
        time.setForeground(Color.WHITE);


        bandeHaut.setLayout(new BorderLayout());
        JPanel bandeHautConstrain = new JPanel(new GridBagLayout());
        bandeHautConstrain.add(bandeHaut);


        bandeHaut.setPreferredSize(tailleBandeHaut);
        bandeHaut.setBackground(Color.GRAY);
        buttonMenu.setBorderPainted(false);
        buttonMenu.setFocusPainted(false);
        buttonMenu.setContentAreaFilled(false);
        bandeBas.add(buttonMenu);
        bandeBas.setPreferredSize(tailleBande);
        bandeBas.setFont(bandeBas.getFont().deriveFont(Font.BOLD));

        //ajout dans le Jpanel du haut le pourcentage de la batterie
        batteryLevel = batteryTask.getBatteryPercentage();
        batteryLevel.setForeground(Color.WHITE);
        bandeHaut.add(batteryLevel,BorderLayout.EAST);



        bandeHaut.setBackground(Color.BLACK);
        bandeHaut.add(time);


        panelCont.setLayout(collectionEcrans);
        panelCont.add(menu, "menu");
        panelCont.add(contacts,"contacts");
        panelCont.add(galerie, "galery");
        panelCont.add(meteo, "meteo");
        panelCont.add(calculette, "calculette");

        //ajout des boutons sur la page menu

        panelBoutons.setOpaque(false);
        panelNoms.setOpaque(false);
        panelBoutons2.setOpaque(false);
        panelNoms2.setOpaque(false);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER,40,3));
        panelBoutons.setPreferredSize(new Dimension(500,50));
        panelNoms.setLayout(new FlowLayout(FlowLayout.CENTER,74,3));
        panelNoms.setPreferredSize(new Dimension(500,50));

        panelBoutons2.setLayout(new FlowLayout(FlowLayout.CENTER,40,3));
        panelBoutons2.setPreferredSize(new Dimension(500,50));
        panelNoms2.setLayout(new FlowLayout(FlowLayout.CENTER,74,3));
        panelNoms2.setPreferredSize(new Dimension(500,50));

        buttonContacts.setBorderPainted(false);
        buttonContacts.setFocusPainted(false);
        buttonContacts.setContentAreaFilled(false);

        buttonMeteo.setBorderPainted(false);
        buttonMeteo.setFocusPainted(false);
        buttonMeteo.setContentAreaFilled(false);

        buttonGalery.setBorderPainted(false);
        buttonGalery.setFocusPainted(false);
        buttonGalery.setContentAreaFilled(false);

        panelBoutons.add(buttonContacts);
        panelBoutons.add(buttonGalery);
        panelBoutons.add(buttonMeteo);




        Font police = new Font("Arial", Font.BOLD, 14);
        labContact.setFont(police);
        labContact.setForeground(Color.black);

        labGalerie.setFont(police);
        labGalerie.setForeground(Color.black);

        labMeteo.setFont(police);
        labMeteo.setForeground(Color.black);
        panelNoms.add(labContact);
        panelNoms.add(labGalerie);
        panelNoms.add(labMeteo);

        buttonCalculette.setBorderPainted(false);
        buttonCalculette.setFocusPainted(false);
        buttonCalculette.setContentAreaFilled(false);

        panelBoutons2.add(buttonCalculette);

        labCalculette.setFont(police);
        labCalculette.setForeground(Color.black);
        panelNoms2.add(labCalculette);


        menu.add(panelBoutons);
        menu.add(panelNoms);
        menu.add(panelBoutons2);
        menu.add(panelNoms2);

        //ajout actionlistener au bouton "galery"
        buttonGalery.addActionListener(new ClicGalery());
        buttonMenu.addActionListener(new ClicMenu());
        buttonContacts.addActionListener(new ClicContacts());
        buttonMeteo.addActionListener(new ClicMeteo());
        buttonCalculette.addActionListener(new ClicCalculette());


        collectionEcrans.show(panelCont,"menu");



        add(bandeHaut,BorderLayout.NORTH);
        add(bandeBas,BorderLayout.SOUTH);
        add(panelCont,BorderLayout.CENTER);

    }



    class ClicGalery implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "galery");
        }
    }

    class ClicMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "menu");
        }
    }

    class ClicContacts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "contacts");
        }
    }

    class ClicMeteo implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            if(toolBox.isReachableByPing("api.openweathermap.org")){
                collectionEcrans.show(panelCont, "meteo");
            }else{
                JOptionPane.showMessageDialog(menu,"Ping to host failed","Internet conection",JOptionPane.WARNING_MESSAGE);
            }
        }


    }
    class ClicCalculette implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "calculette");
        }
    }

}
