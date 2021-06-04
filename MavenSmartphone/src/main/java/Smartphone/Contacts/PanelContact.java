package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Meteo.ClickRecherche;
import Smartphone.Storage.JSONStorage;
import Smartphone.Storage.Storable;
import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanelContact extends JPanel  {
    private CardLayout ecran = new CardLayout();
    //Page base
    private JPanel mainPanel =new JPanel();
        private JPanel contactPage = new JPanel();
            private JPanel PanelBase = new JPanel(); //Contient liste de contacts
            private JPanel PanelHaut = new JPanel(); //Contient bouton add et research
            private JButton buttonAdd ;
            private JButton buttonSearch;


    private JLabel labContactList =new JLabel(" My Contact List : ");


    private JList<Contact> contactsJList = new JList<>();
    private Storable storage = new JSONStorage();
    private ContactList contactList= new ContactList(storage);

    //Page ajouter contact
        private JPanel contactAdd = new PanelAdd();
            private JPanel PanelAddHaut = new JPanel();
                private JLabel labAddPanel = new JLabel("  Add a Contact  : ");

            private JPanel PanelAddCentre = new JPanel();
                private JPanel panelPicture = new JPanel();
                    private JButton buttonPicture ;
                private JPanel panelOk = new JPanel();
                    private JButton buttonCancel ;
                    private JButton buttonSave;

                private JPanel panelInfos = new JPanel();
                    private  JLabel nomLab =new JLabel("Nom : ");
                    private  JTextField nameTxt= new JTextField(30);
                    private  JLabel indicatifLab = new JLabel("Indicatif : ");
                    private  JTextField indictxt = new JTextField(5);
                    private  JLabel numLab = new JLabel("Numéro :  ");
                    private JTextField numTxt = new JTextField(10);
                    private JLabel emailLab = new JLabel("Email :  ");
                    private JTextField emailTxt = new JTextField(35);
                    private JLabel adresseLab = new JLabel("Addresse :  ");
                    private JTextField adresseTxt = new JTextField(35);
                    private JLabel favContactLab = new JLabel("Add contact to favorite : ");
                    private JCheckBox favCheckBox = new JCheckBox("", false);
        //Page rechercher contact
        private JPanel contactSearch = new PanelSearch();
            private JPanel PanelBack = new JPanel();
                private JLabel labSearchPanel = new JLabel("Search a Contact : ");
                private JButton buttonBack ;
            private JPanel PanelCentre = new JPanel();
                private JTextField rechercheBar = new JTextField(30);
                private JButton buttonGoSearch;
            private JPanel PanelBas = new JPanel();




    private ToolBox toolBox = new ToolBox();


    //Fichier de sauvegarde Destination
    File destinationFile = new File("MavenSmartphone/src/main/java/Smartphone/Storage/ContactList.json");

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
        panelInfos.setPreferredSize(new Dimension(350,372));
        panelInfos.setLayout(new GridLayout(6,2,5,2));

        panelInfos.add(nomLab);
        nameTxt.setOpaque(false);
        panelInfos.add(nameTxt);

        panelInfos.add(indicatifLab);
        indictxt.setOpaque(false);
        panelInfos.add(indictxt);

        panelInfos.add(numLab);
        numTxt.setOpaque(false);
        panelInfos.add(numTxt);

        panelInfos.add(emailLab);
        emailTxt.setOpaque(false);
        panelInfos.add(emailTxt);

        panelInfos.add(adresseLab);
        adresseTxt.setOpaque(false);
        panelInfos.add(adresseTxt);

        //Ajouter contaxt au favori
        panelInfos.add(favContactLab);
        favCheckBox.setFocusable(false);
        panelInfos.add(favCheckBox);


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

        buttonSave = new JButton("Save");
        buttonSave.setBorderPainted(false);
        buttonSave.setFocusPainted(false);
        buttonSave.setContentAreaFilled(false);

buttonSave.addActionListener(new Actions());

        panelOk.add(buttonCancel,BorderLayout.EAST);
        panelOk.add(buttonSave,BorderLayout.WEST);

//PANEL CONTACT SEARCH
    contactSearch.add(PanelBack) ;
    contactSearch.add(PanelCentre);
    contactSearch.add(PanelBas);

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
            if (e.getSource() == buttonAdd) {
                ecran.show(mainPanel, "contactAdd");
            }
            if (e.getSource() == buttonSearch) {
                ecran.show(mainPanel, "contactSearch");
            }
            if ((e.getSource() == buttonBack) || (e.getSource() == buttonCancel)) {
                ecran.show(mainPanel, "contactPage");
            }
            //Sauvergarder d'un contact
            if (e.getSource() == buttonSave) {

                //saisie = param du constructeur Contact
                String nom = nameTxt.getText();
                String indicatif = indictxt.getText();
                String numero = numTxt.getText();
                String email = emailTxt.getText();
                String adresse = adresseTxt.getText();

                //Controle si champs nom et numéro rempli avant d'ajouter
                if (nom =="" && numero==""){
                    //Afficher erreur car champs oblig vide
                    System.err.println("Données contact vides");
                }
                else {
                    //Création nouveau contact
                    Contact contact = new Contact(nom, indicatif, numero, email, adresse);

                    //Ajout du contact à la liste de contact
                    try {
                        contactList.addToContactList(contact);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                    //Sauvegarde de la liste des contacts dans fichiers JSON
                    //Ajouter a fichier existant
                    try {
                        contactList.addToFile(destinationFile);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                    //Savuvegarder les fichiers
                    try {
                        contactList.saveToFile(destinationFile);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }

                    //Mise à zéro des champs de saisie
                    nameTxt.setText("");
                    numTxt.setText("");
                    indictxt.setText("");
                    emailTxt.setText("");
                    adresseTxt.setText("");
                }
            }


            //Ajouter photo à un contact
            if (e.getSource() == buttonPicture) {

            }


            //Recherche d'un contact
            if (e.getSource() == buttonGoSearch) {
                String research = "";
                research = rechercheBar.getText();

                if (contactList.contains(contactList, research) == true) {

                } else {

                }
            }
        }

    }
}

