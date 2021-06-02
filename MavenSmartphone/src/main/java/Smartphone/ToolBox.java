package Smartphone;

import javax.swing.*;
import java.awt.*;

public class ToolBox {



    public ImageIcon addImageIconJButton(String path,int width, int height){
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }
}
