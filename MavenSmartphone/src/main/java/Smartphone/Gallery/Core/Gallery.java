package Smartphone.Gallery.Core;

import Smartphone.Errors.BusinessException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gallery extends Album{


    public Gallery() throws BusinessException {
        super(getGalleryPath(),null);
    }

    public Album getRoot() {return this;} //peut être delete

    public static Path getGalleryPath(){

        File file = new File("./Gallery");
        return file.toPath();
    }
}
