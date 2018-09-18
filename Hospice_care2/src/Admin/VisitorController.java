/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Validation.TextFieldValidation;
import dbUtil.dbConnection1;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller
 * @author Reverside
 */
public class VisitorController implements Initializable {

  
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtContact;
    @FXML
    private Label lblCheckInTime;
    @FXML
    private Label lblCheckInDate;
    @FXML
    private Label lblerror_surname;
    @FXML
    private Label lblerror_name;
    @FXML
    private Label lblerror_reason;
    @FXML
    private Label lblpatientdetails;
    @FXML
    private Label lblpatientdetails1;
    @FXML
    private Label lblpatientdetails11;
    @FXML
    private Label lblerror_numeric;
    @FXML
    private Label lblerror_search;
    public String time;
    public String date1;
    
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtReason;
    
    public String searchID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        
        Date date = new Date();
        
        time = stf.format(date);
        date1 = sdf.format(date);
        
        lblCheckInDate.setText(date1);
        
        lblCheckInTime.setText(time);
        
    }    
    
    public void CheckInVisit(ActionEvent event){
        
        
        
        
      boolean isReasonEmpty = TextFieldValidation.isTextFieldNotEmpty(txtReason, lblerror_reason, "Error: Reason input field Is required!");
      boolean isSurnameEmpty = TextFieldValidation.isTextFieldNotEmpty(txtLastName, lblerror_surname, "Error: Surname input field Is required!");
      boolean isNameEmpty = TextFieldValidation.isTextFieldNotEmpty(txtName, lblerror_name, "Error: Name input field Is required!");
      boolean isContactEmpty = TextFieldValidation.istextFieldTypeNumber(txtContact, lblerror_numeric, "Error: Contact Number must be numeric!");
      boolean isSearchEmpty = TextFieldValidation.isTextFieldNotEmpty(txtSearch,lblerror_search,"Error: The Search filter('Search Patient') must be uterlised");
      if(isReasonEmpty&&isSurnameEmpty&&isNameEmpty&&isContactEmpty){
      String sqlInsert = "INSERT INTO visitor(name,Surname,cell_number,checkInTime,checkOutTime,Date_of_visit,reason, PatientId) VALUES(?,?,?,?,?,?,?,?)";
       
      
      try {
            
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            
            
         
            
            stmt.setString(1, this.txtName.getText());
            stmt.setString(2, this.txtLastName.getText());
            stmt.setString(3, this.txtContact.getText());
           stmt.setString(4, this.time);
           stmt.setString(5, " ");
            stmt.setString(6, this.date1);
            //stmt.setString(6, this.gender.getText());
          
            stmt.setString(7, this.txtReason.getText());
            stmt.setString(8, this.searchID);
            //stmt.setString(9, this.txtSearch.getText());
            
            stmt.execute();
            
             
           Alert alter = new Alert(Alert.AlertType.INFORMATION, "Nurse has been registered successfully!", ButtonType.OK);
           alter.show();
            
            this.txtName.setText("");
            this.txtLastName.setText("");
            this.txtContact.setText("");
            this.txtReason.setText("");
           
            conn.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
     }
    @FXML
    private void searchData(ActionEvent event) throws SQLException {

     String sqlSearch = "SELECT Id, first_name, last_name, ward_Id FROM patient WHERE first_name=?";
     
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearch);
            pst.setString(1, this.txtSearch.getText());
            
            
            ResultSet rs = pst.executeQuery();
            
             
            if (rs.next()) {
                
               // JOptionPane.showMessageDialog(null, rs.getString(1) + " " + rs.getString(2));
                String searchResult = rs.getString(2) + " " + rs.getString(3);
                searchID = rs.getString(1);
                String searchWard = rs.getString(4);
                
               lblpatientdetails.setText("ID: "+ searchID); 
               lblpatientdetails1.setText("Name: "+ searchResult);
               lblpatientdetails11.setText("Ward: "+ searchWard);
            }
            else if(!rs.next())
                    {
           Alert alter = new Alert(Alert.AlertType.ERROR,"Patient does not exist in our hospice care!", ButtonType.OK);
           alter.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    
}
   
    

