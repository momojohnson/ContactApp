package com.momo;

import com.momo.dateModel.ContactData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import com.momo.dateModel.Contact;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Optional;


public class Controller {

    @FXML
    private TableView<Contact> contactPersons;
    @FXML
    private AnchorPane mainWindow; // Main app window
    @FXML
    private TableView<Contact> contactTable; // Table form contacts
    private ContactData data; // Instance  of ContactData class

    public void initialize(){
        data = new ContactData();
        data.loadContacts(); // Loads program when app starts
        contactTable.setItems(data.getContacts()); // set Available contacts  within the table



    }


    // A contact dialog box. Adds new contact
    @FXML
    public void addContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Add a Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialogBox.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException exe){
            exe.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        ContactController contactController = fxmlLoader.getController();

        if(result.isPresent() && result.get() == ButtonType.OK){

            Contact newContact = contactController.getContactInfo();
            if(newContact == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Information isn't saved. Please make sure that all fields are filled correctly.");
                alert.showAndWait();
                return;
            }else {
                data.addContact(newContact);
                data.saveContacts();
            }
            ;
        }

    }

    @FXML
    public void editContact(){
        Contact selectedContact = contactTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you would like to edit");
            alert.showAndWait();
            return;

        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialogBox.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException err){
            err.printStackTrace();
        }
        ContactController contactController = fxmlLoader.getController();
        contactController.setEditUIs(selectedContact);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateContact(selectedContact);
            data.saveContacts();
        }
    }
    // Delete contact from available contacts
    @FXML
    public void deleteContact(){
        Contact contactToDelete = contactTable.getSelectionModel().getSelectedItem();
        if(contactToDelete == null){
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Select Contact");
            infoAlert.setContentText("Please select a contact to delete.");
            infoAlert.showAndWait();
            return;
        }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contact");
            alert.setContentText(String.format("Are you sure you want to delete contact %s %s?", contactToDelete.getFirstName(), contactToDelete.getLastName()));
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                data.deleteContact(contactToDelete);
                data.saveContacts();


            }




    }

}
