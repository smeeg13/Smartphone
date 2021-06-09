package Smartphone.Storage;



import Smartphone.Contacts.Contact;
import Smartphone.Errors.BusinessException;

import java.io.File;


public interface Storable {
    Contact[] read(File source) throws BusinessException;
    void write(File destination, Contact[] contacts) throws BusinessException ;

}
