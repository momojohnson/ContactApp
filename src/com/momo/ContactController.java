package com.momo;

import com.momo.dateModel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;



public class ContactController {

    @FXML
    private TextField txtFirstName; // first name textfield
    @FXML
    private TextField txtLastName; // last name textfield
    @FXML
    private TextField txtEmail; // email textfield
    @FXML
    private TextArea arNotes; // notes text area
    @FXML
    private TextField txtPhoneNumber; // phone number textfield


    // Returns a contact is inputs are valid. Otherwise, returns null
    public Contact getContactInfo(){

        if(validateInputs()){
            Contact newContact = new Contact(txtFirstName.getText(), txtLastName.getText(),
                    txtPhoneNumber.getText(), txtEmail.getText(), arNotes.getText());
            return newContact;
        }
        return null;
    }

    // Set various Uis values in their respective fields
    public void setEditUIs(Contact oldContact){
        txtFirstName.setText(oldContact.getFirstName());
        txtLastName.setText(oldContact.getLastName());
        txtEmail.setText(oldContact.getEmail());
        txtPhoneNumber.setText(oldContact.getPhoneNumber());
        arNotes.setText(oldContact.getNotes());
    }

    // Update a current contact in our contact list
    public void updateContact(Contact contact){
        contact.setFirstName(txtFirstName.getText());
        contact.setLastName(txtLastName.getText());
        contact.setEmail(txtEmail.getText());
        contact.setPhoneNumber(txtPhoneNumber.getText());
        contact.setNotes(arNotes.getText());
    }

    // A generic method that checks if control is empty. If the control is empty return false. Otherwise true.
    private static <T extends TextInputControl> boolean isValidString(T control){
        if(control.getText().equals("") || control.getText().trim().equals("")){
            return false;

        }
        return true;
    }

    // A method that validate controls inputs
    public boolean validateInputs(){
        if(!isValidString(txtFirstName)){
            return false;
        }
        if(!isValidString(txtLastName)){
            return false;
        }
        if(!isValidString(txtEmail)){
            return false;
        }
        if(!isValidString(txtPhoneNumber)){
            return false;
        }
        if(!isValidString(arNotes)){
            return false;
        }
        return true;
    }
}
