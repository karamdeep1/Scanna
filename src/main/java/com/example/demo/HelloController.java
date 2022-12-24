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
    private AnchorPane registerAccount;
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
    private TextField descriptionBarcode;
    private static File a = new File("firstBarcode.png");

    @FXML
    private Label barcodeWarningLabel;
    @FXML
    private Label pngWarningLabel;
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
    private Label RALabel;
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

    static int index = 0;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getDBConnection();
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> emp_id = new ArrayList<String>();
    ArrayList<String> SQ = new ArrayList<String>();
    ArrayList<String> SQA = new ArrayList<String>();
    ObservableList<BarcodeSearchModel> barcodeSearchModelObservableList = FXCollections.observableArrayList();

    userClass user1 = new userClass(true,"user","password");

    public void switchToForgotPassword(ActionEvent event) throws IOException
    {
        original.setLayoutY(1450);
        registerAccount.setLayoutY(1931);
        forgotPassword.setLayoutY(948);
        FPSQLabel.setVisible(false);
        FPSQAnswer.setVisible(false);
        FPNewPassword.setVisible(false);
        FPConfirmPassword.setVisible(false);
        FPSubmit.setVisible(false);
        FPUserLabel.setVisible(false);
    }
    public void switchToRegisterAccount(ActionEvent event) throws IOException
    {
        original.setLayoutY(1931);
        forgotPassword.setLayoutY(1450);
        registerAccount.setLayoutY(948);
    }
    public void goBack(ActionEvent event) throws IOException
    {
        original.setLayoutY(948);
        forgotPassword.setLayoutY(1450);
        registerAccount.setLayoutY(1931);
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

    public void registerAccount()
    {

    }
    public void forgotPassword(ActionEvent event) throws IOException, SQLException {
        String fpquery = "select Employee_ID from employee";
        pst = connectDB.prepareStatement(fpquery);
        rs = pst.executeQuery();
        boolean tf = false;
        FPSAIncorrectLabel.setVisible(false);
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
                break;
            }
        }
        if(tf == false)
        {
            FPSQLabel.setVisible(true);
            FPSQAnswer.setVisible(true);
            FPNewPassword.setVisible(true);
            FPConfirmPassword.setVisible(true);
            FPSubmit.setVisible(true);
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
    public void forgotPasswordFinalSubmit(ActionEvent event) throws IOException {
        
    }

    public void switchToScannerUI(ActionEvent event) throws IOException {
        if(username.getText().equals(user1.getUsername()) && password.getText().equals(user1.getPassword())) {
            wrongLogin.setText("Login Successful");
            Parent root = FXMLLoader.load(getClass().getResource("ScannerUI.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Scanna");
            stage.setScene(scene);
            stage.show();
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("You need to type in your username and password");
        }
        else {
            wrongLogin.setText("Wrong username or password");
        }
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
    }
    public void createButton(ActionEvent event) throws IOException {
        setAnchorPaneAway();
        setAnchorPane2Away();
        setAnchorPane4Away();
        anchorPane3.setLayoutX(0.0);
        anchorPane3.setLayoutY(0.0);
    }
    public void settingsButton(ActionEvent event) throws IOException {
        setAnchorPaneAway();
        setAnchorPane2Away();
        setAnchorPane3Away();
        anchorPane4.setLayoutX(0.0);
        anchorPane4.setLayoutY(0.0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resource){


        //SQL Query executed in backend database
        String barcodeViewQuery = "SELECT item_ID, employee_ID, clearance, type, Location, description FROM item_log_history;";

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
            pngWarningLabel.setText("Make sure you add '.png' at the end? (Do not include the ' ')");
        }
        else {
            barcodeID = String.valueOf((int) (Math.random() * MAX_VALUE));
            barcodeImage.setImage(convertToFxImage(generateCode128BarcodeImage(barcodeID)));
            barcodeWarningLabel.setText("Barcode Generated");
            ImageIO.write(generateCode128BarcodeImage(barcodeID), "png", new File(pngBarcode.getText()));
        }
    }
}