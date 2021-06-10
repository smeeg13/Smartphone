package Smartphone.Storage;



import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;

import java.io.File;
import java.util.ArrayList;


public interface Storable {
    ArrayList<Contact> read(File source) throws BusinessException;
    void write(File destination, ArrayList<Contact> contacts) throws BusinessException ;

}
