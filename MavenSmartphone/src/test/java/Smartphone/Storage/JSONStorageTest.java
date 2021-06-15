package Smartphone.Storage;

import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JSONStorageTest {

    @Test
    void read() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        Storable storable = new JSONStorage();
        File testData = null;
        String indicatif = "+41";
        String path = "fg";

        Contact[] contacts = new Contact[5];
            contacts[0] = new Contact("Lou", indicatif, "100", "lou@gmail.com", "Sion", path, false);
            contacts[1] = new Contact("Thierry", indicatif, "101", "Thierry@gmail.com", "Sion", path, false);
            contacts[2] = new Contact("Alfred", indicatif, "102", "alfred@gmail.com", "Sion", path, false);
            contacts[3] = new Contact("Nina", indicatif, "103", "nina@gmail.com", "Sion", path, false);
            contacts[4] = new Contact("Charles", indicatif, "104", "charle@gmail.com", "Sion", path, false);
        for (Contact c : contacts) {
            contactArrayList.add(c);
        }

        try {  // Generate a temporary file
            testData = File.createTempFile("ContactListTest", ".json");
            // createTempFile appends a long to the prefix to generate a unique filename

            testData.deleteOnExit();
        } catch (Exception e) {
            fail("Unexpected error while creating our temporary file");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(testData)){
            //Avec gson
            gson.toJson(contactArrayList.toArray(),writer);
        } catch (IOException e) {
            try {
                throw new BusinessException("failed to save", e, ErrorCodes.IO_ERROR);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        }

        try {
            assertTrue(contactArrayList.equals(storable.read(testData)));
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }

    @Test
    void write() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        Storable storable = new JSONStorage();
        File testData = null;
        String indicatif = "+41";
        String path = "fg";
        ArrayList<Contact> listRead = new ArrayList<>();


        Contact[] contacts = new Contact[5];
        contacts[0] = new Contact("Lou", indicatif, "100", "lou@gmail.com", "Sion", path, false);
        contacts[1] = new Contact("Thierry", indicatif, "101", "Thierry@gmail.com", "Sion", path, false);
        contacts[2] = new Contact("Alfred", indicatif, "102", "alfred@gmail.com", "Sion", path, false);
        contacts[3] = new Contact("Nina", indicatif, "103", "nina@gmail.com", "Sion", path, false);
        contacts[4] = new Contact("Charles", indicatif, "104", "charle@gmail.com", "Sion", path, false);
        for (Contact c : contacts) {
            contactArrayList.add(c);
        }
        try {  // Generate a temporary file
            testData = File.createTempFile("ContactListTest", ".json");
            // createTempFile appends a long to the prefix to generate a unique filename

            testData.deleteOnExit();
        } catch (Exception e) {
            fail("Unexpected error while creating our temporary file");
        }

        try {
            storable.write(testData,contactArrayList);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        try {
            listRead = storable.read(testData);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

            assertArrayEquals(contactArrayList.toArray(),listRead.toArray());

    }
}