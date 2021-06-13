package Smartphone.Gallery.Core;

import Smartphone.Errors.BusinessException;

import java.io.*;

/**
 * This class provides to manage pictures for an Album (see in {@link Smartphone.Gallery.Core.Album})
 */

public class Picture {

    private String path;
    private File file;

    /**
     * This method creates a picture.
     * @param path – the Path of the Picture
     */
    public Picture(String path) {
        this.path = path;
        file = new File(path);
    }

    /**
     * This method modifies the Picture ans sets it a rename.
     * @param name – the new name String
     */
    public void rename(String name) {
        String str = file.getName();
        boolean succeed =false;
        File newFile =new File(file.getParentFile().getPath() + "/" + name + str.substring(str.lastIndexOf('.')));
        try {
            succeed = file.renameTo(newFile); //if the user hasn't the permission to rename a file
        } catch(Exception e){
            e.printStackTrace();
        }
        if(succeed)
            file = newFile;
    }

    /**
     * This method gives the file name.
     * @return – the String containing the name
     */

    public String getName() {
        return file.getName();
    }

    /**
     * Get the path.
     * @return – the Path
     */

    public String getPath() { return path;}

    /**
     * This method sets the path.
     * @param path – the String containing the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This method deletes the file in the folders.
     */

    public void deletePicture(){
        file.delete();
    }
}


