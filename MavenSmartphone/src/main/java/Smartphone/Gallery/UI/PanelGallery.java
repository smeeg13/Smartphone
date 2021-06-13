package Smartphone.Gallery.UI;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.Core.*;
import Smartphone.ToolBox;

/**
 * This class provides graphical implementations for a gallery using {@link javax.swing}  and {@link java.awt}.
 *
 * It use all sub-classes in {@link Smartphone.Gallery.Core}.
 *
 * @author Nathan Dély
 * @version
 */

public class PanelGallery extends JPanel {

    private final int IMAGE_SIZE = 350;
    private final int IMAGE_SIZE_ON_BUTTON = 100;
    private final int IMAGE_SIZE_IN_APPBAR = 25;
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
    private final String RENAME_ERROR = "You can't create an album with one of \n these following characters \\/:*?\"<>|";

    private CardLayout screen = new CardLayout();

    private Album currentAlbum;

    private JPanel mainPanel = new JPanel();
    private JPanel galleryPanel = new JPanel();
    private JPanel picturePanel = new JPanel();
    private JPanel addPanel = new JPanel();

    private ToolBox toolBox = new ToolBox();

    /**
     * This constructor provides the gallery display.
     * @throws BusinessException – if the gallery isn't generate by the {@code file.mkdir() in the Gallery class.}
     *
     */

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

    /**
     * This constructor set the gallery display on another class (by a panel).
     * @param mPanel – the JPanel to add the GridLayout with pictures
     * @param picture – the Picture needed for {@code showAllPicture()}
     * @throws BusinessException – if the gallery isn't generate by the {@code file.mkdir() in the Gallery class.}
     */
    //constructeur pour l'ajout d'une photo au contact
    public PanelGallery(JPanel mPanel, Picture picture) throws BusinessException{
        currentAlbum =new Gallery();
        mPanel.add(fillGridAllPicture(currentAlbum,picture));
    }


    /**
     * This method provides the all picture list and to choose one to set the Contact photo.
     * @param a – the current Album(current location)
     * @param picture – the Picture in the PanelContact to set the photo
     * @return – the JScrollPane containing a GridLayout with a picture in each cells
     */

    public JScrollPane fillGridAllPicture(Album a, Picture picture){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN - 2 * GRID_GAP_WIDTH, rowsLength(a.getAllPictureList().size())*SIZE_OF_ROW));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridLayout showContents = new GridLayout(rowsLength(a.getAllPictureList().size()), GRID_COLUMN_LENGTH, GRID_GAP_HEIGHT, GRID_GAP_WIDTH); //nombre d'élément /3
        panel.setLayout(showContents);
        for (Picture p:a.getAllPictureList()){
            JButton pictureContents = new JButton(toolBox.addPictureIcon(p.getPath(),IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
            pictureContents = setTheIcon(pictureContents);
            pictureContents.addActionListener(e -> {
                picture.setPath((p.getPath()));
                JOptionPane.showMessageDialog(panel,"You select this picture","SELECTED",JOptionPane.WARNING_MESSAGE);
            });
            panel.add(pictureContents);
        }
        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        return scroll;
    }

    /**
     * Show the picture display (picturePanel in the CardLayout).
     * @param picture – the Picture that appears in the JPanel picturePanel
     */

    public void showPicture(Picture picture){
        picturePanel.removeAll();
        picturePanel.add(createAppBarPicture(picture),BorderLayout.NORTH);
        picturePanel.add(createShowPicture(picture),BorderLayout.CENTER);
        picturePanel.validate();
        picturePanel.repaint();
        screen.show(mainPanel, "picturePanel");
    }

    /**
     * Create a new app bar of the picture display.
     * @param picture – Picture to set the name of this on the app bar
     * @return – the JPanel containing the app bar
     */

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


        JButton backPicture = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Fleche_Back.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        backPicture = setTheIcon(backPicture);
        backPicture.addActionListener(e -> showAlbum(currentAlbum));

        JButton renamePicture = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_rename.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        renamePicture = setTheIcon(renamePicture);
        renamePicture.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "renameAppBarPicture"));

        JButton deletePicture = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Delete.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        deletePicture = setTheIcon(deletePicture);
        deletePicture.addActionListener(e -> {
            currentAlbum.deleteImage(picture);
            showAlbum(currentAlbum);
        });

        JButton cancelRename = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Cancel.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        cancelRename = setTheIcon(cancelRename);
        cancelRename.addActionListener(e -> appBarPictureLayout.show(appBarPicture, "defaultAppBarPicture"));

        JTextField newNameOfThePicture = new JTextField(TEXTFIELD_SIZE);
        JButton acceptRename = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Validate.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        acceptRename = setTheIcon(acceptRename);
        acceptRename.addActionListener(e -> {
            try {
                String newName = newNameOfThePicture.getText();
                if(testString(newName))throw new BusinessException(RENAME_ERROR,mainPanel);
                picture.rename(newName);
                showAlbum(currentAlbum);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
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

    /**
     * This method creates the content of the picture display. (shows the picture).
     * @param picture – the Picture displayed
     * @return – the JPanel showing the picture
     */

    public JPanel createShowPicture(Picture picture){
        ImageIcon icon = toolBox.addPictureIcon(picture.getPath(),IMAGE_SIZE,IMAGE_SIZE);
        JPanel showPicture = new JPanel();
        showPicture.setPreferredSize(new Dimension(IMAGE_SIZE,IMAGE_SIZE));
        showPicture.add(new JLabel(icon));
        return showPicture;
    }

    /**
     * This method provides the album display.
     * @param album – the next Album
     */

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

    /**
     * Create the gallery app bar.
     * @return – the JPanel containing the app bar
     */

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

            JButton cancelRename = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Cancel.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
            cancelRename = setTheIcon(cancelRename);
            cancelRename.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JTextField newNameOfTheAlbum = new JTextField(TEXTFIELD_SIZE);
            JButton acceptRename = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Validate.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
            acceptRename = setTheIcon(acceptRename);
            acceptRename.addActionListener(e -> {
                try {
                    String newName = newNameOfTheAlbum.getText();
                    if(testString(newName))throw new BusinessException(RENAME_ERROR,mainPanel);
                    currentAlbum.renameAlbum(newName);
                    currentAlbum.getParent().refresh();
                    showAlbum(currentAlbum.getParent());
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            });

            renameAppBarGallery.add(newNameOfTheAlbum);
            renameAppBarGallery.add(cancelRename);
            renameAppBarGallery.add(acceptRename);

            JButton cancelDelete = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Cancel.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
            cancelDelete = setTheIcon(cancelDelete);
            cancelDelete.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "defaultAppBarGallery"));

            JButton acceptDelete = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Validate.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
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

            JButton renameAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_rename.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
            renameAlbum = setTheIcon(renameAlbum);
            renameAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "renameAppBarGallery"));

            JButton deleteAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Delete.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));

            deleteAlbum = setTheIcon(deleteAlbum);
            deleteAlbum.addActionListener(e -> appBarGalleryLayout.show(appBarGallery, "deleteAppBarGallery"));


            JButton backAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Fleche_Back.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
            backAlbum = setTheIcon(backAlbum);
            backAlbum.addActionListener(e -> showAlbum(currentAlbum.getParent()));

            JLabel nameOfAlbum = new JLabel(checkAlbumName(currentAlbum));

            defaultAppBarGallery.add(backAlbum);
            defaultAppBarGallery.add(nameOfAlbum);
            defaultAppBarGallery.add(renameAlbum);
            defaultAppBarGallery.add(deleteAlbum);
        }

        JButton add = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Plus.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        add = setTheIcon(add);
        add.addActionListener(e -> screen.show(mainPanel, "addPanel"));
        defaultAppBarGallery.add(add);

        return appBarGallery;
    }

    /**
     * Creates the GridLayout with the albumList and the pictureList of the current Album.
     * @param a – the current Album
     * @return – the JScrollPane containing a GridLayout with an Album or an Picture in each cells
     */

    public JScrollPane fillGridLayout(Album a){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH_OF_SCREEN-(GRID_COLUMN_LENGTH-1)* GRID_GAP_WIDTH, rowsLength(a.elementsNumber())*SIZE_OF_ROW));
        panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        GridLayout showContents = new GridLayout(rowsLength(a.elementsNumber()), GRID_COLUMN_LENGTH, GRID_GAP_HEIGHT, GRID_GAP_WIDTH); //elementNumber /3
        panel.setLayout(showContents);
        for (Album al:a.getAlbumList()) {
            JPanel albums = new JPanel();
            JButton albumContents = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_albumsContent.PNG")).getImage().getScaledInstance(IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON,Image.SCALE_SMOOTH)));
            albumContents = setTheIcon(albumContents);
            albumContents.addActionListener(e -> showAlbum(al));
            albums.add(albumContents);
            JLabel nameOfElement = new JLabel(checkAlbumName(al) + " (" + al.elementsNumber() + ")");
            albums.add(nameOfElement,BorderLayout.SOUTH);
            panel.add(albums);
        }
        for (Picture p:a.getPictureList()){
            JPanel pictures = new JPanel();
            pictures.setPreferredSize(new Dimension(WIDTH_OF_SCREEN-(GRID_COLUMN_LENGTH-1)* GRID_GAP_WIDTH,SIZE_OF_ROW));
            JButton pictureContents = new JButton(toolBox.addPictureIcon(a.getPath().toString() + "/" + p.getName(),IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON));
            pictureContents = setTheIcon(pictureContents);
            pictureContents.addActionListener(e -> showPicture(p));
            pictures.add(pictureContents, BorderLayout.CENTER);
            JLabel nameOfElement = new JLabel(checkPictureName(p));
            pictures.add(nameOfElement,BorderLayout.CENTER);
            panel.add(pictures,BorderLayout.SOUTH);
        }

        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        return scroll;
    }

    /**
     * Create the add panel to add Picture and Album to our Gallery.
     */

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

        JButton backAdd = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Fleche_Back.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        backAdd = setTheIcon(backAdd);
        backAdd.addActionListener(e -> screen.show(mainPanel,"galleryPanel"));

        defaultAppBarAdd.add(backAdd);

        appBarNewAlbum.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGHT_OF_APPBAR));
        appBarNewAlbum.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cancelAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Cancel.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        cancelAlbum = setTheIcon(cancelAlbum);
        cancelAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"defaultAppBarAdd"));

        JTextField newNameOfTheAlbum = new JTextField(TEXTFIELD_SIZE);

        JButton acceptAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Validate.png")).getImage().getScaledInstance(IMAGE_SIZE_IN_APPBAR, IMAGE_SIZE_IN_APPBAR,Image.SCALE_SMOOTH)));
        acceptAlbum = setTheIcon(acceptAlbum);
        acceptAlbum.addActionListener(e -> {
            try {
                String newName = newNameOfTheAlbum.getText();
                if(testString(newName))throw new BusinessException(RENAME_ERROR,mainPanel);
                newName = currentAlbum.getPath().toString()+"/" + newName;
                Path path = Paths.get(newName);
                Album a = new Album(path,currentAlbum);
                currentAlbum.refresh();
                showAlbum(a);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        });

        appBarNewAlbum.add(newNameOfTheAlbum);
        appBarNewAlbum.add(cancelAlbum);
        appBarNewAlbum.add(acceptAlbum);

        addPanel.add(addContents,BorderLayout.CENTER);
        addContents.setPreferredSize(new Dimension(WIDTH_OF_SCREEN,HEIGT_OF_CONTENTS));
        addContents.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addAPicture = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Photo.jpg")).getImage().getScaledInstance(IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON,Image.SCALE_SMOOTH)));
        addAPicture = setTheIcon(addAPicture);
        addAPicture.addActionListener(e -> {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", "png", "bmp", "jpg","jpeg","PNG");
            fileChooser.setFileFilter(filter);
            fileChooser.setPreferredSize(new Dimension(IMAGE_SIZE,HEIGT_OF_CONTENTS));
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            File dest = new File(currentAlbum.getPath().toString() + "/" + file.getName());
            try {
                if(dest.exists()) throw new BusinessException("This picture already exists in the Album",mainPanel);
                file.renameTo(dest);
            }catch(Exception exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel,"You are not allowed to move a picture","PC ERROR",JOptionPane.WARNING_MESSAGE);
            }
            currentAlbum.refresh();
            showAlbum(currentAlbum);
        });

        JButton addAAlbum = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icone_Album.jpg")).getImage().getScaledInstance(IMAGE_SIZE_ON_BUTTON,IMAGE_SIZE_ON_BUTTON,Image.SCALE_SMOOTH)));
        addAAlbum = setTheIcon(addAAlbum);
        addAAlbum.addActionListener(e -> appBarAddLayout.show(appBarPanel,"appBarNewAlbum"));

        addContents.add(addAPicture);
        addContents.add(addAAlbum);
    }

    /**
     * This method indicate the rows number to a GridLayout.
     * @param a – the int is divided by the wishing column length
     * @return – the int indicating the rows number in the GridLayout
     */

    public int rowsLength(int a){
        int rowsLength = a / GRID_ROW_LENGTH;
        if(a % GRID_ROW_LENGTH != 0)
            rowsLength += 1;
        return rowsLength;
    }

    /**
     * Shorten the String displayed if it is too long.
     * @param p – the concerned Picture
     * @return – the whole or partial String
     */

    public String checkPictureName(Picture p){
        if(p.getName().lastIndexOf('.')> PICTURE_NAME_LENGTH)
             return p.getName().substring(0, PICTURE_NAME_LENGTH)+"...";
        else
            return p.getName()+" ";
    }

    /**
     * Shorten the String displayed if it is too long.
     * @param a – the concerned Album
     * @return – the whole or partial String
     */

    public String checkAlbumName(Album a){
        if(a.getName().length()>= ALBUM_NAME_LENGTH)
            return a.getName().substring(0, ALBUM_NAME_LENGTH)+"...";
        else
            return a.getName()+" ";

    }

    /**
     * This method test the String with prohibited characters in files'name.
     * @param string – the String to test
     * @return – Boolean
     */

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

    /**
     * This method manage the size of an element on a JButton.
     * @param button – JButton needing modification
     * @return – JButton modified
     */

    public JButton setTheIcon(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}

