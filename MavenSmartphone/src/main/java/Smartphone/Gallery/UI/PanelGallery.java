package Smartphone.Gallery.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.Core.*;


public class PanelGallery extends JPanel {

    public final int IMAGE_SIZE = 350;
    private final int IMAGE_SIZE_ON_BUTTON = 100;
    private final int IMAGE_SIZE_ON_APPBAR = 25;
    private final int PICTURE_NAME_LENGTH = 15;
    private final int ALBUM_NAME_LENGTH = 10;
    private final int TEXTFIELD_SIZE = 10;
    private final int GRID_GAP_WIDTH = 10;
    private final int GRID_GAP_HEIGHT = 10;
    private final int GRID_COLUMN_LENGTH = 3;
    private final int GRID_ROW_LENGTH = 3;
    private final int SIZE_OF_ROW = 155;
    private final int HEIGHT_OF_APPBAR = 40;
    private final int WIDTH_OF_SCREEN = 400;
    private final int HEIGT_OF_SCREEN = 650;
    private final int HEIGT_OF_CONTENTS = 500;

    private CardLayout screen = new CardLayout();

    private Album currentAlbum;

    private JPanel mainPanel = new JPanel();
    private JPanel galleryPanel = new JPanel();
    private JPanel picturePanel = new JPanel();
    private JPanel addPanel = new JPanel();
    private JPanel allPicturePanel = new JPanel();

    public PanelGallery() throws BusinessException {
        //création d'une galerie vierge
        currentAlbum =new Gallery();

        mainPanel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_SCREEN));
        mainPanel.setLayout(screen);

        mainPanel.add(galleryPanel,"galleryPanel");
        mainPanel.add(picturePanel,"picturePanel");
        mainPanel.add(addPanel,"addPanel");

        createAddPanel();
        showAlbum(currentAlbum);

        add(mainPanel);

    }
    //constructeur pour l'ajout d'une photo au contact
    public PanelGallery(JPanel panel, CardLayout cardLayout, Contact contact) throws BusinessException{
        currentAlbum =new Gallery();

        panel.add(allPicturePanel,"galleryPanel");
        showAllPicture(currentAlbum,panel,cardLayout,contact);
    }

    public void showAllPicture(Album a, JPanel panel, CardLayout cardLayout,Contact contact){
        allPicturePanel.add(createAppBarAllPicture(a,panel,cardLayout),BorderLayout.NORTH);
        allPicturePanel.add(fillGridAllPicture(a,panel, cardLayout,contact));
    }

    public JPanel createAppBarAllPicture(Album a, JPanel panel, CardLayout cardLayout){
        JPanel appBarAllPicture = new JPanel();
        appBarAllPicture.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        appBarAllPicture.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backToContact = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Back.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        backToContact = setTheIcon(backToContact);
        backToContact.addActionListener(e -> cardLayout.show(panel, "contactedit"));

        JLabel choosingLabel = new JLabel("Click on a picture to select it");

        appBarAllPicture.add(backToContact);
        appBarAllPicture.add(choosingLabel);

        return appBarAllPicture;
    }

    public JScrollPane fillGridAllPicture(Album a,JPanel mPanel, CardLayout cardLayout,Contact contact){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN-2* GRID_GAP_WIDTH,numberOfRows(a.getAllPictureList().size())*SIZE_OF_ROW));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridLayout showContents = new GridLayout(numberOfRows(a.getAllPictureList().size()), GRID_COLUMN_LENGTH, GRID_GAP_HEIGHT, GRID_GAP_WIDTH); //nombre d'élément /3
        panel.setLayout(showContents);
        for (Picture p:a.getAllPictureList()){
            JButton pictureContents = new JButton(addImageIconJButton(a.getPath().toString() + "/" + p.getName(),IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
            pictureContents = setTheIcon(pictureContents);
            pictureContents.addActionListener(e -> {
                contact.setPathForImage(p.getPath());
                cardLayout.show(mPanel, "contactedit");
            });
            panel.add(pictureContents);
        }
        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        return scroll;
    }

    //gère le picturePanel

    public void showPicture(Picture picture){
        picturePanel.removeAll();
        picturePanel.add(createAppBarPicture(picture),BorderLayout.NORTH);
        picturePanel.add(createShowPicture(picture),BorderLayout.CENTER);
        picturePanel.validate();
        picturePanel.repaint();
        screen.show(mainPanel, "picturePanel");
    }

    public JPanel createAppBarPicture(Picture picture){

        JPanel renameAppBarPicture = new JPanel(); // la barre quand on rename
        renameAppBarPicture.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        renameAppBarPicture.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel defaultAppBarPicture = new JPanel();
        defaultAppBarPicture.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        defaultAppBarPicture.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel appBarPicture = new JPanel(); //va recevoir le cardLayout
        CardLayout appBarPictureLayout = new CardLayout();
        appBarPicture.setLayout(appBarPictureLayout);
        appBarPicture.add(defaultAppBarPicture,"defaultAppBarPicture");
        appBarPicture.add(renameAppBarPicture,"renameAppBarPicture");


        JButton backPicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        backPicture = setTheIcon(backPicture);
        backPicture.addActionListener(e -> showAlbum(currentAlbum));

        JButton renamePicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_rename.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        renamePicture = setTheIcon(renamePicture);
        renamePicture.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "renameAppBarPicture"));

        JButton deletePicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        deletePicture = setTheIcon(deletePicture);
        deletePicture.addActionListener(e -> {
            currentAlbum.deleteImage(picture);
            showAlbum(currentAlbum);
        });

        //gére le panel de rename
        JButton cancelRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
        cancelRename = setTheIcon(cancelRename);
        cancelRename.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "defaultAppBarPicture"));

        JTextField newNameOfThePicture = new JTextField(TEXTFIELD_SIZE);
        JButton acceptRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
        acceptRename = setTheIcon(acceptRename);
        acceptRename.addActionListener(e -> {
            picture.rename(newNameOfThePicture.getText());
            currentAlbum.refresh();
            showAlbum(currentAlbum);
        });

        renameAppBarPicture.add(newNameOfThePicture);
        renameAppBarPicture.add(cancelRename);
        renameAppBarPicture.add(acceptRename);

        JLabel nameOfPicture = new JLabel(checkPictureName(picture));

        defaultAppBarPicture.add(backPicture);
        defaultAppBarPicture.add(nameOfPicture);
        defaultAppBarPicture.add(renamePicture);
        defaultAppBarPicture.add(deletePicture);

        return appBarPicture;
    }

    public JPanel createShowPicture(Picture picture){
        ImageIcon icon = addPictureIcon(picture.getPath(),IMAGE_SIZE,IMAGE_SIZE);
        JPanel showPicture = new JPanel();
        showPicture.setPreferredSize(new Dimension(IMAGE_SIZE,IMAGE_SIZE));
        showPicture.add(new JLabel(icon));
        return showPicture;
    }

    //gère le galleryPanel
    public void showAlbum(Album album){
        currentAlbum=album;
        galleryPanel.removeAll();
        galleryPanel.add(createAppBar(),BorderLayout.NORTH);
        JScrollPane contents = fillGridLayout(currentAlbum);
        galleryPanel.add(contents,BorderLayout.CENTER);
        galleryPanel.validate();
        galleryPanel.repaint();
        screen.show(mainPanel, "galleryPanel");
    }

    public JPanel createAppBar(){

        JPanel defaultAppBarGallery = new JPanel(); //la barre par défaut
        defaultAppBarGallery.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        defaultAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel renameAppBarGallery = new JPanel(); // la barre quand on rename
        renameAppBarGallery.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        renameAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel deleteAppBarGallery = new JPanel(); // la barre quand on delete
        deleteAppBarGallery.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        deleteAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel appBarGallery = new JPanel(); //va recevoir le cardLayout
        CardLayout appBarGalleryLayout = new CardLayout();
        appBarGallery.setLayout(appBarGalleryLayout);
        appBarGallery.add(defaultAppBarGallery,"defaultAppBarGallery");
        appBarGallery.add(renameAppBarGallery,"renameAppBarGallery");
        appBarGallery.add(deleteAppBarGallery,"deleteAppBarGallery");

        if(currentAlbum.getParent()!=null){

            JButton cancelRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            cancelRename = setTheIcon(cancelRename);
            cancelRename.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JTextField newNameOfTheAlbum = new JTextField(TEXTFIELD_SIZE);
            JButton acceptRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            acceptRename = setTheIcon(acceptRename);
            acceptRename.addActionListener(e -> {
                currentAlbum.renameAlbum(newNameOfTheAlbum.getText());
                currentAlbum.getParent().refresh();
                showAlbum(currentAlbum.getParent());
            });

            renameAppBarGallery.add(newNameOfTheAlbum);
            renameAppBarGallery.add(cancelRename);
            renameAppBarGallery.add(acceptRename);

            JButton cancelDelete = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            cancelDelete = setTheIcon(cancelDelete);
            cancelDelete.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JButton acceptDelete = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            acceptDelete = setTheIcon(acceptDelete);
            acceptDelete.addActionListener(e -> {
                Album a = currentAlbum.getParent();
                a.deleteAlbum(currentAlbum);
                a.refresh();
                showAlbum(a);
            });

            JLabel label = new JLabel(" Do you want to delete this album");

            deleteAppBarGallery.add(label);
            deleteAppBarGallery.add(cancelDelete);
            deleteAppBarGallery.add(acceptDelete);

            JButton renameAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_rename.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            renameAlbum = setTheIcon(renameAlbum);
            renameAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "renameAppBarGallery"));

            JButton deleteAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            deleteAlbum = setTheIcon(deleteAlbum);
            deleteAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "deleteAppBarGallery"));


            JButton backAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
            backAlbum = setTheIcon(backAlbum);
            backAlbum.addActionListener(e -> showAlbum(currentAlbum.getParent()));

            JLabel nameOfAlbum = new JLabel(checkAlbumName(currentAlbum));

            defaultAppBarGallery.add(backAlbum);
            defaultAppBarGallery.add(nameOfAlbum);
            defaultAppBarGallery.add(renameAlbum);
            defaultAppBarGallery.add(deleteAlbum);
        }

        JButton add = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Plus.png", IMAGE_SIZE_ON_APPBAR, IMAGE_SIZE_ON_APPBAR));
        add = setTheIcon(add);
        add.addActionListener(e -> screen.show(mainPanel, "addPanel"));
        defaultAppBarGallery.add(add);

        return appBarGallery;
    }

    public JScrollPane fillGridLayout(Album a){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN-2* GRID_GAP_WIDTH,numberOfRows(a.numberOfElements())*SIZE_OF_ROW));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridLayout showContents = new GridLayout(numberOfRows(a.numberOfElements()), GRID_COLUMN_LENGTH, GRID_GAP_HEIGHT, GRID_GAP_WIDTH); //nombre d'élément /3
        panel.setLayout(showContents);
        for (Album al:a.getAlbumList()) {
            JPanel albums = new JPanel();
            JButton albumContents = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_albumsContent.PNG",IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
            albumContents = setTheIcon(albumContents);
            albumContents.addActionListener(e -> showAlbum(al));
            albums.add(albumContents);
            JLabel nameOfElement = new JLabel(checkAlbumName(al) + " (" + al.numberOfElements() + ")");
            albums.add(nameOfElement,BorderLayout.SOUTH);
            panel.add(albums);
        }
        for (Picture p:a.getPictureList()){
            JPanel pictures = new JPanel();
            JButton pictureContents = new JButton(addImageIconJButton(a.getPath().toString() + "/" + p.getName(),IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
            pictureContents = setTheIcon(pictureContents);
            pictureContents.addActionListener(e -> showPicture(p));
            pictures.add(pictureContents, BorderLayout.NORTH);
            JLabel nameOfElement = new JLabel(checkPictureName(p));
            pictures.add(nameOfElement,BorderLayout.SOUTH);
            panel.add(pictures);
        }

        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        return scroll;
    }

    public void createAddPanel(){
        JPanel defaultAppBarAdd = new JPanel(); // l'app bar présente par défaut
        JPanel appBarNewAlbum = new JPanel(); //l'app bar présente quand on va créer un nouvel album
        JPanel addContents = new JPanel(); // la partie centrale où on pourra choisir ce qu'on veut créer
        JPanel appBarPanel = new JPanel();
        appBarPanel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        CardLayout appBarAddLayout = new CardLayout();
        appBarPanel.setLayout(appBarAddLayout);
        appBarPanel.add(defaultAppBarAdd,"defaultAppBarAdd");
        appBarPanel.add(appBarNewAlbum,"appBarNewAlbum");
        addPanel.add(appBarPanel);

        defaultAppBarAdd.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        defaultAppBarAdd.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backAdd = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        backAdd = setTheIcon(backAdd);
        backAdd.addActionListener(e -> screen.show(mainPanel,"galleryPanel"));

        defaultAppBarAdd.add(backAdd);

        appBarNewAlbum.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        appBarNewAlbum.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cancelAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        cancelAlbum = setTheIcon(cancelAlbum);
        cancelAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"defaultAppBarAdd"));

        JTextField newNameOfTheAlbum = new JTextField(TEXTFIELD_SIZE);

        JButton acceptAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png",IMAGE_SIZE_ON_APPBAR,IMAGE_SIZE_ON_APPBAR));
        acceptAlbum = setTheIcon(acceptAlbum);
        acceptAlbum.addActionListener(e -> {
            try {
                String newName = newNameOfTheAlbum.getText();
                if(testString(newName))throw new BusinessException("\"You can't create an album with one of these following characters \\\\/:*?\\\"<>|");
                newName = currentAlbum.getPath().toString()+"/" + newName;
                Path path = Paths.get(newName);
                Album a = new Album(path,currentAlbum);
                currentAlbum.refresh();
                showAlbum(a);
            } catch (BusinessException businessException) {businessException.printStackTrace();}
        });

        appBarNewAlbum.add(newNameOfTheAlbum);
        appBarNewAlbum.add(cancelAlbum);
        appBarNewAlbum.add(acceptAlbum);

        addPanel.add(addContents,BorderLayout.CENTER);
        addContents.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        addContents.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addAPicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Photo.jpg",IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
        addAPicture = setTheIcon(addAPicture);
        addAPicture.addActionListener(e -> {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", "png", "bmp", "jpg","jpeg","PNG");
            fileChooser.setFileFilter(filter);
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            File dest= new File(currentAlbum.getPath().toString()+"/" + file.getName());
            file.renameTo(dest);
            currentAlbum.refresh();
            showAlbum(currentAlbum);
        });

        JButton addAAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Album.jpg",IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
        addAAlbum = setTheIcon(addAAlbum);
        addAAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"appBarNewAlbum"));

        addContents.add(addAPicture);
        addContents.add(addAAlbum);
    }

    public int numberOfRows(int a){
        int numberOfRows = a / GRID_ROW_LENGTH;
        if(a % GRID_ROW_LENGTH != 0)
            numberOfRows += 1;
        return numberOfRows;
    }

    public String checkPictureName(Picture p){
        String str;
        if(p.getName().lastIndexOf('.')> PICTURE_NAME_LENGTH)
             return p.getName().substring(0, PICTURE_NAME_LENGTH)+"...";
        else
            return p.getName()+" ";
    }

    public String checkAlbumName(Album a){
        if(a.getName().length()>= ALBUM_NAME_LENGTH)
            return a.getName().substring(0, ALBUM_NAME_LENGTH)+"...";
        else
            return a.getName()+" ";

    }

    public boolean testString(String string){
        return  string.contains("\\")||
                string.contains("/")||
                string.contains(":")||
                string.contains("*")||
                string.contains("?")||
                string.contains("\"")||
                string.contains("<")||
                string.contains(">")||
                string.contains("|");
    }

    public ImageIcon addImageIconJButton(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }

    public ImageIcon addPictureIcon(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        try {
            File f = new File (path);
            BufferedImage image = ImageIO.read(f);
            double width2 = image.getWidth();
            double height2 = image.getHeight();
            double divisor;
            if(width2<height2)
                divisor=height2/height;
            else
                divisor=width2/width;
            if(divisor==0) throw new BusinessException("can't scale an image of this size");
            width2=width2/divisor;
            height2=height2/divisor;
            Image newTest = imagetest.getScaledInstance((int)width2, (int)height2, Image.SCALE_SMOOTH);
            return new ImageIcon(newTest);
        } catch (IOException | BusinessException e) {
            e.printStackTrace();
        }
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newTest);
    }

    public JButton setTheIcon(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}

