package Smartphone.Contacts;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Contact extends JPanel {

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String address;

    private boolean favorite = false;

    //Utilise classe AtomicInteger pour ne pas avoir 2x le même id
    private static AtomicInteger nextId = new AtomicInteger(0);


    public Contact(String firstName,String lastName,
    int phoneNumber, String email,
            String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber ;
        this.email = email;
        this.address = address;
        nextId.getAndIncrement();

    }
    public Contact(String firstName,String lastName,
    int phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber ;
        this.email = null;
        this.address = null;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
