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
public class PaymentData {

    private final StringProperty accountId;
    private final StringProperty paymentType;
    private final DoubleProperty addmissionFee;
    private final DoubleProperty amountDue;
    private final DoubleProperty totalAmount;

    public PaymentData(String accountId, String paymentType, Double addmissionFee, Double amountDue, Double totalAmount) {
        this.accountId = new SimpleStringProperty(accountId);
        this.paymentType = new SimpleStringProperty(paymentType);
        this.addmissionFee = new SimpleDoubleProperty(addmissionFee);
        
        this.amountDue = new SimpleDoubleProperty(amountDue);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);

    }

    public String getAccountId() {
        return accountId.get();
    }

    public String getPaymentType() {
        return paymentType.get();
    }

    public double getAddmissionFee() {
        return addmissionFee.get();
    }

    

    public double getAmountDue() {
        return amountDue.get();
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public void setAccountId(String value) {
        accountId.set(value);
    }

    public void setPaymentType(String value) {
        paymentType.set(value);
    }

    public void setAddmissionFee(Double value) {
        addmissionFee.set(value);
    }

   

    public void setAmountDue(Double value) {
        amountDue.set(value);
    }

    public void setTotalAmount(Double value) {
        totalAmount.set(value);
    }

    public StringProperty accountIdProperty() {
        return accountId;
    }

    public StringProperty paymentTypeProperty() {
        return paymentType;
    }

    public DoubleProperty addmissionFeeProperty() {
        return addmissionFee;
    }

    

    public DoubleProperty amountDueProperty() {
        return amountDue;
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }

}
