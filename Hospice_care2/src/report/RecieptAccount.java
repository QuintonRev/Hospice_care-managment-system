/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

/**
 *
 * @author Reverside
 */
public class RecieptAccount {

    private String monthly_fee;
    private String payment_type;
    private String amount_due;
    private String date;
    private String time;

    public RecieptAccount(String monthly_fee, String payment_type, String amount_due, String date, String time) {
        this.monthly_fee = monthly_fee;
        this.payment_type = payment_type;
        this.amount_due = amount_due;
        this.date = date;
        this.time = time;
    }

 

    public String getAmount_due() {
        return amount_due;
    }

    public String getMonthly_fee() {
        return monthly_fee;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
