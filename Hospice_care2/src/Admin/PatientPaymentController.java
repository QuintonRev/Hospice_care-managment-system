/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Validation.TextFieldValidation;
import dbUtil.dbConnection1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Reverside
 */
public class PatientPaymentController implements Initializable {

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

            while (rs.next()) {
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

                String monthlyFeeStr = this.txtMonthlyFee.getText();
                Double monthlyFee = Double.parseDouble(monthlyFeeStr);

                totalAmount = monthlyFee;
                lblTotalAmount.setText(Double.toString(totalAmount));

                amountDue = this.getAmountDue();
                amountDue = amountDue - totalAmount;
                lblAmountDue.setText("R" + Double.toString(amountDue));

                btnIsClickable = 0;

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
                if (btnIsClickable == 1 && (!this.txtMonthlyFee.getText().equals("") || !this.txtMonthlyFee.getText().equals(null))) {
                    JOptionPane.showMessageDialog(null, "Please compute payment first");
                }
            }
        } else if (btnIsClickable == 0) {
            String sqlPayment = "UPDATE account SET amount_due = ? WHERE patient_id = ?";
            try {
                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sqlPayment);

                stmt.setString(1, Double.toString(amountDue));

                stmt.setString(2, this.id);

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        String time = stf.format(date);
        String date1 = sdf.format(date);

        String accountNo = "";
        try {
            String sqlReciept = "SELECT account_no FROM account WHERE patient_id = '" + this.id + "'";
            Connection conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlReciept);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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

            printReciept();

            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void printReciept() {

        String patientName = "";
        String paymentType = "";
        String accountNo = "";
        String addmissionFee = "";
        String monthlyFee = this.txtMonthlyFee.getText();
        String amountDue = "";
        String totalAmount = "";
        DecimalFormat decimalformat = new DecimalFormat("#.00");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        String time = stf.format(date);
        String date1 = sdf.format(date);

        try {

            String sqlReciept = "SELECT * FROM patient WHERE id = '" + this.id + "'";
            Connection conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlReciept);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                patientName = rs.getString(2) + " " + rs.getString(3);
            }

            sqlReciept = "SELECT * FROM account WHERE patient_id = '" + this.id + "'";
            conn = dbConnection1.getConnection();
            stmt = conn.prepareStatement(sqlReciept);
            rs = stmt.executeQuery();

            while (rs.next()) {
                paymentType = rs.getString(2);
                accountNo = rs.getString(1);
                addmissionFee = rs.getString(3);
                amountDue = rs.getString(4);
            }

            conn.close();
            stmt.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        try {

            String userHomeDirectory = System.getProperty("user.home") + "\\Desktop\\";
            HashMap<String, Object> para = new HashMap<>();
            para.put("patientId", this.id);
            para.put("patientName", patientName);
            para.put("paymentType", paymentType);
            para.put("accountNo", accountNo);
            para.put("addmissionFee", "R" + addmissionFee);
            para.put("amountDue", "R" + amountDue);
            para.put("monthlyFee", "R" + decimalformat.format(Double.parseDouble(monthlyFee)));
            para.put("totalAmount", "R" + decimalformat.format(Double.parseDouble(monthlyFee)));
            para.put("date", date1);
            para.put("time", time);
            para.put("admin", "Kate");
            JasperPrint jasperPrint = JasperFillManager.fillReport("src\\Report\\reciept.jasper", para, new JREmptyDataSource());

            DateFormat df = new SimpleDateFormat("ddMMyyyy");
            Calendar cal = Calendar.getInstance();
            String outputFile = userHomeDirectory + File.separatorChar + "reciept_" + this.id + "_" + df.format(cal.getTime()) + ".pdf";;

            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(new File(outputFile));
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                System.out.println("Genenaring Reciept...");
                outputStream.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PatientPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Reciept Printed Successfully");
            

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
