package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Gallery.Core.Picture;
import Smartphone.Gallery.UI.PanelGallery;
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
    private final int SIZE_CONTACT_PICTURE = 65;
    private final int SIZE_ICONE = 25;
    private Picture picture = new Picture(ClassLoader.getSystemResource("Icone_AddPicture.png").getPath());


    private CardLayout ecran = new CardLayout();
    //Page base
    private JPanel mainPanel = new JPanel();
    private JPanel PcontactPage = new JPanel();
        private JPanel PContactPageHaut = new JPanel(); //Contient bouton add et research
            private JButton buttonAdd;
            private JButton buttonSearch;
        private JPanel PContactPageBase = new JPanel(); //Contient liste de contacts
            private JPanel panelfavcont = new JPanel();
            private JPanel panelcont = new JPanel();
                private Storable storage = new JSONStorage();
                private JSONStorage favStorage = new JSONStorage();
                private ContactList contactList = new ContactList(storage);
                private ContactList favContactList = new ContactList(favStorage);
                private JLabel labContactList = new JLabel(" My Contact List       ");
                private JList JListContacts;
                private JList JListFavContact;

    //Page ajouter contact
    private JPanel PContactAdd = new PanelAdd();
        private JPanel PContactAddHaut = new JPanel();
            private JLabel labAddPanel = new JLabel("  Add a Contact  : ");

        private JPanel PContactAddCentre = new JPanel();
            private JPanel PContactAddPicture = new JPanel();
                private JButton buttonPicturePContactAdd;
                private final ImageIcon appareilPhoto = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH));

            private JPanel PContactAddInfos = new JPanel();
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
    private JButton buttonContactfind = new JButton("Enter above the contact you want to find");

    //Page Contact selectionné
    private JPanel contactSelected = new PanelContactSelected();
    private JPanel PanelSelectCentre = new JPanel();
    private JPanel panelPictureContSelec = new JPanel();
    private JButton buttonPictureContSelec;
    private ImageIcon appareilPhotoContSelec = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH));

    private JPanel panelInfosContSelec = new JPanel();
    private JLabel nomLabContSelec = new JLabel("Nom : ");
    private JLabel nameTxtContSelec = new JLabel("");
    private JLabel indicatifLabContSelec = new JLabel("Indicatif : ");
    private JLabel indicChoiceContSelec = new JLabel("");
    private JLabel numLabContSelec = new JLabel("Numéro :  ");
    private JLabel numTxtContSelec = new JLabel("");
    private JLabel emailLabContSelec = new JLabel("Email :  ");
    private JLabel emailTxtContSelec = new JLabel("");
    private JLabel adresseLabContSelec = new JLabel("Addresse :  ");
    private JLabel adresseTxtContSelec = new JLabel("");
    private JLabel favContactLabContSelec = new JLabel("Add contact to favorite : ");
    private JCheckBox favCheckBoxContSelec = new JCheckBox("", false);
    private JPanel panelDeleteContSelec = new JPanel();
    private JPanel panelOkContSelec = new JPanel();
    private JButton buttonCancelContSelec;

    //Page Contact edit
    private JPanel contactEdit = new PanelEdit();
    private JButton buttonEdit;
    private JPanel PanelEditCentre = new JPanel();
    private JPanel panelPictureEdit = new JPanel();
    private JButton buttonPictureEdit;
    private ImageIcon photoContact3;
    private ImageIcon appareilPhoto3 = new ImageIcon(new ImageIcon("MavenSmartphone/src/main/java/Smartphone/Icones/icone_AddPicture.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));

    private JPanel panelInfosEdit = new JPanel();
    private JLabel nomLabEdit = new JLabel("Nom : ");
    private JTextField nameTxtEdit = new JTextField(30);
    private JLabel indicatifLabEdit = new JLabel("Indicatif : ");
    private final String[] indicatifs3 = {"+41","+1", "+31", "+39"};
    private JComboBox indicChoiceEdit = new JComboBox(indicatifs3);
    private JLabel numLabEdit = new JLabel("Numéro :  ");
    private JTextField numTxt3Edit = new JTextField(10);
    private JLabel emailLabEdit = new JLabel("Email :  ");
    private JTextField emailTxtEdit = new JTextField(35);
    private JLabel adresseLabEdit = new JLabel("Addresse :  ");
    private JTextField adresseTxtEdit = new JTextField(35);
    private JLabel favContactLabEdit = new JLabel("Add contact to favorite : ");
    private JCheckBox favCheckBoxEdit = new JCheckBox("", false);

    private JPanel panelDeleteEdit = new JPanel();
    private JButton buttonDelete ;

    private JPanel panelOkEdit = new JPanel();
    private JButton buttonCancelEdit;
    private JButton buttonSaveChanges;

    private ToolBox toolBox = new ToolBox();

    //Selectionner photo dans galerie
    private  JPanel panelSelecPhoto = new PanelSelecPhoto();
    JPanel PHautSelecPhoto = new JPanel();
    JButton SelectPictureOK = new JButton("Ok");
    JButton CancelSelectionPic = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Back2.1.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    JLabel labSelectPhoto = new JLabel("     Select a picture");
    JPanel PCentreSelectPhoto = new JPanel();
    CardLayout ecranGalery = new CardLayout();
    private JPanel gallery;
    {
        try {
            gallery = new PanelGallery(PCentreSelectPhoto,picture);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }


    private Contact contactSelec;


    //Fichier de sauvegarde Destination

    File fileContactList = new File(System.getenv("HOME") + "/contacts/ContactList.json");
    File fileFavContactList = new File(System.getenv("HOME") + "/contacts/FavContactList.json");

    /**
     * This constructor provides the Contacts display.
     * @throws BusinessException – if the gallery isn't generate by the {@code file.mkdir() in the Gallery class.}
     *
     */
    public PanelContact() {
        try {
            contactList.readFromFile(fileContactList);
            favContactList.readFromFile(fileFavContactList);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        mainPanel.setPreferredSize(new Dimension(400, 650));
        mainPanel.setLayout(ecran);
        mainPanel.add(PcontactPage, "contactPage");
        mainPanel.add(PContactAdd, "contactAdd");
        mainPanel.add(contactSearch, "contactSearch");
        mainPanel.add(contactSelected, "contactselected");
        mainPanel.add(contactEdit,"contactedit");
        mainPanel.add(panelSelecPhoto,"panelSelecPhoto");

//PANEL CONTACT PAGE
        //Mise en page panel haut
        PcontactPage.add(PContactPageHaut);
        PContactPageHaut.setPreferredSize(new Dimension(400, 35));
        PContactPageHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonAdd = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Plus.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.addActionListener(new Actions());
        buttonSearch = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Recherche.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        buttonSearch.setBorderPainted(false);
        buttonSearch.setFocusPainted(false);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.addActionListener(new Actions());
        PContactPageHaut.add(labContactList);
        PContactPageHaut.add(buttonAdd);
        PContactPageHaut.add(buttonSearch);
        //Mise en page panel base
        PcontactPage.add(PContactPageBase);
        PContactPageBase.setPreferredSize(new Dimension(400, 498));

        //Panel LISTE CONTACT FAVORI
        PContactPageBase.add(panelfavcont);
        panelfavcont.setPreferredSize(new Dimension(390,108));
        panelfavcont.setLayout(new BoxLayout (panelfavcont, BoxLayout.Y_AXIS));

        try { //Ajouter liste de contact fav a partir de fichier JSON
            JListFavContact = new JList(contactList.getNameArrayFromJSON(fileFavContactList).toArray());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        JListFavContact.setLayoutOrientation(JList.VERTICAL);
        JListFavContact.setVisibleRowCount(3);
        JListFavContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListFavContact.addListSelectionListener(new ListSelectionListener());
        JScrollPane jScrollPaneFav = new JScrollPane(JListFavContact);
        JLabel ListeFav = new JLabel("Favorite contact ");
        ListeFav.setSize(400,20);
        panelfavcont.add(ListeFav);
        panelfavcont.add(jScrollPaneFav);

        //Panel LISTE CONTACT
        PContactPageBase.add(panelcont);
        panelcont.setPreferredSize(new Dimension(390,350));
        panelcont.setLayout(new BoxLayout (panelcont, BoxLayout.Y_AXIS));
        try { //Ajouter liste de contact a partir de fichier JSON
            JListContacts = new JList(contactList.getNameArrayFromJSON(fileContactList).toArray());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        JListContacts.setLayoutOrientation(JList.VERTICAL);
        JListContacts.setVisibleRowCount(10);
        JListContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListContacts.addListSelectionListener(new ListSelectionListener());
        JScrollPane jScrollPane = new JScrollPane(JListContacts);
        JLabel ListeCont = new JLabel("Contacts ");
        ListeCont.setSize(400,20);
        panelcont.add(ListeCont);
        panelcont.add(jScrollPane);

//PANEL CONTACT SELECTED
        contactSelected.add(PanelSelectCentre, BorderLayout.EAST);
        //Mise en page panel centre
        PanelSelectCentre.setPreferredSize(new Dimension(350, 518));
        // panel avec boutton pour photo du contact
        PanelSelectCentre.add(panelPictureContSelec);
        buttonPictureContSelec = new JButton();
        buttonPictureContSelec.setSize(55, 55);
        buttonPictureContSelec.setBorderPainted(false);
        buttonPictureContSelec.setFocusPainted(false);
        buttonPictureContSelec.setContentAreaFilled(false);
        buttonPictureContSelec.addActionListener(new Actions());
        panelPictureContSelec.add(buttonPictureContSelec);

        //panel avec infos à saisir
        PanelSelectCentre.add(panelInfosContSelec);
        PanelSelectCentre.add(panelDeleteContSelec);
        panelDeleteContSelec.setPreferredSize(new Dimension(400,44));
        panelInfosContSelec.setPreferredSize(new Dimension(350, 342));
        panelInfosContSelec.setLayout(new GridLayout(6, 2, 5, 2));

        panelInfosContSelec.add(nomLabContSelec);
        nameTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(nameTxtContSelec);

        panelInfosContSelec.add(indicatifLabContSelec);
        panelInfosContSelec.add(indicChoiceContSelec);

        panelInfosContSelec.add(numLabContSelec);
        numTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(numTxtContSelec);

        panelInfosContSelec.add(emailLabContSelec);
        emailTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(emailTxtContSelec);

        panelInfosContSelec.add(adresseLabContSelec);
        adresseTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(adresseTxtContSelec);

        panelInfosContSelec.add(favContactLabContSelec);
        favCheckBoxContSelec.setFocusable(false);
        favCheckBoxContSelec.setEnabled(false); //Ne peut pas être modifier à ce moment-là
        panelInfosContSelec.add(favCheckBoxContSelec);
        try {
            contactSelec = contactList.getContactByName(nameTxtContSelec.getText(),fileContactList);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        //Mise en page Panel du bas
        PanelSelectCentre.add(panelOkContSelec, BorderLayout.SOUTH);
        panelOkContSelec.setPreferredSize(new Dimension(400, 35));
        panelOkContSelec.setLayout(new GridLayout(1, 2));

        buttonCancelContSelec = new JButton("Cancel");
        buttonCancelContSelec.setBorderPainted(false);
        buttonCancelContSelec.setFocusPainted(false);
        buttonCancelContSelec.setContentAreaFilled(false);
        buttonCancelContSelec.addActionListener(new Actions());

        buttonEdit = new JButton("Edit");
        buttonEdit.setBorderPainted(false);
        buttonEdit.setFocusPainted(false);
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.addActionListener(new Actions());
        panelOkContSelec.add(buttonCancelContSelec, BorderLayout.EAST);
        panelOkContSelec.add(buttonEdit, BorderLayout.WEST);

//PANEL CONTACT EDIT
        contactEdit.add(PanelEditCentre, BorderLayout.EAST);
        //Mise en page panel centre
        PanelEditCentre.setPreferredSize(new Dimension(350, 518));
        PanelEditCentre.add(panelPictureEdit);

        buttonPictureEdit = new JButton();
        buttonPictureEdit.setSize(55, 55);
        buttonPictureEdit.setBorderPainted(false);
        buttonPictureEdit.setFocusPainted(false);
        buttonPictureEdit.setContentAreaFilled(false);
        buttonPictureEdit.addActionListener(new Actions());
        panelPictureEdit.add(buttonPictureEdit);

        //panel avec infos à saisir
        PanelEditCentre.add(panelInfosEdit);
        PanelEditCentre.add(panelDeleteEdit);
        panelInfosEdit.setPreferredSize(new Dimension(350, 341));
        panelInfosEdit.setLayout(new GridLayout(6, 2, 5, 2));

        panelInfosEdit.add(nomLabEdit);
        nameTxtEdit.setOpaque(false);
        panelInfosEdit.add(nameTxtEdit);

        panelInfosEdit.add(indicatifLabEdit);
        panelInfosEdit.add(indicChoiceEdit);

        panelInfosEdit.add(numLabEdit);
        numTxt3Edit.setOpaque(false);
        panelInfosEdit.add(numTxt3Edit);

        panelInfosEdit.add(emailLabEdit);
        emailTxtEdit.setOpaque(false);
        panelInfosEdit.add(emailTxtEdit);

        panelInfosEdit.add(adresseLabEdit);
        adresseTxtEdit.setOpaque(false);
        panelInfosEdit.add(adresseTxtEdit);

        panelInfosEdit.add(favContactLabEdit);
        favCheckBoxEdit.setFocusable(false);
        panelInfosEdit.add(favCheckBoxEdit);

        buttonDelete = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Delete.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        buttonDelete.setBorderPainted(false);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.addActionListener(new Actions());
        panelDeleteEdit.add(buttonDelete);

        //Mise en page Panel du bas
        PanelEditCentre.add(panelOkEdit, BorderLayout.SOUTH);
        panelOkEdit.setPreferredSize(new Dimension(400, 35));
        panelOkEdit.setLayout(new GridLayout(1, 2));
        buttonCancelEdit = new JButton("Cancel");
        buttonCancelEdit.setBorderPainted(false);
        buttonCancelEdit.setFocusPainted(false);
        buttonCancelEdit.setContentAreaFilled(false);
        buttonCancelEdit.addActionListener(new Actions());

        buttonSaveChanges = new JButton("Save changes");
        buttonSaveChanges.setBorderPainted(false);
        buttonSaveChanges.setFocusPainted(false);
        buttonSaveChanges.setContentAreaFilled(false);
        buttonSaveChanges.addActionListener(new Actions());
        panelOkEdit.add(buttonCancelEdit, BorderLayout.EAST);
        panelOkEdit.add(buttonSaveChanges, BorderLayout.WEST);

//PANEL CONTACT ADD
        PContactAdd.add(PContactAddHaut, BorderLayout.NORTH);
        PContactAdd.add(PContactAddCentre, BorderLayout.EAST);
        //Mise en page panel haut
        PContactAddHaut.setPreferredSize(new Dimension(400, 30));
        PContactAddHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
        PContactAddHaut.add(labAddPanel);
        //Mise en page panel centre
        PContactAddCentre.setPreferredSize(new Dimension(350, 498));
        PContactAddCentre.add(PContactAddPicture);
        buttonPicturePContactAdd = new JButton(new ImageIcon(picture.getPath()));
        buttonPicturePContactAdd.setIcon(new ImageIcon(new ImageIcon(picture.getPath()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
        buttonPicturePContactAdd.setSize(55, 55);
        buttonPicturePContactAdd.setBorderPainted(false);
        buttonPicturePContactAdd.setFocusPainted(false);
        buttonPicturePContactAdd.setContentAreaFilled(false);
        buttonPicturePContactAdd.addActionListener(new Actions());
        PContactAddPicture.add(buttonPicturePContactAdd);

        //panel avec infos à saisir
        PContactAddCentre.add(PContactAddInfos);
        PContactAddCentre.add(panelErreur);
        PContactAddInfos.setPreferredSize(new Dimension(350, 332));
        PContactAddInfos.setLayout(new GridLayout(6, 2, 5, 2));

        PContactAddInfos.add(nomLab);
        nameTxt.setOpaque(false);
        PContactAddInfos.add(nameTxt);

        PContactAddInfos.add(indicatifLab);
        PContactAddInfos.add(indicChoice);

        PContactAddInfos.add(numLab);
        numTxt.setOpaque(false);
        PContactAddInfos.add(numTxt);

        PContactAddInfos.add(emailLab);
        emailTxt.setOpaque(false);
        PContactAddInfos.add(emailTxt);

        PContactAddInfos.add(adresseLab);
        adresseTxt.setOpaque(false);
        PContactAddInfos.add(adresseTxt);

        //Ajouter contact au favori
        PContactAddInfos.add(favContactLab);
        favCheckBox.setFocusable(false);
        PContactAddInfos.add(favCheckBox);

        labErreurSaisie = new JLabel(" ");
        labErreurSaisie.setFont(new Font("Tahoma",Font.PLAIN,14));
        labErreurSaisie.setForeground(Color.red);
        labErreurSaisie.setSize(400,15);
        labErreurSaisie.setVisible(true);
        panelErreur.add(labErreurSaisie);

        //Mise en page Panel du bas
        PContactAddCentre.add(panelOk,BorderLayout.SOUTH);
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
        buttonBack = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Back2.1.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
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
        buttonGoSearch = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Recherche.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        buttonGoSearch.setBorderPainted(false);
        buttonGoSearch.setFocusPainted(false);
        buttonGoSearch.setContentAreaFilled(false);
        buttonGoSearch.addActionListener(new Actions());
        PanelCentre.add(rechercheBar);
        PanelCentre.add(buttonGoSearch);

        PanelBas.setPreferredSize(new Dimension(400, 435));
        buttonContactfind.setPreferredSize(new Dimension(340,40));
        buttonContactfind.setBorderPainted(true);
        buttonContactfind.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonContactfind.setFocusPainted(false);
        buttonContactfind.setContentAreaFilled(false);
        buttonContactfind.setEnabled(false);
        buttonContactfind.addActionListener(new Actions());
        PanelBas.add(buttonContactfind);

//PANEL SELECTION D'UNE PHOTO DANS GALERIE
        PHautSelecPhoto.setLayout(null);
        PHautSelecPhoto.setPreferredSize(new Dimension(400,45));
        CancelSelectionPic.setBounds(2,2,48,40);
        CancelSelectionPic.setBorderPainted(false);
        CancelSelectionPic.setFocusPainted(false);
        CancelSelectionPic.setContentAreaFilled(false);
        CancelSelectionPic.addActionListener(new Actions());
        PHautSelecPhoto.add(CancelSelectionPic);

        labSelectPhoto.setBounds(50,5,300,40);
        PHautSelecPhoto.add(labSelectPhoto);

        SelectPictureOK.setBounds(350,5,50,40);
        SelectPictureOK.setBorderPainted(true);
        SelectPictureOK.setBorder(BorderFactory.createLineBorder(Color.black));
        SelectPictureOK.setFocusPainted(false);
        SelectPictureOK.setContentAreaFilled(false);
 // SelectPictureOK.addActionListener(new Actions());
        PHautSelecPhoto.add(SelectPictureOK);

        PCentreSelectPhoto.setPreferredSize(new Dimension(400,400));
        PCentreSelectPhoto.add(gallery,"galery");
        PCentreSelectPhoto.setLayout(ecranGalery);


        panelSelecPhoto.add(PHautSelecPhoto, BorderLayout.NORTH);
        panelSelecPhoto.add(PCentreSelectPhoto, BorderLayout.CENTER);



        add(mainPanel);
    }

    class ListSelectionListener implements javax.swing.event.ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            String selectedContact;

            ecran.show(mainPanel,"contactselected");
            try {
                if (e.getSource() == JListContacts) {
                    selectedContact = (String) JListContacts.getSelectedValue();
                    contactSelec = contactList.getContactByName(selectedContact, fileContactList);
                }
                else {
                    selectedContact = (String) JListFavContact.getSelectedValue();
                    contactSelec = favContactList.getContactByName(selectedContact, fileFavContactList);
                }
                    // Si le contact n'existe pas, ne rien mettre à jour
                    if (contactSelec == null) return;
                    //Ajouter les infos du contact sélectionné à la page d'info du contact
                    nameTxtContSelec.setText(contactSelec.getName());
                    indicChoiceContSelec.setText(contactSelec.getIndicative());
                    numTxtContSelec.setText(contactSelec.getPhoneNumber());
                    emailTxtContSelec.setText(contactSelec.getEmail());
                    adresseTxtContSelec.setText(contactSelec.getAddress());

                    buttonPictureContSelec.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));

                    if (contactSelec.isFavContact() == true)
                        favCheckBoxContSelec.setSelected(true);
                    else
                        favCheckBoxContSelec.setSelected(false);
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
            if (e.getSource() == buttonEdit){
                ecran.show(mainPanel,"contactedit");
                nameTxtEdit.setText(nameTxtContSelec.getText());
                numTxt3Edit.setText(numTxtContSelec.getText());
                indicChoiceEdit.setSelectedItem(indicChoiceContSelec.getText());
                emailTxtEdit.setText(emailTxtContSelec.getText());
                adresseTxtEdit.setText(adresseTxtContSelec.getText());
                buttonPictureEdit.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));

                if (favCheckBoxContSelec.isSelected() == true)
                    favCheckBoxEdit.setSelected(true);
                else
                    favCheckBoxEdit.setSelected(false);
            }
            if (e.getSource() == buttonSaveChanges){
                    //Supprimer le contact
                    contactList.delete(nameTxtContSelec.getText());
                    favContactList.delete(nameTxtContSelec.getText());

                    boolean favContact;
                    if (favCheckBoxEdit.isSelected() == true)
                        favContact = true;
                    else
                        favContact = false;
                    //Recréer contact avec changement
                    Contact contact = new Contact(nameTxtEdit.getText(), (String) indicChoiceEdit.getSelectedItem(),numTxt3Edit.getText(),
                                                    emailTxtEdit.getText(),adresseTxtEdit.getText(),picture.getPath(),favContact);
                    if (contact.isFavContact() ==true) {
                        try {
                            favContactList.addToContactList(contact);
                            favContactList.saveToFile(fileFavContactList);
                        } catch (BusinessException businessException){
                            businessException.printStackTrace();
                        }
                    }
                    try {
                        contactList.addToContactList(contact);
                        contactList.saveToFile(fileContactList);
                        favContactList.saveToFile(fileFavContactList);
                        JListContacts.removeAll();
                        JListContacts.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                        JListFavContact.removeAll();
                        JListFavContact.setListData(favContactList.getNameArrayFromJSON(fileFavContactList).toArray());
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                    ecran.show(mainPanel,"contactPage");
                    updateUI();
            }
            if (e.getSource() == buttonDelete){
                contactList.delete(nameTxtContSelec.getText());
                favContactList.delete(nameTxtContSelec.getText());
                try {
                    contactList.saveToFile(fileContactList);
                    favContactList.saveToFile(fileFavContactList);
                    JListContacts.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                    JListFavContact.setListData(favContactList.getNameArrayFromJSON(fileFavContactList).toArray());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                ecran.show(mainPanel,"contactPage");
                updateUI();
            }
            if ((e.getSource() == buttonBack) || (e.getSource() == buttonCancel)) {
                ecran.show(mainPanel, "contactPage");
                //Mise à zéro de tous les champs de saisie quand retour sur menu
                nameTxt.setText("");
                numTxt.setText("");
                indicChoice.setSelectedIndex(0);
                emailTxt.setText("");
                adresseTxt.setText("");
                picture = resetPicture(picture);      //Remet apareil photo sur bouton
                favCheckBox.setSelected(false);
                rechercheBar.setText("");
                LabelErreurRecherche.setVisible(false);
                labErreurSaisie.setText(" ");
                buttonContactfind.setText("Enter above the name of the contact you want to find");
                buttonContactfind.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            if (e.getSource() == buttonCancelEdit){
                ecran.show(mainPanel,"contactselected");
            }
            if (e.getSource() == buttonCancelContSelec){
                nameTxtContSelec.setText("");
                indicChoiceContSelec.setText("");
                numTxtContSelec.setText("");
                emailTxtContSelec.setText("");
                adresseTxtContSelec.setText("");
                favCheckBoxContSelec.setSelected(false);
                picture = resetPicture(picture);
                JListContacts.clearSelection();
                JListFavContact.clearSelection();
                ecran.show(mainPanel, "contactPage");
            }
            if (e.getSource() == buttonPictureEdit) {
                String lastPanel = "contactedit";
                CancelSelectionPic.setText(lastPanel);
                ecran.show(mainPanel,"panelSelecPhoto");
                openGaleryToSelecPic(e, buttonPictureEdit);


            }
            if (e.getSource() == buttonPicturePContactAdd){
                String lastPanel = "contactAdd";
                CancelSelectionPic.setText(lastPanel);
                ecran.show(mainPanel,"panelSelecPhoto");
                openGaleryToSelecPic(e, buttonPicturePContactAdd);

            }

            //Sauvergarde d'un contact
            if (e.getSource() == buttonSave) {
                //Ajouter photo à un contact

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

                boolean contactOK = false;
                do {
                //Controle si champs nom et numéro rempli avant d'ajouter
                if (nameTxt.getText().isEmpty()  || numTxt.getText().isEmpty()) {
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
                            if (contactList.containsNameInJson(nameTxt.getText(), fileContactList)) {
                                labErreurSaisie.setText("Contact Already exist !");
                                throw new BusinessException("Contact already exist", ErrorCodes.CONTACT_ALREADY_EXIST_ERROR);
                                }
                             else {
                                //Création nouveau contact
                                Contact contact = new Contact(nom, indicatif, numero, email, adresse, picture.getPath(), favContact);
                                System.out.println(picture.getPath());
                                picture = resetPicture(picture);
                                System.out.println(picture.getPath()+" ap");

                                //Si contact favori ajout à liste des contacts fav aussi
                                if (contact.isFavContact()) {
                                        //Ajouter à la liste
                                        favContactList.addToContactList(contact);
                                        //Save file
                                        favContactList.saveToFile(fileFavContactList);
                                        JListFavContact.removeAll();
                                        JListFavContact.setListData(contactList.getNameArrayFromJSON(fileFavContactList).toArray());
                                }
                                    //Ajout du contact à la liste de contact
                                    contactList.addToContactList(contact);
                                    //Savuvegarder les fichiers
                                    contactList.saveToFile(fileContactList);
                                    JListContacts.removeAll();
                                    JListContacts.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                                updateUI();

                                //Mise à zéro des champs de saisie
                                nameTxt.setText("");
                                numTxt.setText("");
                                indicChoice.setSelectedIndex(0);
                                emailTxt.setText("");
                                adresseTxt.setText("");
                                //Remet apareil photo sur bouton et texte empty
                                picture = resetPicture(picture);
                                favCheckBox.setSelected(false);
                                contactOK = true;
                                ecran.show(mainPanel, "contactPage"); //Retour sur page des contacts
                            }
                        } catch (BusinessException businessException) {
                            businessException.printStackTrace();
                        }
                    }
            } while (contactOK =false);
            }
            //Recherche d'un contact
            if (e.getSource() == buttonGoSearch) {
                String research = "";
                research = rechercheBar.getText();
                try {
                    if (contactList.containsNameInJson(research,fileContactList) ==true) {
                        Contact searchedCont = contactList.getContactByName(research,fileContactList);
                        buttonContactfind.setText(searchedCont.getName());
                        buttonContactfind.setEnabled(true);
                        buttonContactfind.setBorder(BorderFactory.createTitledBorder("Contact Find"));
                    } else {
                        //Msg erreur car rien trouvé
                        System.err.println("Contact not found");
                        buttonContactfind.setText("Contact not found");
                        buttonContactfind.setBorder(BorderFactory.createLineBorder(Color.black));
                        buttonContactfind.setEnabled(false);
                    }
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                rechercheBar.setText("");
            }
            if (e.getSource() == buttonContactfind){
                String selectedContact;
                ecran.show(mainPanel,"contactselected");
                try {
                        selectedContact = buttonContactfind.getText();
                        contactSelec = contactList.getContactByName(selectedContact, fileContactList);
                    // Si le contact n'existe pas, ne rien mettre à jour
                    if (contactSelec == null) return;
                    //Ajouter les infos du contact sélectionné à la page d'info du contact
                    nameTxtContSelec.setText(contactSelec.getName());
                    indicChoiceContSelec.setText(contactSelec.getIndicative());
                    numTxtContSelec.setText(contactSelec.getPhoneNumber());
                    emailTxtContSelec.setText(contactSelec.getEmail());
                    adresseTxtContSelec.setText(contactSelec.getAddress());

                    buttonPictureContSelec.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));

                    if (contactSelec.isFavContact() == true)
                        favCheckBoxContSelec.setSelected(true);
                    else
                        favCheckBoxContSelec.setSelected(false);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            }
            if (e.getSource() == CancelSelectionPic) {

                ecran.show(mainPanel,CancelSelectionPic.getText());
                CancelSelectionPic.setText("");

            }
            if (e.getSource() == SelectPictureOK) {
//Reprend le path de la photo sélectionnée quand clic sur Ok

            }
        }

        private void openGaleryToSelecPic(ActionEvent e, JButton button) {

                //Ouverture de fenetre pour choisir photo
                try {
                    gallery = new PanelGallery(PCentreSelectPhoto,picture);
                } catch (BusinessException f) {
                    f.printStackTrace();
                }
                ecranGalery.show(PCentreSelectPhoto,"galery");
                /*
                JFileChooser fileChooser=new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", "png", "bmp", "jpg","jpeg","PNG");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(null);
                //Photo choisie
                File file = fileChooser.getSelectedFile();
                //variable : "photoContact" = photo choisie
                try {
                    Image photochoisie = ImageIO.read(file);
                    photoContact = new ImageIcon((new ImageIcon(photochoisie).getImage().getScaledInstance(56, 56, Image.SCALE_DEFAULT)));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                 */
                button.setIcon(new ImageIcon(new ImageIcon(picture.getPath()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE, Image.SCALE_SMOOTH)));
        }
    }

    private Picture resetPicture(Picture picture){
        picture.setPath(ClassLoader.getSystemResource("Icone_AddPicture.png").getPath());
        return picture;
    }
}

