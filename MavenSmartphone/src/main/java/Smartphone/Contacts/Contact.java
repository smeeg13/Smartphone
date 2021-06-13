package Smartphone.Contacts;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class provides the code for a contact.
 *
 * @author Mégane Solliard
 * @version
 */

public class Contact {

    private String name = "";
    private String phoneNumber;
    private String indicative;
    private String email;
    private String address;
    private String pathForImage = ClassLoader.getSystemResource("Icone_AddPicture.png").getPath();
    private ImageIcon photo;
    private boolean favContact = false;  //Fav contact par défaut false
    //Utilise classe AtomicInteger pour ne pas avoir 2x le même id
    private static AtomicInteger nextId = new AtomicInteger(0);

    /**
     * This constructor create a contact.
     * @param name – a String containing the name of the contact
     * @param indicative – a String containing the indicative of the contact
     * @param  phoneNumber – a String containing the phonenumber of the contact
     * @param email – a String containing the email of the contact
     * @param address – a String containing the address of the contact
     * @param pathForImage – a String containing the path of the contact's picture
     * @param favContact – a boolean which is true if the contact is a favourite contact, otherwise false
     */
    public Contact(String name, String indicative, String phoneNumber,
                   String email, String address, String pathForImage, boolean favContact) {
        setName(name);
        this.phoneNumber = phoneNumber;
        this.indicative = indicative;
        this.email = email;
        this.address = address;
        nextId.getAndIncrement();
        this.favContact = favContact;
        this.pathForImage=pathForImage;
    }

    public void setPathForImage(String pathForImage) {
        this.pathForImage = pathForImage;
    }

    public String getPathForImage() {
        return pathForImage;
    }

    public ImageIcon getPhoto() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       if( this.name == ""){
           this.name = name;
       }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndicative() {
        return indicative;
    }

    public void setIndicative(String indicative) {
    }

    public static AtomicInteger getNextId() {
        return nextId;
    }

    public static void setNextId(AtomicInteger nextId) {
        Contact.nextId = nextId;
    }

    public boolean isFavContact() {
        return favContact;
    }

    public void setFavContact(boolean favContact) {
        this.favContact = favContact;
    }

    /**
     * This method put the contact's informations into a String.
     * @return – String composed by the attributes of the contact
     */
    @Override
    public String toString() {
        return name + ", +" + indicative + "  " + phoneNumber
                + ", email "+ email+", addresse "+address;
    }

    /**
     * This method test if a name already exist.
     * @param o – the String to test
     * @return – Boolean true if already exist, otherwise false
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Contact) {
            if (((Contact) o).name.equals(getName())) {
                return true;
            }
        }
        return false;
    }
}
