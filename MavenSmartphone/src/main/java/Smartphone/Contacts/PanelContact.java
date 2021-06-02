package Smartphone.Contacts;

import Smartphone.Meteo.ClickRecherche;
import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelContact extends JPanel  {
    private CardLayout ecran = new CardLayout();
    //Page base
    private JPanel mainPanel =new JPanel();
        private JPanel contactPage = new JPanel();
            private JPanel PanelBase = new JPanel(); //Contient liste de contacts
            private JPanel PanelHaut = new JPanel(); //Contient bouton add et research
            private JButton buttonAdd ;
            private JButton buttonSearch;
    //Page ajouter contact
        private JPanel contactAdd = new PanelAdd();
            private JPanel PanelAddHaut = new JPanel();
                private JLabel labAddPanel = new JLabel("  Contact Adding : ");

            private JPanel PanelAddCentre = new JPanel();
                private JPanel panelPicture = new JPanel();
                    private JButton buttonPicture ;
                private JPanel panelOk = new JPanel();
                    private JButton buttonCancel ;
                    private JButton buttonOk;
                private JPanel panelInfos = new JPanel();
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
        //Page rechercher contact
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
    buttonSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Recherche.png",25,25));
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

            //Ajouter liste de contact a partir de fichier JSON
            //...


//PANEL CONTACT ADD
    contactAdd.add(PanelAddHaut,BorderLayout.NORTH) ;
    contactAdd.add(PanelAddCentre,BorderLayout.EAST);
    //Mise en page panel haut
        PanelAddHaut.setPreferredSize(new Dimension(400,30));
        PanelAddHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
        PanelAddHaut.add(labAddPanel);

    //Mise en page panel centre
        PanelAddCentre.setPreferredSize(new Dimension(350, 498));

        // panel avec boutton pour photo du contact
        PanelAddCentre.add(panelPicture);
        buttonPicture = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_AddPicture.png",55,55));
        buttonPicture.setBorderPainted(false);
        buttonPicture.setFocusPainted(false);
        buttonPicture.setContentAreaFilled(false);
        //buttonPicture.addActionListener(new Actions());
        panelPicture.add(buttonPicture);

        //panel avec infos à saisir
        PanelAddCentre.add(panelInfos);
        panelInfos.setPreferredSize(new Dimension(350,352));
        panelInfos.setLayout(new GridLayout(5,2,5,2));
        panelInfos.add(nomLab);
        nameTxt.setOpaque(false);
        panelInfos.add(nameTxt);

        panelInfos.add(indicatifLab);
        panelInfos.add(indicChoices);

        panelInfos.add(numLab);
        numTxt.setOpaque(false);
        panelInfos.add(numTxt);

        panelInfos.add(emailLab);
        emailTxt.setOpaque(false);
        panelInfos.add(emailTxt);

        panelInfos.add(adresseLab);
        adresseTxt.setOpaque(false);
        panelInfos.add(adresseTxt);

    //Mise en page Panel du bas
        // avec boutton ok ou annuler
        PanelAddCentre.add(panelOk);
        panelOk.setPreferredSize(new Dimension(400,35));
        panelOk.setLayout(new GridLayout(1,2));
        buttonCancel = new JButton("Cancel");
        buttonCancel.setBorderPainted(false);
        buttonCancel.setFocusPainted(false);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.addActionListener(new Actions());

        buttonOk = new JButton("Save");
        buttonOk.setBorderPainted(false);
        buttonOk.setFocusPainted(false);
        buttonOk.setContentAreaFilled(false);
        //buttonOk.addActionListener(new Actions());

        panelOk.add(buttonCancel,BorderLayout.EAST);
        panelOk.add(buttonOk,BorderLayout.WEST);

//PANEL CONTACT SEARCH
    contactSearch.add(PanelBack) ;
    contactSearch.add(PanelCentre);

    //Mise en page panel Back
        PanelBack.setPreferredSize(new Dimension(400,40));
        PanelBack.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonBack = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Back2.1.png",30,30));
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

        buttonGoSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Recherche.png",25,25));
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
            if ((e.getSource() == buttonBack) || (e.getSource()==buttonCancel)){
                ecran.show(mainPanel,"contactPage");
            }
            if (e.getSource() == buttonOk){

            }
            if (e.getSource() == buttonPicture){

            }
            if (e.getSource() == buttonGoSearch){

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

