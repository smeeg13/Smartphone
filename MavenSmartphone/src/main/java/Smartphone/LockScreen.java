package Smartphone;

import Smartphone.Errors.BusinessException;

import javax.swing.*;
import java.awt.*;

/**
 * This class provides graphical implementations for the lock screen {@link javax.swing}  and {@link java.awt}.
 *
 * @author Mégane Solliard
 * @version
 */
public class LockScreen extends JPanel {

    /**
     * This constructor provides the LockScreen display.
     */
    public LockScreen(){

    }

    /**
     * This method put a wallpaper in the lock screen.
     * @param g – the wallpaper that is used.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("FondEcranVerou.png")).getImage());
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
