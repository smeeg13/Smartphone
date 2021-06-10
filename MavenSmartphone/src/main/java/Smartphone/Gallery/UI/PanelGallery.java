package Smartphone.Gallery.UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.Core.*;


public class PanelGallery extends JPanel {

    private CardLayout screen = new CardLayout();

    private Album currentAlbum;

    private JPanel mainPanel = new JPanel();
    private JPanel galleryPanel = new JPanel();
    private JPanel picturePanel = new JPanel();
    private JPanel addPanel = new JPanel();

    public PanelGallery() throws BusinessException {
        //création d'une galerie vierge
        currentAlbum =new Gallery();

        mainPanel.setPreferredSize(new Dimension(400,650));
        mainPanel.setLayout(screen);

        mainPanel.add(galleryPanel,"galleryPanel");
        mainPanel.add(picturePanel,"picturePanel");
        mainPanel.add(addPanel,"addPanel");

        createAddPanel();
        showAlbum(currentAlbum);

        add(mainPanel);
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
        renameAppBarPicture.setPreferredSize(new Dimension(400,40));
        renameAppBarPicture.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel defaultAppBarPicture = new JPanel();
        defaultAppBarPicture.setPreferredSize(new Dimension(400,40));
        defaultAppBarPicture.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel appBarPicture = new JPanel(); //va recevoir le cardLayout
        CardLayout appBarPictureLayout = new CardLayout();
        appBarPicture.setLayout(appBarPictureLayout);
        appBarPicture.add(defaultAppBarPicture,"defaultAppBarPicture");
        appBarPicture.add(renameAppBarPicture,"renameAppBarPicture");


        JButton backPicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png",25,25));
        backPicture = setTheIcon(backPicture);
        backPicture.addActionListener(e -> showAlbum(currentAlbum));

        JButton renamePicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_rename.png",30,30));
        renamePicture = setTheIcon(renamePicture);
        renamePicture.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "renameAppBarPicture"));

        JButton deletePicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png",25,25));
        deletePicture = setTheIcon(deletePicture);
        deletePicture.addActionListener(e -> {
            currentAlbum.deleteImage(picture);
            showAlbum(currentAlbum);
        });

        //gére le panel de rename
        JButton cancelRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", 25, 25));
        cancelRename = setTheIcon(cancelRename);
        cancelRename.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "defaultAppBarPicture"));

        JTextField newNameOfThePicture = new JTextField(10);
        JButton acceptRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", 25, 25));
        acceptRename = setTheIcon(acceptRename);
        acceptRename.addActionListener(e -> {
            picture.rename(newNameOfThePicture.getText());
            currentAlbum.refresh();
            showAlbum(currentAlbum);
        });

        renameAppBarPicture.add(newNameOfThePicture);
        renameAppBarPicture.add(cancelRename);
        renameAppBarPicture.add(acceptRename);

        JLabel nameOfPicture = new JLabel(picture.getName());

        defaultAppBarPicture.add(backPicture);
        defaultAppBarPicture.add(nameOfPicture);
        defaultAppBarPicture.add(renamePicture);
        defaultAppBarPicture.add(deletePicture);

        return appBarPicture;
    }

    public JPanel createShowPicture(Picture picture){
        ImageIcon icon = new ImageIcon(picture.getPath());
        JPanel showPicture = new JPanel();
        showPicture.setPreferredSize(new Dimension(350,350));
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
        defaultAppBarGallery.setPreferredSize(new Dimension(400,40));
        defaultAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel renameAppBarGallery = new JPanel(); // la barre quand on rename
        renameAppBarGallery.setPreferredSize(new Dimension(400,40));
        renameAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel deleteAppBarGallery = new JPanel(); // la barre quand on delete
        deleteAppBarGallery.setPreferredSize(new Dimension(400,40));
        deleteAppBarGallery.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel appBarGallery = new JPanel(); //va recevoir le cardLayout
        CardLayout appBarGalleryLayout = new CardLayout();
        appBarGallery.setLayout(appBarGalleryLayout);
        appBarGallery.add(defaultAppBarGallery,"defaultAppBarGallery");
        appBarGallery.add(renameAppBarGallery,"renameAppBarGallery");
        appBarGallery.add(deleteAppBarGallery,"deleteAppBarGallery");

        if(currentAlbum.getParent()!=null){

            JButton cancelRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", 25, 25));
            cancelRename = setTheIcon(cancelRename);
            cancelRename.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JTextField newNameOfTheAlbum = new JTextField(10);
            JButton acceptRename = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", 25, 25));
            acceptRename = setTheIcon(acceptRename);
            acceptRename.addActionListener(e -> {
                currentAlbum.renameAlbum(newNameOfTheAlbum.getText());
                currentAlbum.getParent().refresh();
                showAlbum(currentAlbum.getParent());
            });

            renameAppBarGallery.add(newNameOfTheAlbum);
            renameAppBarGallery.add(cancelRename);
            renameAppBarGallery.add(acceptRename);

            JButton cancelDelete = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png", 25, 25));
            cancelDelete = setTheIcon(cancelDelete);
            cancelDelete.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JButton acceptDelete = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png", 25, 25));
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

            JButton renameAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_rename.png", 30, 30));
            renameAlbum = setTheIcon(renameAlbum);
            renameAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "renameAppBarGallery"));

            JButton deleteAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Delete.png", 25, 25));
            deleteAlbum = setTheIcon(deleteAlbum);
            deleteAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "deleteAppBarGallery"));


            JButton backAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png", 25, 25));
            backAlbum = setTheIcon(backAlbum);
            backAlbum.addActionListener(e -> showAlbum(currentAlbum.getParent()));

            JLabel nameOfAlbum = new JLabel(currentAlbum.getName());

            defaultAppBarGallery.add(backAlbum);
            defaultAppBarGallery.add(nameOfAlbum);
            defaultAppBarGallery.add(renameAlbum);
            defaultAppBarGallery.add(deleteAlbum);

        }
        JButton add = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Plus.png", 25, 25));
        add = setTheIcon(add);
        add.addActionListener(e -> screen.show(mainPanel, "addPanel"));
        defaultAppBarGallery.add(add);

        return appBarGallery;
    }

    public JScrollPane fillGridLayout(Album a){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(380,a.numberOfRows()*150));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridLayout showContents = new GridLayout(a.numberOfRows(), 3,10,10); //nombre d'élément /3
        panel.setLayout(showContents);
        for (Album al:a.getAlbumList()) {
            JPanel albums = new JPanel();
            JButton albumContents = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_albumsContent.PNG",100,100));
            albumContents = setTheIcon(albumContents);
            albumContents.addActionListener(e -> showAlbum(al));
            albums.add(albumContents);
            JLabel nameOfElement = new JLabel(al.getName() + " (" + al.numberOfElements() + ")");
            albums.add(nameOfElement,BorderLayout.SOUTH);
            panel.add(albums);
        }
        for (Picture p:a.getPictureList()){
            System.out.println(a.getPictureList());
            JPanel pictures = new JPanel();
            JButton pictureContents = new JButton(addImageIconJButton(a.getPath().toString() + "/" + p.getName(),100,100));
            pictureContents = setTheIcon(pictureContents);
            pictureContents.addActionListener(e -> showPicture(p));
            pictures.add(pictureContents, BorderLayout.NORTH);
            JLabel nameOfElement = new JLabel(p.getName());
            pictures.add(nameOfElement,BorderLayout.SOUTH);
            panel.add(pictures);
        }

        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(400,500));
        return scroll;
    }

    public void createAddPanel(){
        JPanel defaultAppBarAdd = new JPanel(); // la app bar présente par défaut
        JPanel appBarNewAlbum = new JPanel(); //la app bar présente quand on va créer un nouvel album
        JPanel addContents = new JPanel(); // la partie centrale où on pourra choisir ce qu'on veut créer
        JPanel appBarPanel = new JPanel();
        appBarPanel.setPreferredSize(new Dimension(400,40));
        CardLayout appBarAddLayout = new CardLayout();
        appBarPanel.setLayout(appBarAddLayout);
        appBarPanel.add(defaultAppBarAdd,"defaultAppBarAdd");
        appBarPanel.add(appBarNewAlbum,"appBarNewAlbum");
        addPanel.add(appBarPanel);

        defaultAppBarAdd.setPreferredSize(new Dimension(400,40));
        defaultAppBarAdd.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backAdd = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/Fleche_Back.png",25,25));
        backAdd = setTheIcon(backAdd);
        backAdd.addActionListener(e -> screen.show(mainPanel,"galleryPanel"));

        defaultAppBarAdd.add(backAdd);

        appBarNewAlbum.setPreferredSize(new Dimension(400,40));
        appBarNewAlbum.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cancelAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Cancel.png",25,25));
        cancelAlbum = setTheIcon(cancelAlbum);
        cancelAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"defaultAppBarAdd"));

        JTextField newNameOfTheAlbum = new JTextField(10);

        JButton acceptAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Validate.png",25,25));
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
        addContents.setPreferredSize(new Dimension(400,400));
        addContents.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addAPicture = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Photo.jpg",100,100));
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

        JButton addAAlbum = new JButton(addImageIconJButton("MavenSmartphone/src/main/java/Smartphone/Icones/icone_Album.jpg",100,100));
        addAAlbum = setTheIcon(addAAlbum);
        addAAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"appBarNewAlbum"));

        addContents.add(addAPicture);
        addContents.add(addAAlbum);
    }

    public ImageIcon addImageIconJButton(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }

    public JButton setTheIcon(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public boolean testString(String string){
        if(string.contains("\\")) return true;
        if(string.contains("/")) return true;
        if(string.contains(":")) return true;
        if(string.contains("*")) return true;
        if(string.contains("?")) return true;
        if(string.contains("\"")) return true;
        if(string.contains("<")) return true;
        if(string.contains(">")) return true;
        if(string.contains("|")) return true;
        return false;
    }
}

