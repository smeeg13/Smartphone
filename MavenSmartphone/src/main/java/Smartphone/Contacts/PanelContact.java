package Smartphone.Contacts;

import Smartphone.Meteo.ClickRecherche;
import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelContact extends JPanel  {
    private CardLayout ecran = new CardLayout();
    private JPanel mainPanel =new JPanel();
        private JPanel contactPage = new JPanel();
            private JPanel PanelBase = new JPanel(); //Contient liste de contacts
            private JPanel PanelHaut = new JPanel(); //Contient bouton add et research
            private JButton buttonAdd ;
            private JButton buttonSearch;

        private JPanel contactAdd = new PanelAdd();
            private JPanel PanelBack2 = new JPanel();
                private JLabel labAddPanel = new JLabel("Contact Adding : ");
                private JButton buttonBack2 ;
            private JPanel PanelCentre2 = new JPanel();
                private  JLabel nomLab =new JLabel("Nom : ");
                private  JTextField nameTxt= new JTextField(30);
                private  JLabel indicatifLab = new JLabel("Indicatif : ");
                private  JComboBox  indicChoices= new JComboBox( new Object[]{"+1   ","+32   ","+33   ","+41   ","+44   ","+49   "});
                private  JLabel numLab = new JLabel("Numéro :  ");
                private JTextField numTxt = new JTextField(10);
                private JLabel emailLab = new JLabel("Email :  ");
                private JTextField emailTxt = new JTextField(35);
                private JLabel adresseLab = new JLabel("Addresse :  ");
                private JTextField adresseTxt = new JTextField(35);

        private JPanel contactSearch = new PanelSearch();
            private JPanel PanelBack = new JPanel();
            private JLabel labSearchPanel = new JLabel("Contact Research : ");
            private JButton buttonBack ;
            private JPanel PanelCentre = new JPanel();
            private JTextField rechercheBar = new JTextField(30);
            private JButton buttonGoSearch;









    private ToolBox toolBox = new ToolBox();

    private JLabel labContactList =new JLabel("Contact List : ");

    //Limité à 200 contacts pour le test
    static  final  int MAX_CONTACTS = 200;
    private Contact[] contacts = new Contact[MAX_CONTACTS];

    // private JList<Contact> contactList = new JList<Contact>(contacts[]);

    public PanelContact() {
/*
setBackground(Color.RED);
mainPanel.setBackground(Color.yellow);
contactPage.setBackground(Color.BLACK);
 */

    mainPanel.setPreferredSize(new Dimension(400,650));
    mainPanel.setLayout(ecran);

    mainPanel.add(contactPage,"contactPage");
    mainPanel.add(contactAdd,"contactAdd");
    mainPanel.add(contactSearch,"contactSearch");

//PANEL CONTACT PAGE
    //Mise en page panel haut
    contactPage.add(PanelHaut,BorderLayout.NORTH);
    PanelHaut.setPreferredSize(new Dimension(400,40));
    PanelHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));

    buttonAdd = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Plus.png",25,25));
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.addActionListener(new Actions());
    buttonSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Search.png",25,25));
        buttonSearch.setBorderPainted(false);
        buttonSearch.setFocusPainted(false);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.addActionListener(new Actions());

    PanelHaut.add(buttonAdd);
    PanelHaut.add(buttonSearch);

    //Mise en page panel base
    contactPage.add(PanelBase,BorderLayout.EAST);
    PanelBase.setPreferredSize(new Dimension(400,498));
    PanelBase.add(labContactList);

//PANEL CONTACT ADD
    contactAdd.add(PanelBack2) ;
    contactAdd.add(PanelCentre2);
    //Mise en page panel Back
        PanelBack2.setPreferredSize(new Dimension(400,40));
        PanelBack2.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonBack2 = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Back.png",25,25));
        buttonBack2.setBorderPainted(false);
        buttonBack2.setFocusPainted(false);
        buttonBack2.setContentAreaFilled(false);
        buttonBack2.addActionListener(new Actions());
        PanelBack2.add(buttonBack2);
        PanelBack2.add(labAddPanel);

    //Mise en page panel centre
        PanelCentre2.setPreferredSize(new Dimension(350, 498));
        PanelCentre2.setLayout(new GridLayout(5,2,5,2));

        PanelCentre2.add(nomLab);
        nameTxt.setOpaque(false);
        PanelCentre2.add(nameTxt);

        PanelCentre2.add(indicatifLab);
        PanelCentre2.add(indicChoices);

        PanelCentre2.add(numLab);
        numTxt.setOpaque(false);
        PanelCentre2.add(numTxt);

        PanelCentre2.add(emailLab);
        emailTxt.setOpaque(false);
        PanelCentre2.add(emailTxt);

        PanelCentre2.add(adresseLab);
        adresseTxt.setOpaque(false);
        PanelCentre2.add(adresseTxt);

//PANEL CONTACT SEARCH
    contactSearch.add(PanelBack) ;
    contactSearch.add(PanelCentre);

        //Mise en page panel Back
        PanelBack.setPreferredSize(new Dimension(400,40));
        PanelBack.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonBack = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Back.png",25,25));
        buttonBack.setBorderPainted(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.addActionListener(new Actions());
        PanelBack.add(buttonBack);
        PanelBack.add(labSearchPanel);
    //Mise en page panel centre
        PanelCentre.setPreferredSize(new Dimension(400,498));
        ClickRecherche clickRecherche = new ClickRecherche(rechercheBar);
        rechercheBar.addFocusListener(clickRecherche);
        rechercheBar.setOpaque(false);

        buttonGoSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Search.png",25,25));
        buttonGoSearch.setBorderPainted(false);
        buttonGoSearch.setFocusPainted(false);
        buttonGoSearch.setContentAreaFilled(false);
        // à Ajouté action listener pour lancer recherche
        PanelCentre.add(rechercheBar);
        PanelCentre.add(buttonGoSearch);



        add(mainPanel);

    }

    class Actions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd){
                ecran.show(mainPanel,"contactAdd");
            }
            if (e.getSource() == buttonSearch){
                ecran.show(mainPanel,"contactSearch");
            }
            if ((e.getSource() == buttonBack) || (e.getSource()==buttonBack2)){
                ecran.show(mainPanel,"contactPage");
            }
        }
    }

    public void load(String json) {

    }

    public String save(){

        String ab="abc";
        return ab;
    }


}

