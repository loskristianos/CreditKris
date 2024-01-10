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

public class LoginController {

    Stage currentStage;
    Scene currentScene;

    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML protected void loginButtonAction() throws Exception {
        String username = userName.getText();
        String password = this.password.getText();
        loginAttempt(username,password);
    }

    @FXML protected void newUserButtonAction() throws Exception {
        new NewCustomerApplication().start(currentStage);
    }
    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }
    void loginAttempt(String username, String password) throws Exception {
        LoginObject loginObject = new LoginObject(username, password);
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
           new CustomerApplication(returnedCustomer).start(currentStage);
        }
        else {
            alert();
        }
    }

    void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"", ButtonType.OK);
        alert.setTitle("Unable to login");
        alert.setHeaderText("No matching login found.");
        alert.setContentText("Please check your details and try again, or create a new user account.");
        alert.show();

    }
}
