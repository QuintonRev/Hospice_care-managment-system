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
public class MedicationData {

    //private final StringProperty medId;
    private final StringProperty barcode;
    private final StringProperty medName;
    private final StringProperty medQuantity;
    private final StringProperty medUpdate;
    private final StringProperty medCustomerService;
    private final StringProperty medWebsite;
    private final StringProperty AdminName;
    private final StringProperty Id;
    
    public MedicationData(String barcode, String medName, String medQuantity, String medUpdate,
            String medCustomerService, String medWebsite, String AdminName, String Id) {

         //this.medId = new SimpleStringProperty(medId);
        this.barcode = new SimpleStringProperty(barcode);
        this.medName = new SimpleStringProperty(medName);
        this.medQuantity = new SimpleStringProperty(medQuantity);
        this.medUpdate = new SimpleStringProperty(medUpdate);
        this.medCustomerService = new SimpleStringProperty(medCustomerService);
        this.medWebsite = new SimpleStringProperty(medWebsite);
        this.AdminName = new SimpleStringProperty(AdminName);
        this.Id = new SimpleStringProperty(Id);
    }
////////////getters
    public String getPatientId(){
        return Id.get();
    }
    
    public String getAdmin(){
        return AdminName.get();
    }
    public String getBarcode() {
        return barcode.get();
    }

    public String getMedName() {
        return medName.get();
    }

    public String getMedQuantity() {
        return medQuantity.get();
    }

    public String getMedUpdate() {
        return medUpdate.get();
    }

    public String getMedCustomerService() {
        return medCustomerService.get();
    }

    public String getMedWebsite() {
        return medWebsite.get();
    }
///////////////////////////////////Setters
    
    public void setPatientId(String value){
        Id.set(value);
    }
    
    public void setBarcode(String value) {
        barcode.set(value);
    }
    public void setAdmin(String value){
        AdminName.set(value);
    }
    public void setMedName(String value) {
        medName.set(value);
    }
    
    public void setMedQuantity(String value) {
        medQuantity.set(value);
    }

    public void setMedUpdate(String value) {
        medUpdate.set(value);
    }

    public void setMedCustomerService(String value) {
        medCustomerService.set(value);
    }

    public void setMedWebsite(String value) {
        medWebsite.set(value);
    }
//////////////String Property objects
    

    public StringProperty BarcodeProperty() {
        return barcode;
    }

    public StringProperty MedNameProperty() {
        return medName;
    }
    public StringProperty MedQuantityProperty() {
        return medQuantity;
    }

    public StringProperty MedUpdateProperty() {
        return medUpdate;
    }

    public StringProperty MedCustomerServiceProperty() {
        return medCustomerService;
    }

    public StringProperty MedWebsiteProperty() {
        return medWebsite;
    }
    public StringProperty AdminNameProperty(){
        return AdminName;
}
    public StringProperty PatientId(){
        return Id;
    }
    
    }
