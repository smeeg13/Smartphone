package Smartphone.Contacts;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Contact extends JPanel {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int indicatif;
    private String email;
    private String address;

    private boolean favorite = false;

    public JList<Contact> contactJList = new JList<Contact>();
    private Contact[] contacts ;

    //Utilise classe AtomicInteger pour ne pas avoir 2x le même id
    private static AtomicInteger nextId = new AtomicInteger(0);


    public Contact(String firstName,String lastName,int indicatif,
                    String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        this.email = email;
        this.address = address;
        isFavorite();
        nextId.getAndIncrement();


    }
    public Contact(String firstName,String lastName,int indicatif,
                   String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.indicatif = indicatif;
        isFavorite();
        nextId.getAndIncrement();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(int indicatif) {
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public static AtomicInteger getNextId() {
        return nextId;
    }

    public static void setNextId(AtomicInteger nextId) {
        Contact.nextId = nextId;
    }
}
