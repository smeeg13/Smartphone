package Smartphone;

import Smartphone.Contacts.PanelContact;
import Smartphone.Errors.BusinessException;
import Smartphone.Meteo.Meteo;
import Smartphone.Task.BatteryTask;
import Smartphone.Task.ClockTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StructureFrame extends JFrame {

    //création JPanel pour le borderlayout
    private JPanel bandeHaut = new JPanel();
    private JPanel bandeBas = new JPanel();


    private CardLayout collectionEcrans = new CardLayout();
    private JPanel panelCont = new JPanel();
    private JPanel menu = new Menu();
    private JPanel contacts = new PanelContact();
    private JPanel galerie = new Galerie();
    private JPanel meteo = new Meteo();

    private JButton buttonContacts = new JButton("contacts");
    private JButton buttonGalery = new JButton("galery");
    private JButton buttonMeteo = new JButton("meteo");
    private JButton buttonMenu = new JButton("Menu");
    private JPanel panelMenu = new JPanel();

    private DateFormat formatDate = new SimpleDateFormat("HH:mm");
    private Calendar maintenant = Calendar.getInstance();

    private BatteryTask batteryTask = new BatteryTask();    //Objet BatteryTask pour trouver le niveau de battery
    private JLabel batteryLevel;                            //Label pour contenir le niveau de la batterie

    private ClockTask clockTask = new ClockTask();
    private JLabel time;

    private ToolBox toolBox = new ToolBox();


    public StructureFrame() throws BusinessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension tailleBande = new Dimension(500, 30);
        Dimension tailleBord = new Dimension(5, 750);

        //instanciation du début de la tache pour récupérer l'heure
        time = clockTask.getTime();
        time.setForeground(Color.WHITE);


        bandeHaut.setLayout(new BorderLayout());
        JPanel bandeHautConstrain = new JPanel(new GridBagLayout());
        bandeHautConstrain.add(bandeHaut);


        bandeHaut.setPreferredSize(tailleBande);
        bandeHaut.setBackground(Color.BLACK);
        bandeBas.add(buttonMenu);
        bandeBas.setPreferredSize(tailleBande);
        bandeBas.setFont(bandeBas.getFont().deriveFont(Font.BOLD));

        //ajout dans le Jpanel du haut le pourcentage de la batterie
        batteryLevel = batteryTask.getBatteryPercentage();
        batteryLevel.setForeground(Color.WHITE);
        bandeHaut.add(batteryLevel, BorderLayout.EAST);


        bandeHaut.setPreferredSize(tailleBande);
        bandeHaut.setBackground(Color.BLACK);
        bandeHaut.add(time);

        panelCont.setLayout(collectionEcrans);
        panelCont.add(menu, "menu");
        panelCont.add(contacts, "contacts");
        panelCont.add(galerie, "galery");
        panelCont.add(meteo, "meteo");

        //ajout des boutons sur la page menu
        menu.add(buttonContacts);
        menu.add(buttonGalery);
        menu.add(buttonMeteo);

        //ajout actionlistener au bouton "galery"
        buttonGalery.addActionListener(new ClicGalery());
        buttonMenu.addActionListener(new ClicMenu());
        buttonContacts.addActionListener(new ClicContacts());
        buttonMeteo.addActionListener(new ClicMeteo());

        collectionEcrans.show(panelCont, "menu");

        buttonGalery.addActionListener(new ClicGalery());

        add(bandeHaut, BorderLayout.NORTH);
        add(bandeBas, BorderLayout.SOUTH);
        add(panelCont, BorderLayout.CENTER);


    }


    class ClicGalery implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "galery");
        }
    }

    class ClicMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "menu");
        }
    }

    class ClicContacts implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            collectionEcrans.show(panelCont, "contacts");
        }
    }

    class ClicMeteo implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (isReachableByPing("api.openweathermap.org")) {
                collectionEcrans.show(panelCont, "meteo");
            } else {
                JOptionPane.showMessageDialog(menu, "Ping to host failed", "Internet connection", JOptionPane.ERROR_MESSAGE);
            }

        }

        public boolean isReachableByPing(String host) {

            try {
                String cmd = "";
                if (toolBox.isWindows()) {
                    cmd = "ping -n 1 " + host;
                }

                if (toolBox.isMac()) {
                    cmd = "ping -c 1 " + host;
                }

                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();

                if (process.exitValue() == 0) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
    }



}
