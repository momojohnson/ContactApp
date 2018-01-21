package com.momo.dateModel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName = new SimpleStringProperty(""); //first name property
    private SimpleStringProperty lastName = new SimpleStringProperty(""); // last name property
    private SimpleStringProperty phoneNumber = new SimpleStringProperty(""); //  phone number property
    private SimpleStringProperty email = new SimpleStringProperty("");  // email property
    private SimpleStringProperty notes = new SimpleStringProperty(""); // notes property

    public Contact() {
    }

    public Contact(String firstName, String lastName, String phoneNumber, String email, String notes){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.email.set(email);
        this.notes.set(notes);
    }
    // returns first name
    public String getFirstName() {
        return firstName.get();
    }
    // returns first name property
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    // sets first Name
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    // Returns last name
    public String getLastName() {
        return lastName.get();
    }


    // sets last name
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    // return first name
    public String getPhoneNumber() {
        return phoneNumber.get();
    }


    // set phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    // return email
    public String getEmail() {
        return email.get();
    }

    // set email
    public void setEmail(String email) {
        this.email.set(email);
    }
    // return notes
    public String getNotes() {
        return notes.get();
    }

    // set notes
    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    // Override to string method
    @Override
    public String toString() {
        return "Contact{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", notes=" + notes +
                '}';
    }
}
