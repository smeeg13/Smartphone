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

/**
 * This class provides graphical implementations for a gallery using {@link javax.swing}  and {@link java.awt}.
 *
 * @author Mégane Solliard
 * @version 3
 * @see PanelAdd#PanelAdd()
 * @see PanelContactSelected#PanelContactSelected()
 * @see PanelEdit#PanelEdit()
 * @see PanelSearch#PanelSearch()
 * @see PanelSelecPhoto#PanelSelecPhoto()
 */
public class PanelContact extends JPanel {
    private final int SIZE_CONTACT_PICTURE = 65;
    private final String PICTURE_DEFAULT = ClassLoader.getSystemResource("Icone_AddPicture.png").getPath();
    private final String[] indicatifs = {"+41","+1", "+31", "+39"};
    private final String[] indicatifsEdit = {"+41","+1", "+31", "+39"};
    JPanel PHautSelecPhoto = new JPanel();
    JButton SelectPictureOK = new JButton("Ok");
    JButton CancelSelectionPic = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Back2.1.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    JLabel labSelectPhoto = new JLabel("     Select a picture");
    JPanel PCentreSelectPhoto = new JPanel();
    CardLayout ecranGalery = new CardLayout();
    File fileContactList = new File(System.getenv("HOME") + "/contacts/ContactList.json");
    File fileFavContactList = new File(System.getenv("HOME") + "/contacts/FavContactList.json");
    private Picture picture = new Picture(PICTURE_DEFAULT);
    private final CardLayout ecran = new CardLayout();
    private final JPanel mainPanel = new JPanel();
    private final JButton buttonAdd;
    private final JButton buttonSearch;
    private final Storable storage = new JSONStorage();
    private final JSONStorage favStorage = new JSONStorage();
    private final ContactList contactList = new ContactList(storage);
    private final ContactList favContactList = new ContactList(favStorage);
    private JList JListContacts;
    private JList JListFavContact;
    private final JButton buttonPictureAdd;
    private final JTextField nameTxt = new JTextField(30);
    private final JComboBox indicChoice = new JComboBox(indicatifs);
    private final JTextField numTxt = new JTextField(10);
    private final JTextField emailTxt = new JTextField(35);
    private final JTextField addressTxt = new JTextField(35);
    private final JCheckBox favCheckBox = new JCheckBox("", false);
    private final JLabel labErreurSaisie;
    private final JButton buttonCancel;
    private final JButton buttonSave;
    private final JButton buttonBack;
    private final JTextField rechercheBar = new JTextField(30);
    private final JButton buttonGoSearch;
    private final JLabel LabelErreurRecherche = new JLabel();
    private final JButton buttonContactFind = new JButton("Enter above the contact you want to find");
    private final JButton buttonPictureContSelec;
    private final JLabel nameTxtContSelec = new JLabel("");
    private final JLabel indicChoiceContSelec = new JLabel("");
    private final JLabel numTxtContSelec = new JLabel("");
    private final JLabel emailTxtContSelec = new JLabel("");
    private final JLabel adresseTxtContSelec = new JLabel("");
    private final JCheckBox favCheckBoxContSelec = new JCheckBox("", false);
    private final JButton buttonRemovePic ;
    private final JButton buttonCancelContSelec;
    private final JButton buttonEdit;
    private final JButton buttonPictureEdit;
    private final JTextField nameTxtEdit = new JTextField(30);
    private final JComboBox indicChoiceEdit = new JComboBox(indicatifsEdit);
    private final JTextField numTxtEdit = new JTextField(10);
    private final JTextField emailTxtEdit = new JTextField(35);
    private final JTextField adresseTxtEdit = new JTextField(35);
    private final JCheckBox favCheckBoxEdit = new JCheckBox("", false);
    private final JButton buttonDelete ;
    private final JButton buttonCancelEdit;
    private final JButton buttonSaveChanges;
    private final ToolBox toolBox = new ToolBox();
    private JPanel gallery;
    private Contact contactSelec;
    {
        try {
            gallery = new PanelGallery(PCentreSelectPhoto,picture);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }

    /**
     * This constructor provides the Contacts display.
     * @throws BusinessException – if the contact list / favorite contact list isn't generate
     *                              by the {@code readFromFile(File file) in the ContactList class.}
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
        JPanel pcontactPage = new JPanel();
        mainPanel.add(pcontactPage, "contactPage");
        JPanel PContactAdd = new PanelAdd();
        mainPanel.add(PContactAdd, "contactAdd");
        JPanel contactSearch = new PanelSearch();
        mainPanel.add(contactSearch, "contactSearch");
        JPanel contactSelected = new PanelContactSelected();
        mainPanel.add(contactSelected, "contactselected");
        JPanel contactEdit = new PanelEdit();
        mainPanel.add(contactEdit,"contactedit");
        //Selectionner photo dans galerie
        JPanel panelSelecPhoto = new PanelSelecPhoto();
        mainPanel.add(panelSelecPhoto,"panelSelecPhoto");

//PANEL CONTACT PAGE
        JPanel PContactPageHaut = new JPanel();
        pcontactPage.add(PContactPageHaut);
        PContactPageHaut.setPreferredSize(new Dimension(400, 35));
        PContactPageHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));

        int SIZE_ICONE = 25;
        buttonAdd = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Plus.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        setFontButton(buttonAdd);
        buttonSearch = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Recherche.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        setFontButton(buttonSearch);
        JLabel labContactList = new JLabel(" My Contact List       ");
        PContactPageHaut.add(labContactList);
        PContactPageHaut.add(buttonAdd);
        PContactPageHaut.add(buttonSearch);
        JPanel PContactPageBase = new JPanel();
        pcontactPage.add(PContactPageBase);
        PContactPageBase.setPreferredSize(new Dimension(400, 498));
    //Panel LISTE CONTACT FAVORI
        JPanel panelfavcont = new JPanel();
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
        JPanel panelcont = new JPanel();
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
        JPanel panelSelectCentre = new JPanel();
        contactSelected.add(panelSelectCentre, BorderLayout.EAST);
        //Mise en page panel centre
        panelSelectCentre.setPreferredSize(new Dimension(350, 518));
        JPanel panelPictureContSelec = new JPanel();
        panelSelectCentre.add(panelPictureContSelec);
        Dimension SIZE_PANEL_PICTURE = new Dimension(400, 70);
        panelPictureContSelec.setPreferredSize(SIZE_PANEL_PICTURE);
        buttonPictureContSelec = new JButton();
        buttonPictureContSelec.setSize(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE);
        setFontButton(buttonPictureContSelec);
        panelPictureContSelec.add(buttonPictureContSelec);

        //panel avec infos à saisir
        JPanel panelInfosContSelec = new JPanel();
        panelSelectCentre.add(panelInfosContSelec);
        JPanel panelRemovePic = new JPanel();
        panelSelectCentre.add(panelRemovePic);
        panelRemovePic.setPreferredSize(new Dimension(400,44));
        panelInfosContSelec.setPreferredSize(new Dimension(350, 342));
        panelInfosContSelec.setLayout(new GridLayout(6, 2, 5, 2));

        JLabel nomLabContSelec = new JLabel("Nom : ");
        panelInfosContSelec.add(nomLabContSelec);
        nameTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(nameTxtContSelec);
        JLabel indicatifLabContSelec = new JLabel("Indicatif : ");
        panelInfosContSelec.add(indicatifLabContSelec);
        panelInfosContSelec.add(indicChoiceContSelec);
        JLabel numLabContSelec = new JLabel("Numéro :  ");
        panelInfosContSelec.add(numLabContSelec);
        numTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(numTxtContSelec);
        JLabel emailLabContSelec = new JLabel("Email :  ");
        panelInfosContSelec.add(emailLabContSelec);
        emailTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(emailTxtContSelec);
        JLabel adresseLabContSelec = new JLabel("Addresse :  ");
        panelInfosContSelec.add(adresseLabContSelec);
        adresseTxtContSelec.setOpaque(false);
        panelInfosContSelec.add(adresseTxtContSelec);

        JLabel favContactLabContSelec = new JLabel("Add contact to favorite : ");
        panelInfosContSelec.add(favContactLabContSelec);
        favCheckBoxContSelec.setFocusable(false);
        favCheckBoxContSelec.setEnabled(false); //Ne peut pas être modifier à ce moment-là
        panelInfosContSelec.add(favCheckBoxContSelec);
        try {
            contactSelec = contactList.getContactByName(nameTxtContSelec.getText(),fileContactList);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        buttonRemovePic = new JButton("Remove Picture");
        setFontButton(buttonRemovePic);
        panelRemovePic.add(buttonRemovePic);

        //Mise en page Panel du bas
        JPanel panelOkContSelec = new JPanel();
        panelSelectCentre.add(panelOkContSelec, BorderLayout.SOUTH);
        panelOkContSelec.setPreferredSize(new Dimension(400, 35));
        panelOkContSelec.setLayout(new GridLayout(1, 2));
        buttonCancelContSelec = new JButton("Cancel");
        setFontButton(buttonCancelContSelec);
        buttonEdit = new JButton("Edit");
        setFontButton(buttonEdit);
        panelOkContSelec.add(buttonCancelContSelec, BorderLayout.EAST);
        panelOkContSelec.add(buttonEdit, BorderLayout.WEST);

//PANEL CONTACT EDIT
        JPanel panelEditCentre = new JPanel();
        contactEdit.add(panelEditCentre, BorderLayout.EAST);
        //Mise en page panel centre
        panelEditCentre.setPreferredSize(new Dimension(350, 518));
        JPanel panelPictureEdit = new JPanel();
        panelEditCentre.add(panelPictureEdit);
        panelPictureEdit.setPreferredSize(SIZE_PANEL_PICTURE);
        buttonPictureEdit = new JButton();
        buttonPictureEdit.setSize(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE);
        setFontButton(buttonPictureEdit);
        panelPictureEdit.add(buttonPictureEdit);

        //panel avec infos à saisir
        JPanel panelInfosEdit = new JPanel();
        panelEditCentre.add(panelInfosEdit);
        JPanel panelDeleteEdit = new JPanel();
        panelEditCentre.add(panelDeleteEdit);
        panelInfosEdit.setPreferredSize(new Dimension(350, 341));
        panelInfosEdit.setLayout(new GridLayout(6, 2, 5, 2));

        JLabel nomLabEdit = new JLabel("Nom : ");
        panelInfosEdit.add(nomLabEdit);
        nameTxtEdit.setOpaque(false);
        panelInfosEdit.add(nameTxtEdit);
        JLabel indicatifLabEdit = new JLabel("Indicatif : ");
        panelInfosEdit.add(indicatifLabEdit);
        panelInfosEdit.add(indicChoiceEdit);
        JLabel numLabEdit = new JLabel("Numéro :  ");
        panelInfosEdit.add(numLabEdit);
        numTxtEdit.setOpaque(false);
        panelInfosEdit.add(numTxtEdit);
        JLabel emailLabEdit = new JLabel("Email :  ");
        panelInfosEdit.add(emailLabEdit);
        emailTxtEdit.setOpaque(false);
        panelInfosEdit.add(emailTxtEdit);
        JLabel adresseLabEdit = new JLabel("Addresse :  ");
        panelInfosEdit.add(adresseLabEdit);
        adresseTxtEdit.setOpaque(false);
        panelInfosEdit.add(adresseTxtEdit);

        JLabel favContactLabEdit = new JLabel("Add contact to favorite : ");
        panelInfosEdit.add(favContactLabEdit);
        favCheckBoxEdit.setFocusable(false);
        panelInfosEdit.add(favCheckBoxEdit);
        buttonDelete = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Delete.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        setFontButton(buttonDelete);
        panelDeleteEdit.add(buttonDelete);
        //Mise en page Panel du bas
        JPanel panelOkEdit = new JPanel();
        panelEditCentre.add(panelOkEdit, BorderLayout.SOUTH);
        panelOkEdit.setPreferredSize(new Dimension(400, 35));
        panelOkEdit.setLayout(new GridLayout(1, 2));
        buttonCancelEdit = new JButton("Cancel");
        setFontButton(buttonCancelEdit);
        buttonSaveChanges = new JButton("Save changes");
        setFontButton(buttonSaveChanges);
        panelOkEdit.add(buttonCancelEdit, BorderLayout.EAST);
        panelOkEdit.add(buttonSaveChanges, BorderLayout.WEST);

//PANEL CONTACT ADD
        JPanel PContactAddHaut = new JPanel();
        PContactAdd.add(PContactAddHaut, BorderLayout.NORTH);
        JPanel PContactAddCentre = new JPanel();
        PContactAdd.add(PContactAddCentre, BorderLayout.EAST);
        //Mise en page panel haut
        PContactAddHaut.setPreferredSize(new Dimension(400, 30));
        PContactAddHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labAddPanel = new JLabel("  Add a Contact  : ");
        PContactAddHaut.add(labAddPanel);
        //Mise en page panel centre
        PContactAddCentre.setPreferredSize(new Dimension(350, 498));
        JPanel panelPictureAdd = new JPanel();
        PContactAddCentre.add(panelPictureAdd);
        panelPictureAdd.setPreferredSize(SIZE_PANEL_PICTURE);

        buttonPictureAdd = new JButton();
        buttonPictureAdd.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
        setFontButton(buttonPictureAdd);
        panelPictureAdd.add(buttonPictureAdd);
        //panel avec infos à saisir
        JPanel PContactAddInfos = new JPanel();
        PContactAddCentre.add(PContactAddInfos);
        JPanel panelErreur = new JPanel();
        PContactAddCentre.add(panelErreur);
        PContactAddInfos.setPreferredSize(new Dimension(350, 332));
        PContactAddInfos.setLayout(new GridLayout(6, 2, 5, 2));

        JLabel nomLab = new JLabel("Nom : ");
        PContactAddInfos.add(nomLab);
        nameTxt.setOpaque(false);
        PContactAddInfos.add(nameTxt);
        JLabel indicatifLab = new JLabel("Indicatif : ");
        PContactAddInfos.add(indicatifLab);
        PContactAddInfos.add(indicChoice);
        JLabel numLab = new JLabel("Numéro :  ");
        PContactAddInfos.add(numLab);
        numTxt.setOpaque(false);
        PContactAddInfos.add(numTxt);
        JLabel emailLab = new JLabel("Email :  ");
        PContactAddInfos.add(emailLab);
        emailTxt.setOpaque(false);
        PContactAddInfos.add(emailTxt);
        JLabel adresseLab = new JLabel("Addresse :  ");
        PContactAddInfos.add(adresseLab);
        addressTxt.setOpaque(false);
        PContactAddInfos.add(addressTxt);

        //Ajouter contact au favori
        JLabel favContactLab = new JLabel("Add contact to favorite : ");
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
        JPanel panelOk = new JPanel();
        PContactAddCentre.add(panelOk,BorderLayout.SOUTH);
        panelOk.setPreferredSize(new Dimension(400, 35));
        panelOk.setLayout(new GridLayout(1, 2));
        buttonCancel = new JButton("Cancel");
        setFontButton(buttonCancel);
        buttonSave = new JButton("Save");
        setFontButton(buttonSave);
        panelOk.add(buttonCancel, BorderLayout.EAST);
        panelOk.add(buttonSave, BorderLayout.WEST);

//PANEL CONTACT SEARCH
        JPanel panelBack = new JPanel();
        contactSearch.add(panelBack);
        JPanel panelCentre = new JPanel();
        contactSearch.add(panelCentre);
        JPanel panelBas = new JPanel();
        contactSearch.add(panelBas);
        //Mise en page panel Back
        panelBack.setPreferredSize(new Dimension(400, 40));
        panelBack.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonBack = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Back2.1.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        setFontButton(buttonBack);
        panelBack.add(buttonBack);
        JLabel labSearchPanel = new JLabel("Search a Contact : ");
        panelBack.add(labSearchPanel);

        //Mise en page panel centre
        panelCentre.setPreferredSize(new Dimension(400, 50));
        ClickRecherche clickRecherche = new ClickRecherche(rechercheBar);
        rechercheBar.addFocusListener(clickRecherche);
        rechercheBar.setOpaque(false);
        buttonGoSearch = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Recherche.png")).getImage().getScaledInstance(SIZE_ICONE, SIZE_ICONE,Image.SCALE_SMOOTH)));
        setFontButton(buttonGoSearch);
        panelCentre.add(rechercheBar);
        panelCentre.add(buttonGoSearch);
        panelBas.setPreferredSize(new Dimension(400, 435));
        buttonContactFind.setPreferredSize(new Dimension(340,40));
        buttonContactFind.setBorderPainted(true);
        buttonContactFind.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonContactFind.setFocusPainted(false);
        buttonContactFind.setContentAreaFilled(false);
        buttonContactFind.setEnabled(false);
        buttonContactFind.addActionListener(new Actions());
        panelBas.add(buttonContactFind);

//PANEL SELECTION D'UNE PHOTO DANS GALERIE
        PHautSelecPhoto.setLayout(null);
        PHautSelecPhoto.setPreferredSize(new Dimension(400,45));
        CancelSelectionPic.setBounds(2,2,48,40);
        setFontButton(CancelSelectionPic);
        PHautSelecPhoto.add(CancelSelectionPic);
        labSelectPhoto.setBounds(50,5,300,40);
        PHautSelecPhoto.add(labSelectPhoto);
        SelectPictureOK.setBounds(350,5,50,40);
        setFontButton(SelectPictureOK);
        SelectPictureOK.setBorder(BorderFactory.createLineBorder(Color.black));
        PHautSelecPhoto.add(SelectPictureOK);
        PCentreSelectPhoto.setPreferredSize(new Dimension(400,400));
        PCentreSelectPhoto.add(gallery,"galery");
        PCentreSelectPhoto.setLayout(ecranGalery);
        panelSelecPhoto.add(PHautSelecPhoto, BorderLayout.NORTH);
        panelSelecPhoto.add(PCentreSelectPhoto, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * This method create the Gallery panel and display it in the.
     * @throws BusinessException – if the contact list / favorite contact list isn't generate
     *                              by the {@code readFromFile(File file) in the ContactList class.}
     *
     */
    private void openGaleryToSelecPic() {
        try {
            gallery = new PanelGallery(PCentreSelectPhoto,picture);
        } catch (BusinessException f) {
            f.printStackTrace();
        }
        ecranGalery.show(PCentreSelectPhoto,"galery");
    }

    /**
     * This method reset the picture's path with the path PICTURE_DEFAULT.
     */
    private void resetPicture(Picture picture){
        picture.setPath(PICTURE_DEFAULT);
    }
    /**
     * This method save both contact file and favourite contact file and update the contacts of the two JList.
     * @throws BusinessException – if the json cannot be written by the {@code saveToFile(File file) in the ContactList class.}
     *                           - if it cannot be read by the {@code getNameArrayFromJSON(File file) in the ContactList class.}
     */
    private void saveToJson(){
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
    /**
     * This method create the font for a button.
     * @param button - the JButton that we want to set the font and add the ActionListener
     */
    private void setFontButton(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new Actions());
    }
    /**
     * This method sets the information of the contact selected into the panel.
     * @param contactSelec - The contact we want to display information
     */
    private void setInfoFromContactSelected(Contact contactSelec){
        if (contactSelec == null) return;
        nameTxtContSelec.setText(contactSelec.getName());
        indicChoiceContSelec.setText(contactSelec.getIndicative());
        numTxtContSelec.setText(contactSelec.getPhoneNumber());
        emailTxtContSelec.setText(contactSelec.getEmail());
        adresseTxtContSelec.setText(contactSelec.getAddress());
        favCheckBoxContSelec.setSelected(contactSelec.isFavContact());

        if (!PICTURE_DEFAULT.equals(contactSelec.getPathForImage()))
            buttonPictureContSelec.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
        else
            buttonPictureContSelec.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));

    }
    /**
     * This method reset all the textFields, Jbutton and Checkbox to default texte/choice.
     */
    private void resetFields(){
        nameTxt.setText("");
        numTxt.setText("");
        indicChoice.setSelectedIndex(0);
        emailTxt.setText("");
        addressTxt.setText("");
        resetPicture(picture);
        buttonPictureAdd.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
        favCheckBox.setSelected(false);
    }

    /**
     * This class create List Selection Listener.
     * @see ListSelectionListener#valueChanged(ListSelectionEvent)
     */
    class ListSelectionListener implements javax.swing.event.ListSelectionListener{

        /**
         * This method displays the contact selected panel when selecting a name in the list.
         * @throws BusinessException – if Json File cannot be read by the {@code getContactByName(String string,File file) in the ContactList class.}
         */
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
                setInfoFromContactSelected(contactSelec);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        }
    }
    /**
     * This class create List Selection Listener.
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    class Actions implements ActionListener {

        /**
         * Invoked when an action on a JButtons occurs.
         * @param e the event to be processed
         * @throws BusinessException – if the contact List cannot be written into the Json File
         *                           - if contact already exist
         *                           - if name or phone number textField are empty when you want to add a contact
         *                           - if the contact we want to search has not been found
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Display the panelAdd when buttonAdd has been clicked on
            if (e.getSource() == buttonAdd) {
                ecran.show(mainPanel, "contactAdd");
            }

            //Display the panelSearch when buttonSearch has been clicked on
            if (e.getSource() == buttonSearch) {
                ecran.show(mainPanel, "contactSearch");
            }

            //Display the panelEdit when buttonEdit has been clicked on And set the information of the contact selected
            if (e.getSource() == buttonEdit) {
                if (contactSelec == null) return;
                nameTxtEdit.setText(contactSelec.getName());
                indicChoiceEdit.setSelectedItem(contactSelec.getIndicative());
                numTxtEdit.setText(contactSelec.getPhoneNumber());
                emailTxtEdit.setText(contactSelec.getEmail());
                adresseTxtEdit.setText(contactSelec.getAddress());
                favCheckBoxEdit.setSelected(contactSelec.isFavContact());

                if (!PICTURE_DEFAULT.equals(contactSelec.getPathForImage()))
                    buttonPictureEdit.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
                else
                    buttonPictureEdit.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));

                ecran.show(mainPanel, "contactedit");
            }

            //Delete the contact, add another one with the changes
            // And Display the panelcontact when buttonSaveChanges has been clicked on
            if (e.getSource() == buttonSaveChanges) {
                if (PICTURE_DEFAULT.equals(picture.getPath()))
                    if (!picture.getPath().equals(contactSelec.getPathForImage()))
                        picture.setPath(contactSelec.getPathForImage());

                contactList.delete(nameTxtContSelec.getText());
                favContactList.delete(nameTxtContSelec.getText());
                boolean favContact;
                favContact = favCheckBoxEdit.isSelected();
                Contact contact = new Contact(nameTxtEdit.getText(), (String) indicChoiceEdit.getSelectedItem(), numTxtEdit.getText(),
                        emailTxtEdit.getText(), adresseTxtEdit.getText(), picture.getPath(), favContact);
                if (contact.isFavContact()) {
                    try {
                        favContactList.addToContactList(contact);
                        favContactList.saveToFile(fileFavContactList);
                    } catch (BusinessException businessException) {
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
                    resetPicture(picture);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                ecran.show(mainPanel, "contactPage");
                updateUI();
            }

            //Delete the contact and save the list of contact without this contact
            if (e.getSource() == buttonDelete) {
                contactList.delete(nameTxtContSelec.getText());
                favContactList.delete(nameTxtContSelec.getText());
                saveToJson();
            }

            //Reset all the necessary fields when buttonBack or buttonCancel has been clicked on
            //And display the contactPanel
            if ((e.getSource() == buttonBack) || (e.getSource() == buttonCancel)) {
                resetFields();//Mise à zéro de tous les champs de saisie et de tout les autres champs aussi
                rechercheBar.setText("");
                LabelErreurRecherche.setVisible(false);
                labErreurSaisie.setText(" ");
                buttonContactFind.setText("Enter above the name of the contact you want to find");
                buttonContactFind.setBorder(BorderFactory.createLineBorder(Color.black));
                ecran.show(mainPanel, "contactPage");
            }

            //Reset the contact's information and his picture when buttonCancelEdit has been clicked on
            if (e.getSource() == buttonCancelEdit) {
                resetPicture(picture);
                buttonPictureEdit.setIcon(new ImageIcon(new ImageIcon(picture.getPath()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE, Image.SCALE_SMOOTH)));
                //buttonPictureContactAdd.setIcon(new ImageIcon(new ImageIcon(picture.getPath()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE, Image.SCALE_SMOOTH)));
                ecran.show(mainPanel, "contactselected");

            }

            //Reset fields in the panelContactSelected when buttonCancelContSelec has been clicked on
            if (e.getSource() == buttonCancelContSelec) {
                nameTxtContSelec.setText("");
                indicChoiceContSelec.setText("");
                numTxtContSelec.setText("");
                emailTxtContSelec.setText("");
                adresseTxtContSelec.setText("");
                favCheckBoxContSelec.setSelected(false);
                resetPicture(picture);
                JListContacts.clearSelection();
                JListFavContact.clearSelection();
                ecran.show(mainPanel, "contactPage");
            }

            //Display the panelSelecPhoto with the galery when buttonPictureEdit has been clicked on
            if (e.getSource() == buttonPictureEdit) {
                String lastPanel = "contactedit";
                CancelSelectionPic.setText(lastPanel);
                openGaleryToSelecPic();
                ecran.show(mainPanel, "panelSelecPhoto");
            }

            //Display the panelSelecPhoto with the galery when buttonPictureAdd has been clicked on
            if (e.getSource() == buttonPictureAdd) {
                String lastPanel = "contactAdd";
                CancelSelectionPic.setText(lastPanel);
                openGaleryToSelecPic();
                ecran.show(mainPanel, "panelSelecPhoto");
            }

            //Set the information in the textfield as parameter for a new contact, add it to the list of the contact
            //And display the panelContact
            if (e.getSource() == buttonSave) { //Sauvergarder un nouveau contact
                String nom = nameTxt.getText();
                String indicatif = (String) indicChoice.getSelectedItem(); //saisie = param du constructeur Contact
                String numero = numTxt.getText();
                String email = emailTxt.getText();
                String adresse = addressTxt.getText();
                boolean favContact;
                favContact = favCheckBox.isSelected();
                try {
                boolean contactOK = false;
                do {
                    if (nameTxt.getText().isEmpty() || numTxt.getText().isEmpty()) {
                        //Afficher erreur car champs oblig vide
                        System.err.println("Données contact vides");
                        labErreurSaisie.setText("Nom et/ou numéro du contact vide");
                            throw new BusinessException("Nom et/ou numéro du contact vide", ErrorCodes.CONTACT_INFORMATIONS_EMPTY);
                    } else {
                        if (contactList.containsNameInJson(nameTxt.getText(), fileContactList)) {
                            labErreurSaisie.setText("Contact Already exist !");
                            throw new BusinessException("Contact already exist", ErrorCodes.CONTACT_ALREADY_EXIST);
                        } else {
                            Contact contact = new Contact(nom, indicatif, numero, email, adresse, picture.getPath(), favContact);
                            if (contact.isFavContact()) {
                                favContactList.addToContactList(contact);//Ajouter à la liste de contact fav
                                favContactList.saveToFile(fileFavContactList);//Sauvegarde
                                JListFavContact.removeAll();
                                JListFavContact.setListData(contactList.getNameArrayFromJSON(fileFavContactList).toArray());
                            }
                            contactList.addToContactList(contact);//Ajout du contact à la liste de contact
                            contactList.saveToFile(fileContactList);//Sauvegarde
                            JListContacts.removeAll();
                            JListContacts.setListData(contactList.getNameArrayFromJSON(fileContactList).toArray());
                            updateUI();
                            resetFields(); //Mise à zéro des champs de saisie
                            contactOK = true;
                            ecran.show(mainPanel, "contactPage");
                        }
                    }
                } while (contactOK = false);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            }
            //Display on the buttonContactFind the name of the contact if existing in the Json File
            //If the contact does not exist, put message in the button instead of the name
            if (e.getSource() == buttonGoSearch) {
                String research = "";
                research = rechercheBar.getText();
                try {
                    if (contactList.containsNameInJson(research, fileContactList)) {
                        Contact searchedCont = contactList.getContactByName(research, fileContactList);
                        buttonContactFind.setText(searchedCont.getName());
                        buttonContactFind.setEnabled(true);
                        buttonContactFind.setBorder(BorderFactory.createTitledBorder("Contact Find"));
                    } else {
                        buttonContactFind.setText("Contact not found");
                        buttonContactFind.setBorder(BorderFactory.createLineBorder(Color.black));
                        buttonContactFind.setEnabled(false);
                        throw new BusinessException("Contact not found", ErrorCodes.CONTACT_NOT_FOUND);
                    }
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                rechercheBar.setText("");
            }
            //Display the panelContactSelected when buttonContactFind has been clicked on
            if (e.getSource() == buttonContactFind) {
                String selectedContact;
                ecran.show(mainPanel, "contactselected");
                try {
                    selectedContact = buttonContactFind.getText();
                    contactSelec = contactList.getContactByName(selectedContact, fileContactList);
                    setInfoFromContactSelected(contactSelec);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            }

            //Display the panelAdd or panelEdit without any changes when CancelSelectionPic has been clicked on
            if (e.getSource() == CancelSelectionPic) {
                resetPicture(picture);
                if (CancelSelectionPic.getText().equals("contactAdd"))
                    buttonPictureAdd.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")).getImage().getScaledInstance(SIZE_CONTACT_PICTURE,SIZE_CONTACT_PICTURE,Image.SCALE_SMOOTH)));
                else
                    buttonPictureEdit.setIcon(new ImageIcon(new ImageIcon(contactSelec.getPathForImage()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE, Image.SCALE_SMOOTH)));
                ecran.show(mainPanel, CancelSelectionPic.getText());
                CancelSelectionPic.setText(null);
            }

            //Display the panelAdd or panelEdit with the picture change when SelectPictureOK has been clicked on
            if (e.getSource() == SelectPictureOK) {
                ImageIcon picChoose = new ImageIcon(new ImageIcon(picture.getPath()).getImage().getScaledInstance(SIZE_CONTACT_PICTURE, SIZE_CONTACT_PICTURE, Image.SCALE_SMOOTH));
                if (CancelSelectionPic.getText().equals("contactAdd"))
                    buttonPictureAdd.setIcon(picChoose);
                   // buttonPictureContactAdd.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icone_AddPicture.png")));
                else
                    buttonPictureEdit.setIcon(picChoose);
                ecran.show(mainPanel, CancelSelectionPic.getText());
            }

            //Delete the picture of the contact and reset it with the PICTURE_DEFAULT
            if (e.getSource() == buttonRemovePic) {
                Contact contact = new Contact(contactSelec.getName(), contactSelec.getIndicative(), contactSelec.getPhoneNumber(), contactSelec.getEmail(), contactSelec.getAddress(), PICTURE_DEFAULT, contactSelec.isFavContact());
                contactList.delete(contactSelec.getName());
                favContactList.delete(contactSelec.getName());
                if (contact.isFavContact()) {
                    try {
                        favContactList.addToContactList(contact);
                        contactList.addToContactList(contact); //Ajout du contact à la liste de contact
                    } catch (BusinessException businessException) {
                        businessException.printStackTrace();
                    }
                }
                saveToJson();
            }
        }
    }
}

/**
 * These Inner Classes provide panels to Panel Contact.
 */
class PanelSelecPhoto extends JPanel {
    public PanelSelecPhoto(){

    }
}
class PanelSearch extends JPanel {
    public PanelSearch(){
    }
}
class PanelEdit  extends JPanel {
    public PanelEdit(){
    }
}
class PanelContactSelected extends JPanel {
    public PanelContactSelected() {
    }
}
class PanelAdd extends JPanel {
    public PanelAdd() {
    }
}