package Smartphone;

import Smartphone.Errors.BusinessException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ToolBox {

    private String OS = System.getProperty("os.name").toLowerCase();

    public boolean isWindows() {
        return OS.contains("win");
    }

    public boolean isMac() {
        return OS.contains("mac");
    }


    /**
     * This method readjusts the picture with the right perspectives
     * @param path – Path of the picture
     * @param width – desired width in int
     * @param height – desired height in int
     * @return – ImageIcon with right perspectives
     *
     */
    // image nathan
    public ImageIcon addPictureIcon(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        try {
            File f = new File (path);
            BufferedImage image = ImageIO.read(f);
            double width2 = image.getWidth();
            double height2 = image.getHeight();
            double divisor;
            if(width2<height2)
                divisor=height2/height;
            else
                divisor=width2/width;
            if(divisor==0) throw new BusinessException("can't scale an image of this size");
            width2=width2/divisor;
            height2=height2/divisor;
            Image newTest = imagetest.getScaledInstance((int)width2, (int)height2, Image.SCALE_SMOOTH);
            return new ImageIcon(newTest);
        } catch (IOException | BusinessException e) {
            e.printStackTrace();
        }
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newTest);
    }

    /**
     * This method get the country flag
     * @param path  Path of the picture
     * @param width desired width in int
     * @param height desired height in int
     * @return ImageIcon with right perspectives
     */
    public ImageIcon addPictureIconFlag(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(ClassLoader.getSystemResource(path));
        Image imagetest = imageSearch.getImage();
        try {
            File f = new File (path);
            BufferedImage image = ImageIO.read(f);
            double width2 = image.getWidth();
            double height2 = image.getHeight();
            double divisor;
            if(width2<height2)
                divisor=height2/height;
            else
                divisor=width2/width;
            if(divisor==0) throw new BusinessException("can't scale an image of this size");
            width2=width2/divisor;
            height2=height2/divisor;
            Image newTest = imagetest.getScaledInstance((int)width2, (int)height2, Image.SCALE_SMOOTH);
            return new ImageIcon(newTest);
        } catch (IOException | BusinessException e) {
            e.printStackTrace();
        }
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newTest);
    }


    public boolean isReachableByPing(String host) {
        try {
            String cmd = "";
            if (isWindows()) {
                // For Windows
                cmd = "ping -n 1 " + host;
            }
            if (isMac()) {
                cmd = "ping -c 1 " + host;
            }

            Process pingProcess = Runtime.getRuntime().exec(cmd);
            pingProcess.waitFor();

            if (pingProcess.exitValue() == 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
