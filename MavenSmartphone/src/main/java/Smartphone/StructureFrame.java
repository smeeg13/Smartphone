package Smartphone;

import Smartphone.Calculator.Calculator;
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
import java.util.Date;

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
    private JPanel calculette = new Calculator();

    private ToolBox toolBox = new ToolBox();

    private JButton buttonContacts = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_Contact2.png")).getImage().getScaledInstance(55,55,Image.SCALE_SMOOTH)));
    private JButton buttonGalery = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_Galerie.png")).getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH)));
    private JButton buttonMeteo = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Meteo.png")).getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH)));
    private JButton buttonMenu = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_Menu.png")).getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
    private JButton buttonCalculette = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Calculette.png")).getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH)));

   // private JButton buttonContacts = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Icone_Contact2.png",55,55));
   // private JButton buttonGalery = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Icone_Galerie.png",48,48));
   // private JButton buttonMeteo = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Meteo.png",48,48));
   // private JButton buttonMenu = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Icone_Menu.png",20,20));
   // private JButton buttonCalculette = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Calculator.png",48,48));
    private JPanel panelBoutons =new JPanel();
    private JPanel panelNoms = new JPanel();
    private JPanel panelBoutons2 =new JPanel();
    private JPanel panelNoms2 = new JPanel();

    private JLabel labContact = new JLabel("  Contact");
    private JLabel labGalerie = new JLabel("Galery");
    private JLabel labMeteo = new JLabel(  "Meteo   ");
    private JLabel labCalculette = new JLabel("Calculator");

    private DateFormat formatDate = new SimpleDateFormat("HH:mm");
    private Calendar maintenant = Calendar.getInstance();

    private BatteryTask batteryTask = new BatteryTask();    //Objet BatteryTask pour trouver le niveau de battery
    private JLabel batteryLevel;                            //Label pour contenir le niveau de la batterie

    private ClockTask clockTask = new ClockTask();
    private JLabel time;




    private JPanel PageVerouillage = new PageVerouillage();
    private ClockTask clockTaskVerouillage = new ClockTask();
    private JLabel timeVerouillage;
    private JLabel labelDate;
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
    Date date = new Date();

    private JButton buttonDeverouiller ;
    private JButton buttonVerouiller;


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
//bouton pour vérouiller
        buttonVerouiller = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_Verrou.png")).getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH)));
        buttonVerouiller.setBorderPainted(false);
    buttonVerouiller.setFocusPainted(false);
    buttonVerouiller.setContentAreaFilled(false);
    buttonVerouiller.addActionListener(new ClicVerrouillage());
    bandeHaut.add(buttonVerouiller);

        //ajout dans le Jpanel du haut le pourcentage de la batterie
        batteryLevel = batteryTask.getBatteryPercentage();
        batteryLevel.setForeground(Color.WHITE);
        bandeHaut.add(batteryLevel,BorderLayout.EAST);



        bandeHaut.setBackground(Color.BLACK);
        bandeHaut.add(time,BorderLayout.WEST);


        panelCont.setLayout(collectionEcrans);
        panelCont.add(menu, "menu");
        panelCont.add(contacts,"contacts");
        panelCont.add(galerie, "galery");
        panelCont.add(meteo, "meteo");
        panelCont.add(calculette, "calculette");
        panelCont.add(PageVerouillage, "EcranVerrouillage");

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
        panelBoutons2.setPreferredSize(new Dimension(500,70));
        panelNoms2.setLayout(new FlowLayout(FlowLayout.CENTER,74,3));
        panelNoms2.setPreferredSize(new Dimension(500,70));

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


    //    collectionEcrans.show(panelCont,"menu");
        collectionEcrans.show(panelCont,"EcranVerrouillage");



        add(bandeHaut,BorderLayout.NORTH);
        add(bandeBas,BorderLayout.SOUTH);
        add(panelCont,BorderLayout.CENTER);

//Page Verouillage
        PageVerouillage.setLayout(null);
        Font fontTime = new Font("Tahoma", Font.BOLD,38);
        Font fontDate = new Font("Tahoma", Font.BOLD,15);
        timeVerouillage = clockTaskVerouillage.getTime();
        timeVerouillage.setFont(fontTime);
        timeVerouillage.setBounds(140,100, 120,60);
        PageVerouillage.add(timeVerouillage);
        labelDate = new JLabel(dateFormat.format(date));
        labelDate.setFont(fontDate);
        labelDate.setBounds(127,160,150,40);
        PageVerouillage.add(labelDate);
        buttonDeverouiller = new JButton("Clic here to unlock");
        buttonDeverouiller.setBorderPainted(true);
        buttonDeverouiller.setFocusPainted(false);
        buttonDeverouiller.setContentAreaFilled(false);
        buttonDeverouiller.setBounds(3, 440, 380,100);
        buttonDeverouiller.addActionListener(new ClicDeverouillage());
        PageVerouillage.add(buttonDeverouiller);

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
                JOptionPane.showMessageDialog(menu,"Ping to host failed","Internet connection",JOptionPane.WARNING_MESSAGE);
            }
        }


    }


    class ClicCalculette implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "calculette");
        }
    }
    class ClicVerrouillage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "EcranVerrouillage");
        }
    }
    class ClicDeverouillage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "menu");
        }
    }

}
