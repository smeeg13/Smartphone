package Smartphone.Gallery.Core;

import java.io.*;


public class Picture {

    private String path;
    private File file;

    public Picture(String path) {
        this.path = path;
        file = new File(path);
    }

    public void rename(String name) {
        String str = file.getName();
        File newFile =new File(file.getParentFile().getPath() + "/" + name + str.substring(str.lastIndexOf('.'),str.length()));
        boolean succeed = file.renameTo(newFile);
        if(succeed)
            file = newFile;
    }

    public String getName() {
        return file.getName();
    }

    public String getPath() { return path;}

    public void deletePicture(){
        file.delete();
    }
}


