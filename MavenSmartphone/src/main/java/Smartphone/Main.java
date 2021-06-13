package Smartphone;

import Smartphone.Errors.BusinessException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws BusinessException, IOException {

        String str = System.getenv("HOME") + "/contacts";
        File contactFolder = new File(str);
        contactFolder.mkdir();
        File fileContactList = new File(System.getenv("HOME") + "/contacts/ContactList.json");
        File fileFavContactList = new File(System.getenv("HOME") + "/contacts/FavContactList.json");
        System.out.println(System.getenv());
        if (!fileContactList.exists()) {
            fileContactList.createNewFile();
            String str1 = "[]";
            Path path = Paths.get(String.valueOf(fileContactList));
            byte[] strToBytes = str1.getBytes();
            Files.write(path, strToBytes);
            System.out.println("File has been created.");
        } else {
            System.out.println("File already exists.");
        }
        if (!fileFavContactList.exists()) {
            fileFavContactList.createNewFile();
            String str2 = "[]";
            Path path = Paths.get(String.valueOf(fileFavContactList));
            byte[] strToBytes = str2.getBytes();
            Files.write(path, strToBytes);
            System.out.println("File has been created.");
        } else {
            System.out.println("File already exists.");
        }

        JFrame frame = new StructureFrame();

        frame.setSize(400,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
