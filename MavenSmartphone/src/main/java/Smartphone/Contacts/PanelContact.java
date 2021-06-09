package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Meteo.ClickRecherche;
import Smartphone.Storage.JSONStorage;
import Smartphone.Storage.Storable;
import Smartphone.ToolBox;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class PanelContact extends JPanel {
    private CardLayout ecran = new CardLayout();
    //Page base
    private JPanel mainPanel = new JPanel();
    private JPanel contactPage = new JPanel();

        private JPanel PanelHaut = new JPanel(); //Contient bouton add et research
            private JButton buttonAdd;
            private JButton buttonSearch;

        private JPanel PanelBase = new JPanel(); //Contient liste de contacts
            private JPanel panelfavcont = new JPanel();
            private JPanel panelcont = new JPanel();
                private Storable storage = new JSONStorage();
                private JSONStorage favStorage = new JSONStorage();
                private ContactList contactList = new ContactList(storage);
                private ContactList favContactList = new ContactList(favStorage);
                private JLabel labContactList = new JLabel(" My Contact List : ");
                private JList NomContactsList;
                private JList NomFavContact;



    //Page ajouter contact
    private JPanel contactAdd = new PanelAdd();
        private JPanel PanelAddHaut = new JPanel();
            private JLabel labAddPanel = new JLabel("  Add a Contact  : ");

        private JPanel PanelAddCentre = new JPanel();
            private JPanel panelPicture = new JPanel();
                private JButton buttonPicture;
                private ImageIcon photoContact;
                private ImageIcon appareilPhoto = new ImageIcon(new ImageIcon("MavenSmartphone/src/main/java/Smartphone/Icones/icone_AddPicture.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));

            private JPanel panelInfos = new JPanel();
                private JLabel nomLab = new JLabel("Nom : ");
                private JTextField nameTxt = new JTextField(30);
                private JLabel indicatifLab = new JLabel("Indicatif : ");
                private final String[] indicatifs = {"+41","+1", "+31", "+39"};
                private JComboBox indicChoice = new JComboBox(indicatifs);
                private JLabel numLab = new JLabel("Numéro :  ");
                private JTextField numTxt = new JTextField(10);
                private JLabel emailLab = new JLabel("Email :  ");
                private JTextField emailTxt = new JTextField(35);
                private JLabel adresseLab = new JLabel("Addresse :  ");
                private JTextField adresseTxt = new JTextField(35);
                private JLabel favContactLab = new JLabel("Add contact to favorite : ");
                private JCheckBox favCheckBox = new JCheckBox("", false);


            private JPanel panelErreur = new JPanel();
                private JLabel labErreurSaisie;
            private JPanel panelOk = new JPanel();
                private JButton buttonCancel;
                private JButton buttonSave;

    //Page rechercher contact
    private JPanel contactSearch = new PanelSearch();
    private JPanel PanelBack = new JPanel();
    private JLabel labSearchPanel = new JLabel("Search a Contact : ");
    private JButton buttonBack;
    private JPanel PanelCentre = new JPanel();
    private JTextField rechercheBar = new JTextField(30);
    private JButton buttonGoSearch;
    private JPanel PanelBas = new JPanel();
    private JLabel LabelErreurRecherche = new JLabel();
    private JPanel contactShow = new PanelContactShow();

    //Page Contact selectionné
    private JPanel contactSelected = new PanelContactSelected();

    private JButton buttonDelete ;
    private JButton buttonDelete2 ;

    private JPanel PanelSelectCentre = new JPanel();
    private JPanel panelPicture2 = new JPanel();
    private JButton buttonPicture2;
    private ImageIcon photoContact2;
    private ImageIcon appareilPhoto2 = new ImageIcon(new ImageIcon("MavenSmartphone/src/main/java/Smartphone/Icones/icone_AddPicture.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));

    private JPanel panelInfos2 = new JPanel();
    private JLabel nomLab2 = new JLabel("Nom : ");
    private JLabel nameTxt2 = new JLabel("");
    private JLabel indicatifLab2 = new JLabel("Indicatif : ");
    private final String[] indicatifs2 = {"+41","+1", "+31", "+39"};
    private JLabel indicChoice2 = new JLabel("");
    private JLabel numLab2 = new JLabel("Numéro :  ");
    private JLabel numTxt2 = new JLabel("");
    private JLabel emailLab2 = new JLabel("Email :  ");
    private JLabel emailTxt2 = new JLabel("");
    private JLabel adresseLab2 = new JLabel("Addresse :  ");
    private JLabel adresseTxt2 = new JLabel("");
    private JLabel favContactLab2 = new JLabel("Add contact to favorite : ");
    private JCheckBox favCheckBox2 = new JCheckBox("", false);


    private JPanel panelDelete = new JPanel();

    private JPanel panelOk2 = new JPanel();
    private JButton buttonCancel2;
    private JButton buttonEdit;

    //Page Contact edit
    private JPanel contactEdit = new PanelEdit();

    private JPanel PanelEditCentre = new JPanel();
    private JPanel panelPicture3 = new JPanel();
    private JButton buttonPicture3;
    private ImageIcon photoContact3;
    private ImageIcon appareilPhoto3 = new ImageIcon(new ImageIcon("MavenSmartphone/src/main/java/Smartphone/Icones/icone_AddPicture.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));

    private JPanel panelInfos3 = new JPanel();
    private JLabel nomLab3 = new JLabel("Nom : ");
    private JTextField nameTxt3 = new JTextField(30);
    private JLabel indicatifLab3 = new JLabel("Indicatif : ");
    private final String[] indicatifs3 = {"+41","+1", "+31", "+39"};
    private JComboBox indicChoice3 = new JComboBox(indicatifs3);
    private JLabel numLab3 = new JLabel("Numéro :  ");
    private JTextField numTxt3 = new JTextField(10);
    private JLabel emailLab3 = new JLabel("Email :  ");
    private JTextField emailTxt3 = new JTextField(35);
    private JLabel adresseLab3 = new JLabel("Addresse :  ");
    private JTextField adresseTxt3 = new JTextField(35);
    private JLabel favContactLab3 = new JLabel("Add contact to favorite : ");
    private JCheckBox favCheckBox3 = new JCheckBox("", false);


    private JPanel panelDelete2 = new JPanel();

    private JPanel panelOk3 = new JPanel();
    private JButton buttonCancel3;
    private JButton buttonSaveChanges;

    private ToolBox toolBox = new ToolBox();
    private boolean contactOK = false;


    //Fichier de sauvegarde Destination
    File fileContactList = new File("MavenSmartphone/src/main/java/Smartphone/Storage/ContactList.json");
    File fileFavContactList = new File("MavenSmartphone/src/main/java/Smartphone/Storage/FavContactList.json");


    public PanelContact() {
/*
setBackground(Color.RED);
mainPanel.setBackground(Color.yellow);
contactPage.setBackground(Color.BLACK);
 */
        try {
            contactList.readFromFile(fileContactList);
            favContactList.readFromFile(fileFavContactList);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        mainPanel.setPreferredSize(new Dimension(400, 650));
        mainPanel.setLayout(ecran);

        mainPanel.add(contactPage, "contactPage");
        mainPanel.add(contactAdd, "contactAdd");
        mainPanel.add(contactSearch, "contactSearch");
        //Panel contactSelected s'affiche quand un contact de la liste est sélectionné
        mainPanel.add(contactSelected, "contactselected");
        mainPanel.add(contactEdit,"contactedit");
        // mainPanel.add(contactShow,"contactShow");

//PANEL CONTACT PAGE
        //Mise en page panel haut
        contactPage.add(PanelHaut);
        PanelHaut.setPreferredSize(new Dimension(400, 35));
        PanelHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonAdd = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Plus.png", 25, 25));
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.addActionListener(new Actions());
        buttonSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Recherche.png", 25, 25));
        buttonSearch.setBorderPainted(false);
        buttonSearch.setFocusPainted(false);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.addActionListener(new Actions());


        PanelHaut.add(buttonAdd);
        PanelHaut.add(buttonSearch);

        //Mise en page panel base
        contactPage.add(PanelBase);
        PanelBase.setPreferredSize(new Dimension(400, 498));
        PanelBase.add(labContactList);

        //Panel LISTE CONTACT FAVORI
        PanelBase.add(panelfavcont);
        panelfavcont.setPreferredSize(new Dimension(390,108));

        NomFavContact = new JList();//Ajouter liste de contact a partir de fichier JSON
        try {
            NomFavContact = new JList(contactList.getNameArrayFromJSON(fileFavContactList).toArray());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        NomFavContact.setLayoutOrientation(JList.VERTICAL);
        NomFavContact.setVisibleRowCount(3);
        NomFavContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        NomFavContact.addListSelectionListener(new ListSelectionListener());

        JScrollPane jScrollPaneFav = new JScrollPane(NomFavContact);
        JLabel ListeFav = new JLabel("Favorite contact list : ");
        panelfavcont.add(ListeFav);
        panelfavcont.add(jScrollPaneFav);



        //Panel LISTE CONTACT
        PanelBase.add(panelcont);
        panelcont.setPreferredSize(new Dimension(390,350));

        //Ajouter liste de contact a partir de fichier JSON
        try {
            NomContactsList = new JList(contactList.getNameArrayFromJSON(fileContactList).toArray());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        NomContactsList.setLayoutOrientation(JList.VERTICAL);
        NomContactsList.setVisibleRowCount(10);
        NomContactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        NomContactsList.addListSelectionListener(new ListSelectionListener());

        JScrollPane jScrollPane = new JScrollPane(NomContactsList);
        JLabel ListeCont = new JLabel("Contact list : ");
        panelcont.add(ListeCont);
        panelcont.add(jScrollPane);

//PANEL CONTACT SELECTED
        contactSelected.add(PanelSelectCentre, BorderLayout.EAST);

        //Mise en page panel centre
        PanelSelectCentre.setPreferredSize(new Dimension(350, 518));

        // panel avec boutton pour photo du contact
        PanelSelectCentre.add(panelPicture2);

        buttonPicture2 = new JButton("", appareilPhoto2);
        buttonPicture2.setSize(55, 55);
        buttonPicture2.setBorderPainted(false);
        buttonPicture2.setFocusPainted(false);
        buttonPicture2.setContentAreaFilled(false);
        buttonPicture2.addActionListener(new Actions());

        panelPicture2.add(buttonPicture2);

        //panel avec infos à saisir
        PanelSelectCentre.add(panelInfos2);
        PanelSelectCentre.add(panelDelete);
        panelInfos2.setPreferredSize(new Dimension(350, 352));
        panelInfos2.setLayout(new GridLayout(6, 2, 5, 2));

        panelInfos2.add(nomLab2);
        nameTxt2.setOpaque(false);
        panelInfos2.add(nameTxt2);

        panelInfos2.add(indicatifLab2);
        panelInfos2.add(indicChoice2);

        panelInfos2.add(numLab2);
        numTxt2.setOpaque(false);
        panelInfos2.add(numTxt2);

        panelInfos2.add(emailLab2);
        emailTxt2.setOpaque(false);
        panelInfos2.add(emailTxt2);

        panelInfos2.add(adresseLab2);
        adresseTxt2.setOpaque(false);
        panelInfos2.add(adresseTxt2);

        //Ajouter contaxt au favori
        panelInfos2.add(favContactLab2);
        favCheckBox2.setFocusable(false);
        panelInfos2.add(favCheckBox2);

        buttonDelete = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png", 25, 25));
        buttonDelete.setBorderPainted(false);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.addActionListener(new Actions());
        panelDelete.add(buttonDelete);

        //Mise en page Panel du bas
        // avec boutton ok ou annuler
        PanelSelectCentre.add(panelOk2, BorderLayout.SOUTH);
        panelOk2.setPreferredSize(new Dimension(400, 35));
        panelOk2.setLayout(new GridLayout(1, 2));
        buttonCancel2 = new JButton("Cancel");
        buttonCancel2.setBorderPainted(false);
        buttonCancel2.setFocusPainted(false);
        buttonCancel2.setContentAreaFilled(false);
        buttonCancel2.addActionListener(new Actions());

        buttonEdit = new JButton("Edit");
        buttonEdit.setBorderPainted(false);
        buttonEdit.setFocusPainted(false);
        buttonEdit.setContentAreaFilled(false);

        buttonEdit.addActionListener(new Actions());

        panelOk2.add(buttonCancel2, BorderLayout.EAST);
        panelOk2.add(buttonEdit, BorderLayout.WEST);

//PANEL CONTACT EDIT
        contactEdit.add(PanelEditCentre, BorderLayout.EAST);

        //Mise en page panel centre
        PanelEditCentre.setPreferredSize(new Dimension(350, 518));

        // panel avec boutton pour photo du contact
        PanelEditCentre.add(panelPicture3);

        buttonPicture3 = new JButton("", appareilPhoto3);
        buttonPicture3.setSize(55, 55);
        buttonPicture3.setBorderPainted(false);
        buttonPicture3.setFocusPainted(false);
        buttonPicture3.setContentAreaFilled(false);
        buttonPicture3.addActionListener(new Actions());

        panelPicture3.add(buttonPicture3);

        //panel avec infos à saisir
        PanelEditCentre.add(panelInfos3);
        PanelEditCentre.add(panelDelete2);
        panelInfos3.setPreferredSize(new Dimension(350, 352));
        panelInfos3.setLayout(new GridLayout(6, 2, 5, 2));

        panelInfos3.add(nomLab3);
        nameTxt3.setOpaque(false);
        panelInfos3.add(nameTxt3);

        panelInfos3.add(indicatifLab3);
        panelInfos3.add(indicChoice3);

        panelInfos3.add(numLab3);
        numTxt3.setOpaque(false);
        panelInfos3.add(numTxt3);

        panelInfos3.add(emailLab3);
        emailTxt3.setOpaque(false);
        panelInfos3.add(emailTxt3);

        panelInfos3.add(adresseLab3);
        adresseTxt3.setOpaque(false);
        panelInfos3.add(adresseTxt3);

        //Ajouter contaxt au favori
        panelInfos3.add(favContactLab3);
        favCheckBox3.setFocusable(false);
        panelInfos3.add(favCheckBox3);

        buttonDelete2 = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png", 25, 25));
        buttonDelete2.setBorderPainted(false);
        buttonDelete2.setFocusPainted(false);
        buttonDelete2.setContentAreaFilled(false);
        buttonDelete2.addActionListener(new Actions());

        panelDelete2.add(buttonDelete2);


        //Mise en page Panel du bas
        // avec boutton ok ou annuler
        PanelEditCentre.add(panelOk3, BorderLayout.SOUTH);
        panelOk3.setPreferredSize(new Dimension(400, 35));
        panelOk3.setLayout(new GridLayout(1, 2));
        buttonCancel3 = new JButton("Cancel");
        buttonCancel3.setBorderPainted(false);
        buttonCancel3.setFocusPainted(false);
        buttonCancel3.setContentAreaFilled(false);
        buttonCancel3.addActionListener(new Actions());

        buttonSaveChanges = new JButton("Save changes");
        buttonSaveChanges.setBorderPainted(false);
        buttonSaveChanges.setFocusPainted(false);
        buttonSaveChanges.setContentAreaFilled(false);
        buttonSaveChanges.addActionListener(new Actions());

        panelOk3.add(buttonCancel3, BorderLayout.EAST);
        panelOk3.add(buttonSaveChanges, BorderLayout.WEST);


//PANEL CONTACT ADD
        contactAdd.add(PanelAddHaut, BorderLayout.NORTH);
        contactAdd.add(PanelAddCentre, BorderLayout.EAST);
        //Mise en page panel haut
        PanelAddHaut.setPreferredSize(new Dimension(400, 30));
        PanelAddHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
        PanelAddHaut.add(labAddPanel);

        //Mise en page panel centre
        PanelAddCentre.setPreferredSize(new Dimension(350, 498));

        // panel avec boutton pour photo du contact
        PanelAddCentre.add(panelPicture);

        buttonPicture = new JButton("", appareilPhoto);
        buttonPicture.setSize(55, 55);
        buttonPicture.setBorderPainted(false);
        buttonPicture.setFocusPainted(false);
        buttonPicture.setContentAreaFilled(false);
        buttonPicture.addActionListener(new Actions());
        panelPicture.add(buttonPicture);

        //panel avec infos à saisir
        PanelAddCentre.add(panelInfos);
        PanelAddCentre.add(panelErreur);
        panelInfos.setPreferredSize(new Dimension(350, 342));
        panelInfos.setLayout(new GridLayout(6, 2, 5, 2));

        panelInfos.add(nomLab);
        nameTxt.setOpaque(false);
        panelInfos.add(nameTxt);

        panelInfos.add(indicatifLab);
        panelInfos.add(indicChoice);

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

        labErreurSaisie = new JLabel(" ");
        labErreurSaisie.setFont(new Font("Tahoma",Font.PLAIN,14));
        labErreurSaisie.setForeground(Color.red);
        labErreurSaisie.setSize(400,15);
        labErreurSaisie.setVisible(true);
        panelErreur.add(labErreurSaisie);


        //Mise en page Panel du bas
        // avec boutton ok ou annuler
        PanelAddCentre.add(panelOk,BorderLayout.SOUTH);
        panelOk.setPreferredSize(new Dimension(400, 35));
        panelOk.setLayout(new GridLayout(1, 2));
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

        panelOk.add(buttonCancel, BorderLayout.EAST);
        panelOk.add(buttonSave, BorderLayout.WEST);

//PANEL CONTACT SEARCH
        contactSearch.add(PanelBack);
        contactSearch.add(PanelCentre);
        contactSearch.add(PanelBas);

        //Mise en page panel Back
        PanelBack.setPreferredSize(new Dimension(400, 40));
        PanelBack.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonBack = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Back2.1.png", 30, 30));
        buttonBack.setBorderPainted(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.addActionListener(new Actions());
        PanelBack.add(buttonBack);
        PanelBack.add(labSearchPanel);

        //Mise en page panel centre
        PanelCentre.setPreferredSize(new Dimension(400, 50));
        ClickRecherche clickRecherche = new ClickRecherche(rechercheBar);
        rechercheBar.addFocusListener(clickRecherche);
        rechercheBar.setOpaque(false);

        buttonGoSearch = new JButton(toolBox.addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Recherche.png", 25, 25));
        buttonGoSearch.setBorderPainted(false);
        buttonGoSearch.setFocusPainted(false);
        buttonGoSearch.setContentAreaFilled(false);
        buttonGoSearch.addActionListener(new Actions());

        PanelCentre.add(rechercheBar);
        PanelCentre.add(buttonGoSearch);

        PanelBas.setPreferredSize(new Dimension(400, 435));
        PanelBas.add(contactShow, "contactshow");

        add(mainPanel);



    }

    class ListSelectionListener implements javax.swing.event.ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String selectedContact = (String) NomContactsList.getSelectedValue();
            ecran.show(mainPanel,"contactselected");

            try {
                Contact contactSelec = contactList.getContactByName(selectedContact,fileContactList);
                // Si le contact n'existe pas, ne rien mettre à jour
                if (contactSelec == null) return;
                //Ajouter les infos du contact sélectionné à la page d'info du contact
                nameTxt2.setText(contactSelec.getName());
                indicChoice2.setText(contactSelec.getIndicatif());
                numTxt2.setText(contactSelec.getPhoneNumber());
                emailTxt2.setText(contactSelec.getEmail());
                adresseTxt2.setText(contactSelec.getAddress());

                if (contactSelec.isAddphoto() == true){
                    buttonPicture2.setText(" ");
                }
                else
                    buttonPicture2.setText("");

                if (contactSelec.isFavContact() == true)
                    favCheckBox2.setSelected(true);
                else
                    favCheckBox2.setSelected(false);


            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }

        }
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
            if (e.getSource() == buttonCancel3){
                ecran.show(mainPanel,"contactselected");
            }
            if ((e.getSource() == buttonBack) || (e.getSource() == buttonCancel) || (e.getSource() == buttonCancel2)) {
                ecran.show(mainPanel, "contactPage");
                //Mise à zéro de tous les champs de saisie quand retour sur menu
                nameTxt.setText("");
                numTxt.setText("");
                indicChoice.setSelectedIndex(0);
                emailTxt.setText("");
                adresseTxt.setText("");
                buttonPicture.setText("");
                //Remettre photo de base
                buttonPicture.setIcon(appareilPhoto);
                favCheckBox.setSelected(false);
                rechercheBar.setText("");
                LabelErreurRecherche.setVisible(false);
                labErreurSaisie.setText(" ");
            }

            //Ajouter photo au contact
            if (e.getSource() == buttonPicture) {

                //Ouverture de fenetre pour choisir photo

                //modifier image du bouton avec la photo choisie
                //variable : "photoContact" = photo choisie
                //photoContact= new ImageIcon(new ImageIcon("").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
                //ajout photo choisie sur le bouton
                //buttonPicture.setIcon(photoContact);

                //Set texte du boutton si photo ajoutée
                buttonPicture.setText("  ");
            }
            if (e.getSource() == buttonPicture2){

            }

            //Sauvergarde d'un contact
            if (e.getSource() == buttonSave) {

                boolean addphoto;
                //Ajouter photo à un contact
                if (buttonPicture.getText() == "  ")
                    addphoto = true;
                else
                    addphoto = false;

                //saisie = param du constructeur Contact
                String nom = nameTxt.getText();
                String indicatif = (String) indicChoice.getSelectedItem();
                String numero = numTxt.getText();
                String email = emailTxt.getText();
                String adresse = adresseTxt.getText();

                boolean favContact;
                if (favCheckBox.isSelected() == true)
                    favContact = true;
                else
                    favContact = false;

                do {
                //Controle si champs nom et numéro rempli avant d'ajouter
                if (nameTxt.getText().isEmpty() == true || numTxt.getText().isEmpty() == true) {
                    //Afficher erreur car champs oblig vide
                    System.err.println("Données contact vides");
                    try {
                        throw new BusinessException("Nom et/ou numéro du contact vide", ErrorCodes.CONTACT_INFORMATIONS_EMPTY);
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                    labErreurSaisie.setText("Nom et/ou numéro du contact vide");
                }
                else {
                        try {
                            if (contactList.containsNameInJson(nameTxt.getText(), fileContactList) == true) {
                                try {
                                    throw new BusinessException("Contact already exist", ErrorCodes.CONTACT_ALREADY_EXIST_ERROR);
                                } catch (BusinessException businessException) {
                                    businessException.printStackTrace();
                                    labErreurSaisie.setText("Contact Already exist !");
                                }
                            } else {
                                //Création nouveau contact
                                Contact contact = new Contact(nom, indicatif, numero, email, adresse, addphoto, favContact);

                                //ajout de la photo liée
                                if (addphoto == true)
                                    contact.setPhoto(photoContact);


                                //Si contact favori ajout à liste des contacts fav aussi
                                if (favContact == true) {
                                    //Ajouter à la liste
                                    try {
                                        favContactList.addToContactList(contact);
                                    } catch (BusinessException businessException) {
                                        businessException.printStackTrace();
                                    }
                                    //Sauvegarder liste contact fav dans fichier JSON
                                    //Ajouter
//                                    try {
//                                        favContactList.addToFile(fileFavContactList);
//                                    } catch (BusinessException businessException) {
//                                        businessException.printStackTrace();
//                                    }
                                    //Save file
                                    try {
                                        favContactList.saveToFile(fileFavContactList);
                                    } catch (BusinessException businessException) {
                                        businessException.printStackTrace();
                                    }

                                    try {
                                        NomFavContact.removeAll();
                                        NomFavContact.setListData(contactList.getNameArrayFromJSON(fileFavContactList).toArray());
                                    } catch (BusinessException businessException) {
                                        businessException.printStackTrace();
                                    }
                                }

                                //Ajout du contact à la liste de contact
                                try {
                                    contactList.addToContactList(contact);
                                } catch (BusinessException businessException) {
                                    businessException.printStackTrace();
                                }
                                //Sauvegarde de la liste des contacts dans fichiers JSON
                                //Ajouter a fichier existant
//                                try {
//                                    contactList.addToFile(fileContactList);
//                                } catch (BusinessException businessException) {
//                                    businessException.printStackTrace();
//                                }
                                //Savuvegarder les fichiers
                                try {
                                    contactList.saveToFile(fileContactList);
                                } catch (BusinessException businessException) {
                                    businessException.printStackTrace();
                                }
                                try {
                                    NomContactsList.removeAll();
                                    NomContactsList.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                                } catch (BusinessException businessException) {
                                    businessException.printStackTrace();
                                }
                                updateUI();


                                //Mise à zéro des champs de saisie
                                nameTxt.setText("");
                                numTxt.setText("");
                                indicChoice.setSelectedIndex(0);
                                emailTxt.setText("");
                                adresseTxt.setText("");
                                buttonPicture.setText("");
                                //Remettre photo de base
                                buttonPicture.setIcon(appareilPhoto);
                                favCheckBox.setSelected(false);
                                contactOK = true;

                                //Retour sur page des contacts
                                ecran.show(mainPanel, "contactPage");
                            }
                        } catch (BusinessException businessException) {
                            businessException.printStackTrace();
                        }
                    }

            } while (contactOK=false);
        }
            if (e.getSource() == buttonEdit){
                ecran.show(mainPanel,"contactedit");

                nameTxt3.setText(nameTxt2.getText());
                numTxt3.setText(numTxt2.getText());
                indicChoice3.setSelectedItem(indicChoice2.getText());
                emailTxt3.setText(emailTxt2.getText());
                adresseTxt3.setText(adresseTxt2.getText());
                if (buttonPicture2.getText() == " "){
                    buttonPicture3.setText(" ");
                }
                else
                    buttonPicture3.setText("");

                if (favCheckBox2.isSelected() == true)
                    favCheckBox3.setSelected(true);
                else
                    favCheckBox3.setSelected(false);
            }
            if (e.getSource() == buttonDelete){
                contactList.delete(nameTxt2.getText());
                favContactList.delete(nameTxt2.getText());
                try {
                    contactList.saveToFile(fileContactList);
                    favContactList.saveToFile(fileFavContactList);
                    NomContactsList.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                    NomFavContact.setListData(favContactList.getNameArrayFromJSON(fileFavContactList).toArray());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                ecran.show(mainPanel,"contactPage");
                updateUI();
            }
            if (e.getSource() == buttonSaveChanges){

            }

            //Recherche d'un contact
            if (e.getSource() == buttonGoSearch) {
                String research = "";
                research = rechercheBar.getText();

                /*
                if () {
                    ecran.show(PanelBas,"contactShow");

                    //Et affichage des infos du contact choisi

                } else {

                    System.err.println("Contact not found");
                    LabelErreurRecherche.setText("Contact not found");

                    LabelErreurRecherche.setVisible(true);
                    PanelBas.add(LabelErreurRecherche);
                    //Msg erreur car rien trouvé
                }
                rechercheBar.setText("");
                 */
            }
        }

    }
}




