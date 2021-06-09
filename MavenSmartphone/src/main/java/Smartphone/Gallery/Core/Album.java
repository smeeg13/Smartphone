package Smartphone.Gallery.Core;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Album {

    private List<Picture> pictureList;
    private List<Album> albumList;
    private Path path;

    public Album(Path path) throws Exception {
        File file = path.toFile();
        this.path = path;
        if (!file.exists()){
            boolean created = file.mkdir();
            if(!created)throw new Exception("file not created");
            pictureList = new ArrayList<>();
            albumList = new ArrayList<>();
        } else {
            pictureList = discoverImages(path);
            albumList = discoverAlbums(path);
        }

    }

    private static List<Picture> discoverImages(Path path){
        List<Picture> pictureList = new ArrayList<>();
        File folder = path.toFile();
        String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp", "jpg","jpeg"
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

    private static List<Album> discoverAlbums(Path path) throws Exception {
        List<Album> albumList = new ArrayList<>();
        File folder = path.toFile();
        for (File f:folder.listFiles()) {
            if(f.isDirectory()){
                albumList.add(new Album(f.toPath()));
            }
        }
        return albumList;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public List<Picture> getAllPictureList(){
        List<Picture> allPictureList= new ArrayList<>();
        allPictureList.addAll(pictureList);
        for (Album a:albumList) {
            allPictureList.addAll(a.getAllPictureList()); //appel tous les albums de façon récursives
        }
        return allPictureList;
    }

    public List<Album> getAlbumList(){ return albumList;}

    public List<Album> getAllAlbumList(){
        List<Album> allAlbumList= new ArrayList<>();
        allAlbumList.addAll(albumList);
        for (Album a:albumList) {
            allAlbumList.addAll(a.getAllAlbumList()); //appel tous les albums de façon récursives
        }
        return allAlbumList;
    }

    public void addImage(Picture p){
        pictureList.add(p);
        
    }
    
    public void deleteImage(Picture p) {
        if(!pictureList.contains(p))return;
        p.deletePicture();
    }

    public void addAlbum(Album a) {
        albumList.add(a);
    }
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

    public void deleteFolder(){
        path.toFile().delete();
    }

    public String getName(){return path.toFile().getName();}

    public void renameAlbum(String name){
    }


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
}
