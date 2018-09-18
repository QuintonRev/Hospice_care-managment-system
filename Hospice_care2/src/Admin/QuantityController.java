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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class QuantityController extends AdminController {
    
   @FXML
    private Label lblCurrentQuantity;
   
   @FXML
    private TextField txtUpdatedQuantity;       
           
   MedicationData md;
  
   private dbConnection1 dc;
    
   public AdminController admin;
   
    public void initialize(URL url, ResourceBundle rb) {
        //when ever this window pops out it will create a new instance from our database
        this.dc = new dbConnection1();
        
     
    }
    
    public void setMedicationData(MedicationData md){
        this.md = md;
        lblCurrentQuantity.setText(this.md.getMedQuantity());
    }
    
    @FXML
    public void updateQuantity(ActionEvent event) throws SQLException{
        
        int Quantity = Integer.parseInt(this.txtUpdatedQuantity.getText());
        
        if(Quantity<1){
        Alert alter = new Alert(Alert.AlertType.ERROR, "Input value can not be less than 1", ButtonType.OK);
           alter.show();
        }else{
          String sqlUpdate = "UPDATE medication SET Med_quantity=? WHERE Barcode=?";
         
          
          int total = Integer.parseInt(md.getMedQuantity()) + Integer.parseInt(this.txtUpdatedQuantity.getText());
          
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            stmt.setString(1, Integer.toString(total));
            stmt.setString(2, this.md.getBarcode());
           
            stmt.execute();

            Alert alter = new Alert(Alert.AlertType.CONFIRMATION, "Quantity Stock has been successfully updtate", ButtonType.OK);
           alter.show();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }
    
    
}
