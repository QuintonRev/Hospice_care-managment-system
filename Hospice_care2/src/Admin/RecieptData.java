/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Reverside
 */
public class RecieptData {
    
    private final StringProperty recieptId;
    private final StringProperty date;
    private final StringProperty time;
    private final DoubleProperty monthlyFee;

    public RecieptData(String recieptId, String date, String time, double monthlyFee) {
        this.recieptId = new SimpleStringProperty(recieptId);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.monthlyFee = new SimpleDoubleProperty(monthlyFee);
    }

    public String getRecieptId() {
        return recieptId.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public Double getMonthlyFee() {
        return monthlyFee.get();
    }
    
      public void setRecieptId(String value){
        recieptId.set(value);
    }
    
        public void setDate(String value){
        date.set(value);
    }
          public void setTime(String value){
        time.set(value);
    }
          
            public void setMonthlyFee(double value){
        monthlyFee.set(value);
    }
            
        public StringProperty recieptIdProperty() {
        return recieptId;
    }
        
         public StringProperty dateProperty() {
        return date;
    }
          public StringProperty timeProperty() {
        return time;
    }
           public DoubleProperty monthlyFeeProperty() {
        return monthlyFee;
    }
}
