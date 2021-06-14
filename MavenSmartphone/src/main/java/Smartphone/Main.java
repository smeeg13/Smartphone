package Smartphone;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws BusinessException, IOException {

        createContactFolder();

        File fileContactList = new File(System.getenv("HOME") + "/contacts/ContactList.json");
        File fileFavContactList = new File(System.getenv("HOME") + "/contacts/FavContactList.json");

        checkFileExisted(fileContactList);
        checkFileExisted(fileFavContactList);

        JFrame frame = new StructureFrame();

        frame.setSize(400,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * This method check if the File exists, when not
     * it creates a file
     * @param file is the new file we want to create if it is not created yet
     * @throws IOException
     */
    public static void checkFileExisted(File file) throws BusinessException,IOException {
        if (!file.exists()) {
            file.createNewFile();
            String str1 = "[]";
            Path path = Paths.get(String.valueOf(file));
            byte[] strToBytes = str1.getBytes();

            try {
                Files.write(path, strToBytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException("Cannot write in the file",e, ErrorCodes.IO_ERROR);
            }

            System.out.println("File has been created.");
        } else {
            System.out.println("File already exists.");
        }
    }

    /**
     * This method create a new contact folder when starting the app
     * we first start the application
     */
    public static void createContactFolder(){
        String str = System.getenv("HOME") + "/contacts";
        File contactFolder = new File(str);
        contactFolder.mkdir();
    }

}
