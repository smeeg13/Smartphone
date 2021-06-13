package Smartphone.Gallery.Core;

import java.io.*;


public class Picture {

    private String path;
    private File file;

    /**
     *
     * @param path
     */
    public Picture(String path) {
        this.path = path;
        file = new File(path);
    }

    public void rename(String name) {
        String str = file.getName();
        File newFile =new File(file.getParentFile().getPath() + "/" + name + str.substring(str.lastIndexOf('.')));
        boolean succeed = file.renameTo(newFile);
        if(succeed)
            file = newFile;
    }

    public String getName() {
        return file.getName();
    }

    public String getPath() { return path;}

    public void setPath(String path) {
        this.path = path;
    }

    public void deletePicture(){
        file.delete();
    }
}


