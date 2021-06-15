package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Gallery.Core.Picture;
import Smartphone.Storage.Storable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class provides the code for creating a list of contacts.
 *
 * @author Mégane Solliard
 * @version 2
 */
public class ContactList {
    private ArrayList<Contact> contacts ;
    private Storable storage;
    private Picture picture = new Picture(ClassLoader.getSystemResource("Icone_AddPicture.png").getPath());

    /**
     * This constructor provides the Arraylist of contacts.
     * @param storage - the interface that provides Methods to read and write to a Json File
     * @see Smartphone.Storage.JSONStorage#read(File)
     * @see Smartphone.Storage.JSONStorage#write(File, ArrayList)
     */
    public ContactList(Storable storage){
        contacts = new ArrayList<>();
        this.storage = storage;
    }
    /**
     * Get all contacts in an Arraylist.
     * @return – this Arraylist of Contact
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Set the ArrayList of contact.
     * @param contacts – the ArrayList of contact
     */
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Get the picture of a contact.
     * @return – this picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * Set the picture of the contact.
     * @param picture  – the picture of the contact
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * This method add the contact in ArrayList of Contact.
     * @param contact – the contact that will be added
     */
    public void addToContactList(Contact contact) throws BusinessException {
        if (contacts.contains(contact))
            throw new BusinessException("Contact already exist", ErrorCodes.CONTACT_ALREADY_EXIST);
        else
        contacts.add(contact);
    }

    /**
     * This method read the list of contacts that is in the Json File.
     * @param source – the Json File where the contact List is to be save
     * @throws BusinessException If the file cannot be read
     * @see Smartphone.Storage.JSONStorage#read(File)
     */
    public void readFromFile(File source) throws  BusinessException{
        ArrayList<Contact> newContact = storage.read(source);

        for (Contact contact: newContact) {
            addToContactList(contact);
        }
    }

    /**
     * This method save the contact List in the Json File.
     * @param destination – the Json File where the contact List has to be saved.
     * @throws BusinessException if the contact List cannot be written into the Json File
     * @see Smartphone.Storage.JSONStorage#write(File, ArrayList)
     */
    public void saveToFile(File destination) throws BusinessException{
        storage.write(destination, contacts) ;
    }

    /**
     * This method provide an alphabetical order ArrayList<String> of the contact List stored in the Json File by his name.
     * @param JsonFile - the Json file where the contacts are saved into
     * @return an ArrayListof String containing the name of the contacts saved in the Json File
     * @throws BusinessException if the Json File cannot be read
     * @see Smartphone.Storage.JSONStorage#read(File)
     */
    public ArrayList<String> getNameArrayFromJSON(File JsonFile) throws BusinessException {

        ArrayList<Contact> contactNames = storage.read(JsonFile);

        ArrayList<String> contactNamesString = new ArrayList<>();

        for (Contact contact : contactNames){
            contactNamesString.add(contact.getName());
        }
        contactNamesString.sort(Comparator.naturalOrder());
        return contactNamesString;
    }

    /**
     * This method provide the contact stored in the Json File by his name.
     * @param nameSelected – the name of the contact that has to be provide.
     * @param JsonFile - the Json file where the contacts are saved into
     * @return a contact if the name selected exist in the Json File
     * @throws BusinessException if the Json File cannot be read
     * @see Smartphone.Storage.JSONStorage#read(File)
     */
    public Contact getContactByName(String nameSelected,File JsonFile) throws BusinessException {
        ArrayList<Contact> contacts = storage.read(JsonFile);
        for (Contact c : contacts){
            if (c.getName().equals(nameSelected))
                return c ;
        }
        return new Contact("","","","","",picture.getPath(),false);
    }

    /**
     * This method is true if the name search correspond to a contact in the Json File.
     * @param valSearched – the name of the contact that has to be searched.
     * @param JsonFile - the Json file where the contacts are saved into
     * @return true if the name searched exist in the Json File
     * @throws BusinessException if the Json File cannot be read
     * @see Smartphone.Storage.JSONStorage#read(File)
     */
    public boolean containsNameInJson(String valSearched, File JsonFile) throws BusinessException {

        ArrayList<Contact> contacts = storage.read(JsonFile);

        for (Contact c : contacts){
            if(c.getName().equals(valSearched))
                return true;
        }
        return false;
    }

    /**
     * This method can remove a contact if the name given equals a contact into the ArrayList.
     * @param name – the name of the contact that has to be delete.
     */
    public void delete(String name){
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    /**
     * This method put the contact List into a String.
     * @return – String composed by all the information of the contacts
     */
    @Override
    public String toString() {
        String myContacts ="";
        for (Contact c : contacts) {
            myContacts += c.toString() + "\n";
        }
        return myContacts;
    }
}

