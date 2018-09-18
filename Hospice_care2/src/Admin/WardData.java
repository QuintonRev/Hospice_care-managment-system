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
public class WardData {
    
    private final StringProperty wardID;
    private final StringProperty wardName;
    private final StringProperty numberOfBeds;

    public WardData(String wardID, String wardName, String numberOfBeds) {
        this.wardID = new SimpleStringProperty(wardID);
        this.wardName = new SimpleStringProperty(wardName);
        this.numberOfBeds = new SimpleStringProperty(numberOfBeds);
    }
    
    public String getWardID() {
        return wardID.get();
    }



    public String getWardName() {
        return wardName.get();
    }

   

    public String getNumberOfBeds() {
        return numberOfBeds.get();
    }
    
     public void setWardName(String value){
        wardName.set(value);
    }
    
        public void setNumberOfBeds(String value){
        numberOfBeds.set(value);
    }
         public StringProperty wardIDProperty() {
        return wardID;
    }

    public StringProperty wardNameProperty() {
        return wardName;
    }

    public StringProperty numberOfBedsProperty() {
        return numberOfBeds;
    }
}
