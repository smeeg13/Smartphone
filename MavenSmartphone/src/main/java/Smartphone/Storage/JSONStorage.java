package Smartphone.Storage;



import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;

public class JSONStorage implements Storable {

//Méthode pour lire un ficher JSON
    @Override
    public Contact[] read(File source) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        Contact[] contacts;

        try {
            contacts = mapper.readValue(source, Contact[].class);
        } catch (IOException e) {
            throw new BusinessException("read error", e, ErrorCodes.IO_ERROR);
        }

        return contacts;
    }

//Méthode pour lire un ficher JSON
    @Override
    public void write(File destination, Contact[] contacts) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            throw new BusinessException("failed to save", e, ErrorCodes.IO_ERROR);
        }
    }


}

