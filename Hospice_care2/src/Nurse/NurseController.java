/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nurse;

import Admin.MedicationData;
import Admin.PatientData;
import dbUtil.dbConnection1;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Reverside
 */
public class NurseController implements Initializable {
    
    private AnchorPane anchorPane;
    private Stage stage = new Stage();
    
           
    
    @FXML
    private Label lblNurseLog;
    
    @FXML
    private TableView<PatientData> tablePatient;
    @FXML
    private TableColumn<PatientData, String> PatientID;

    @FXML
    private TableColumn<PatientData, String> PatientName;

    @FXML
    private TableColumn<PatientData, String> PatientSurname;

    @FXML
    private TableColumn<PatientData, String> PatientAge;

    @FXML
    private TableColumn<PatientData, String> PatientCondition;
     @FXML
    private TableColumn<PatientData, String> namenxt_column;

    @FXML
    private TableColumn<PatientData, String> contactnxt_column;

    @FXML
    private TableColumn<PatientData, String> nxtrelationship_column;
    
    @FXML
    private TextArea address;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblNo;

    @FXML
    private Label lblExpiary;

    @FXML
    private Label lblBatch;

    @FXML
    private Label lblMedicine;

    @FXML
    private Label lblTablets;

    @FXML
    private Label lblDays;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblWebsite;
    @FXML
    private Label lblNurse;
    @FXML
    private Label lblCount;
    
    @FXML
    private Label lblDate;
     @FXML
    private Label lblWardName;
       @FXML
    private Label lblWardName1;
       
    private ObservableList<PatientData> data;
    private ObservableList<MedicationData> data2;
    public String NurseID;

    private dbConnection1 dc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection1();
        
       
        
        setCellValueFromTableVisitorToTextField();
    }
    
    public void btnSite(ActionEvent event) {
        //engine.load("https://www.google.com");
    }
    
    public void setNurseName(String user) {

        //this.lblAdminLog.setText("Kate williams");
        //this.lblUserMain.setText("KATE");
        String sqlSearch = "SELECT * FROM nurse WHERE email=?";
        
        
        String firstName = null;
        String lastName = null;
        String WardId = null;
        
        String ward = null;
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearch);
           
            
            pst.setString(1, user);
            
            
            ResultSet rs = pst.executeQuery();
            
            //ward = " "+WardId+" "+WardName;
            while (rs.next()) {
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                WardId = rs.getString(12);
                
                //this.lblUserMain.setText(rs.getString(3));

                NurseID = rs.getString(1);
                
            }
            
            String fullName = firstName + " " + " " + lastName;
            this.lblNurseLog.setText(fullName);
            this.lblWardName.setText(WardId);
            getWardName(WardId);
        } catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getWardName(String WardId){
        String WardName = null;
        
        try {
            String sqlWardName = "Select * From ward where Ward_ID=?";
            Connection conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlWardName);
            stmt.setString(1, WardId);
            ResultSet rs2 = stmt.executeQuery();
            
            while (rs2.next()){
                WardName = rs2.getString(2);
            }
            this.lblWardName1.setText(WardName);
   
        } catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void loadPatientData(ActionEvent event) throws SQLException {
        try {
            Connection conn = dbConnection1.getConnection();
            this.data = FXCollections.observableArrayList();

            //rs gets the data and executes the query from the sql database
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM patient");
            //checks if theres anything in the table and runs until the last row
            while (rs.next()) {
                this.data.add(new PatientData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));

            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }

        //calculates patients age 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date date = new Date();

        String date1 = sdf.format(date);

        //"this" referes to the global variable
        this.PatientID.setCellValueFactory(new PropertyValueFactory<PatientData, String>("ID"));
        this.PatientName.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firtName"));
        this.PatientSurname.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        this.PatientAge.setCellValueFactory(new PropertyValueFactory<PatientData, String>("email"));
        this.PatientCondition.setCellValueFactory(new PropertyValueFactory<PatientData, String>("DOB"));
        this.contactnxt_column.setCellValueFactory(new PropertyValueFactory<PatientData, String>("nxtCellNumber"));
        this.namenxt_column.setCellValueFactory(new PropertyValueFactory<PatientData, String>("nxtSurname"));
        this.nxtrelationship_column.setCellValueFactory(new PropertyValueFactory<PatientData, String>("nxtRelationship"));
        //this.conditioncolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("special_condition"));
        tablePatient.setItems(null);
        tablePatient.setItems(data);

    }

    public PatientData patientDataPrescr;

    @FXML
    public void setCellValueFromTableVisitorToTextField() {
        tablePatient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                patientDataPrescr = tablePatient.getItems().get(tablePatient.getSelectionModel().getSelectedIndex());

                DisplayPrescription(patientDataPrescr.getID());

            }
        });
    }

    public String Barcode = null;

    @FXML
    public void DisplayPrescription(String ID) {
        String sqlSearch = "SELECT * FROM medication WHERE Id=?";

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearch);

            pst.setString(1, patientDataPrescr.getID());

            ResultSet rs = pst.executeQuery();

            String contact = null;
            String med_Name = null;
            String web = null;

            //Prescription variables
            while (rs.next()) {
                Barcode = rs.getString(1);
                med_Name = rs.getString(2);
                contact = rs.getString(5);
                web = rs.getString(6);

                this.lblPatientName.setText("SURNAME: " + this.patientDataPrescr.getLastName());
                this.lblMedicine.setText("MEDICINE: " + med_Name);
                this.lblContact.setText("Tel :" + contact);
                this.lblWebsite.setText("Website " + web);

                prescription(patientDataPrescr.getID());
                getRowCount(patientDataPrescr.getID());
            }

            // pst.close();
            // pst2.close();
           // conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getRowCount(String Id){
       
       
       try {
           String sqlRowCount = "select COUNT(prescription_no) from prescription where PatientId =?";
            Connection conn = dbConnection1.getConnection();
            PreparedStatement pst2 = conn.prepareStatement(sqlRowCount);

            pst2.setString(1, Id);
            
            ResultSet rs2 = pst2.executeQuery();
            
            if(rs2.next()){
                String sum = rs2.getString("COUNT(prescription_no)");
                
                this.lblCount.setText(sum);
            }
            
            //Get result set metadata
            //ResultSetMetaData rsMetaData = rs2.getMetaData();
            
            //Display info
           // int coulumnCount = rsMetaData.getColumnCount();
            
    }   catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void prescription(String Id) {
        String nurseId = null;
        
        try {
            String sqlPrescription = "SELECT * FROM prescription WHERE PatientId=?";

            Connection conn = dbConnection1.getConnection();
            PreparedStatement pst2 = conn.prepareStatement(sqlPrescription);
            
            pst2.setString(1, Id);
            
            ResultSet rs2 = pst2.executeQuery();
            
            String tablet = null;
            String exp = null;
            String batch = null;
            String PrescNum = null;
            String Days = null;
            String DateIn = null;
            
            while (rs2.next()) {
                PrescNum = rs2.getString(1);
                exp = rs2.getString(2);
                batch = rs2.getString(3);
                tablet = rs2.getString(4);
                Days = rs2.getString(5);
                DateIn = rs2.getString(6);
                
                nurseId = rs2.getString(9);

                this.lblNo.setText("No.: " + PrescNum);
                this.lblExpiary.setText("Exp: " + exp);
                this.lblBatch.setText("Batch: " + batch);
                this.lblTablets.setText(tablet);
                this.lblDays.setText(Days);
                
                this.lblDate.setText("Date: "+ DateIn); 
                getNurseName(nurseId);
                
               // conn.close();

            }

           // conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getNurseName(String nurseId) {
        
        
        try {

            String sqlNurse = "SELECT first_name, last_name FROM nurse WHERE id=?";

            Connection conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlNurse);
            stmt.setString(1, nurseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nurseName = rs.getString("first_name");
                String nurseSurname = rs.getString("last_name");
                this.lblNurse.setText("By: " + nurseName + " " + nurseSurname);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void PrescriptionForm(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("Prescription.fxml"));
        anchorPane = loader.load();
        
        PrescriptionController prescriptionController = loader.getController();
        prescriptionController.setPatientAccount(patientDataPrescr);
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.setTitle("Prescription Application");
        stage.show();

    }
    
    @FXML
    private void WebView(ActionEvent event) throws SQLException, IOException {

        try {

            /////////////////////////////////////////////
            if (patientDataPrescr != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("webview.fxml"));
                anchorPane = loader.load();

                //WebviewController webviewController = loader.getController();
                //webviewController.setMedicationData(md);

                Scene scene = new Scene(anchorPane);

                stage.setScene(scene);
                stage.setTitle("Webview Application");
                stage.show();
            } else {
           Alert alter = new Alert(Alert.AlertType.ERROR, "Please select the appropriate row first", ButtonType.OK);
           alter.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
