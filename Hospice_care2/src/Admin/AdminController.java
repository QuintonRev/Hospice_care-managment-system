/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import LoginApp.LoginController;
import LoginApp.LoginModel;
import Validation.TextFieldValidation;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import dbUtil.dbConnection1;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

/**
 *
 * @author Reverside
 */
public class AdminController implements Initializable {

  
    private AnchorPane anchorPane;
    private Stage stage;
    
    MedicationData md;
    NurseData nd;
    PatientData patientData;
    LoginModel username;
    VisitorData VD;
    
    public String FirstName;
    public String LastName;
    
     @FXML
    private Label lblSite;
    @FXML
    private Label lbllastUpdate;
    @FXML
    private TextField id;
    @FXML
    private Label lblerror_ID;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextArea address;
    @FXML
    private TextField gender;
    @FXML
    private TextField zip;
    @FXML
    private TextField cellno;
    @FXML
    private TextField condition;
    @FXML
    private TextField nxtName;
    @FXML
    private TextField nxtSurname;
    @FXML
    private TextField nxtCell;
    @FXML
    private TextField nxtRelationship;
    @FXML
    private TextField bednum;
    @FXML
    private Label error_dob;
    @FXML
    private Desktop deskTop = Desktop.getDesktop();
    
    
    //Admin controller is in a different package so in order for us to use dbUtilities we need to import our connection
    private dbConnection1 dc;

    //ObservableList is an interface: initialized observable list to hold our database data
    private ObservableList<PatientData> data;

    private ObservableList<AdminData> data1;
    private ObservableList<NurseData> data2;
    private ObservableList<MedicationData> data3;
    private ObservableList<VisitorData> data4;

    @FXML
    private TextField firstnameD;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField first;
    ///////////////////////////////////////////////
    @FXML
    private TableView<VisitorData> tableVisitor;

   @FXML
    private TableColumn<VisitorData, String> column_visitorNo;
   @FXML
   private TableColumn<VisitorData, String> visitorId;
   
    @FXML
    private TableColumn<VisitorData, String> column_visitorName;

    @FXML
    private TableColumn<VisitorData, String> column_visitorSurname;

    @FXML
    private TableColumn<VisitorData, String> column_visitorNum;

    @FXML
    private TableColumn<VisitorData, String> column_reason;

    @FXML
    private TableColumn<VisitorData, String> column_checkInTime;

    @FXML
    private TableColumn<VisitorData, String> column_checkOut;

    @FXML
    private TableColumn<VisitorData, String> column_checkinDate;

    @FXML
    private TableColumn<VisitorData, String> column_checkPatientName;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Binds our class to the table patient and injects all the initialized controls to the controller
    @FXML
    private TableView<PatientData> tablePatient;
    @FXML
    private TableColumn<PatientData, String> idcolumn;

    @FXML
    private TableColumn<PatientData, String> firstnamecolumn;

    @FXML
    private TableColumn<PatientData, String> lastnamecolumn;

    @FXML
    private TableColumn<PatientData, String> emailcolumn; //PatientData is all the data coming from our patientdata class
    @FXML
    private TableColumn<PatientData, String> dobcolumn;
    @FXML
    private TableColumn<PatientData, String> addresscolumn;

    @FXML
    private TableColumn<PatientData, String> gendercolumn;
    @FXML
    private TableColumn<PatientData, String> zipcolumn;
    @FXML
    private TableColumn<PatientData, String> cellcolumn;

    @FXML
    private TableColumn<PatientData, String> conditioncoloumn;
    @FXML
    private TableColumn<PatientData, String> nxtnamecoloumn;
    @FXML
    private TableColumn<PatientData, String> nxtsurnamecoloumn;
    @FXML
    private TableColumn<PatientData, String> nxtcellcoloumn;
    @FXML
    private TableColumn<PatientData, String> nxtrelationshipcoloumn;
    @FXML
    private Button btnLoad;
    @FXML
    private TitledPane nxtPane;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Binding and instantiating the table and the Data access layer/model class
    @FXML
    private TableView<AdminData> tableUser;

    @FXML
    private TableView<NurseData> tableNurse;

    @FXML
    private TableView<MedicationData> tableMedication;

    @FXML
    private TableColumn<AdminData, String> userid_column;

    @FXML
    private TableColumn<AdminData, String> firstname_column;

    @FXML
    private TableColumn<AdminData, String> lastname_column;

    @FXML
    private TableColumn<AdminData, String> dob_column; //PatientData is all the data coming from our patientdata class
    @FXML
    private TableColumn<AdminData, String> gender_column;
    @FXML
    private TableColumn<AdminData, String> address_column;

    @FXML
    private TableColumn<AdminData, String> email_column;
    @FXML
    private TableColumn<AdminData, String> cellno_column;
    @FXML
    private TableColumn<AdminData, String> tell_column;
    @FXML
    private TableColumn<AdminData, String> password_column;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableColumn<NurseData, String> Nurse_ID;

    @FXML
    private TableColumn<NurseData, String> nurseSurname_column;

    @FXML
    private TableColumn<NurseData, String> nurseFirstName_column;

    @FXML
    private TableColumn<NurseData, String> nurseMedian_column;

    @FXML
    private TableColumn<NurseData, String> nurseDOB_column;

    @FXML
    private TableColumn<NurseData, String> nurseQualification_column;

    @FXML
    private TableColumn<NurseData, String> nurseCell_column;

    @FXML
    private TableColumn<NurseData, String> nurseAddress_column;

    @FXML
    private TableColumn<NurseData, String> nurseGender_column;

    @FXML
    private TableColumn<NurseData, String> nurseEmail_column;

    @FXML
    private TableColumn<NurseData, String> nursePassword_column;

    @FXML
    private TableColumn<NurseData, String> nurseWard_column;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Medication table
    @FXML
    private TableColumn<MedicationData, String> medId_column;
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
    private TableColumn<MedicationData, String> AdminName;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Variable data for medication addition
    @FXML
    private TextField medBarcode;

    @FXML
    private TextField txtMedName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtCustService;

    @FXML
    private TextField txthyperlink;

    @FXML
    private DatePicker txtMedDate;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label error_firstName;
    @FXML
    private Label error_lastName;
    @FXML
    private Label lblerror_email;
    @FXML
    private Label lblPatientAddress;
    @FXML
    private Label lblCellNumber;
    @FXML
    private Label lblCondition;
    @FXML
    private Label lblRelationships;
    @FXML
    private Label lblerror_cellKeen;
    @FXML
    private Label lblerror_surnameKeen;
    @FXML
    private Label lblerror_nameKeen;
    @FXML
    private Label lblerror_zip;
    
    @FXML
    private RadioButton rdbMale;
    @FXML
    private RadioButton rdbFemale;

    @FXML
    private Label numOfBeds;

    public ObservableList<WardData> ward;
    private String wardId;
    
    
    
    @FXML
    private ComboBox<WardData> combobox;

    private FileChooser fileChooser;
    private File file;
    private Stage stage2;

    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private ImageView ImageView1;
    private Image image;
    private FileInputStream fInput;
    @FXML
    private ImageView ImageView11;
    @FXML
    private Label lblUserMain;
    //For quantity
    @FXML
    private TextField txtRowSelected;

    //public String quantity;
    String quantity = "";
    @FXML
    private Label lblAdminLog;
    @FXML
    private Label label;
   
    private String AdminId=null;
    private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
    private BorderPane root;
    
    private String time;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //when ever this window pops out it will create a new instance from our database
        this.dc = new dbConnection1();
        stage = new Stage();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        
        Date date = new Date();
        
        time = sdf.format(date);
        
        
        this.lbllastUpdate.setText(time);
        
                
        setCellValueFromTableToTextField();
        setCellValueFromTableNurseToTextField();
        setCellValueFromTablePatientToTextField2();
        
        setCellValueFromTableVisitorToTextField();
        
        String SQLWard = "Select * From ward";

        ward = FXCollections.observableArrayList();

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(SQLWard);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ward.add(new WardData(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            //if (rs.next()) {
            //    numOfBeds.setText(rs.getString("Num_Beds"));
            // }
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

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "* *"),
                new FileChooser.ExtensionFilter("Images", "* png", "* jpg", "* gif"),
                new FileChooser.ExtensionFilter("Text File", "* txt")
        );
      

    }
    public void setAdminName(String user){
        
        //this.lblAdminLog.setText("Kate williams");
        //this.lblUserMain.setText("KATE");
        String sqlSearch = "SELECT * FROM administrator WHERE email=?";
        
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearch);
            
            pst.setString(1, user);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                this.lblAdminLog.setText(rs.getString(2));
                this.lblUserMain.setText(rs.getString(3));
                
                AdminId = rs.getString(1);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //A function to add our student data
    @FXML
    private void searchData(ActionEvent event) throws SQLException {

        String sqlSearch = "SELECT * FROM patient WHERE first_name=?";

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearch);
            pst.setString(1, txtSearch.getText());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                id.setText(rs.getString("Id"));
                firstname.setText(rs.getString("first_name"));
                lastname.setText(rs.getString("last_name"));
                email.setText(rs.getString("email"));

                address.setText(rs.getString("address"));
                zip.setText(rs.getString("zip_code"));
                gender.setText(rs.getString("gender"));
                cellno.setText(rs.getString("cell_number"));
                condition.setText(rs.getString("special_condition"));
                nxtName.setText(rs.getString("nxt_name"));
                nxtSurname.setText(rs.getString("nxt_surname"));
                nxtCell.setText(rs.getString("nxt_cellno"));
                nxtRelationship.setText(rs.getString("nxt_relationship") );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String sqlSearchid = "SELECT * FROM patient WHERE Id=?";

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement pst = conn.prepareStatement(sqlSearchid);
            pst.setString(1, txtSearch.getText());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                firstname.setText(rs.getString("first_name"));
                lastname.setText(rs.getString("last_name"));
                email.setText(rs.getString("email"));

                address.setText(rs.getString("address"));
                zip.setText(rs.getString("zip_code"));
                gender.setText(rs.getString("gender"));
                cellno.setText(rs.getString("cell_number"));
                condition.setText(rs.getString("special_condition"));
                nxtName.setText(rs.getString("nxt_name"));
                nxtSurname.setText(rs.getString("nxt_surname"));
                nxtCell.setText(rs.getString("nxt_cellno"));
                nxtRelationship.setText(rs.getString("nxt_relationship"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

        //"this" referes to the global variable
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("Id"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("first_name"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("last_name"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("DOB"));
        this.addresscolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("address"));
        this.gendercolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("gender"));
        this.zipcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("zip_code"));
        this.cellcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("cell_number"));
        //this.conditioncolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("special_condition"));

    }

    @FXML
    private void updatePatient(ActionEvent event) throws SQLException {
        String sqlInsert = "UPDATE patient SET first_name=?, last_name=?, email=?, dob=?, gender=?, address=?, zip_code=? ,"
                + "cell_number=?, special_condition=?, nxt_name=?,"
                + "nxt_surname=?, nxt_cellno=?,nxt_relationship=? WHERE first_name=?";

        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.firstname.getText());
            stmt.setString(2, this.lastname.getText());
            stmt.setString(3, this.email.getText());
            stmt.setString(4, this.dob.getEditor().getText());
            stmt.setString(5, this.gender.getText());
            stmt.setString(6, this.address.getText());
            stmt.setString(7, this.zip.getText());
            stmt.setString(8, this.cellno.getText());
            stmt.setString(9, this.condition.getText());
            stmt.setString(10, this.nxtName.getText());
            stmt.setString(11, this.nxtSurname.getText());
            stmt.setString(12, this.nxtCell.getText());
            stmt.setString(13, this.nxtRelationship.getText());
            stmt.setString(14, txtSearch.getText());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "Patient information has been updated");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addPatient(ActionEvent event) throws IOException {

        boolean isPatientId13 = TextFieldValidation.istextFieldId13(id, lblerror_ID, "Invalid! The ID characters must be exactly 13 Characters!");
        boolean isFirstNameEmpty = TextFieldValidation.isTextFieldNotEmpty(firstname, error_firstName, "Nurse First Name Is required!");
        boolean isSurnameEmpty = TextFieldValidation.isTextFieldNotEmpty(lastname, error_lastName, "Nurse Last Name Is required!");
        boolean isMedianEmpty = TextFieldValidation.isTextFieldNotEmpty(email, lblerror_email, "Nurse Median Name Is required!");

        boolean isContactEmpty = TextFieldValidation.istextFieldTypeNumber(cellno, lblCellNumber, "Nurse Contact Number Is must be numeric!");
        boolean isAddressEmpty = TextFieldValidation.isTextFieldNotEmptyAddress(address, lblPatientAddress, "Nurse Physical Address Is required!");
        //boolean isEmailEmpty = TextFieldValidation.isTextFieldNotEmpty(nursemail, error_email, "Nurse Email address Is required!");
        //boolean isPasswordEmpty = TextFieldValidation.isTextFieldNotEmpty(nurspassword, error_password, "Nurse Password Is required!");
        boolean isQualificationEmpty = TextFieldValidation.isTextFieldNotEmpty(condition, lblCondition, "Nurse Qualification Is required!");
        //boolean isDateBirthEmpty = TextFieldValidation.isTextFieldNotEmpty(nursdate, error_DateOfBirth, "Nurse Date of birth Is required!");
        //boolean isconfirm_nurspassword1Empty = TextFieldValidation.isTextFieldNotEmpty(confirm_nurspassword1, error_confirm, "Please confirm your password!");
        boolean isValidEmail = TextFieldValidation.isValidEmail(email, lblerror_email, "invalid email! please try again!");
        //boolean isPasswordMatched = TextFieldValidation.isPasswordMatched(nurspassword, confirm_nurspassword1, error_confirm, "password is not matched");
        boolean isTextFieldNot2018 = TextFieldValidation.isTextFieldNot2018(dob.getEditor(), error_dob, "Date must not be greater than 2018");
        boolean isRelationshipKeenEmpty = TextFieldValidation.isTextFieldNotEmpty(nxtRelationship, lblRelationships, "Nurse First Name Is required!");
        boolean isCellKeenEmpty = TextFieldValidation.isTextFieldNotEmpty(nxtCell, lblerror_cellKeen, "Nurse First Name is required!");
        boolean isSurnameKeenEmpty = TextFieldValidation.isTextFieldNotEmpty(nxtSurname, lblerror_surnameKeen, "Nurse First Name is required!");

        boolean isNameKeenEmpty = TextFieldValidation.isTextFieldNotEmpty(nxtName, lblerror_nameKeen, "Nurse First Name is required!");
        boolean isValidZipCode = TextFieldValidation.istextFieldZip4(zip, lblerror_zip, "zip code must be exactly 4 characters!");

        if (isPatientId13 && isFirstNameEmpty && isSurnameEmpty && isMedianEmpty && isContactEmpty && isAddressEmpty && isRelationshipKeenEmpty
                && isQualificationEmpty && isTextFieldNot2018 && isValidEmail && isCellKeenEmpty && isSurnameKeenEmpty && isNameKeenEmpty && isValidZipCode) {
            String sqlInsert = "INSERT INTO Patient(Id,first_name,last_name,email,dob,gender,address,zip_code,cell_number,special_condition,nxt_name,"
                    + "nxt_surname,nxt_cellno,nxt_relationship, patient_pic,ward_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {

                FirstName = this.firstname.getText();
                LastName = this.lastname.getText();

                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sqlInsert);

                stmt.setString(1, this.id.getText());
                stmt.setString(2, FirstName);
                stmt.setString(3, LastName);
                stmt.setString(4, this.email.getText());
                stmt.setString(5, this.dob.getEditor().getText());
                //stmt.setString(6, this.gender.getText());
                if (rdbMale.isSelected()) {
                    stmt.setString(6, this.rdbMale.getText());
                } else {
                    stmt.setString(6, this.rdbFemale.getText());
                }
                stmt.setString(7, this.address.getText());
                stmt.setString(8, this.zip.getText());
                stmt.setString(9, this.cellno.getText());
                stmt.setString(10, this.condition.getText());
                stmt.setString(11, this.nxtName.getText());
                stmt.setString(12, this.nxtSurname.getText());
                stmt.setString(13, this.nxtCell.getText());
                stmt.setString(14, this.nxtRelationship.getText());
                stmt.setString(16, this.combobox.getValue().getWardID());

                fInput = new FileInputStream(file);
                stmt.setBinaryStream(15, fInput, file.length());

                stmt.execute();

                JOptionPane.showMessageDialog(null, "Patient information has been successfully temporarily");
                makePayment();
                conn.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
        this.address.setText("");
        this.zip.setText("");
        this.cellno.setText("");
        this.condition.setText("");
        this.nxtName.setText("");
        this.nxtSurname.setText("");
        this.nxtCell.setText("");
        this.nxtRelationship.setText("");
        this.gender.setText("");
    }

    @FXML
    private void loadPatientTable(ActionEvent event) {

        try {
            Connection conn = dc.getConnection();

            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM patient");

            while (rs.next()) {
                data.add(new PatientData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstnamecolumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastnamecolumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dobcolumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        cellcolumn.setCellValueFactory(new PropertyValueFactory<>("cellNumber"));
        addresscolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipcolumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        gendercolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        conditioncoloumn.setCellValueFactory(new PropertyValueFactory<>("specialCondition"));
        nxtnamecoloumn.setCellValueFactory(new PropertyValueFactory<>("nxtName"));
        nxtsurnamecoloumn.setCellValueFactory(new PropertyValueFactory<>("nxtSurname"));
        nxtcellcoloumn.setCellValueFactory(new PropertyValueFactory<>("nxtCellNumber"));
        nxtrelationshipcoloumn.setCellValueFactory(new PropertyValueFactory<>("nxtRelationship"));

        tablePatient.setItems(null);
        tablePatient.setItems(data);

    }

    @FXML
    private void getPatientID(ActionEvent event) {

    }

    @FXML
    private void deleteRowFromTable() {

        {
            String id = null;
            String name = null;
            try {
                PatientData user = (PatientData) tablePatient.getSelectionModel().getSelectedItem();
                String query = "delete from patient where Id=?";

                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user.getID());
                id = user.getID();
                name = user.getLastName();
                stmt.executeUpdate();

                stmt.close();
                conn.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            JOptionPane.showMessageDialog(null, "Patient information has been temporarily saved successfully");

        }
    }

    //This function gets the add user administrator window on menu click/action
    //getWindow() Gets the value of the property window, the window for this scene.
    @FXML
    private void AddAdministrator(ActionEvent event) throws IOException {

        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("AddAdmin.fxml"));
            anchorPane = loader.load();
            //UserController userController = loader.getController();
            
            Scene scene = new Scene(anchorPane);
            
            stage.setScene(scene);
            stage.setTitle("Administrator Application");
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddNurse(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("Nurse.fxml"));
            anchorPane = (AnchorPane) loader.load();
            UserController userController = loader.getController();
            userController.setNurseFrm();
            Scene scene = new Scene(anchorPane);
             
            stage.setScene(scene);
            stage.setTitle("Nurse Application");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadUserTable(ActionEvent event) {

        try {
            Connection conn = dc.getConnection();

            data1 = FXCollections.observableArrayList();
            data2 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM administrator");

            ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM nurse");

            while (rs.next()) {
                data1.add(new AdminData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));

            }

            while (rs2.next()) {
                data2.add(new NurseData(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5),
                        rs2.getString(6), rs2.getString(7), rs2.getString(8), rs2.getString(9), rs2.getString(10), rs2.getString(11), rs2.getString(12)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        userid_column.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        firstname_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastname_column.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cellno_column.setCellValueFactory(new PropertyValueFactory<>("cellNumber"));
        tell_column.setCellValueFactory(new PropertyValueFactory<>("tellNumber"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        dob_column.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        password_column.setCellValueFactory(new PropertyValueFactory<>("password"));
        nurseWard_column.setCellValueFactory(new PropertyValueFactory<>("wardName"));

        tableUser.setItems(null);
        tableUser.setItems(data1);

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        //Loads data to the nurse table
        Nurse_ID.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
        nurseFirstName_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nurseSurname_column.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        nurseMedian_column.setCellValueFactory(new PropertyValueFactory<>("medianName"));
        nurseDOB_column.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        nurseQualification_column.setCellValueFactory(new PropertyValueFactory<>("Qualification"));
        nurseAddress_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        nurseCell_column.setCellValueFactory(new PropertyValueFactory<>("cellNumber"));
        nurseGender_column.setCellValueFactory(new PropertyValueFactory<>("gender"));
        nurseEmail_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        nursePassword_column.setCellValueFactory(new PropertyValueFactory<>("password"));
        nurseWard_column.setCellValueFactory(new PropertyValueFactory<>("wardName"));

        tableNurse.setItems(null);
        tableNurse.setItems(data2);

    }

    @FXML
    private void deleteRowFromNurseTable() {

        {
            String id = null;
            String name = null;
            try {
                NurseData user = (NurseData) tableNurse.getSelectionModel().getSelectedItem();
                String query = "delete from nurse where Id=?";

                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user.getNurseId());
                id = user.getNurseId();
                name = user.getLastName();
                stmt.executeUpdate();

                stmt.close();
                conn.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            JOptionPane.showMessageDialog(null, "Nurse information has been successfully deleted");

        }
    }

    @FXML
    private void updateNurse(ActionEvent event) throws SQLException, IOException {

        try {

            /////////////////////////////////////////////
            if (nd != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("Nurse.fxml"));
                anchorPane = loader.load();

                UserController userController = loader.getController();
                userController.setNurseData(nd);

                Scene scene = new Scene(anchorPane);

                stage.setScene(scene);
                stage.setTitle("Nurse Application");
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Please select the appropriate record in the table before pressing the select ");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void AddMedication(ActionEvent event) throws SQLException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        
        Date date = new Date();
        
        String time = sdf.format(date);
        
        
        lbllastUpdate.setText(time);
        
        
        String sqlInsert = "INSERT INTO medication(Barcode,Med_name,Med_quantity,Med_DateIn,Customer_center,Website, Admin_id"
                + ")VALUES(?,?,?,?,?,?,?)";
        
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.medBarcode.getText());
            stmt.setString(2, this.txtMedName.getText());
            stmt.setString(3, this.txtQuantity.getText());
            stmt.setString(4, this.time);
            stmt.setString(5, this.txtCustService.getText());
           
            stmt.setString(6, this.txthyperlink.getText());
            stmt.setString(7, this.AdminId);
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Medication information has been Added successfully");
            conn.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        AdminName.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
        
        tableMedication.setItems(null);
        tableMedication.setItems(data3);

        //Set cell Value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class.
        //Loads data to the nurse table
    }

    @FXML
    private void RemoveMedication(ActionEvent even) throws SQLException {
        {
            String id = null;
            String name = null;
            try {
                MedicationData user = (MedicationData) tableMedication.getSelectionModel().getSelectedItem();
                String query = "delete from medication where Barcode=?";

                Connection conn = dbConnection1.getConnection();

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user.getBarcode());
                id = user.getBarcode();
                name = user.getMedName();
                stmt.executeUpdate();

                stmt.close();
                conn.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }
           
           Alert alter = new Alert(Alert.AlertType.ERROR, "Medication"+ name + " has been successfully deleted", ButtonType.OK);
           alter.show();
        }

    }

    public String quantity_Id;

    //this is for the medication table
    @FXML
    public void setCellValueFromTableToTextField() {
        tableMedication.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                md = tableMedication.getItems().get(tableMedication.getSelectionModel().getSelectedIndex());

                quantity_Id = md.getBarcode();
                quantity = md.getMedQuantity();
                
                
                
                lblSite.setText(md.getMedWebsite());
            }
        });
    }

    @FXML
    public void setCellValueFromTableNurseToTextField() {
        tableNurse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                nd = tableNurse.getItems().get(tableNurse.getSelectionModel().getSelectedIndex());

            }
        });
    }

    @FXML
    public void setCellValueFromTablePatientToTextField2() {
        tablePatient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                patientData = tablePatient.getItems().get(tablePatient.getSelectionModel().getSelectedIndex());
                try {
                    String query = "select * from patient where Id =?";
                    Connection conn = dbConnection1.getConnection();

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, patientData.getID());
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        //extracting the image from the column
                        txtRowSelected.setText(rs.getString("first_name"));
                        InputStream is = rs.getBinaryStream("patient_pic");
                        //we create a file and this is where we are going to save our file, file name is instantiated
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));
                        byte[] content = new byte[1024];
                        int size = 0;
                        while ((size = is.read(content)) != -1) { //when all the content in the output stream have been extracted its going to pass a value of -1
                            os.write(content, 0, size);         // we going to read the input image and store it in the size and then write that content in output stream
                        }

                        //create a new object image and pass the path of the image, rooy
                        image = new Image("file:photo.jpg", 100, 150, true, true);

                        ImageView11 = new ImageView(image);
                        ImageView11.setFitWidth(100);
                        ImageView11.setFitHeight(150);
                        ImageView11.setPreserveRatio(true);

                        os.close();
                        is.close();
                    }

                    stmt.close();
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

    @FXML
    private void upDateQuantity(ActionEvent event) throws SQLException, IOException {

        try {

            /////////////////////////////////////////////
            if (md != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("Quantity.fxml"));
                anchorPane = loader.load();

                QuantityController quantityController = loader.getController();
                quantityController.setMedicationData(md);

                Scene scene = new Scene(anchorPane);

                stage.setScene(scene);
                stage.setTitle("Quantity Application");
                stage.show();
            } else {
           Alert alter = new Alert(Alert.AlertType.ERROR, "Please select the appropriate row first", ButtonType.OK);
           alter.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makePayment() throws IOException {

        //  String sqlSelect = "SELECT * FROM patient WHERE first_name=? AND last_name=?";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("Payment.fxml"));
        anchorPane = loader.load();
        PaymentController paymentController = loader.getController();
        paymentController.setPatientData(FirstName, LastName);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Payment Application");
        stage.show();
    }

    @FXML
    private void handleBrowser(ActionEvent event) {
        stage2 = (Stage) anchorPane2.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage2);
        /* try {
            deskTop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(), ImageView1.getFitWidth(), ImageView1.getFitHeight(), true, true);

            ImageView1.setImage(image);
            ImageView1.setPreserveRatio(true);
        }

    }

    @FXML
    private void checkInVisitor(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("Visitor.fxml"));
        anchorPane = loader.load();

        //UserController userController = loader.getController();
        //userController.setNurseData(nd);
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.setTitle("Visitor Application");
        stage.show();

    }

    private PieChart pieChart;

    @FXML
    private void financialReport(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(this.getClass().getResource("Visitor.fxml"));
        // anchorPane = loader.load();

        //UserController userController = loader.getController();
        //userController.setNurseData(nd);
        //Scene scene = new Scene(anchorPane);
        stage.setTitle("Financial Report");
        details.addAll(new PieChart.Data("Annual outstanding amount", 25),
                new PieChart.Data("Annual paid amount", 20),
                new PieChart.Data("Outstanding Addmission fee", 35),
                new PieChart.Data("Addmission Fee paid", 15),
                new PieChart.Data("Amount owed to patients", 5)
        );
        root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

        pieChart = new PieChart();
        pieChart.setData(details);
        pieChart.setTitle("Depicts patients overral fincancial status (in %) Incured in the current month");
        // pieChart.setLabelsVisible(true);
        // pieChart.setClockwise(false);

        root.setCenter(pieChart);
        label = new Label();
        label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
        pieChart.getData()
                .stream()
                .forEach(data -> {
                    data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                        label.setText(data.getName() + " Expenditure: " + (int) data.getPieValue()
                                + "%\n" + "Total Amount owed: " + ((data.getPieValue() / 100) * 360)
                                + "degrees");

                    });
                });
        root.setBottom(label);
        BorderPane.setMargin(label, new Insets(0, 0, 10, 120));

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadVisitorData(ActionEvent event) throws SQLException {

        try {
            Connection conn = dbConnection1.getConnection();
            this.data4 = FXCollections.observableArrayList();

            //rs gets the data and executes the query from the sql database
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM visitor");
            //checks if theres anything in the table and runs until the last row
            while (rs.next()) {
                this.data4.add(new VisitorData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

                //this.column_visitorNo.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("visitor_no"));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }

        //"this" referes to the global variable
        this.column_visitorNo.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("visitor_no"));
        this.column_visitorName.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("name"));
        this.column_visitorSurname.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("Surname"));
        this.column_visitorNum.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("contactNumber"));
        this.column_reason.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("reason"));
        this.column_checkInTime.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("checkIn"));
        this.column_checkOut.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("checkout"));
        this.column_checkinDate.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("dateVisit"));
        this.column_checkPatientName.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("PatientId"));
        this.visitorId.setCellValueFactory(new PropertyValueFactory<VisitorData, String>("visitor_no"));
        //this.conditioncolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("special_condition"));

        tableVisitor.setItems(null);
        tableVisitor.setItems(data4);
    }
    //private void showProductImage(String Id) throws FileNotFoundException{

    /*        Connection conn;
        try {
            conn = dbConnection1.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Select patient_pic from patient where Id=?");
            stmt.setString(1, Id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                
                int size = 0;
                while((size=is.read(contents)) != -1){
                    os.write(contents,0,size);
                }
                image = new Image("file.photo.jpg", ImageView11.getFitWidth(), ImageView11.getFitHeight(), true, true);
                ImageView11.setImage(image);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    @FXML
    private void CheckoutData(ActionEvent event) throws SQLException {

        String sqlCheckOut = "UPDATE visitor SET checkOutTime=? WHERE visitor_no=?";
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        String time = stf.format(date);
        try {
            Connection conn = dbConnection1.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sqlCheckOut);
            
            String VisitorID = VD.getVisitorNumber();
                    
            stmt.setString(1, time);
            stmt.setString(2, VisitorID);

            int i = stmt.executeUpdate();

            if (i == 1) {
                Alert alter = new Alert(Alert.AlertType.INFORMATION, "Visitor has been checked out of the hospice!", ButtonType.OK);
                alter.show();
            }

         
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void setCellValueFromTableVisitorToTextField() {
        tableVisitor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                VD = tableVisitor.getItems().get(tableVisitor.getSelectionModel().getSelectedIndex());

            }
        });

}
}
