package Smartphone;

import javax.swing.*;
import java.awt.*;

public class PageVerouillage extends JPanel {

    public PageVerouillage(){

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("C:\\Users\\megan\\Desktop\\HES\\FondEcranVerou.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
