package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class provides the code for a contact.
 *
 * @author Mégane Solliard
 * @version 2
 */
public class Contact {

    private String name = "";
    private String phoneNumber;
    private String indicative;
    private String email;
    private String address;
    private String pathForImage = ClassLoader.getSystemResource("Icone_AddPicture.png").getPath();
    private boolean favContact = false;  //Fav contact par défaut false

    /**
     * This constructor create a contact.
     *
     * @param name         – a String containing the name of the contact
     * @param indicative   – a String containing the indicative of the contact
     * @param phoneNumber  – a String containing the phone number of the contact
     * @param email        – a String containing the email of the contact
     * @param address      – a String containing the address of the contact
     * @param pathForImage – a String containing the path of the contact's picture
     * @param favContact   – a boolean which is true if the contact is a favourite contact, otherwise false
     */
    public Contact(String name, String indicative, String phoneNumber,
                   String email, String address, String pathForImage, boolean favContact) {
        try {
            setName(name);
            setPhoneNumber(phoneNumber);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
        this.indicative = indicative;
        this.email = email;
        this.address = address;
        this.favContact = favContact;
        this.pathForImage = pathForImage;
    }

    /**
     * Set the path for the contact's picture.
     *
     * @param pathForImage – the path of the picture
     */
    public void setPathForImage(String pathForImage) {
        this.pathForImage = pathForImage;
    }

    /**
     * Get the path of the contact's picture.
     *
     * @return – this path
     */
    public String getPathForImage() {
        return pathForImage;
    }

    /**
     * Get the name of the contact.
     *
     * @return – this name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the contact.
     *
     * @param name – the name of the contact
     */
    public void setName(String name) {
        if (this.name == "") {
            this.name = name;
        }

    }


    /**
     * Get the phone number of the contact.
     * @return – this phone number in a String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phonenumber of the contact.
     * @param phoneNumber  – the phonenumber of the contact in a String
     */
    public void setPhoneNumber(String phoneNumber) throws BusinessException {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the email of the contact.
     * @return – this email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the contact.
     * @param email  – the email of the contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the address of the contact.
     * @return – this address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the contact.
     * @param address – the address of the contact
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the indicative for the contact's phone number.
     * @return – this indicative
     */
    public String getIndicative() {
        return indicative;
    }

    /**
     * Set the indicative for the contact's phone number.
     * @param indicative  – the indicative in a String
     */
    public void setIndicative(String indicative) {
        this.indicative = indicative;
    }

    /**
     * Get true if the contact is a favourite contact.
     * @return – true if contact is a favorite contact, otherwise false
     */
    public boolean isFavContact() {
        return favContact;
    }

    /**
     * Set if a contact is favorite.
     * @param favContact  – the contact is favourite or not
     */
    public void setFavContact(boolean favContact) {
        this.favContact = favContact;
    }

    /**
     * This method put the contact's information into a String.
     * @return – String composed by the attributes of the contact
     */
    @Override
    public String toString() {
        return name + ", +" + indicative + "  " + phoneNumber
                + ", email "+ email+", addresse "+address+
                ", path "+pathForImage;
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
