package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



import java.util.HashMap;
import java.util.Map.*;


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
        int x = newCustomerApplication.verifyInputFields(login,customerDetails);
        if (x==-1) {}; // highlight all fields where getText().isEmpty() (except address2)
        int y = newCustomerApplication.verifyPasswordMatch(passwordField.getText(),confirmPasswordField.getText());
        if (y==-1) {passwordAlert();}; // highlight password fields here also maybe
        int returnValue = newCustomerApplication.createCustomer(login, customerDetails);
    }

    @FXML void cancelButtonAction() throws Exception {
        new LoginApplication().start(currentStage);
    }

    void passwordAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING,null, ButtonType.OK);
        alert.setTitle("Passwords do not match");
        alert.setHeaderText("The passwords entered do not match.");
        alert.setContentText("Please check and re-enter your password.");
        alert.show();
    }

    void blankFieldAlert(String field){
        Alert alert = new Alert(Alert.AlertType.WARNING,null, ButtonType.OK);
        alert.setTitle("Field "+field+" is blank");
        alert.setHeaderText("Required field "+field+" cannot be blank.");
        alert.setContentText("Please complete all the required fields to submit your request.");
        alert.showAndWait();
    }
}