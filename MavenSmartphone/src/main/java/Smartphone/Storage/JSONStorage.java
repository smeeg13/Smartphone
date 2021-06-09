package Smartphone.Storage;



import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


import java.io.*;

public class JSONStorage implements Storable {

//Méthode pour lire un ficher JSON
    @Override
    public Contact[] read(File source) throws BusinessException {

        //Avec gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Contact[] contacts;

        try (JsonReader reader = new JsonReader(new FileReader(source))){
            contacts = gson.fromJson(reader, Contact[].class);

        } catch (IOException e) {
            throw new BusinessException("read error", e, ErrorCodes.IO_ERROR);
        }

        return contacts;
    }

//Méthode pour ecrire dans un ficher JSON
    @Override
    public void write(File destination, Contact[] contacts) throws BusinessException {
        //avec Jackson
        ObjectMapper mapper = new ObjectMapper();
        //Avec gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try (FileWriter writer = new FileWriter(destination)){
            //Avec gson
            gson.toJson(contacts,writer);
            //Avec jacskon
            //     mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            throw new BusinessException("failed to save", e, ErrorCodes.IO_ERROR);
        }
    }


}

