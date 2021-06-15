package SmartphoneTests;

import Smartphone.Contacts.Contact;
import Smartphone.Contacts.ContactList;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Gallery.Core.Picture;
import Smartphone.Storage.JSONStorage;
import Smartphone.Storage.Storable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {
    Storable storable = new JSONStorage();
    ContactList list = new ContactList(storable);
    ArrayList<Contact> contacts = new ArrayList<>();
    Contact Marie = new Contact("Marie","+41","004","m.@g","Sion","P",false);
    Contact Jon = new Contact("Jon","+41","006","m.@g","Sion","P",false);
    Contact Lisa = new Contact("Lisa","+41","007","m.@g","Sion","P",false);
    Contact Marc = new Contact("Marc","+41","008","m.@g","Sion","P",false);

    @Test
    void getContacts() {

        try {
            list.addToContactList(Marc);
            list.addToContactList(Marie);
            list.addToContactList(Lisa);
            list.addToContactList(Jon);

        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        assertArrayEquals(list.getContacts().toArray(new Contact[4]), list.getContacts().toArray(new Contact[4]));
    }

    @Test
    void addToContactList() {
        Storable storable = new JSONStorage();
        String indicatif = "+41";
        String path = "pathstring";
        ContactList contactList = new ContactList(storable);
        BusinessException e;

        Contact[] contacts = new Contact[10];

        try {
            contacts[0] = new Contact("Lou", indicatif, "100", "lou@gmail.com", "Sion", path, false);
            contacts[1] = new Contact("Thierry", indicatif, "101", "Thierry@gmail.com", "Sion", path, false);
            contacts[2] = new Contact("Alfred", indicatif, "102", "alfred@gmail.com", "Sion", path, false);
            contacts[3] = new Contact("Nina", indicatif, "103", "nina@gmail.com", "Sion", path, false);
            contacts[4] = new Contact("Charles", indicatif, "104", "charle@gmail.com", "Sion", path, false);
            contacts[5] = new Contact("Luke", indicatif, "105", "luke@gmail.com", "Sion", path, false);
            contacts[6] = new Contact("Haley", indicatif, "106", "h@gmail.com", "Sion", path, false);
            contacts[7] = new Contact("Penelope", indicatif, "107", "penelope@gmail.com", "Sion", path, false);
            contacts[8] = new Contact("Louis", indicatif, "108", "louis@gmail.com", "Sion", path, false);
            contacts[9] = new Contact("Sam", indicatif, "109", "sam@gmail.com", "Sion", path, false);
        } catch (Exception unexpected) {
            fail("Failed to create new heroes");
        }

        for (int i = 0; i < 9; i++) {
            try {
                contactList.addToContactList(contacts[i]);
            } catch (Exception unexpected) {
                fail("Unexpected exception");
            }
            assertTrue(contactList.toString().contains(contacts[i].getName()));
        }
    }
    @Test
    void addToContactListClone() {
        Storable storable = new JSONStorage();
        ContactList contactList = new ContactList(storable);
        int nbContact = 0;
        BusinessException e;
        String defaultValue = "string";
        String contactName = "Emy";
        Contact contactJay = null;
        Contact contactCloneJay = null;

        try {
            contactJay = new Contact(contactName, defaultValue, defaultValue, defaultValue,defaultValue,defaultValue,false);
            contactCloneJay = new Contact("Jo", defaultValue, defaultValue, defaultValue,defaultValue,defaultValue,false);
        } catch (Exception unexpected) {
            fail("Failed to create new contact");
        }

        try {
            contactList.addToContactList(contactJay);
        } catch (Exception unexpected) {
            fail("Failed to add " + contactJay.getName());
        }
        nbContact++;
        assertEquals(contactList.getContacts().size(),nbContact);

        try {
            contactList.addToContactList(contactCloneJay);
        } catch (Exception unexpected) {
            fail("Failed to add " + contactCloneJay.getName());
        }
        nbContact++;
        assertEquals(contactList.getContacts().size(),nbContact);

        // We can't add the same contact a second time
        Contact finalContactJay = contactJay;
        e = assertThrows(BusinessException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        contactList.addToContactList(finalContactJay);
                    }
                });
        assertEquals(ErrorCodes.CONTACT_ALREADY_EXIST.getCode(), e.getErrorCode());
        assertEquals(contactList.getContacts().size(),nbContact);

        Contact finalContactCloneOfJay = contactCloneJay;
        e = assertThrows(BusinessException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        contactList.addToContactList(finalContactCloneOfJay);
                    }
                });
        assertEquals(ErrorCodes.CONTACT_ALREADY_EXIST.getCode(), e.getErrorCode());
        assertEquals(contactList.getContacts().size(),nbContact);
    }

    @Test
    void saveToFile() {
    }

    private File generateTempFile(String contacts) {
        File testData = null;

        // Generate a temporary file
        try {
            testData = File.createTempFile("ContactListTest", ".json");
            // createTempFile appends a long to the prefix to generate a unique filename

            testData.deleteOnExit();
        } catch (Exception e) {
            fail("Unexpected error while creating our temporary file");
        }

        if (contacts != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(testData))) {
                bw.write(contacts);
            } catch (Exception e) {
                fail("Unexpected error while updating our temporary file");
            }
        }
        return testData;
    }

    @Test
    public void deserializeContact() {

        String[] contactsJson = {"{\"name\":\"Jo\",\"phoneNumber\":15,\"indicative\":\"+41\",\"email\":mg,\"address\":mg,\"pathForImage\":f,\"favContact\":false}",
                "{\"name\":\"Marie\",\"phoneNumber\":14,\"indicative\":\"+41\",\"email\":fr,\"address\":mg,\"pathForImage\":f,\"favContact\":false}",
                "{\"name\":\"Marc\",\"phoneNumber\":13,\"indicative\":\"+41\",\"email\":gr,\"address\":mg,\"pathForImage\":gson,\"favContact\":false}",
                "{\"name\":\"Lisa\",\"phoneNumber\":12,\"indicative\":\"+41\",\"email\":df,\"address\":mg,\"pathForImage\":r,\"favContact\":false}",
                "{\"name\":\"Amy\",\"phoneNumber\":11,\"indicative\":\"+41\",\"email\":fg,\"address\":mg,\"pathForImage\":p,\"favContact\":false}"};

        Gson gson = new GsonBuilder().create();

        for (String contactJson : contactsJson) {
            Contact contact = null;

            try {
                contact = gson.fromJson(contactJson, Contact.class);
            } catch (Exception e) {
                fail("Could not parse JSON string");
            }

            String contactName = contact.getName();
            assertTrue(contactJson.contains(contactName));
        }
    }

    @Test
    public void createCompanyFromJSONFile() {
        Storable storable = new JSONStorage();
        ContactList contactList = new ContactList(storable);
        String indicatif = "+41";
        String path = "stringPath";

        Contact[] contacts = new Contact[5];

        try {
            contacts[0] = new Contact("Lou", indicatif, "100", "lou@gmail.com", "Sion", path, false);
            contacts[1] = new Contact("Thierry", indicatif, "101", "Thierry@gmail.com", "Sion", path, false);
            contacts[2] = new Contact("Alfred", indicatif, "102", "alfred@gmail.com", "Sion", path, false);
            contacts[3] = new Contact("Nina", indicatif, "103", "nina@gmail.com", "Sion", path, false);
            contacts[4] = new Contact("Charles", indicatif, "104", "charle@gmail.com", "Sion", path, false);
        } catch (Exception e) {
            fail("Failed to create contacts");
        }

        // Generate a temporary file
        File testData = null;
        File savedData = null;
        try {
            testData = File.createTempFile("contactList", ".json");
            savedData = File.createTempFile("saved", ".json");
            // createTempFile appends a long to the prefix to generate a unique filename

            testData.deleteOnExit();
            savedData.deleteOnExit();
        } catch (Exception e) {
            fail("Unexpected error while creating our temporary files");
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(testData, contacts);
        } catch (Exception e) {
            fail("Could not write JSON data");
        }

        try {
            contactList.readFromFile(testData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            fail("Could not hire contacts");
        }

        for (int idx = 0; idx < contacts.length; idx++) {
            Contact contact = null;
            try {
                contact = contactList.getContacts().get(idx);
            } catch (Exception e) {
                fail("Could not get contact #" + idx);
            }
            assertEquals(contacts[idx], contact);
        }

        // Save our company
        try {
            contactList.saveToFile(savedData);
        } catch (Exception e) {
            fail("Could not save data");
        }

        // Files testData and savedData should contain the same data
        try {
            assertEquals(mapper.readTree(testData), mapper.readTree(savedData));
        } catch (Exception e) {
            fail("Could not compare JSON files");
        }
    }
}