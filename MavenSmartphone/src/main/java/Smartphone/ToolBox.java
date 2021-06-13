package Smartphone;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class ToolBox {

    private String OS = System.getProperty("os.name").toLowerCase();

    public ImageIcon addImageIconJButton(String path, int width, int height) {
        ImageIcon imageSearch;
        imageSearch = new ImageIcon(path);
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }

    public ImageIcon addImageIcon(String flagNamePath, int width, int height) {
        ImageIcon imageSearch;
        System.out.println(flagNamePath);
        imageSearch = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(flagNamePath)).getImage());
        Image imagetest = imageSearch.getImage();
        Image newTest = imagetest.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return imageSearch = new ImageIcon(newTest);
    }

    public boolean isWindows() {
        return OS.contains("win");
    }

    public boolean isMac() {
        return OS.contains("mac");
    }

//    public boolean isUnix() {
//        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
//    }

//    public boolean isSolaris() {
//        return OS.contains("sunos");
//    }

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
