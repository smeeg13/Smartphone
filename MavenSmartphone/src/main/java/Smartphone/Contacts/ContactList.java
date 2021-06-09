package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Storage.JSONStorage;
import Smartphone.Storage.Storable;
import Smartphone.Storage.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

public class ContactList {
    //Limité à 200 contacts pour le test

    private ArrayList<Contact> contacts ;
    private Storable storage;

    String name;
    Label labErreurSaisie=new Label("");

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

    //Pour ajouter un nouveau contact à la liste
    public void addToContactList(Contact contact) throws BusinessException{

        contacts.add(contact);

    }
//
    public void readFromFile(File contactList) throws  BusinessException{
        ArrayList<Contact> newContact = storage.read(contactList);

        for (Contact contact: newContact) {
            addToContactList(contact);
        }
    }

    public void saveToFile(File destination) throws BusinessException{

        storage.write(destination, contacts) ;
    }


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
        return null ;
    }

    //Méthode retourne vraie si un contact est existant
    public boolean containsNameInJson(String valSearched, File JsonFile) throws BusinessException {

        ArrayList<Contact> contacts = storage.read(JsonFile);

        for (Contact c : contacts){
            if(c.toString().equals(valSearched))
                return true;
        }
        return false;
    }

    public void delete(String name){
        contacts.removeIf(contact -> contact.getName().equals(name));
    }


    @Override
    public String toString() {
        String myContacts ="";
        for (Contact c : contacts) {
            myContacts += c.toString() + "\n";
        }
        return myContacts;
    }

}
