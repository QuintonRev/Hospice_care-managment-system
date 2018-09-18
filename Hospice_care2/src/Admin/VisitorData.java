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
 * @author Reverside
 */
public class VisitorData {
    private final StringProperty visitor_no;
    private final StringProperty surname;
    private final StringProperty name;
    private final StringProperty checkIn;
    private final StringProperty checkout;  
    private final StringProperty contactNumber;
    private final StringProperty dateVisit;
    private final StringProperty reason;
    private final StringProperty PatientId;

    //Default constructor
    public VisitorData(String visitor_no, String name, String Surname, String cell_number, String checkinTime,
            String checkOutTime, String Date_of_visit, String reason,String PatientId) {

        this.visitor_no = new SimpleStringProperty(visitor_no);
        this.surname = new SimpleStringProperty(Surname);
        this.name = new SimpleStringProperty(name);
        this.contactNumber = new SimpleStringProperty(cell_number);
        this.checkIn = new SimpleStringProperty(checkinTime);
        this.checkout = new SimpleStringProperty(checkOutTime);
        
        this.dateVisit = new SimpleStringProperty(Date_of_visit);
        this.reason = new SimpleStringProperty(reason);
        this.PatientId = new SimpleStringProperty(PatientId);
    }

    public String getVisitorNumber() {
        return visitor_no.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public String getName() {
        return name.get();
    }
    public String getCheckIn() {
        return checkIn.get();
    }
    public String getCheckout() {
        return checkout.get();
    }
    public String getContact() {
        return contactNumber.get();
    }
    public String getReason() {
        return reason.get();
    }
    
    public String getDateVisit() {
        return dateVisit.get();
    }
    public String getDataVisit(){
        return PatientId.get();
    }
    
    public String getPatientId(){
        return PatientId.get();
        
    }
    
    public void setPatientId(String value){
        PatientId.set(value);
    }
    public void setVisitorNumber(String value){
        visitor_no.set(value);
    }
    
    public void setSurname(String value){
        surname.set(value);
    }
    
   public void setName(String value){
        name.set(value);
    }
    
   public void setCheckIn(String value){
        checkIn.set(value);
    }
    
   public void setCheckOut(String value){
        checkout.set(value);
    }

   public void setContact(String value){
        contactNumber.set(value);
    }

   public void setReason(String value){
        reason.set(value);
    }
   public void setDateVisit(String value){
        dateVisit.set(value);
    }
   

    //Property values
    public StringProperty VisitorNumberProperty() {
        return visitor_no;
    }

    public StringProperty Surnameperty() {
        return surname;
    }

    public StringProperty NameProperty() {
        return name;
    }

    public StringProperty CheckInProperty() {
        return checkIn;
    }

    public StringProperty CheckOutProperty() {
        return checkout;
    }

    public StringProperty ContactProperty() {
        return contactNumber;
    }

    public StringProperty ReasonProperty() {
        return reason;
    }

    public StringProperty DateVisitProperty() {
        return dateVisit;
    }
    
    public StringProperty PatientIdProperty(){
        return PatientId;
    }
    
    
}
