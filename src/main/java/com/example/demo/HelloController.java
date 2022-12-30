package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.linear.code128.Code128Barcode;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;

import static java.lang.Integer.MAX_VALUE;

public class HelloController implements Initializable{
    public HelloController() {

    }
    @FXML
    private Button Searchbtn;
    @FXML
    private Button Scanbtn;
    @FXML
    private Button Createbtn;
    @FXML
    private Button Settingsbtn;
    @FXML
    private Button LogOutButton;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private Label topBar;
    @FXML
    private ColorPicker ColorPicker;
    @FXML
    private Slider FontSlider;
    @FXML
    private Button loginButton;
    @FXML
    private Button CreateBarcode;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Button homeButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Label notAdminLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private TableView<BarcodeSearchModel> barcodeTableView;
    @FXML
    private TableColumn<BarcodeSearchModel, String> itemIDTableColumn;
    @FXML
    private TableColumn<BarcodeSearchModel, String> employeeIDTableColumn;
    @FXML
    private TableColumn<BarcodeSearchModel, Integer> clearanceTableColumn;
    @FXML
    private TableColumn<BarcodeSearchModel, String> descriptionTableColumn;
    @FXML
    private TableColumn<BarcodeSearchModel, String> locationTableColumn;
    @FXML
    private TableColumn<BarcodeSearchModel, String> typeTableColumn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private AnchorPane anchorPane3;
    @FXML
    private AnchorPane anchorPane4;
    @FXML
    private Button secondSearchButton;
    @FXML
    private AnchorPane registerAccountFinalPage;
    @FXML
    private AnchorPane registerAccountFirstPage;
    @FXML
    private AnchorPane forgotPassword;
    @FXML
    private AnchorPane original;
    @FXML
    private ImageView barcodeImage;
    private String barcodeID;
    private String employeeID;
    @FXML
    private TextField userBarcode;
    @FXML
    private TextField locationBarcode;
    @FXML
    private TextField typeBarcode;
    @FXML
    private TextField pngBarcode;
    @FXML
    private TextField clearanceBarcode;
    @FXML
    private TextField descriptionBarcode;
    private static File a = new File("firstBarcode.png");

    @FXML
    private Label barcodeWarningLabel;
    @FXML
    private Hyperlink forgotPass;
    @FXML
    private Hyperlink registerAcc;
    @FXML
    private Hyperlink back1;
    @FXML
    private Hyperlink back2;
    @FXML
    private Button FPSubmitUser;
    @FXML
    private TextField FPUserTextField;
    @FXML
    private Label FPUserLabel;
    @FXML
    private Label FPSQLabel;
    @FXML
    private TextField FPSQAnswer;
    @FXML
    private TextField FPNewPassword;
    @FXML
    private TextField FPConfirmPassword;
    @FXML
    private Button FPSubmit;
    @FXML
    private Button FPSASubmit;
    @FXML
    private Label FPSAIncorrectLabel;
    @FXML
    private Label FPPasswordIncorrectLabel;
    @FXML
    private Label RALabel;
    @FXML
    private Label RALabelFirstPage;
    @FXML
    private TextField RAUser;
    @FXML
    private TextField RAPassword;
    @FXML
    private TextField RASQ;
    @FXML
    private TextField RASQAnswer;
    @FXML
    private Label RAWarningLabel;
    @FXML
    private Button RACreateAccount;
    @FXML
    private CheckBox RACheckBox;
    @FXML
    private TextField RAFirstName;
    @FXML
    private TextField RALastName;
    @FXML
    private TextField RAEmail;
    @FXML
    private TextField RADateOfBirth;
    @FXML
    private TextField RASocialSecurityNumber;
    @FXML
    private Label RAFirstPageWarningLabel;
    @FXML
    private Button RAFirstPageNextPageButton;
    @FXML
    private Button RAFinalPagePreviousPageButton;
    @FXML
    private Label dateTimeLabelLabel;
    @FXML
    private Label scanBarcodeIDLabel;
    @FXML
    private Label scanEmployeeIDLabel;
    @FXML
    private Label scanClearanceLabel;
    @FXML
    private Label scanTypeLabel;
    @FXML
    private Label scanLocationLabel;
    @FXML
    private Label scanDescriptionLabel;
    @FXML
    private TextField scanBarcodeIDText;
    @FXML
    private TextField scanEmployeeIDText;
    @FXML
    private TextField scanClearanceText;
    @FXML
    private TextField scanTypeText;
    @FXML
    private TextField scanLocationText;
    @FXML
    private TextField scanDescriptionText;
    @FXML
    private Button scanBarcodeIDSubmitButton;
    @FXML
    private Button scanUpdate;
    @FXML
    private Button scanDelete;
    @FXML
    private CheckBox scanCheckBox;
    @FXML
    private Label scanWarningLabel;
    @FXML
    private Button scanChangeBarcodeID;
    @FXML
    private Label settingsUsername;
    @FXML
    private Label settingsClearance;
    @FXML
    private TableView<SettingsSearchModel> sAccountItems;
    @FXML
    private TableColumn<SettingsSearchModel, String> sBarcodeID;
    @FXML
    private TableColumn<SettingsSearchModel, Integer> sClearance;
    @FXML
    private TableColumn<SettingsSearchModel, String> sType;
    @FXML
    private TableColumn<SettingsSearchModel, String> sLocation;
    @FXML
    private TableColumn<SettingsSearchModel, String> sDescription;
    @FXML
    private TableView<settingsAdminSearchModel> saEmployeeInformation;
    @FXML
    private TableColumn<settingsAdminSearchModel, String> saEmployeeID;
    @FXML
    private TableColumn<settingsAdminSearchModel, Integer> saClearance;
    @FXML
    private TableColumn<settingsAdminSearchModel, String> saEmail;
    @FXML
    private TableColumn<settingsAdminSearchModel, String> saName;
    ArrayList<String> loginUsers = new ArrayList<String>();
    ArrayList<String> loginPasswords = new ArrayList<String>();
    ArrayList<String> scanItemID = new ArrayList<String>();
    static int index = 0;
    static int index2 = 0;
    static int index3 = 0;
    static String scanBarcodeID = "";
    static String settingsUser = "";
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getDBConnection();
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> emp_id = new ArrayList<String>();
    ArrayList<String> SQ = new ArrayList<String>();
    ArrayList<String> SQA = new ArrayList<String>();
    ArrayList<String> scanItem = new ArrayList<String>();
    ArrayList<String> scanEmp = new ArrayList<String>();
    ObservableList<BarcodeSearchModel> barcodeSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<SettingsSearchModel> settingsSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<settingsAdminSearchModel> settingsAdminSearchModelObservableList = FXCollections.observableArrayList();
    userClass user1 = new userClass(true,"user","password");
    public void switchToForgotPassword(ActionEvent event) throws IOException
    {
        original.setLayoutY(1864);
        registerAccountFinalPage.setLayoutY(2292);
        registerAccountFirstPage.setLayoutY(2797);
        forgotPassword.setLayoutY(1379);
        FPSQLabel.setVisible(false);
        FPSQAnswer.setVisible(false);
        FPNewPassword.setVisible(false);
        FPConfirmPassword.setVisible(false);
        FPSubmit.setVisible(false);
        FPUserLabel.setVisible(false);
        FPSAIncorrectLabel.setVisible(false);
        FPSASubmit.setVisible(false);
        FPPasswordIncorrectLabel.setVisible(false);
    }
    public void switchToRegisterAccountFirstPage(ActionEvent event) throws IOException
    {
        original.setLayoutY(2292);
        forgotPassword.setLayoutY(1864);
        registerAccountFirstPage.setLayoutY(1379);
        registerAccountFinalPage.setLayoutY(2797);
        RAFirstPageWarningLabel.setVisible(false);
    }
    public void goBack(ActionEvent event) throws IOException
    {
        original.setLayoutY(1379);
        forgotPassword.setLayoutY(1864);
        registerAccountFinalPage.setLayoutY(2292);
        registerAccountFirstPage.setLayoutY(2797);
    }
    public void switchToRegisterAccountFinalPage(ActionEvent event) throws IOException
    {
        if(RAFirstName.getText().equals("") || RALastName.getText().equals("") || RADateOfBirth.getText().equals("") || RASocialSecurityNumber.getText().equals("") || RAFirstName == null || RALastName == null || RADateOfBirth == null || RASocialSecurityNumber == null)
        {
            RAFirstPageWarningLabel.setVisible(true);
        }
        else
        {
            RAFirstPageWarningLabel.setVisible(false);
            original.setLayoutY(2292);
            forgotPassword.setLayoutY(1864);
            registerAccountFirstPage.setLayoutY(2797);
            registerAccountFinalPage.setLayoutY(1379);
        }
    }
    public void previousButton(ActionEvent event) throws IOException
    {
        registerAccountFirstPage.setLayoutY(1379);
        registerAccountFinalPage.setLayoutY(2797);
    }
    //Switching between the login page and the UI
    public void switchToLogIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Scanna Login");
        stage.setScene(scene);
        stage.show();
    }

    public void registerAccount() throws SQLException {
        if(RAUser.getText().equals("") || RAPassword.getText().equals("") || RASQ.getText().equals("") || RASQAnswer.getText().equals("") || RAUser == null || RAPassword == null || RASQ == null || RASQAnswer == null)
        {
            RAWarningLabel.setText("All fields must be completed");
        }
        else if(!(RACheckBox.isSelected()))
        {
            RAWarningLabel.setText("Make sure the checkbox is checked");
        }
        else
        {
            RAWarningLabel.setText("Make sure this is something you can remember");
            String raquery = "";
            if(!(RAEmail.getText().equals("")) || RAEmail != null)
            {
                raquery = "INSERT INTO employee VALUES(" +  "'" + RAUser.getText() + "'" +"," + "'" + RAFirstName.getText() + "'" + "," + "'" + RALastName.getText() + "'" + "," + "'" + RAEmail.getText() + "'" + "," + "'" + RAPassword.getText() + "'" + "," + "NULL" + "," + "'" + RADateOfBirth.getText() + "'" + "," + "'" + RASocialSecurityNumber.getText() + "'" + "," + "NULL" + ")";
            }
            else
            {
                raquery = "INSERT INTO employee VALUES(" +  "'" + RAUser.getText() + "'" +"," + "'" + RAFirstName.getText() + "'" + "," + "'" + RALastName.getText() + "'" + "," + "NULL" + "," + "'" + RAPassword.getText() + "'" + "," + "NULL" + "," + "'" + RADateOfBirth.getText() + "'" + "," + "'" + RASocialSecurityNumber.getText() + "'" + "," + "NULL" + ")";
            }
            pst = connectDB.prepareStatement(raquery);
            pst.execute(raquery);
            String rasqquery = "INSERT INTO security_questions VALUES(" + "'" + RAUser.getText() + "sq" + "'" + "," + "'" + RAUser.getText() + "'" + "," + "'" + RASQ.getText() + "'" + "," + "'" + RASQAnswer.getText() + "'" + ")";
            pst = connectDB.prepareStatement(rasqquery);
            pst.execute(rasqquery);
            RAUser.setText("");
            RAPassword.setText("");
            RASQ.setText("");
            RASQAnswer.setText("");
            RAFirstName.setText("");
            RALastName.setText("");
            RAEmail.setText("");
            RADateOfBirth.setText("");
            RASocialSecurityNumber.setText("");
            original.setLayoutY(2292);
            forgotPassword.setLayoutY(1864);
            registerAccountFirstPage.setLayoutY(1379);
            registerAccountFinalPage.setLayoutY(2797);
            RAFirstPageWarningLabel.setVisible(false);
        }
    }
    public void forgotPassword(ActionEvent event) throws IOException, SQLException {
        String fpquery = "select Employee_ID from employee";
        pst = connectDB.prepareStatement(fpquery);
        rs = pst.executeQuery();
        boolean tf = false;
        while(rs.next())
        {
            usernames.add(rs.getString("Employee_ID"));
        }
        for(int i = 0; i < usernames.size(); i++)
        {
            if(!(FPUserTextField.getText().equals(usernames.get(i))) || FPUserTextField == null)
            {
                FPUserLabel.setVisible(true);
                FPSQLabel.setText("");
                tf = true;
            }
            else
            {
                FPUserLabel.setVisible(false);
                tf = false;
                usernames.clear();
                break;
            }
        }
        if(tf == false)
        {
            FPSQLabel.setVisible(true);
            FPSQAnswer.setVisible(true);
            FPSASubmit.setVisible(true);
            String SQquery = "SELECT Emp_ID, SQ, SQ_Answer FROM security_questions";
            Statement s1 = connectDB.createStatement();
            ResultSet rs1 = s1.executeQuery(SQquery);
            while(rs1.next())
            {
                String empid = rs1.getString("Emp_ID");
                emp_id.add(empid);
                String sq = rs1.getString("SQ");
                SQ.add(sq);
                String sqa = rs1.getString("SQ_Answer");
                SQA.add(sqa);
            }

            for(int i = 0; i < emp_id.size(); i++) {
                if(emp_id.get(i).equals(FPUserTextField.getText()))
                {
                    index = i;
                    FPUserLabel.setVisible(false);
                    break;
                }
                else
                {
                    FPUserLabel.setVisible(true);
                }
            }
            FPSQLabel.setText(SQ.get(index));
            emp_id.clear();
            SQ.clear();
            SQA.clear();
        }
    }
    public void FPSASubmitButton(ActionEvent event) throws IOException {
        if(FPSQAnswer.getText() == null || !(FPSQAnswer.getText().equals(SQA.get(index))))
        {
            FPSAIncorrectLabel.setVisible(true);
        }
        else
        {
            FPSAIncorrectLabel.setVisible(false);
            FPNewPassword.setVisible(true);
            FPConfirmPassword.setVisible(true);
            FPSubmit.setVisible(true);
        }
    }
    public void forgotPasswordFinalSubmit(ActionEvent event) throws IOException, SQLException {
        if(!(FPNewPassword.getText().equals(FPConfirmPassword.getText())) || FPConfirmPassword == null || FPNewPassword == null) {
            FPPasswordIncorrectLabel.setText("Passwords dont match");
            FPPasswordIncorrectLabel.setVisible(true);
        }
        else if(FPNewPassword.getText().equals("") || FPConfirmPassword.getText().equals(""))
        {
            FPPasswordIncorrectLabel.setText("Password must contain characters");
            FPPasswordIncorrectLabel.setVisible(true);
        }
        else {
            FPPasswordIncorrectLabel.setVisible(false);
            String FPFSQuery = "UPDATE employee SET Password = " +  "'" + FPConfirmPassword.getText() + "'" + " where Employee_ID = " +  "'" + usernames.get(index) + "'";
            pst = connectDB.prepareStatement(FPFSQuery);
            pst.execute(FPFSQuery);
            FPUserTextField.setText("");
            FPSQLabel.setText("");
            FPSQAnswer.setText("");
            FPNewPassword.setText("");
            FPConfirmPassword.setText("");
            FPSQLabel.setVisible(false);
            FPSQAnswer.setVisible(false);
            FPSASubmit.setVisible(false);
            FPNewPassword.setVisible(false);
            FPConfirmPassword.setVisible(false);
        }
    }

    public void switchToScannerUI(ActionEvent event) throws IOException, SQLException {
        String userpass = "SELECT Employee_ID, Password FROM employee";
        Statement ls = connectDB.createStatement();
        rs = ls.executeQuery(userpass);
        while(rs.next())
        {
            String queryUser = rs.getString("Employee_ID");
            loginUsers.add(queryUser);
            String queryPass = rs.getString("Password");
            loginPasswords.add(queryPass);
        }
        for(int i = 0; i < loginUsers.size(); i++)
        {
            if(username.getText().equals(loginUsers.get(i)) && password.getText().equals(loginPasswords.get(i)))
            {
                settingsUser = loginUsers.get(i);
                index2 = i;
                wrongLogin.setText("Login Successful");
                Parent root = FXMLLoader.load(getClass().getResource("ScannerUI.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Scanna");
                stage.setScene(scene);
                stage.show();
                loginUsers.clear();
                loginPasswords.clear();
                break;
            }
            else if(username.getText().isEmpty() && password.getText().isEmpty())
            {
                wrongLogin.setText("You need to type in your username and password");
            }
            else
            {
                wrongLogin.setText("Wrong username or password");
            }
        }
    }
    public void setScanComponentsAway()
    {
        scanEmployeeIDLabel.setVisible(false);
        scanEmployeeIDText.setVisible(false);
        scanClearanceLabel.setVisible(false);
        scanClearanceText.setVisible(false);
        scanTypeLabel.setVisible(false);
        scanTypeText.setVisible(false);
        scanLocationLabel.setVisible(false);
        scanLocationText.setVisible(false);
        scanDescriptionLabel.setVisible(false);
        scanDescriptionText.setVisible(false);
        scanUpdate.setVisible(false);
        scanDelete.setVisible(false);
        scanCheckBox.setVisible(false);
        scanChangeBarcodeID.setVisible(false);
    }
    public void showScanComponents()
    {
        scanEmployeeIDLabel.setVisible(true);
        scanEmployeeIDText.setVisible(true);
        scanClearanceLabel.setVisible(true);
        scanClearanceText.setVisible(true);
        scanTypeLabel.setVisible(true);
        scanTypeText.setVisible(true);
        scanLocationLabel.setVisible(true);
        scanLocationText.setVisible(true);
        scanDescriptionLabel.setVisible(true);
        scanDescriptionText.setVisible(true);
        scanUpdate.setVisible(true);
        scanDelete.setVisible(true);
        scanCheckBox.setVisible(true);
        scanChangeBarcodeID.setVisible(true);
    }
    //Putting away anchor panes for later use
    public void setAnchorPaneAway()
    {
        searchBar.setVisible(false);
        barcodeTableView.setVisible(false);
    }
    public void setAnchorPane2Away()
    {
        anchorPane2.setLayoutX(-1.0);
        anchorPane2.setLayoutY(-768.0);
    }
    public void setAnchorPane3Away()
    {
        anchorPane3.setLayoutX(3.0);
        anchorPane3.setLayoutY(912.0);
    }
    public void setAnchorPane4Away()
    {
        anchorPane4.setLayoutX(-1.0);
        anchorPane4.setLayoutY(1375.0);
    }

    //Switches anchor pane using button
    public void searchButton(ActionEvent event) throws IOException {
        setAnchorPane2Away();
        setAnchorPane3Away();
        setAnchorPane4Away();
        searchBar.setVisible(true);
        barcodeTableView.setVisible(true);
    }
    public void scanButton(ActionEvent event) throws IOException {
        setAnchorPaneAway();
        setAnchorPane3Away();
        setAnchorPane4Away();
        anchorPane2.setLayoutX(0.0);
        anchorPane2.setLayoutY(0.0);
        scanEmployeeIDText.setText("");
        scanClearanceText.setText("");
        scanTypeText.setText("");
        scanLocationText.setText("");
        scanDescriptionText.setText("");
        scanCheckBox.setSelected(false);
        setScanComponentsAway();
    }
    public void createButton(ActionEvent event) throws IOException {
        setAnchorPaneAway();
        setAnchorPane2Away();
        setAnchorPane4Away();
        anchorPane3.setLayoutX(0.0);
        anchorPane3.setLayoutY(0.0);
        userBarcode.setText("");
        clearanceBarcode.setText("");
        typeBarcode.setText("");
        locationBarcode.setText("");
        descriptionBarcode.setText("");
        pngBarcode.setText("");
        barcodeImage.setImage(null);
    }
    public void settingsButton(ActionEvent event) throws IOException {
        setAnchorPaneAway();
        setAnchorPane2Away();
        setAnchorPane3Away();
        anchorPane4.setLayoutX(0.0);
        anchorPane4.setLayoutY(0.0);
    }
    public void searchList()
    {
        FilteredList<BarcodeSearchModel> filteredData = new FilteredList<>(barcodeSearchModelObservableList, b -> true);
        if(searchBar != null)
        {
            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(barcodeSearchModel -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if(barcodeSearchModel.getItem_ID().toLowerCase().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Item ID
                    }
                    else if(barcodeSearchModel.getEmployee_ID().toLowerCase().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Employee ID
                    }
                    else if(barcodeSearchModel.getClearance().toString().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Clearance
                    }
                    else if(barcodeSearchModel.getType().toLowerCase().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Type
                    }
                    else if(barcodeSearchModel.getLocation().toLowerCase().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Location
                    }
                    else if(barcodeSearchModel.getDescription().toLowerCase().indexOf(searchKeyword) > -1)
                    {
                        return true; //Means we found a match in Description
                    }
                    else
                    {
                        return false; //Means no match found
                    }
                });
            });
        }
        SortedList<BarcodeSearchModel> sortedData = new SortedList<>(filteredData);
        //Bind sorted result with Table View
        if(barcodeTableView != null)
        {
            sortedData.comparatorProperty().bind(barcodeTableView.comparatorProperty());

            barcodeTableView.setItems(sortedData);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resource){
        //SQL Query executed in backend database
        String barcodeViewQuery = "SELECT item_ID, employee_ID, clearance, type, Location, description FROM item_log_history;";
        String settingsAccountInfo = "SELECT item_ID, clearance, type, Location, description FROM item_log_history WHERE employee_ID = '" + settingsUser + "'";
        String settingsAdminEmpInfo = "SELECT Employee_ID, Clearance, Email, FirstName, LastName FROM employee";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(barcodeViewQuery);
            while(queryOutput.next())
            {
                String queryItemID = queryOutput.getString("item_ID");
                String queryEmployeeID = queryOutput.getString("employee_ID");
                Integer queryClearance = queryOutput.getInt("clearance");
                String queryType = queryOutput.getString("type");
                String queryLocation = queryOutput.getString("Location");
                String queryDescription = queryOutput.getString("description");

                //Populate ObservableList
                barcodeSearchModelObservableList.add(new BarcodeSearchModel(queryItemID, queryEmployeeID, queryClearance, queryType, queryLocation, queryDescription));
            }

            //Adding value to the table columns
            if(itemIDTableColumn != null)
            {
                itemIDTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, String>("item_ID"));
            }
            if(employeeIDTableColumn != null)
            {
                employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, String>("employee_ID"));
            }
            if(clearanceTableColumn != null)
            {
                clearanceTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, Integer>("clearance"));
            }
            if(typeTableColumn != null)
            {
                typeTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, String>("type"));
            }
            if(locationTableColumn != null)
            {
                locationTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, String>("location"));
            }
            if(descriptionTableColumn != null)
            {
                descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<BarcodeSearchModel, String>("description"));
            }
            if(barcodeTableView != null)
            {
                barcodeTableView.setItems(barcodeSearchModelObservableList);
            }

            //Intial filtered list
            searchList();
            ResultSet settingsEmpOutput = statement.executeQuery(settingsAccountInfo);
            while(settingsEmpOutput.next())
            {
                String seoBarcodeID = settingsEmpOutput.getString("item_ID");
                Integer seoClearance = settingsEmpOutput.getInt("clearance");
                String seoType = settingsEmpOutput.getString("type");
                String seoLocation = settingsEmpOutput.getString("Location");
                String seoDescription = settingsEmpOutput.getString("Description");
                settingsSearchModelObservableList.add(new SettingsSearchModel(seoBarcodeID, seoClearance, seoType, seoLocation, seoDescription));
            }

            if(sBarcodeID != null)
            {
                sBarcodeID.setCellValueFactory(new PropertyValueFactory<SettingsSearchModel, String>("settingsBarcodeID"));
            }
            if(sClearance != null)
            {
                sClearance.setCellValueFactory(new PropertyValueFactory<SettingsSearchModel, Integer>("settingsClearance"));
            }
            if(sType != null)
            {
                sType.setCellValueFactory(new PropertyValueFactory<SettingsSearchModel, String>("settingsType"));
            }
            if(sLocation != null)
            {
                sLocation.setCellValueFactory(new PropertyValueFactory<SettingsSearchModel, String>("settingsLocation"));
            }
            if(sDescription != null)
            {
                sDescription.setCellValueFactory(new PropertyValueFactory<SettingsSearchModel, String>("settingsDescription"));
            }
            if(sAccountItems != null)
            {
                sAccountItems.setItems(settingsSearchModelObservableList);
            }

            ResultSet settingsAdminEmpOutput = statement.executeQuery(settingsAdminEmpInfo);
            while(settingsAdminEmpOutput.next())
            {
                String saeoEmployeeID = settingsAdminEmpOutput.getString("Employee_ID");
                Integer saeoClearance = settingsAdminEmpOutput.getInt("Clearance");
                String saeoEmail = settingsAdminEmpOutput.getString("Email");
                String saeoName = settingsAdminEmpOutput.getString("FirstName") + " " + settingsAdminEmpOutput.getString("LastName");
                settingsAdminSearchModelObservableList.add(new settingsAdminSearchModel(saeoEmployeeID, saeoClearance, saeoEmail, saeoName));
            }
            if(saEmployeeID != null)
            {
                saEmployeeID.setCellValueFactory(new PropertyValueFactory<settingsAdminSearchModel, String>("settingsAdminEmployeeID"));
            }
            if(saClearance != null)
            {
                saClearance.setCellValueFactory(new PropertyValueFactory<settingsAdminSearchModel, Integer>("settingsAdminClearance"));
            }
            if(saEmail != null)
            {
                saEmail.setCellValueFactory(new PropertyValueFactory<settingsAdminSearchModel, String>("settingsAdminEmail"));
            }
            if(saName != null)
            {
                saName.setCellValueFactory(new PropertyValueFactory<settingsAdminSearchModel, String>("settingsAdminName"));
            }
            if(saEmployeeInformation != null)
            {
                saEmployeeInformation.setItems(settingsAdminSearchModelObservableList);
            }
        }
        catch(SQLException e){
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    //Generating barcodes using barbecue
    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws IOException, BarcodeException, OutputException {
        Code128Barcode barcode = (Code128Barcode) BarcodeFactory.createCode128(barcodeText);
        //BarcodeImageHandler.savePNG(barcode,a);
        return BarcodeImageHandler.getImage(barcode);
    }

    //converts buffered image to javafx imageview
    private static javafx.scene.image.Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
    //sets the imageview in the create anchorpane to the barcode
    public void generateBarcode(ActionEvent event) throws IOException, OutputException, BarcodeException {
        if(userBarcode.getText().equals("") || locationBarcode.getText().equals("") || typeBarcode.getText().equals("") || pngBarcode.getText().equals("") || !(pngBarcode.getText().contains(".png")))
        {
            barcodeWarningLabel.setText("You need to fill in all areas");
        }
        else if(!(pngBarcode.getText().contains(".png")))
        {
            barcodeWarningLabel.setText("Make sure you add '.png' at the end. DO not include ' '");
        }
        else if(clearanceBarcode.getText().equals("0") || clearanceBarcode.getText().equals("1"))
        {
            barcodeID = String.valueOf((int) (Math.random() * MAX_VALUE));
            barcodeImage.setImage(convertToFxImage(generateCode128BarcodeImage(barcodeID)));
            barcodeWarningLabel.setText("Barcode Generated");
            ImageIO.write(generateCode128BarcodeImage(barcodeID), "png", new File(pngBarcode.getText()));
            String createBarcodeID = barcodeID;
            String createEmployeeID = userBarcode.getText();
            int createClearance = Integer.parseInt(clearanceBarcode.getText());
            String createType = typeBarcode.getText();
            String createLocation = locationBarcode.getText();
            String createDescription = descriptionBarcode.getText();
            String insertQuery = "INSERT INTO item_log_history(item_ID, employee_ID, clearance, type, location, description) VALUES (?,?,?,?,?,?)";
            try
            {
                pst = connectDB.prepareStatement(insertQuery);
                pst.setString(1, createBarcodeID);
                pst.setString(2, createEmployeeID);
                pst.setInt( 3, createClearance);
                pst.setString(4, createType);
                pst.setString(5, createLocation);
                pst.setString(6, createDescription);
                pst.execute();

                String queryCreateBarcodeID = createBarcodeID;
                String queryCreateEmployeeID = createEmployeeID;
                int queryCreateClearance = createClearance;
                String queryCreateType = createType;
                String queryCreateLocation = createLocation;
                String queryCreateDescription = createDescription;
                barcodeSearchModelObservableList.add(new BarcodeSearchModel(queryCreateBarcodeID, queryCreateEmployeeID, queryCreateClearance, queryCreateType, queryCreateLocation, queryCreateDescription));
                barcodeTableView.setItems(barcodeSearchModelObservableList);
                searchList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            barcodeWarningLabel.setText("Clearance must be either 0 or 1");
        }
    }
    public void scanSubmit(ActionEvent event) throws IOException, SQLException {
        String itemQuery = "SELECT item_ID FROM item_log_history";
        pst = connectDB.prepareStatement(itemQuery);
        rs = pst.executeQuery();
        while (rs.next())
        {
            String scanItemOutput = rs.getString("item_ID");
            scanItemID.add(scanItemOutput);
        }
        if(scanBarcodeIDText.getText().equals("") || scanBarcodeIDText == null)
        {
            scanWarningLabel.setText("You must type in the barcode ID");
        }
        else if(!(scanItemID.contains(scanBarcodeIDText.getText())))
        {
            scanWarningLabel.setText("Barcode ID not found");
        }
        else
        {
            scanBarcodeID = scanBarcodeIDText.getText();
            showScanComponents();
            scanItemID.clear();
        }
    }
    public void changeBarcodeID(ActionEvent event) throws IOException
    {
        scanEmployeeIDText.setText("");
        scanClearanceText.setText("");
        scanTypeText.setText("");
        scanLocationText.setText("");
        scanDescriptionText.setText("");
        scanCheckBox.setSelected(false);
        setScanComponentsAway();
    }
    public void delete(ActionEvent event) throws IOException, SQLException
    {
         if(scanEmployeeIDText.getText().equals("") || scanClearanceText.getText().equals("") || scanTypeText.getText().equals("") || scanLocationText.getText().equals("") || scanDescriptionText.getText().equals("") || scanEmployeeIDText == null || scanClearanceText == null || scanTypeText == null || scanLocationText == null || scanDescriptionText == null)
         {
             scanWarningLabel.setText("All fields must be filled");
         }
         else if(scanCheckBox.isSelected())
         {
             String selectDeleteQuery = "SELECT item_ID, employee_ID FROM item_log_history";
             pst = connectDB.prepareStatement(selectDeleteQuery);
             rs = pst.executeQuery();
             while(rs.next())
             {
                 String scanItemQuery = rs.getString("item_ID");
                 scanItem.add(scanItemQuery);
                 String scanEmpQuery = rs.getString("employee_ID");
                 scanEmp.add(scanEmpQuery);
             }
             for(int i = 0; i < scanItem.size(); i++)
             {
                 if(scanBarcodeIDText.getText().equals(scanItem.get(i)) && scanEmployeeIDText.getText().equals(scanEmp.get(i)))
                 {
                     index3 = i;
                     String deleteQuery = "DELETE FROM item_log_history WHERE item_ID = '" + scanItem.get(index3) + "'" + " AND " + "employee_ID = '" + scanEmp.get(index3) + "'";
                     pst = connectDB.prepareStatement(deleteQuery);
                     pst.execute(deleteQuery);
                     barcodeSearchModelObservableList.clear();
                     String barcodeViewQuery2 = "SELECT item_ID, employee_ID, clearance, type, Location, description FROM item_log_history;";
                     Statement s = connectDB.createStatement();
                     rs = s.executeQuery(barcodeViewQuery2);
                     while(rs.next())
                     {
                         String queryItemID2 = rs.getString("item_ID");
                         String queryEmployeeID2 = rs.getString("employee_ID");
                         Integer queryClearance2 = rs.getInt("clearance");
                         String queryType2 = rs.getString("type");
                         String queryLocation2 = rs.getString("Location");
                         String queryDescription2 = rs.getString("description");

                         //Populate ObservableList
                         barcodeSearchModelObservableList.add(new BarcodeSearchModel(queryItemID2, queryEmployeeID2, queryClearance2, queryType2, queryLocation2, queryDescription2));
                     }
                     barcodeTableView.setItems(barcodeSearchModelObservableList);

                     searchList();
                     scanWarningLabel.setText("Item deleted");
                     scanItem.clear();
                     scanEmp.clear();
                     break;
                 }
                 else
                 {
                     scanWarningLabel.setText("Wrong Employee ID or Barcode ID");
                 }
             }
         }
         else
         {
             scanWarningLabel.setText("check the checkbox to confirm delete");
         }
    }

    public void update(ActionEvent event) throws IOException, SQLException
    {
        if(scanEmployeeIDText.getText().equals("") || scanClearanceText.getText().equals("") || scanTypeText.getText().equals("") || scanLocationText.getText().equals("") || scanDescriptionText.getText().equals("") || scanEmployeeIDText == null || scanClearanceText == null || scanTypeText == null || scanLocationText == null || scanDescriptionText == null)
        {
            scanWarningLabel.setText("All fields must be filled");
        }
        else
        {
            String selectUpdateQuery = "SELECT item_ID, employee_ID, clearance, type, Location, description FROM item_log_history";
            pst = connectDB.prepareStatement(selectUpdateQuery);
            rs = pst.executeQuery();
            while(rs.next())
            {
                String scanItemQuery = rs.getString("item_ID");
                scanItem.add(scanItemQuery);
            }
            for(int i = 0; i < scanItem.size(); i++)
            {
                if (scanBarcodeIDText.getText().equals(scanItem.get(i))) {
                    String updateQuery = "UPDATE item_log_history SET employee_ID = '" + scanEmployeeIDText.getText() + "'" + "," + "clearance = " + scanClearanceText.getText() + "," + "type = '" + scanTypeText.getText() + "'" + "," + "Location = '" + scanLocationText.getText() + "'" + "," + "description = '" + scanDescriptionText.getText() + "'" + "WHERE item_ID = '" + scanItem.get(i) + "'";
                    pst = connectDB.prepareStatement(updateQuery);
                    pst.execute(updateQuery);
                    barcodeSearchModelObservableList.clear();
                    String barcodeViewQuery2 = "SELECT item_ID, employee_ID, clearance, type, Location, description FROM item_log_history;";
                    Statement s = connectDB.createStatement();
                    rs = s.executeQuery(barcodeViewQuery2);
                    while(rs.next())
                    {
                        String queryItemID3 = rs.getString("item_ID");
                        String queryEmployeeID3 = rs.getString("employee_ID");
                        Integer queryClearance3 = rs.getInt("clearance");
                        String queryType3 = rs.getString("type");
                        String queryLocation3 = rs.getString("Location");
                        String queryDescription3 = rs.getString("description");

                        //Populate ObservableList
                        barcodeSearchModelObservableList.add(new BarcodeSearchModel(queryItemID3, queryEmployeeID3, queryClearance3, queryType3, queryLocation3, queryDescription3));
                    }
                    barcodeTableView.setItems(barcodeSearchModelObservableList);

                    searchList();
                    scanWarningLabel.setText("Item updated");
                    scanItem.clear();
                    break;
                }
            }
        }
    }
}