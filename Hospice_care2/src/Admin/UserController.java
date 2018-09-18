 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Validation.TextFieldValidation;

import dbUtil.dbConnection1;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */

public class UserController implements Initializable {

    //Administrators private variables
    @FXML
    private TextField adminname;
    @FXML
    private TextField adminsurname;
    @FXML
    private TextField adminaddress;
    @FXML
    private TextField admincell;
    @FXML
    private TextField admintell;
    @FXML
    private DatePicker admindob;
    @FXML
    private TextField adminemail;
    @FXML
    private TextField adminpassword;

    //Nurse private variables
    private TextField nursFirstName;
    private TextField nursLastName;
    private TextField nursMedian;
    private DatePicker nursdate;
    private TextField nurscell;
    private TextField nursaddress;
    private TextField nursemail;
    private PasswordField nurspassword;
    private TextField Qualification;
    @FXML
    private RadioButton rdbMale;
    @FXML
    private RadioButton rdbFemale;
    private ComboBox<WardData> combobox;
    private Label numOfBeds;
    
    private dbConnection1 dc;
    private String wardId;
    //error display messages
    private Label error_lastName;
    private Label error_medianName;
    private Label error_firstName;

    private Label error_DateOfBirth;
    private Label error_contactNumber;

    private Label error_qualification;
    private Label error_physical;
    private Label error_email;
    private Label error_password;

    private PasswordField confirm_nurspassword1;
    private Button btnNurse;
    private Button btnUpdate;
    
    private Label error_confirm;
    
    public ObservableList<WardData> ward;
    
    
    NurseData nd;
    @FXML
    private PasswordField adminpassword1;
 
    @FXML
    private ToggleGroup Gender;
    
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection1();
        
         String SQLWard = "Select * From ward";

        ward = FXCollections.observableArrayList();

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(SQLWard);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ward.add(new WardData(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
           // if (rs.next()) {
           //    numOfBeds.setText(rs.getString(3));
            //}
             this.combobox.setItems(ward);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        combobox.setConverter(new StringConverter<WardData>() {
            @Override
            public String toString(WardData object) {
                return object.getWardName();
            }

            @Override
            public WardData fromString(String string) {
                return null;
            }
        });

        combobox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue != null) {
                    /*Alert alter = new Alert(Alert.AlertType.INFORMATION, newValue.getWardID(), ButtonType.OK);
                   alter.show();*/
                    wardId = newValue.getWardID();
                    numOfBeds.setText(newValue.getNumberOfBeds());
                }
            }
        });
        
    

    }

        public void setNurseData(NurseData nd){
        this.nd = nd;
        //lblCurrentQuantity.setText(md.getMedQuantity());
        nursFirstName.setText(nd.getFirstName());
        nursLastName.setText(nd.getLastName());
        nursMedian.setText(nd.getMedianName());
        nurscell.setText(nd.getCellNumber());
        Qualification.setText(nd.getQualification());
        nursaddress.setText(nd.getAddress());
        nursemail.setText(nd.getEmail());
        nurspassword.setText(nd.getPassword());
        String gender = nd.getGender().toLowerCase();
        if(gender.equals("male")){
            rdbMale.isSelected();
        }else{
            rdbFemale.isSelected();
        }
        
        btnNurse.setVisible(false);
        
    }
        public void setNurseFrm(){
            btnUpdate.setVisible(false);
        }
    
        
   

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        //parent is what will be using to access the actual fxml file
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));

        //Scene is the actual user interface
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(home_page_scene);

        app_stage.close();
    }

    
    public void updateNurse(ActionEvent event) throws SQLException{
          String sqlUpdate = "UPDATE nurse SET first_name=?, last_name=?, Median_name=?, Cell_Number=?, Date_of_birth=?, Address=?, Gender=? ,"
                + "qualification=?, Email=?, Password=?,"
                + "ward_id=? WHERE id=?";

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, this.nursFirstName.getText());
            stmt.setString(2, this.nursLastName.getText());
            stmt.setString(3, this.nursMedian.getText());
            stmt.setString(4, this.nurscell.getText());
            stmt.setString(5, this.nursdate.getEditor().getText());
            
            stmt.setString(6, this.nursaddress.getText());
            if (rdbMale.isSelected()) {
                stmt.setString(7, this.rdbMale.getText());
            } else {
                stmt.setString(7, this.rdbFemale.getText());
            }
            
            stmt.setString(8, this.Qualification.getText());
            stmt.setString(9, this.nursemail.getText());
            stmt.setString(10, this.nurspassword.getText());
            stmt.setString(11, wardId);
             stmt.setString(12, nd.getNurseId());   
            

            int i = stmt.executeUpdate();
            if(i==1){
                Alert alter = new Alert(Alert.AlertType.INFORMATION, "Nurse Information has been updated successfully!", ButtonType.OK);
                alter.show();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public void addNurse(ActionEvent event) throws SQLException {
        
       // LocalDate nurseDate = this.nursdate.getValue();
        
        
        
        
        boolean isFirstNameEmpty = TextFieldValidation.isTextFieldNotEmpty(nursFirstName, error_firstName, "Nurse First Name Is required!");
        boolean isSurnameEmpty = TextFieldValidation.isTextFieldNotEmpty(nursLastName, error_lastName, "Nurse Last Name Is required!");
        boolean isMedianEmpty = TextFieldValidation.isTextFieldNotEmpty(nursMedian, error_medianName, "Nurse Median Name Is required!");

        boolean isContactEmpty = TextFieldValidation.istextFieldTypeNumber(nurscell, error_contactNumber, "Nurse Contact Number Is must be numeric!");
        boolean isAddressEmpty = TextFieldValidation.isTextFieldNotEmpty(nursaddress, error_physical, "Nurse Physical Address Is required!");
        //boolean isEmailEmpty = TextFieldValidation.isTextFieldNotEmpty(nursemail, error_email, "Nurse Email address Is required!");
        boolean isPasswordEmpty = TextFieldValidation.isTextFieldNotEmpty(nurspassword, error_password, "Nurse Password Is required!");
        boolean isQualificationEmpty = TextFieldValidation.isTextFieldNotEmpty(Qualification, error_qualification, "Nurse Qualification Is required!");
        //boolean isDateBirthEmpty = TextFieldValidation.isTextFieldNotEmpty(nursdate, error_DateOfBirth, "Nurse Date of birth Is required!");
        //boolean isconfirm_nurspassword1Empty = TextFieldValidation.isTextFieldNotEmpty(confirm_nurspassword1, error_confirm, "Please confirm your password!");
        boolean isValidEmail = TextFieldValidation.isValidEmail(nursemail, error_email, "invalid email! please try again!");
        boolean isPasswordMatched = TextFieldValidation.isPasswordMatched(nurspassword, confirm_nurspassword1, error_confirm, "password is not matched");
        boolean isTextFieldNot2018 = TextFieldValidation.isTextFieldNot2018(nursdate.getEditor(), error_DateOfBirth, "Date must not be greater than 2018");
        
        if(isFirstNameEmpty&&isSurnameEmpty&&isMedianEmpty&&isContactEmpty&&isAddressEmpty&&
                isPasswordEmpty&&isQualificationEmpty&&isPasswordMatched&&isTextFieldNot2018&&isValidEmail){
            
        String sqlInsert = "INSERT INTO Nurse(first_name,last_name,Median_name, Cell_number,Date_of_birth,"
                + " Address,Gender,qualification,Email, ward_id) VALUES(,?,?,?,?,?,?,?,?,?,?)";
        
        String sqlInsertLogin = "INSERT INTO login(email, password, division)";
        
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            PreparedStatement pst = conn.prepareStatement(sqlInsertLogin);
            
            stmt.setString(1, this.nursFirstName.getText());
            stmt.setString(2, this.nursLastName.getText());
            stmt.setString(3, this.nursMedian.getText());
            stmt.setString(4, this.nurscell.getText());
            stmt.setString(5, this.nursdate.getEditor().getText());
            
            stmt.setString(6, this.nursaddress.getText());
            if (rdbMale.isSelected()) {
                stmt.setString(7, this.rdbMale.getText());
            } else {
                stmt.setString(7, this.rdbFemale.getText());
            }
            
            stmt.setString(8, this.Qualification.getText());
            stmt.setString(9, this.nursemail.getText());
            
            
            pst.setString(1, this.nursemail.getText());
            pst.setString(2, this.adminpassword.getText());
            pst.setString(3, "Nurse");
            
            /*switch (((WardName) this.combobox.getValue()).toString()) {
                case "Nelson_Mandela":
                    stmt2.setString(1, (this.combobox.getValue()).toString());
                    break;
                case "Chris_Hani":
                    stmt2.setString(1, (this.combobox.getValue()).toString());
                    break;
                case "Winnie_Mandela":
                    stmt2.setString(1, (this.combobox.getValue()).toString());
                    break;
            }*/

            stmt.setString(11, wardId);
            
            pst.setString(1, this.nursemail.getText());
            pst.setString(2, this.adminpassword.getText());
            pst.setString(3, "Nurse");
            //JOptionPane.showMessageDialog(null, "Nurse information has been Added successfully");
            int j = pst.executeUpdate();
            int i = pst.executeUpdate();
            if(i==1&&j==1){
                Alert alter = new Alert(Alert.AlertType.INFORMATION, "Nurse has been registered successfully!", ButtonType.OK);
                alter.show();
            }
            
            this.Qualification.setText("");
            this.nursFirstName.setText("");
            this.nursLastName.setText("");
            this.nursMedian.setText("");
            this.nursaddress.setText("");
            this.nurscell.setText("");
            this.nursemail.setText("");
            this.nurspassword.setText("");
            this.confirm_nurspassword1.setText("");
            
            pst.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
         } 
        }   
    }

}
