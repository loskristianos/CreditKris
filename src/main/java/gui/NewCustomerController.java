package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class NewCustomerController {

    Stage currentStage;
    Scene previousScene;
    NewCustomerApplication newCustomerApplication;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField dobField;
    @FXML private TextField address1Field;
    @FXML private TextField address2Field;
    @FXML private TextField addressTownField;
    @FXML private TextField addressPostcodeField;

    public NewCustomerController(){
    }
    @FXML private void initialize(){
        // set formatting on DoB text field
        SimpleDateFormat dobFormat = new SimpleDateFormat("dd/MM/yyyy");
        dobField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(dobFormat)));
    }
    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    public void setNewCustomerApplication(NewCustomerApplication newCustomerApplication){
        this.newCustomerApplication = newCustomerApplication;
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    @FXML void submitButtonAction() throws Exception {
        HashMap<String,String> login = new HashMap<>() {{
            put("username",usernameField.getText()); put("password",passwordField.getText());
        }};
        HashMap<String,String> customerDetails = new HashMap<>() {{
            put("firstName",firstNameField.getText()); put("lastName",lastNameField.getText()); put("dob", dobField.getText());
            put("address1",address1Field.getText()); put("address2",address2Field.getText()); put("addressTown", addressTownField.getText());
            put("addressPostcode", addressPostcodeField.getText());
        }};
        TextField[] inputFields = new TextField[]{usernameField,passwordField,confirmPasswordField,firstNameField,lastNameField,dobField,address1Field,address2Field,addressTownField,addressPostcodeField};
        int x = newCustomerApplication.verifyInputFields(login,customerDetails);
        if (x==-1) {
            for (int i=0; i< inputFields.length; i++){
                if (inputFields[i].getText().isBlank()){
                    inputFields[i].setStyle("-fx-border-color: firebrick; -fx-background-color: tomato");
                }
            }
            blankFieldAlert();
        } // highlight all fields where getText().isEmpty() (except address2)
        int y = newCustomerApplication.verifyPasswordMatch(passwordField.getText(),confirmPasswordField.getText());
        if (y==-1) {
            confirmPasswordField.setStyle("-fx-border-color: firebrick; -fx-background-color: tomato" );
            passwordAlert();
        }
        if (x==0 && y==0) {
            int returnValue = newCustomerApplication.createCustomer(login, customerDetails);
        }
    }

    @FXML void cancelButtonAction() throws Exception {
        new LoginApplication().start(currentStage);
    }

    void passwordAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING,null, ButtonType.OK);
        alert.setTitle("Passwords do not match");
        alert.setHeaderText("The passwords entered do not match.");
        alert.setContentText("Please check and re-enter your password.");
        alert.showAndWait();
    }

    void blankFieldAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING,null, ButtonType.OK);
        alert.setTitle("Required field is blank");
        alert.setHeaderText("Please complete all the required fields to submit your request.");
        alert.showAndWait();
    }
}