 package LoginApp;

import Admin.AdminController;
import Nurse.NurseController;
import Validation.TextFieldValidation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();
    
    @FXML
     private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;
    
    @FXML
    private Button btnRegister;
    @FXML
    private Label lbluser_required;
    @FXML
    private Label lblpassword_required;
    
    //public String UsernameAdmin = this.username.getText().toString();
    
    public void initialize (URL url, ResourceBundle rb){
        if(this. loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected to database");
        }else{
            this.dbstatus.setText("Not connected to Database");
        }
        // we use our enum to populte our combobox
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }
    
    @FXML  
    public void Login(ActionEvent event){
        
        
        
         boolean isUserNameEmpty = TextFieldValidation.isTextFieldNotEmpty(username, lbluser_required,"invaldi! user name can not be empty!");
         boolean isPasswordEmpty = TextFieldValidation.isTextFieldNotEmpty(password, lblpassword_required,"invalid! password can not be empty!");
        
         if(isUserNameEmpty&&isPasswordEmpty){
             
         try{
            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString())){
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                
                switch (((option) this.combobox.getValue()).toString())
                {
                    case"Admin":
                        adminlogin();
                        break;
                    case "Nurse":
                        nurseLogin();
                        break;
                                
                }
                
    
                
            }
            else {
                
            Alert alter = new Alert(Alert.AlertType.ERROR, "Please ensure that password or username(email) is correct ", ButtonType.OK);
            alter.show();
            }
            
           
            
        }catch (Exception localException){
            
        }
      }
    }
    
    public void nurseLogin(){
        try{
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            
            //calls the nnurse form
            Pane root = (Pane)loader.load(getClass().getResource("/Nurse/Nurse.fxml").openStream());
            
             //attaches the controller file to this fxml file
            NurseController nurseController = (NurseController)loader.getController();
            
            nurseController.setNurseName(this.username.getText());
            //creates a new scene
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Nurse Dashboard");
            userStage.setResizable(false);
            userStage.show();
            
        }catch (IOException ex){
            ex.printStackTrace();
        }
    } 
    public void adminlogin(){
        
          //  Alert alter = new Alert(Alert.AlertType.CONFIRMATION,"You are successfully logged in! Welcome", ButtonType.OK);
           //      alter.show();
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            //calls the nnurse form
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            
             //attaches the controller file to this fxml file
            AdminController adminController = (AdminController)adminLoader.getController();
            adminController.setAdminName(this.username.getText());
            //creates a new scene
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            /*Screen screen = Screen.getPrimary();
            adminStage.setWidth(screen.getVisualBounds().getMaxX()-screen.getVisualBounds().getMinX());
            adminStage.setHeight(screen.getVisualBounds().getMaxY()-screen.getVisualBounds().getMinY());*/
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
            
        }catch (IOException e){
            e.printStackTrace();
        }
    
    }
    
    @FXML 
    public void adminRegister(ActionEvent event){
        
        
     

    }
}

    

  
        

