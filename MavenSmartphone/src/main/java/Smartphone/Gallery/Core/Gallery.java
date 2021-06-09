package Smartphone.Gallery.Core;

import java.io.File;
import java.nio.file.Path;

public class Gallery {
    private Album root;
    private Path path;

    public Gallery() throws Exception {
        File file = new File("./Gallery");
        path = file.toPath();
        root = new Album(path);
    }

    public void print() {root.print();}

    public Album getRoot() {return root;}
}
