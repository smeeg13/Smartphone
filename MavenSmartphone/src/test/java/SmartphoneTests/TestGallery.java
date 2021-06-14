package SmartphoneTests;

import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.Core.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

public class TestGallery {

    private final String PICTURE_NAME = "nathan.jpg";
    private final String PICTURE_NAME2 = "thomas.png";
    private final String SLASH = "/";
    private final String ALBUM_NAME = "album";
    private final String ALBUM_NAME2 = "megane";

    private Gallery g1;

    {
        try {
            g1 = new Gallery();
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }

    private Album album;

    //gallery
    @Test
    public void createGallery() {
        try {
            g1 = new Gallery();

        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }

    @Test
    public void GalleryPath() {
        g1.getPath();
    }

    @Test
    public void galleryOrAlbum() {
        g1.getRoot();
    }

    //album
    @Test
    public void createAlbum() throws BusinessException {

        for (int i = 0; i < 5; i++) {
            g1.addAlbum(new Album(Paths.get(g1.getPath().toString() + SLASH + i + ALBUM_NAME),g1));
        }
        album = g1.getAlbumList().get(g1.getAlbumList().size()-1);
        album.addAlbum(new Album(Paths.get(album.getPath() +SLASH + ALBUM_NAME),album));

        //Test each Album getter
        System.out.println(g1.getName() + SLASH + g1.getParent() + SLASH + g1.getPath() + SLASH + g1.getAllPictureList() + SLASH + g1.getAllAlbumList() + SLASH + g1.getPictureList() + SLASH + g1.getAlbumList());

        g1.getAlbumList().get(g1.getAlbumList().size()-1).renameAlbum(ALBUM_NAME2);

        //discoverImages and discoverAlbums are in the constructor of Album
        Album g2 = new Gallery();
        g2.print();

        //deleteFolder is done in deleteAlbum
        g1.deleteAlbum(g1.getAlbumList().get(g1.getAlbumList().size()-2));

        g2.refresh();
        System.out.println(g2.getAlbumList().size());

    }

    @Test
    public void Picture() {

        for (int i = 0; i < 4; i++) {
            File file = new File(g1.getAlbumList().get(0).getPath() + SLASH + i + PICTURE_NAME);
            File file2 = new File(g1.getPath() + SLASH + i + PICTURE_NAME);
            Picture picture = new Picture(file.getPath());
            Picture picture2 = new Picture(file2.getPath());
            g1.getAlbumList().get(0).addImage(picture);
            g1.addImage(picture2);
        }

        g1.getAlbumList().get(0).getPictureList().get(0).rename(PICTURE_NAME2 + SLASH);
        g1.getAlbumList().get(0).getPictureList().get(0).rename(PICTURE_NAME2);
        Picture picture = g1.getAlbumList().get(0).getPictureList().get(0);
        g1.deleteImage(picture);
        g1.getAlbumList().get(0).deleteImage(picture);
        g1.deleteAlbum(g1.getAlbumList().get(g1.getAlbumList().size()-1));

    }

//    @Test
//    public void deleteAlbum(){
//        for (Album al:g1.getAlbumList()) {
//            g1.deleteAlbum(al);
//        }
//        g1.deleteFolder();
//    }
}

