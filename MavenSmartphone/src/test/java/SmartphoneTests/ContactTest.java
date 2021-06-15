package SmartphoneTests;

import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Storage.JSONStorage;
import Smartphone.Storage.Storable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    String name = "Marie";
    String phoneNumber = "09791001010";
    String indicatif = "+41";
    String email = "ContactX@hotmail.com";
    String address = "Sion";
    ImageIcon photo;
    String path = "as";
    boolean favContact = false;

    @Test
    void getName() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(name, c.getName());
    }

    @Test
    void setName() {
            String name = "Contact test";
            String phoneNumber = "09791001010";
            String indicatif="+41";
            String email = "t@hotmail.com";
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
            testC.setName("henri");
            assertEquals(name, testC.getName());
        }




        @Test
    void getPhoneNumber() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(phoneNumber, c.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        try {
            c.setPhoneNumber("0791001010");
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        assertEquals("0791001010", c.getPhoneNumber());
    }



    @Test
    void testContactToString() {
        Contact contactX = null;

        try {
            contactX = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        } catch (Exception unexpected) {
            fail("Failed to create a new Wizard");
        }

        String description = contactX.toString();

        assertTrue(description.contains(name));
        assertTrue(description.contains(indicatif));
        assertTrue(description.contains(phoneNumber));
        assertTrue(description.contains(email));
        assertTrue(description.contains(address));
        assertTrue(description.contains(path));
    }

    @Test
    void setPathForImage() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        c.setPathForImage("ba");
        assertEquals("ba", c.getPathForImage());
    }

    @Test
    void getPathForImage() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(path, c.getPathForImage());
    }

    @Test
    void isFavContact() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(favContact, c.isFavContact());
    }

    @Test
    void setFavContact() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        c.setFavContact(true);
        assertEquals(true, c.isFavContact());
    }

    @Test
    void getEmail() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(email, c.getEmail());
    }

    @Test
    void setEmail() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        c.setEmail("a.@gmail.com");
        assertEquals("a.@gmail.com", c.getEmail());
    }

    @Test
    void getAddress() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(address, c.getAddress());
    }

    @Test
    void setAddress() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        c.setAddress("Sierre");
        assertEquals("Sierre", c.getAddress());
    }

    @Test
    void getIndicative() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        assertEquals(indicatif, c.getIndicative());
    }

    @Test
    void setIndicative() {
        Contact c = new Contact(name, indicatif, phoneNumber, email, address, path, favContact);
        c.setIndicative("+33");
        assertEquals("+33", c.getIndicative());
    }

    @Test
    void testBusinessException() {
        // BusinessException must be a checked exception
        assertTrue(Exception.class.isAssignableFrom(BusinessException.class)); // Child of Exception
        assertFalse(RuntimeException.class.isAssignableFrom(BusinessException.class));
    }
}