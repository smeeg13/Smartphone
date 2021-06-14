package Smartphone.Gallery.Core;

import Smartphone.Errors.BusinessException;

import java.io.File;
import java.nio.file.Path;

/**
 * This class creates or find the first Album (see in {@link Smartphone.Gallery.Core.Album}).
 *
 * @author Nathan Dély
 * @version
 */

public class Gallery extends Album{

    /**
     * This method creates or discovers an album at the same level of the project.
     * @throws BusinessException if the file failed to create by the path (the user hasn't the permission)
     *
     */

    public Gallery() throws BusinessException {
        super(getGalleryPath(),null);
    }

    /**
     * Get the root Album.
     * @return – this Album
     */

    public Album getRoot() {return this;} //peut être delete

    /**
     * Return the path of this.
     * @return – Path
     */

    public static Path getGalleryPath(){
        File file = new File("./Gallery");
        return file.toPath();
    }
}
