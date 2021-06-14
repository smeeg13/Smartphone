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
/**
 * This class implement the methods of the interface Storable, used to save or read a Json.
 *
 * @author Mégane Solliard
 * @version
 */
public class JSONStorage implements Storable {

    /**
     * This method is used to put into an ArrayList all the contacts already in the Json File.
     * @param source - the Json file where the contacts are saved into
     * @return the ArrayList of Contact from the json file
     * @throws BusinessException If the file cannot be read
     *                           (the user hasn't the permission)
     * @see Storable#read(File)
     *
     */
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

    /**
     * This method is used to put into the Json File the ArrayList of contacts.
     * @param destination - the Json file where the contacts will be saved into
     * @param contacts - the ArrayList of contacts which has to be saved
     * @throws BusinessException If the file cannot be save
     *                           (the user hasn't the permission)
     * @see Storable#write(File, ArrayList)
     *
     */
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

