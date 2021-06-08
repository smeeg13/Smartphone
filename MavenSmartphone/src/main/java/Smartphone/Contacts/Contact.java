package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Contact {

    private String name = "";
    private String phoneNumber;
    private String indicatif;
    private String email;
    private String address;

    //Par défaut il n'y a pas de photo
    private ImageIcon photo;
    private boolean addphoto=false;

    //Fav contact par défaut false
    private boolean favContact = false;
    //Utilise classe AtomicInteger pour ne pas avoir 2x le même id
    private static AtomicInteger nextId = new AtomicInteger(0);

    //Constructeur par défaut
    public Contact(){}

    public Contact(String name, String indicatif, String phoneNumber,
                   String email, String address) {
        setName(name);
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        this.email = email;
        this.address = address;
        nextId.getAndIncrement();

        if (isAddphoto() == true) {
            setPhoto(photo);
        }
        isFavContact();
    }

    public Contact(String name,String indicatif,
                   String phoneNumber) {
        this.name=name;
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        nextId.getAndIncrement();

        if (isAddphoto() == true) {
            setPhoto(photo);
        }
        isFavContact();
    }

    public Contact(String name, String indicatif, String phoneNumber, String email) {
        setName(name);
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        this.email = email;
        nextId.getAndIncrement();

        if (isAddphoto() == true) {
            setPhoto(photo);
        }
        isFavContact();
    }


    public ImageIcon getPhoto() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       if( this.name == ""){
           this.name = name;
       }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
    }

    public static AtomicInteger getNextId() {
        return nextId;
    }

    public static void setNextId(AtomicInteger nextId) {
        Contact.nextId = nextId;
    }

    public boolean isAddphoto() {
        return addphoto;
    }

    public void setAddphoto(boolean addphoto) {
        this.addphoto = addphoto;
    }

    public boolean isFavContact() {
        return favContact;
    }

    public void setFavContact(boolean favContact) {
        this.favContact = favContact;
    }

    @Override
    public String toString() {
        return name + ", +" + indicatif + "  " + phoneNumber
                + ", email "+ email+", addresse "+address;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Contact) {
            if (((Contact) o).name.equals(getName())) {
                return true;
            }
        }
        return false;
    }
}
