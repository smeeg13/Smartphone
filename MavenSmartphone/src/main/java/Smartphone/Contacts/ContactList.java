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
import java.util.Arrays;

import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

public class ContactList {
    //Limité à 200 contacts pour le test
    public static  final  int MAX_CONTACTS = 200;

    private Contact[] contacts ;
    private int nbContacts;
    private Storable storage;

    String name;
    String[] contactsNames = new String[nbContacts];
    Label labErreurSaisie=new Label("");

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

    public static int getMaxContacts() {
        return MAX_CONTACTS;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public void setNbContacts(int nbContacts) {
        this.nbContacts = nbContacts;
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

    public String[] getContactsNames() {
        return contactsNames;
    }

    public void setContactsNames(String[] contactsNames) {
        this.contactsNames = contactsNames;
    }

    public Label getLabErreurSaisie() {
        return labErreurSaisie;
    }

    public void setLabErreurSaisie(Label labErreurSaisie) {
        this.labErreurSaisie = labErreurSaisie;
    }

    //Pour ajouter un nouveau contact à la liste
    public void addToContactList(Contact contact) throws BusinessException{

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


//Méthode pour pouvoir afficher les données de contact des Json dans une JList
    public String[] getNameArrayFromJSON(File JsonFile) throws BusinessException {

        //Récupérer données dans JSON
        Contact[] contactNames = storage.read(JsonFile);

        String[] contactNamesString = new String[contactNames.length];
        //Transformer Contact[] en String[] et en prenant que les noms
        for (int i = 0 ; i< contactNames.length ; i++){
            contactNamesString[i] = contactNames[i].getName();
            i++;
        }
        return contactNamesString;
    }

    //Méthode retourne vraie si un contact est existant
    public boolean containsNameInJson(String valSearched, File JsonFile){

        boolean contactExist = false;
        Contact[] contacts ;
        //GSON
        Gson gson = new GsonBuilder().create();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(JsonFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        contacts = gson.fromJson(reader,Contact[].class);

        for (int i = 0 ; i< nbContacts ; i++){
            contactExist = contacts[i].toString().contains(valSearched);
            i++;
        }
        return contactExist;
    }

    @Override
    public String toString() {
        String myContacts ="";
        for (int idx=0; idx<nbContacts; idx++) {
            myContacts += contacts[idx].toString() + "\n";
        }
        return myContacts;
    }

}
