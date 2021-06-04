package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Storage.Storable;

import java.io.File;
import java.util.Arrays;

public class ContactList {
    //Limité à 200 contacts pour le test
    private static  final  int MAX_CONTACTS = 200;
    private Contact[] contacts ;
    private int nbContacts;
    private Storable storage;

    public ContactList(Storable storage){
        contacts = new Contact[MAX_CONTACTS];
        nbContacts =0;
        this.storage = storage;
    }

    public Contact getContacts(int idx) throws ArrayIndexOutOfBoundsException{
        if (idx>=nbContacts){
            throw new ArrayIndexOutOfBoundsException();
        }
        return contacts[idx];
    }

    public int getNbContacts() {
        return nbContacts;
    }

//Pour ajouter un nouveau contact à la liste
    public void addToContactList(Contact contact) throws BusinessException{
        if (nbContacts == MAX_CONTACTS){
            throw new BusinessException("no more contact can be added...", ErrorCodes.CONTACTLIST_FULL);
        }

        for (int i = 0; i < nbContacts; i++){
            if(contact.equals(contacts[i])){
                throw new BusinessException("Contact already exist",ErrorCodes.CONTACT_ALREADY_EXIST_ERROR);
            }
        }
        contacts[nbContacts] = contact;
        nbContacts++;
    }
//
    public void addToFile(File contactList) throws  BusinessException{
        Contact[] newContact = storage.read(contactList);

        for (Contact contact: newContact) {
            addToContactList(contact);
        }
    }

    public void saveToFile(File destination) throws BusinessException{
        Contact[] cont = new Contact[nbContacts];
        for(int i = 0 ; i<nbContacts ; i++){
            cont[i] = contacts[i];
        }
        storage.write(destination, cont) ;
    }
/*
    //Retourne le contact si existant
    public Contact searchedContact(ContactList contactList, String saisieRecherche){

        for (int i = 0; i<= nbContacts; i++){
            if (contactList.contains(contactList,saisieRecherche) ==true)
                return contacts[i];
            else
                System.out.println("Contact not found...");
        }

        return null;
    }

 */
    //Méthode retourne vraie si recherche est dans Contact List
    public boolean contains(ContactList contactList, String valSearched){
        return Arrays.toString(contacts).contains(valSearched);
    }

    @Override
    public String toString() {
        String myContacts = "My Contacts : \""+"\"\n";
        for (int idx=0; idx<nbContacts; idx++) {
            myContacts += contacts[idx].toString() + "\n";
        }
        return myContacts;
    }
}
