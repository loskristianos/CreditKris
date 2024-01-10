package gui;

import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginObject;

public class LoginController {

    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML protected void loginButtonAction(){
        String username = userName.getText();
        String password = this.password.getText();
        loginAttempt(username,password);
    }

    @FXML protected void newUserButtonAction(){
        new NewCustomerController();
    }

    void loginAttempt(String username, String password){
        LoginObject loginObject = new LoginObject(username, password);
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
           new CustomerController(returnedCustomer);
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
