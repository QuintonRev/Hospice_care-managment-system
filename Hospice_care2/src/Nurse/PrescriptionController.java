/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nurse;

import Admin.MedicationData;
import Admin.PatientData;
import Validation.TextFieldValidation;
import dbUtil.dbConnection1;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reverside
 */
public class PrescriptionController implements Initializable {
    
    
    PatientData patientData;
    
    @FXML
    private TableView<MedicationData> tableMedication;


    @FXML
    private TableColumn<MedicationData, String> barcode_column;

    @FXML
    private TableColumn<MedicationData, String> medName_column;

    @FXML
    private TableColumn<MedicationData, String> medQuantity_column;

    @FXML
    private TableColumn<MedicationData, String> medDate_column;

    @FXML
    private TableColumn<MedicationData, String> custService_column;

    @FXML
    private TableColumn<MedicationData, String> website_column;

    @FXML
    private Label lblPatientname;

    @FXML
    private Label lblPatientSurname;

    @FXML
    private Label lblPatientID;

    @FXML
    private TextField txtCapsules;

    @FXML
    private TextField txtBatchNo;

    @FXML
    private DatePicker txtExpiary;

    @FXML
    private TextField txtDays;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private Label lblCurrentDate;

    /**
     * Initializes the controller class.
     */
    public String currentDate = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        
        Date date = new Date();
        
        
        currentDate = sdf.format(date);
        
        this.lblCurrentDate.setText(currentDate);
        
         try {
            Connection conn = dc.getConnection();

            data3 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM medication");

            while (rs.next()) {
                data3.add(new MedicationData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
                
            }
            
          
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        
        barcode_column.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        medName_column.setCellValueFactory(new PropertyValueFactory<>("medName"));
        medDate_column.setCellValueFactory(new PropertyValueFactory<>("medUpdate"));
        medQuantity_column.setCellValueFactory(new PropertyValueFactory<>("medQuantity"));
        custService_column.setCellValueFactory(new PropertyValueFactory<>("medCustomerService"));
        website_column.setCellValueFactory(new PropertyValueFactory<>("medWebsite"));
       
        
        tableMedication.setItems(null);
        tableMedication.setItems(data3);

        
    }    
    
    public void setPatientAccount(PatientData patientData){
        this.lblPatientID.setText(patientData.getID());
        this.lblPatientSurname.setText(patientData.getLastName());
        this.lblPatientname.setText(patientData.getFirstName());
        
    }
     private dbConnection1 dc;
     private ObservableList<MedicationData> data3;
     
    @FXML
    private void LoadMedication(ActionEvent event) throws SQLException {
        try {
            Connection conn = dc.getConnection();

            data3 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM medication");

            while (rs.next()) {
                data3.add(new MedicationData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
                 Alert alter = new Alert(Alert.AlertType.ERROR, rs.getString(7), ButtonType.OK);
           alter.show();
            }
            
          
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        
        barcode_column.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        medName_column.setCellValueFactory(new PropertyValueFactory<>("medName"));
        medDate_column.setCellValueFactory(new PropertyValueFactory<>("medUpdate"));
        medQuantity_column.setCellValueFactory(new PropertyValueFactory<>("medQuantity"));
        custService_column.setCellValueFactory(new PropertyValueFactory<>("medCustomerService"));
        website_column.setCellValueFactory(new PropertyValueFactory<>("medWebsite"));
       
        
        tableMedication.setItems(null);
        tableMedication.setItems(data3);

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        //Loads data to the nurse table
    }
    
    
     public void PrescribeMedication(ActionEvent event){
        
        
        
        
     
      String sqlInsert = "INSERT INTO visitor(name,Surname,cell_number,checkInTime,checkOutTime,Date_of_visit,reason, PatientId) VALUES(?,?,?,?,?,?,?,?)";
       
      
      try {
            
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            
            
         
            
            stmt.setString(1, this.txtExpiary.getEditor().getText());
            stmt.setString(2, this.txtBatchNo.getText());
            stmt.setString(3, this.txtCapsules.getText());
           stmt.setString(4, this.txtDays.getText());
           stmt.setString(5, this.txtDiagnosis.getText());
            stmt.setString(6, this.patientData.getID());
            //stmt.setString(6, this.gender.getText());
          
            
            //stmt.setString(9, this.txtSearch.getText());
            
            stmt.execute();
            
             
           Alert alter = new Alert(Alert.AlertType.INFORMATION, "Nurse has been registered successfully!", ButtonType.OK);
           alter.show();
            
            this.txtBatchNo.setText("");
            this.txtCapsules.setText("");
            this.txtDays.setText("");
            this.txtDiagnosis.setText("");
           
            conn.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
     }
    

