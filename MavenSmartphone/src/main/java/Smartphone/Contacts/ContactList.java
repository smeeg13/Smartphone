package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Gallery.Core.Picture;
import Smartphone.Storage.Storable;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * This class provides the code for creating a contacts List.
 *
 * @author Mégane Solliard
 * @version
 */
public class ContactList {
    //Limité à 200 contacts pour le test

    private ArrayList<Contact> contacts ;
    private Storable storage;
    private Picture picture = new Picture(ClassLoader.getSystemResource("Icone_AddPicture.png").getPath());
    String name;
    Label labErreurSaisie=new Label("");

    /**
     * This constructor provides the gallery display.
     *
     */
    public ContactList(Storable storage){
        contacts = new ArrayList<>();
        this.storage = storage;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public Storable getStorage() {
        return storage;
    }

    public void setStorage(Storable storage) {
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Label getLabErreurSaisie() {
        return labErreurSaisie;
    }

    public void setLabErreurSaisie(Label labErreurSaisie) {
        this.labErreurSaisie = labErreurSaisie;
    }

    /**
     * This method add the contact in the contact List.
     * @param contact – the contact that will be added to the ArrayList of Contact.
     */
    //Pour ajouter un nouveau contact à la liste
    public void addToContactList(Contact contact) {
        contacts.add(contact);
    }

    /**
     * This method read the contacts stored in the Json File.
     * @param source – the Json File where the contact List is save.
     */
    public void readFromFile(File source) throws  BusinessException{
        ArrayList<Contact> newContact = storage.read(source);

        for (Contact contact: newContact) {
            addToContactList(contact);
        }
    }

    /**
     * This method write the contact List in the Json File.
     * @param destination – the Json File where the contact List is save.
     */
    public void saveToFile(File destination) throws BusinessException{

        storage.write(destination, contacts) ;
    }

    /**
     * This method provides an ArrayList of String of the contact's names in the Json File.
     * @param JsonFile – the Json File where the contact List is save.
     * @return - the ArrayList of String containing the names of the contacts save in the Json
     */
//Méthode pour pouvoir afficher les données de contact des Json dans une JList
    public ArrayList<String> getNameArrayFromJSON(File JsonFile) throws BusinessException {

        //Récupérer données dans JSON
        ArrayList<Contact> contactNames = storage.read(JsonFile);

        ArrayList<String> contactNamesString = new ArrayList<>();
        //Transformer Contact[] en String[] et en prenant que les noms
        for (Contact contact : contactNames){
            contactNamesString.add(contact.getName());

        }
        contactNamesString.sort(Comparator.naturalOrder());
        return contactNamesString;
    }

//Méthode pour pouvoir reprendre infos du contact sélectionné
    public Contact getContactByName(String nameSelected,File JsonFile) throws BusinessException {
        ArrayList<Contact> contacts = storage.read(JsonFile);
         for (Contact c : contacts){
             if (c.getName().equals(nameSelected))
                return c ;
         }
        //return null ;
         return new Contact("","","","","",picture.getPath(),false);
    }

    //Méthode retourne vraie si un contact est existant
    public boolean containsNameInJson(String valSearched, File JsonFile) throws BusinessException {

        ArrayList<Contact> contacts = storage.read(JsonFile);

        for (Contact c : contacts){
            if(c.getName().equals(valSearched))
                return true;
        }
        return false;
    }

    /**
     * This method provide the contact .
     * @param name – the name of the contact that has to be delete.
     */
    public void delete(String name){
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    /**
     * This method put the contact List into a String.
     * @return – String composed by all the informations of the contacts
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
