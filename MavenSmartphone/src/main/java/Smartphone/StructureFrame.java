package Smartphone;

import Smartphone.Contacts.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public  class StructureFrame extends JFrame {

    //création JPanel pour le borderlayout
    private JPanel bandeHaut = new JPanel();
    private JPanel bandeBas = new JPanel();




    private CardLayout collectionEcrans = new CardLayout();
    private JPanel panelCont = new JPanel();
    private JPanel menu = new Menu();
    private JPanel contacts = new Contacts();
    private JPanel galerie = new Galerie();

    private JButton buttonContacts = new JButton("contacts");
    private JButton buttonGalery = new JButton("galery");
    private JButton buttonOther = new JButton("other");
    private JButton buttonMenu = new JButton("Menu");
    private JPanel panelMenu = new JPanel();

    private DateFormat formatDate = new SimpleDateFormat("HH:mm");
    private Calendar maintenant = Calendar.getInstance();
    private JLabel time = new JLabel(formatDate.format(maintenant.getTime()) + "   ");
    private int wait = 1000;




    public StructureFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension tailleBande = new Dimension(500, 30);
        Dimension tailleBord = new Dimension(5, 750);


        bandeHaut.setLayout(new BoxLayout(bandeHaut,BoxLayout.PAGE_AXIS));
        JPanel bandeHautConstrain = new JPanel(new GridBagLayout());
        bandeHautConstrain.add(bandeHaut);
        bandeHaut.add(time);


        time.setForeground(Color.WHITE);
        time.setBackground(Color.YELLOW);
        bandeHaut.setPreferredSize(tailleBande);
        bandeHaut.setBackground(Color.BLACK);
        bandeBas.add(buttonMenu);
        bandeBas.setPreferredSize(tailleBande);
        bandeBas.setFont(bandeBas.getFont().deriveFont(Font.BOLD));


        panelCont.setLayout(collectionEcrans);
        panelCont.add(menu, "menu");
        panelCont.add(contacts,"contacts");
        panelCont.add(galerie, "galery");

        //ajout des boutons sur la page menu
        menu.add(buttonContacts);menu.add(buttonGalery);menu.add(buttonOther);

        //ajout actionlistener au bouton "galery"
        buttonGalery.addActionListener(new ClicGalery());
        buttonMenu.addActionListener(new ClicMenu());
        buttonContacts.addActionListener(new ClicContacts());

        collectionEcrans.show(panelCont,"menu");

        buttonGalery.addActionListener(new ClicGalery());

        add(bandeHaut,BorderLayout.NORTH);
        add(bandeBas,BorderLayout.SOUTH);
        add(panelCont,BorderLayout.CENTER);


        Time time = new Time();
        Timer timer = new Timer(wait,time);
        timer.start();



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

    class Time implements ActionListener{
        public void actionPerformed(ActionEvent e2) {
            Calendar maintenant = Calendar.getInstance();

            time.setText(formatDate.format(maintenant.getTime()));
        }
    }
}
