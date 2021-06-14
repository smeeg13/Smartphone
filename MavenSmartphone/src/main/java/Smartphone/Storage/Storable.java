package Smartphone.Storage;



import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;

import java.io.File;
import java.util.ArrayList;

/**
 * This interface provides the methods for reading/writing in a Json File.
 * They are implemented by the class JSONStorage
 *
 * @author Mégane Solliard
 * @version
 */
public interface Storable {
    ArrayList<Contact> read(File source) throws BusinessException;
    void write(File destination, ArrayList<Contact> contacts) throws BusinessException ;

}
