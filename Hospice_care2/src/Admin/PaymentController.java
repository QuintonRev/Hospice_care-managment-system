/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import dbUtil.dbConnection1;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Reverside
 */
public class PaymentController implements Initializable {

    PatientData patientData;
    public String FirstName;
    public String LastName;
    public Double totalAmount;
    public String id;
    public String time;
    public String date1;

    @FXML
    private Label lblPatientName;

    @FXML
    private TextField txtMonthlyFee;

    @FXML
    private Label lblAmountDue;

    @FXML
    private Button btnCompute;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleGroup Payment;
    @FXML
    private Label lblerror_ID;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private CheckBox chkAddmissionFee;

    @FXML
    private RadioButton rdbCash;

    @FXML
    private RadioButton rdbDebit;

    private dbConnection1 dc;
    private double amountDue = 12000;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //when ever this window pops out it will create a new instance from our database
        this.dc = new dbConnection1();
    }

    public void setPatientData(String firstName, String lastName) {
        this.patientData = patientData;
        this.FirstName = firstName;
        this.LastName = lastName;
        lblPatientName.setText(" " + this.FirstName + " " + " " + lastName);
        lblAmountDue.setText("R" + Double.toString(amountDue));
    }

    public void setPatientData(String id, String firstName, String lastName) {
        this.patientData = patientData;
        this.FirstName = firstName;
        this.LastName = lastName;
        lblPatientName.setText(" " + this.FirstName + " " + " " + lastName);
        this.id = id;
        lblAmountDue.setText("R" + this.getAmountDue());
    }

    @FXML
    public void addmissionPaid(ActionEvent action) {
        if (chkAddmissionFee.isSelected()) {
            amountDue = amountDue - 2000;
            lblAmountDue.setText("R" + Double.toString(amountDue));
        } else if (!chkAddmissionFee.isSelected()) {
            amountDue = amountDue + 2000;
            lblAmountDue.setText("R" + Double.toString(amountDue));
        }
    }

    int btnIsClickable = 1;
    double packagePrice = 12000;
    String admission = "2000";

    @FXML
    public void resetFields() {
        btnIsClickable = 1;
    }

    public Double getAmountDue() {
        Double rslt = 0.00;
        String sqlPayment = "SELECT * FROM account WHERE patient_id LIKE ?";
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlPayment);
            
            stmt.setString(1, this.id);
         
            ResultSet rs = stmt.executeQuery();
            
            
            while(rs.next())
            {
                rslt = rslt + Double.parseDouble(rs.getString("amount_due"));
            }

            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rslt;
    }

    @FXML
    public void makeCalculations(ActionEvent action) {
        if (btnIsClickable == 1) {

            lblAmountDue.setText("R" + Double.toString(amountDue));

            if (this.txtMonthlyFee.getText().equals("") || this.txtMonthlyFee.getText().equals(null)) {
                JOptionPane.showMessageDialog(null, "Please enter monthly fee to compute payment");

            } else {
                if (chkAddmissionFee.isSelected()) {
                    String monthlyFeeStr = this.txtMonthlyFee.getText();
                    Double monthlyFee = Double.parseDouble(monthlyFeeStr);

                    totalAmount = monthlyFee + Double.parseDouble(admission);
                    lblTotalAmount.setText(Double.toString(totalAmount));

                    amountDue = packagePrice;
                    amountDue = amountDue - totalAmount;
                    lblAmountDue.setText("R" + Double.toString(amountDue));

                    btnIsClickable = 0;
                } else {
                    JOptionPane.showMessageDialog(null, "Please ensure that an admission fee is paid first");
                }
            }
        } else if (btnIsClickable == 0) {

        }
    }

    @FXML
    public void PaymentTransaction(ActionEvent action) {
        if (btnIsClickable == 1) {
            if (this.txtMonthlyFee.getText().equals("") || this.txtMonthlyFee.getText().equals(null)) {
                JOptionPane.showMessageDialog(null, "Please enter monthly fee to compute payment");

            } else {
                if (!chkAddmissionFee.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please ensure that an admission fee is paid first");
                } else if (btnIsClickable == 1 && (!this.txtMonthlyFee.getText().equals("") || !this.txtMonthlyFee.getText().equals(null))) {
                    JOptionPane.showMessageDialog(null, "Please compute payment first");
                }
            }
        } else if (btnIsClickable == 0) {
            String sqlPayment = "INSERT INTO account(payment_type,addmission_fee,amount_due,total_amount"
                    + ")VALUES(?,?,?,?)";
            try {
                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sqlPayment);

                if (rdbCash.isSelected()) {
                    stmt.setString(1, this.rdbCash.getText());
                } else {
                    stmt.setString(2, this.rdbDebit.getText());
                }

                if (chkAddmissionFee.isSelected()) {
                    stmt.setString(2, admission);
                }

                stmt.setString(3, Double.toString(amountDue));
                stmt.setString(4, Double.toString(totalAmount));

                stmt.execute();

                JOptionPane.showMessageDialog(null, "Payment Transaction has been successfully made");

                reciept(Double.parseDouble(this.txtMonthlyFee.getText()));

                conn.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void reciept(double monthlyFee) {

        JOptionPane.showMessageDialog(null, "Writing to Reciept table...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        String time = stf.format(date);
        String date1 = sdf.format(date);

        String accountNo = "";
        try {
            String sqlReciept = "SELECT account_no FROM account";
            Connection conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlReciept);
            ResultSet rs = stmt.executeQuery();

            while (rs.last()) {
                accountNo = rs.getString(1);
                break;
            }

            conn.close();
            stmt.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        try {

            String sqlReciept = "INSERT INTO reciept(Date, Time, Monthly_fee, account_no)VALUES(?, ?, ?, ?)";

            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlReciept);

            stmt.setString(1, date1);
            stmt.setString(2, time);
            stmt.setString(3, Double.toString(monthlyFee));
            stmt.setString(4, accountNo);

            stmt.execute();

            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
