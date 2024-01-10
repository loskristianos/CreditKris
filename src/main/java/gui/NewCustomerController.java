package gui;

import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.LoginObject;


import java.util.HashMap;
import java.util.Map.*;


public class NewCustomerController {

    Stage currentStage;
    Scene previousScene;

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
        verifyInput(login,customerDetails);
        createCustomer(login, customerDetails);
    }

    @FXML void cancelButtonAction() throws Exception {
        new StartApplication().start(currentStage);
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

    void verifyInput(HashMap<String,String> login, HashMap<String,String> customerDetails){
        // check password matches confirmPassword
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            passwordAlert();
        }
        // check login and password aren't blank
        for (Entry<String,String> entry : login.entrySet()){
            if(entry.getValue()==null || entry.getValue().isBlank()){
                blankFieldAlert(entry.getKey());
            }
        }
        // check none of the customer detail fields are blank (except for address2 which is allowed to be blank)
        // needs redoing for multiple blank fields, it currently fires an alert for each one at the same time
        // (and then tries to write the object with the blank fields to the database anyway
        for (Entry<String,String> entry : customerDetails.entrySet()){
            if(entry.getValue()==null || entry.getValue().isBlank()){
                if (!entry.getKey().equals("address2"))
                    blankFieldAlert(entry.getKey());
            }
        }

    }

    void createCustomer(HashMap<String,String> loginDetails, HashMap<String,String> customerDetails) throws Exception {
        LoginObject loginObject = new LoginObject(loginDetails.get("username"), loginDetails.get("password"));
        LoginObject newLogin = loginObject.write();
        Customer customer = new Customer(customerDetails);
        customer.setCustomerID(newLogin.getCustomerID());
        customer.write();
        new CustomerApplication(customer).start(currentStage);

    }

}
