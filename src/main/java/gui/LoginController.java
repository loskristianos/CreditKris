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
    LoginApplication loginApplication;

    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML protected void loginButtonAction() throws Exception {
        String username = userName.getText();
        String password = this.password.getText();
        int returnValue = loginApplication.loginAttempt(username,password);
        if (returnValue == -1) alert();
    }

    @FXML protected void newUserButtonAction() throws Exception {
        new NewCustomerApplication().start(currentStage);
    }
    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }

    public void setLoginApplication(LoginApplication loginApplication) {
        this.loginApplication = loginApplication;
    }

    void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"", ButtonType.OK);
        alert.setTitle("Unable to login");
        alert.setHeaderText("No matching login found.");
        alert.setContentText("Please check your details and try again, or create a new user account.");
        alert.show();

    }
}
