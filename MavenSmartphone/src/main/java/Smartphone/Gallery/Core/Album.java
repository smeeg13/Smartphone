package Smartphone.Gallery.Core;
import Smartphone.Errors.BusinessException;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the code for an album in a gallery.
 *
 * @author Nathan Dély
 * @version
 */

public class Album {

    private List<Picture> pictureList;
    private List<Album> albumList;
    private Path path;
    private Album parent;

    /**
     * This constructor create or detect an album.
     * @param path – a String containing the album path in the computer
     * @param parent – the parent Album
     * @throws BusinessException if the file failed to create by the path (the user hasn't the permission)
     */

    public Album(Path path,Album parent) throws BusinessException {
        File file = path.toFile();
        this.path = path;
        this.parent=parent;
        if (!file.exists()){
            boolean created = file.mkdir();
            if(!created)throw new BusinessException("file not created");
            pictureList = new ArrayList<>();
            albumList = new ArrayList<>();
        } else {
            pictureList = discoverImages(path);
            albumList = discoverAlbums(path,this);
        }
    }

    /**
     *This method provides a picture List
     *
     * @param path – a String containing the album path in the computer
     * @return – a List<Picture> containing all the pictures in this Album
     */

    private static List<Picture> discoverImages(Path path){
        List<Picture> pictureList = new ArrayList<>();
        File folder = path.toFile();
        String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp", "jpg","jpeg","PNG"
        };
        // filter to identify images based on their extensions
        FilenameFilter IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
        for (File f:folder.listFiles(IMAGE_FILTER)) {
            pictureList.add(new Picture(f.getPath()));
        }
        return pictureList;
    }

    /**
     * This method discovers all the album in the Album by its path.
     * @param path – the album Path needed to be discover
     * @param parent - the parent album of each album discover
     * @return - the List<Album> containing the childs
     * @throws BusinessException see the constructor
     */

    private static List<Album> discoverAlbums(Path path,Album parent) throws BusinessException {
        List<Album> albumList = new ArrayList<>();
        File folder = path.toFile();
        for (File f:folder.listFiles()) {
            if(f.isDirectory()){
                albumList.add(new Album(f.toPath(),parent));
            }
        }
        return albumList;
    }

    /**
     * Get the pictureList.
     * @return – the List<Picture> containing its Picture
     */

    public List<Picture> getPictureList() {
        return pictureList;
    }

    /**
     * Get the pictureList of the Album and child albums.
     * @return - the List<Picture> containing its Picture and its Album Picture
     */

    public List<Picture> getAllPictureList(){
        List<Picture> allPictureList= new ArrayList<>();
        allPictureList.addAll(pictureList);
        for (Album a:albumList) {
            allPictureList.addAll(a.getAllPictureList()); //appel tous les albums de façon récursives
        }
        return allPictureList;
    }

    /**
     * Get the albumList.
     * @return – the List<Album> containing its Album
     */

    public List<Album> getAlbumList(){ return albumList;}

    /**
     * Get the albumList of the Album and child albums.
     * @return – the List<Album> containing its Album and their Album
     */

    public List<Album> getAllAlbumList(){
        List<Album> allAlbumList= new ArrayList<>();
        allAlbumList.addAll(albumList);
        for (Album a:albumList) {
            allAlbumList.addAll(a.getAllAlbumList()); //appel tous les albums de façon récursives
        }
        return allAlbumList;
    }

    /**
     * This method add a Picture to the Album.
     * @param p – the Picture added
     */

    public void addImage(Picture p){
        pictureList.add(p);
    }

    /**
     * Delete the Picture file and in the pictureList.
     * @param p – the Picture to delete
     */

    public void deleteImage(Picture p) {
        if(!pictureList.contains(p))return;
        p.deletePicture();
        pictureList.remove(p);
    }

    /**
     * This method add an Album to the Album.
     * @param a – the Album added
     */

    public void addAlbum(Album a) {
        albumList.add(a);
    }

    /**
     * Remove the Album and his contents in the Album and delete the folder
     * @param a – the Album concerned
     */

    public void deleteAlbum(Album a){

        if(!albumList.contains(a))return;
        albumList.remove(a);

        List<Album> albums = new ArrayList<>(a.getAlbumList()); //clone l'album pour éviter de supprimer des éléments la liste qu'on itère actuellement
        for (Album a1:albums) {
            a.deleteAlbum(a1);
        }

        List<Picture> pictures = new ArrayList<>(a.getPictureList()); //clone les images..
        for (Picture p:pictures) {
            a.deleteImage(p);
        }

        a.deleteFolder();
    }

    /**
     * Delete the folder.
     */

    public void deleteFolder(){
        path.toFile().delete();
    }

    /**
     * Get the name.
     * @return – the String containing the name
     */

    public String getName(){return path.toFile().getName();}

    public void renameAlbum(String name){
        File file = path.toFile();
        File dest = new File(parent.getPath().toString()+"/" + name);
        file.renameTo(dest);
    }

    /**
     * This method counts the number of element.
     * @return – the int of size
     */

    public int elementsNumber(){
        return pictureList.size()+albumList.size();
    }

    /**
     * Print all the content of the Album and his Album
     */

    public void print(){
        for (Album a:albumList) {
            System.out.println(a.getName() + " [");
            a.print();
            System.out.println("]");
        }
        for (Picture p:pictureList) {
            System.out.println(p.getName());
        }
    }

    /**
     * Get the parent
     * @return – the parent Album
     */

    public Album getParent() {
        return parent;
    }

    /**
     * Get the path
     * @return – the Path
     */

    public Path getPath() {
        return path;
    }

    /**
     * This method refresh and discover the albums and pictures
     */

    public void refresh(){
        try {
            albumList = discoverAlbums(path,this);
            pictureList = discoverImages(path);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
