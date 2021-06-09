package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.Storage.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.annotations.Expose;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Contact {

   @JsonView(Views.NameOnly.class)
    @Expose
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
                   String email, String address, boolean addphoto,boolean favContact) {
        setName(name);
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        this.email = email;
        this.address = address;
        nextId.getAndIncrement();

        this.addphoto = addphoto;
        this.favContact = favContact;
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
        this.phoneNumber = phoneNumber;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
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

    public ImageIcon getPhoto() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
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

    public static AtomicInteger getNextId() {
        return nextId;
    }

    public static void setNextId(AtomicInteger nextId) {
        Contact.nextId = nextId;
    }

    @Override
    public String toString() {
        return name + ", " + indicatif + "  " + phoneNumber
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
