//This class acts as a model class, holding getters, setters and properties
package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class PatientData {

    private final StringProperty ID;
    
    
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty DOB;
    private final StringProperty cellNumber;
    
    private final StringProperty address;
    private final StringProperty zipCode;
    private final StringProperty gender;
    private final StringProperty specialCondition;
    private final StringProperty nxtName;
    private final StringProperty nxtSurname;
    private final StringProperty nxtCellNumber;
    private final StringProperty nxtRelationship;

    //Default constructor
    public PatientData(String Id, String first_name, String last_name, String email, String dob,
            String cell_number, String address, String zip_code, String gender, String special_condition,
            String nxt_name, String nxt_surname, String nxt_cellno, String nxt_relationship) {

        this.ID = new SimpleStringProperty(Id);
        this.firstName = new SimpleStringProperty(first_name);
        this.lastName = new SimpleStringProperty(last_name);
        this.email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);
        this.cellNumber = new SimpleStringProperty(cell_number);
        this.address = new SimpleStringProperty(address);
        this.zipCode = new SimpleStringProperty(zip_code);
        this.gender = new SimpleStringProperty(gender);
        this.specialCondition = new SimpleStringProperty(special_condition);
        this.nxtName = new SimpleStringProperty(nxt_name);
        this.nxtSurname = new SimpleStringProperty(nxt_surname);
        this.nxtCellNumber = new SimpleStringProperty(nxt_cellno);
        this.nxtRelationship = new SimpleStringProperty(nxt_relationship);
    }

    public String getID() {
        return ID.get();
    }
    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getAddress() {
        return address.get();
    }
    public String getGender() {
        return gender.get();
    }
    public String getZipCode() {
        return zipCode.get();
    }
    public String getCellNumber() {
        return cellNumber.get();
    }
    public String getSpecialCondition() {
        return specialCondition.get();
    }

    public String getNxtName() {
        return nxtName.get();
    }

    public String getNxtSurname() {
        return nxtSurname.get();
    }

    public String getNxtCellNumber() {
        return nxtCellNumber.get();
    }

    public String getNxtRelationship() {
        return nxtRelationship.get();
    }

        public void setID(String value){
        ID.set(value);
    }
    
    public void setFirstName(String value){
        firstName.set(value);
    }
    
        public void setLastName(String value){
        lastName.set(value);
    }
    
    public void setEmail(String value){
        email.set(value);
    }
    
   public void setAddress(String value){
        address.set(value);
    }

    public void setGender(String value){
        gender.set(value);
    }

   public void setZipCode(String value){
        zipCode.set(value);
    }
   
   public void setCellNumber(String value){
        cellNumber.set(value);
    }

   public void setSpecialCondition(String value){
        specialCondition.set(value);
    }
    
   public void setNxtName(String value){
        nxtName.set(value);
    }

   public void setNxtSurname(String value){
        nxtSurname.set(value);
    }

   public void setNxtCellNumber(String value){
        nxtCellNumber.set(value);
    }
    
   public void setNxtRelationship(String value){
        nxtRelationship.set(value);
    }

    public StringProperty getDOB() {
        return DOB;
    }

    public void setDOB(String value){
        DOB.set(value);
    }

    //Property values
    public StringProperty IDProperty() {
        return ID;
    }

    public StringProperty firstNameroperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public StringProperty cellNumberProperty() {
        return cellNumber;
    }

    public StringProperty specialConditionProperty() {
        return specialCondition;
    }

    public StringProperty nxtSurnameProperty() {
        return nxtSurname;
    }

    public StringProperty nxtCellNumberProperty() {
        return nxtCellNumber;
    }

    public StringProperty nxtRelationshipProperty() {
        return nxtRelationship;
    }
    
}
