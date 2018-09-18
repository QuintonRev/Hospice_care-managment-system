/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author PC
 */
public class AdminData {

    private final StringProperty adminId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty cellNumber;
    private final StringProperty tellNumber;
    private final StringProperty dateOfBirth;
    private final StringProperty email;
    private final StringProperty password; //SHA encryption
    

    public AdminData(String adminId, String firstName, String lastName, String cellNumber,
            String tellNumber, String dateOfBirth, String email, String password) {

        this.adminId = new SimpleStringProperty(adminId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.cellNumber = new SimpleStringProperty(cellNumber);
        this.tellNumber = new SimpleStringProperty(tellNumber);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        
    }

    public String getAdminId() {
        return adminId.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getCellNumber() {
        return cellNumber.get();
    }

    public String getTellNumber() {
        return tellNumber.get();
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

   

    public void setAdminId(String value) {
        adminId.set(value);
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

    public void setCellNumber(String value) {
        cellNumber.set(value);
    }

    public void setTellNumber(String value) {
        tellNumber.set(value);
    }

    public void setDateOfBirth(String value) {
        dateOfBirth.set(value);
    }

  

    public void setEmail(String value) {
        email.set(value);
    }

    public void setPassword(String value) {
        password.set(value);
    }

    public StringProperty PasswordProperty() {
        return password;
    }

    public StringProperty EmailProperty() {
        return email;
    }

    

    public StringProperty DateOfBirthProperty() {
        return dateOfBirth;
    }

    public StringProperty TellNumberProperty() {
        return tellNumber;
    }

    public StringProperty cellNumberProperty() {
        return cellNumber;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty FirstNameProperty() {
        return firstName;
    }

    public StringProperty AdminIdProperty() {
        return adminId;
    }
    
    
}
