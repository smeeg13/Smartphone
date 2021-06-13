package Smartphone.Storage;


import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONStorage implements Storable {

//Méthode pour lire un ficher JSON
    @Override
    public ArrayList<Contact> read(File source) throws BusinessException {

        //Avec gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Contact[] contacts;
        try (JsonReader reader = new JsonReader(new FileReader(source))){
            contacts = gson.fromJson(reader, Contact[].class);
        } catch (IOException e) {
            throw new BusinessException("read error", e, ErrorCodes.IO_ERROR);
        }
        return new ArrayList<>(Arrays.asList(contacts));

    }

//Méthode pour ecrire dans un ficher JSON
    @Override
    public void write(File destination, ArrayList<Contact> contacts) throws BusinessException {

        //Avec gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(destination)){
            //Avec gson
            gson.toJson(contacts.toArray(),writer);

        } catch (IOException e) {
            throw new BusinessException("failed to save", e, ErrorCodes.IO_ERROR);
        }
    }


}

