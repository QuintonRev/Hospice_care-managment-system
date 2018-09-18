/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author PC
 */
public class NurseData {
    
    private final StringProperty nurseId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty medianName;
    private final StringProperty cellNumber;
    private final StringProperty dateOfBirth;
    private final StringProperty address;
    private final StringProperty gender;
    private final StringProperty Qualification;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty wardName;
    
    public NurseData(String nurseId, String firstName, String lastName, String medianName,
            String cellNumber, String dateOfBirth, String address, String gender,
            String Qualification, String email, String password, String ward_id) {
        
        this.nurseId = new SimpleStringProperty(nurseId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.medianName = new SimpleStringProperty(medianName);
        this.cellNumber = new SimpleStringProperty(cellNumber);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleStringProperty(gender);
        this.Qualification = new SimpleStringProperty(Qualification);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(DigestUtils.shaHex(password));
        this.wardName = new SimpleStringProperty(ward_id);
    }
    
    

    public String getNurseId() {
        return nurseId.get();
    }
    public String getWardName(){
        return wardName.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getMedianName() {
        return medianName.get();
    }

    public String getCellNumber() {
        return cellNumber.get();
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getQualification() {
        return Qualification.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }
    public void setPassword(String value){
        password.set(value);
    }
    public void setNursId(String value){
        nurseId.set(value);
    }
    
    public void setFirstName(String value){
        firstName.set(value);
    }
    
    public void setLastName(String value){
        lastName.set(value);
    }
    
    public void setMedianName(String value){
        medianName.set(value);
    }
    
    public void setGet(String value){
        nurseId.set(value);
    }
    
        public StringProperty LastNameProperty() {
        return lastName;
    }
        public StringProperty MedianNameProperty() {
        return medianName;
        }
        
        public StringProperty NurseIdProperty() {
        return nurseId;
    }
        
        public StringProperty FirstNameProperty() {
        return firstName;
    }
        
        public StringProperty PasswordProperty(){
            return password;
        }
        
        public StringProperty EmailProperty() {
        return email;
    }
        
        public StringProperty QualificationProperty() {
        return Qualification;
    }
        public StringProperty GenderProperty() {
        return gender;
    }
        public StringProperty AddressProperty() {
        return address;
    }
    
        public StringProperty DateOfBirthProperty() {
        return dateOfBirth;
    }
        
        public StringProperty CellNumberProperty() {
        return cellNumber;
    }
        
        public StringProperty WardNameProperty() {
        return wardName;
    }
}
