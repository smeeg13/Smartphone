package SmartphoneTests;

import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestsContacts {
    private File genTestData(String heroes) {
        File testData = null;

        // Generate a temporary file
        try {
            testData = File.createTempFile("fotr", ".txt");
            // createTempFile appends a long to the prefix to generate a unique filename

            testData.deleteOnExit();
        } catch (Exception e) {
            fail("Unexpected error while creating our temporary file");
        }

        if (heroes != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(testData))) {
                bw.write(heroes);
            } catch (Exception e) {
                fail("Unexpected error while updating our temporary file");
            }
        }

        return testData;
    }
    @Test
    public void testSetNameContact(){
        String name = "Contact test";
        String phoneNumber = "09791001010";
        String indicatif="+41";
        String email = "gandalf@hotmail.com";
        String address= "Sion";
        String path = "as";
        ImageIcon photo;
        boolean addphoto=false;
        boolean favContact = false;

        Contact testC = null;

        try {
            testC = new Contact(name,indicatif,phoneNumber,email,address,path,favContact);
        } catch (Exception e) {
            fail("Failed to create a new Contact");
        }

        assertEquals(name, testC.getName());

        // The name of a contact can be updated
        testC.setName("Fenrir");
        assertEquals(name, testC.getName());
    }

    @Test
    void testContact() {
        String name = "Contact test";
        String phoneNumber = "09791001010";
        String indicatif="+41";
        String email = "gandalf@hotmail.com";
        String address= "Sion";
        ImageIcon photo;
        String path = "as";
        boolean favContact = false;

        Contact gandalf = null;

        try {
            gandalf = new Contact(name,indicatif,phoneNumber,email,address,path,favContact);
        } catch (Exception unexpected) {
            fail("Failed to create a new Wizard");
        }

        String description = gandalf.toString();

        // toString should return the name, the health, the mana, and the xp of our hero
        assertTrue(description.contains(name));
        assertTrue(description.contains(indicatif));
        assertTrue(description.contains(phoneNumber));
        assertTrue(description.contains(email));
        assertTrue(description.contains(address));
    }

    @Test
    public void AddContactToContactList(){

    }
    @Test
    public void saveContactToJson(){

    }
    @Test
    void testBusinessException() {
        // BusinessException must be a checked exception
        assertTrue(Exception.class.isAssignableFrom(BusinessException.class)); // Child of Exception
        assertFalse(RuntimeException.class.isAssignableFrom(BusinessException.class));
    }


}

