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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Reverside
 */


public class UserController2 implements Initializable { 
     private dbConnection1 dc;
     
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
    private TextField adminemail;

    @FXML
    private DatePicker admindob;

    @FXML
    private PasswordField adminpassword;

    @FXML
    private PasswordField adminpassword1;

    @FXML
    private Button btnAdminCancel;

    @FXML
    private Button btnAddAdmin;

    @FXML
    private RadioButton rdbMale;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton rdbFemale;
    
     public void initialize(URL url, ResourceBundle rb) {
         
        this.dc = new dbConnection1();
        
     }
     
    @FXML
    private void addAdministrator(ActionEvent event) {
        String sqlInsert = "INSERT INTO administrator(First_name,Last_name,Cell_number,Email,Password,DOB,Work_number"
                + ")VALUES(?,?,?,?,?,?,?)";
        
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.adminname.getText());
            stmt.setString(2, this.adminsurname.getText());
            stmt.setString(3, this.admincell.getText());
            
            stmt.setString(4, this.adminemail.getText());
            stmt.setString(5, this.adminpassword.getText());
            stmt.setString(6, this.admindob.getEditor().getText());
            stmt.setString(7, this.admintell.getText());
            
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Administrator information has been Added successfully");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
}
